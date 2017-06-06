package com.steven.schooldelivery.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by 22340 on 2017/5/3.
 */

public class OrderFragment extends Fragment {
    private static final String TAG = "OrderFragment";

    private View mRootView;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    //todo 分类
    private OrderListFragment mAllFragment, mUndoneFragment;

    private String[] mTitle = {"未完成", "已完成", "所有"};
    private ArrayList<OrderListFragment> mFragments;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
        mRootView = inflater.inflate(R.layout.fragment_order, container, false);
        initView();
        }
        return mRootView;
    }

    private void initView() {
        //fragment 初始化
        mFragments = new ArrayList<>();
        mUndoneFragment = new OrderListFragment(OrderListFragment.TYPE.UNFINISHED);
        // mUndoneFragment.setContentType(OrderListFragment.TYPE.UNFINISHED);
        mAllFragment = new OrderListFragment(OrderListFragment.TYPE.ALL);
        // mAllFragment.setContentType(OrderListFragment.TYPE.ALL);
        mFragments.add(mUndoneFragment);
        mFragments.add(new OrderListFragment(OrderListFragment.TYPE.FINISHED));
        mFragments.add(mAllFragment);

        //tab, viewPager 初始化
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.order_tab_layout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);

        FragmentPagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
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
            LogUtil.d(TAG, "getItem: " + position);
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
