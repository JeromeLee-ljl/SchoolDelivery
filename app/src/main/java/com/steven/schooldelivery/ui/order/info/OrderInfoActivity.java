package com.steven.schooldelivery.ui.order.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderInfoActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mTitle;
    private List<Fragment> mFragmentList;

    // private String  mOrderId;
    private Order mOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        init();
    }

    private void init() {
        mOrder = (Order) getIntent().getSerializableExtra("order");

        setToolBar("订单信息");
        initFragment();
        initViewPager();
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new OrderDetailFragment(mOrder));
        mFragmentList.add(new OrderStateFragment(mOrder.getOrderId()));//// TODO: 2017/5/17
    }

    private void initViewPager() {
        mTitle = new String[]{"详细信息","进度"};
        //tab, mViewPager 初始化
        mTabLayout = (TabLayout) findViewById(R.id.order_info_tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

    public static void actionStart(Context context, Order order){
        Intent intent = new Intent(context,OrderInfoActivity.class);
        intent.putExtra("order", order);
        context.startActivity(intent);
    }
}
