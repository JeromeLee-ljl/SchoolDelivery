package com.steven.schooldelivery.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.steven.schooldelivery.R;
import com.steven.schooldelivery.base.BaseActivity;
import com.steven.schooldelivery.db.User;
import com.steven.schooldelivery.http.gson.HttpResponse;
import com.steven.schooldelivery.ui.acceptOrder.AcceptOrderFragment;
import com.steven.schooldelivery.ui.message.MessagesFragment;
import com.steven.schooldelivery.ui.order.NewOrderActivity;
import com.steven.schooldelivery.ui.order.OrderFragment;
import com.steven.schooldelivery.ui.setting.SettingActivity;
import com.steven.schooldelivery.ui.userinfo.UserInfoActivity;

import static org.litepal.LitePalApplication.getContext;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton mFloatingActionButton;
    private Fragment mOrderFragment, mAcceptOrderFragment, mMessageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initActionbar();
        initDrawer();
        initFloatingActionButton();
        initFragments();
    }

    private void initActionbar() {
        setToolBar("订单");
        //todo 透明
    }

    private void initFragments() {
        // TODO: 2017/5/5  Fragment初始化

        mOrderFragment = new OrderFragment();
        mAcceptOrderFragment = new AcceptOrderFragment();
        mMessageFragment = new MessagesFragment();
        //加载订单fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mOrderFragment);
        fragmentTransaction.commit();
    }

    private void initFloatingActionButton() {
        //FloatingBtn 初始化
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.new_order_fab);
        mFloatingActionButton.setOnClickListener(v ->
                new AsyncTask<Void, Void, HttpResponse>() {
                    @Override
                    protected HttpResponse doInBackground(Void... params) {
                        //检查用户是否能够创建订单
                        //// TODO: 2017/5/5  检查用户是否能够创建订单
                        // String response = HttpUtil.doPostWithToken(HttpUrl.ORDER_CREATE_CHECK, new HashMap<>());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(HttpResponse httpResponse) {
                        if (httpResponse == null) {
                            NewOrderActivity.actionStart(getContext());
                            return;
                        }
                        if (httpResponse.getStatus() == 200) {
                            NewOrderActivity.actionStart(getContext());
                        } else {
                            Toast.makeText(getContext(), "无法创建订单", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute());
    }

    private void initDrawer() {
        //初始化滑动菜单
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        //设置化滑动菜单顶部 用户信息
        View headView = navigationView.getHeaderView(0);
        TextView name = (TextView) headView.findViewById(R.id.name_textView);
        TextView phone = (TextView) headView.findViewById(R.id.phone_textView);
        User user = User.getCurrentUser(this);
        if (user != null) {
            name.setText(user.getName());
            phone.setText(user.getPhone());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //ActionBarDrawerToggle是DrawerLayout事件的监听器
        //改变android.R.id.home返回图标。
        //Drawer拉出、隐藏，带有android.R.id.home动画效果。
        //监听Drawer拉出、隐藏；
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.setDrawerListener(toggle);
    }

    // private void initActionbar() {
    //     //初始化导航栏
    //     toolbar = (Toolbar) findViewById(R.id.toolbar);
    //     setSupportActionBar(toolbar);
    //     mActionBar = getSupportActionBar();
    //     if (mActionBar != null) {
    //         mActionBar.setTitle("订单");
    //     }
    // }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO: 2017/5/5 menu
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: 2017/5/3 滑动菜单按钮点击事件
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            actionBar.setTitle("我的订单");
            replaceFragment(mOrderFragment);
            showFab();
            // setActionBarAutoHide(true);

        } else if (id == R.id.nav_accept_order) {
            actionBar.setTitle("我要接单");
            replaceFragment(mAcceptOrderFragment);
            hidenFab();
            // setActionBarAutoHide(false);

        } else if (id == R.id.nav_msg) {
            actionBar.setTitle("我的消息");
            replaceFragment(mMessageFragment);
            hidenFab();
            // setActionBarAutoHide(true);

        } else if (id == R.id.nav_user_info) {
            UserInfoActivity.actionStart(this);
            // overridePendingTransition(R.anim.enter_from_left,R.anim.exit_to_right);
            // overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);
            // mActionBar.setTitle("个人信息");
        } else if (id == R.id.nav_setting) {
            SettingActivity.actionStart(this);
            // mActionBar.setTitle("设置");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * 交换fragment显示
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 显示floatingActionButton
     */
    private void showFab() {
        mFloatingActionButton.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏floatingActionButton
     */
    private void hidenFab() {
        mFloatingActionButton.setVisibility(View.GONE);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}
