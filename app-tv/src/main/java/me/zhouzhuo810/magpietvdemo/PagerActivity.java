package me.zhouzhuo810.magpietvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.zhouzhuo810.magpietv.act.BaseActivity;
import me.zhouzhuo810.magpietv.widget.Indicator;
import me.zhouzhuo810.magpietv.widget.adapter.BaseFragmentPagerAdapter;
import me.zhouzhuo810.magpietvdemo.fgm.TestFragmentOne;


public class PagerActivity extends BaseActivity {

    private Indicator indicator;
    private ViewPager viewPager;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pager;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        indicator = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("军事");
        titles.add("生活");
        titles.add("爱情");
        titles.add("汽车");
        titles.add("美女");
        titles.add("小说");
        titles.add("更多更长");
        titles.add("更多更长更长");
        titles.add("更多更长更长更长");
        titles.add("更多更长更长更长更长");
        final List<Fragment> fgms = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fgms.add(TestFragmentOne.newInstance(TestFragmentOne.class, null));
        }
        viewPager.setOffscreenPageLimit(titles.size());
        viewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(), titles) {
            @Override
            public int getSelectedIcon(int position) {
                return R.mipmap.ic_launcher;
            }

            @Override
            public int getUnselectedIcon(int position) {
                return R.mipmap.ic_launcher_round;
            }

            @Override
            protected Fragment getFragment(int position) {
                return fgms.get(position);
            }
        });
        indicator.setViewPager(viewPager);
    }

    @Override
    public void initEvent() {

    }
}
