package me.zhouzhuo810.magpie.mvp.presenter;

import me.zhouzhuo810.magpie.mvp.ui.activity.IBaseMVPActivity;
import me.zhouzhuo810.magpie.mvp.model.IBaseMVPModel;

public class BasePresenter implements IBasePresenter {

    protected IBaseMVPModel mModel;
    protected IBaseMVPActivity mView;

    public BasePresenter(IBaseMVPModel mModel, IBaseMVPActivity mView) {
        this.mModel = mModel;
        this.mView = mView;
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showUpdateDialog() {

    }

    @Override
    public void hideUpdateDialog() {

    }

    @Override
    public void showTwoBtnDialog() {

    }

    @Override
    public void hideTwoBtnDialog() {

    }

    @Override
    public void showEditDialog() {

    }

    @Override
    public void hideEditDialog() {

    }

    @Override
    public void refreshData(String... params) {

    }

    @Override
    public void loadMoreData(String... params) {

    }

}
