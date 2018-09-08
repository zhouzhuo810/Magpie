package me.zhouzhuo810.magpiedemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.fgm.BaseFragment;
import me.zhouzhuo810.magpie.ui.widget.TabBar;
import me.zhouzhuo810.magpiedemo.fgm.TestFragment;

public class TabActivity extends BaseActivity {

    TabBar tabBar;
    private TestFragment fgm1;
    private TestFragment fgm2;
    private TestFragment fgm3;
    private TestFragment fgm4;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tabBar = findViewById(R.id.tab_bar);
    }

/*    private void changeToPosition(int position, Bundle bundle) {
        switch (position) {
            case 0:
                replaceFragment(R.id.fgm_container, TestFragment.class, fgm1, bundle);
                break;
            case 1:
                replaceFragment(R.id.fgm_container, TestFragment.class, fgm2, bundle);
                break;
            case 2:
                replaceFragment(R.id.fgm_container, TestFragment.class, fgm3, bundle);
                break;
            case 3:
                replaceFragment(R.id.fgm_container, TestFragment.class, fgm4, bundle);
                break;

        }
    }*/

    private void changeToPositionBetter(int position, Bundle bundle) {
        switch (position) {
            case 0:
                addOrShowFragment(R.id.fgm_container, TestFragment.class, fgm1, bundle);
                break;
            case 1:
                addOrShowFragment(R.id.fgm_container, TestFragment.class, fgm2, bundle);
                break;
            case 2:
                addOrShowFragment(R.id.fgm_container, TestFragment.class, fgm3, bundle);
                break;
            case 3:
                addOrShowFragment(R.id.fgm_container, TestFragment.class, fgm4, bundle);
                break;

        }
    }

    @Override
    public void initData() {
        tabBar.setNormalIconRes(
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round
        );
        tabBar.setPressIconRes(
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        );
    }

    @Override
    public void initEvent() {
        tabBar.setOnTabBarClickListener(new TabBar.OnTabBarClick() {
            @Override
            public void onTabClick(ImageView iv, TextView tv, int position, boolean changed) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", position);
                changeToPositionBetter(position, bundle);
            }
        });

        //这个要在设置setOnTabBarClickListener监听之后调用才会触发onTabClick方法
        tabBar.setSelection(0);
    }
}
