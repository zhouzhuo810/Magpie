package me.zhouzhuo810.magpie.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.zhouzhuo810.magpie.utils.DisplayUtil;

public abstract class BaseMVPActivity extends AppCompatActivity implements IBaseMVPActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        DisplayUtil.getInstance().loadView(getDecorView());

        initView(savedInstanceState);
        initData();
        initEvent();
    }

    @Override
    public View getView(int id) {
        return findViewById(id);
    }

    @Override
    public void startActWithIntent() {

    }

    @Override
    public void startActWithIntentForResult() {

    }

    @Override
    public void closeAct() {

    }

    @Override
    public void closeAct(boolean defaultBack) {

    }

    @Override
    public void closeAllAct() {

    }

    @Override
    public int outAnim() {
        return 0;
    }

    @Override
    public int inAnim() {
        return 0;
    }

    public void showProgressDialog() {

    }

    public void hideProgressDialog();

    public void showUpdateDialog();

    public void hideUpdateDialog();

    public void showTwoBtnDialog();

    public void hideTwoBtnDialog();

    public void showEditDialog();

    public void hideEditDialog();

    public void refreshData(String... params);

    public void loadMoreData(String... params);

}
