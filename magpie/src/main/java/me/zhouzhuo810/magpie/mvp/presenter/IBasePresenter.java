package me.zhouzhuo810.magpie.mvp.presenter;


import java.util.List;

public interface IBasePresenter<T> {

    void showProgressDialog();

    void hideProgressDialog();

    void showUpdateDialog();

    void hideUpdateDialog();

    void showTwoBtnDialog();

    void hideTwoBtnDialog();

    void showEditDialog();

    void hideEditDialog();

    void refreshData(OnResultListener<T> listener, String... params);

    void loadMoreData(OnResultListener<T> listener, String... params);

    public interface OnResultListener<T> {
        public void onOk(List<T> result);

        public void onFail(String errorMsg);
    }


}
