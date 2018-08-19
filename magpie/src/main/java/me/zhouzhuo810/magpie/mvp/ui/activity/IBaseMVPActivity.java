package me.zhouzhuo810.magpie.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import me.zhouzhuo810.magpie.mvp.presenter.IBasePresenter;

public interface IBaseMVPActivity {

    public int getLayoutId();

    public View getDecorView();

    public View getView(@IdRes int id);

    public IBasePresenter getPresenter();

    public void initView(@Nullable Bundle savedInstanceState);

    public void initData();

    public void initEvent();

    public void closeAct();

    public void closeAct(boolean defaultBack);

    public void closeAllAct();

    public void startActWithIntent();

    public void startActWithIntentForResult();

    public int inAnim();

    public int outAnim();

    public void updateLanguage();

    public void showProgressDialog();

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
