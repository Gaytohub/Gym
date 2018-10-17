package com.example.tiamo.gym;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class FitnessActivity extends AppCompatActivity {
    private BottomNavigationView bottomNaviga;
    private ViewPager viewPager;
    List<Fragment> listFragment;//存储页面对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);
        initView();
    }



        private void initView(){
        viewPager = (ViewPager) findViewById(R.id.vp);
        bottomNaviga = (BottomNavigationView) findViewById(R.id.bottomNavigationView);

        //向ViewPager添加各页面
        listFragment = new ArrayList<>();
        listFragment.add(new Page3());
        listFragment.add(new Page1());
        listFragment.add(new Page1());
        listFragment.add(new PersonalCenter());
        //listFragment.add(new Fragment2());
        //listFragment.add(new Fragment3());
        //listFragment.add(new Fragment4());

        MyFragAdaptre myFragAdaptre = new MyFragAdaptre(getSupportFragmentManager(), this, listFragment);
        viewPager.setAdapter(myFragAdaptre);

        //导航栏点击事件和ViewPager滑动事件,让两个控件相互关联
        bottomNaviga.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //这里设置为：当点击到某子项，ViewPager就滑动到对应位置
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_person:
                        viewPager.setCurrentItem(3);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //注意这个方法滑动时会调用多次，下面是参数解释：
                //position当前所处页面索引,滑动调用的最后一次绝对是滑动停止所在页面
                //positionOffset:表示从位置的页面偏移的[0,1]的值。
                //positionOffsetPixels:以像素为单位的值，表示与位置的偏移
            }

            @Override
            public void onPageSelected(int position) {
                //该方法只在滑动停止时调用，position滑动停止所在页面位置
                //当滑动到某一位置，导航栏对应位置被按下
                bottomNaviga.getMenu().getItem(position).setChecked(true);
                //这里使用navigation.setSelectedItemId(position);无效，
                //setSelectedItemId(position)的官网原句：Set the selected
                // menu item ID. This behaves the same as tapping on an item
                //未找到原因
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //              这个方法在滑动是调用三次，分别对应下面三种状态
                //              这个方法对于发现用户何时开始拖动，
                //              何时寻呼机自动调整到当前页面，或何时完全停止/空闲非常有用。
                //              state表示新的滑动状态，有三个值：
                //              SCROLL_STATE_IDLE：开始滑动（空闲状态->滑动），实际值为0
                //              SCROLL_STATE_DRAGGING：正在被拖动，实际值为1
                //              SCROLL_STATE_SETTLING：拖动结束,实际值为2
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent sEIntent = new Intent(FitnessActivity.this, MainActivity.class);
            startActivity(sEIntent);
            finish();

        }
        return true;
    }
}
