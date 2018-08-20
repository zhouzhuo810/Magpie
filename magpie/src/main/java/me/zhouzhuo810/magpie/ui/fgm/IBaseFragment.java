package me.zhouzhuo810.magpie.ui.fgm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import me.zhouzhuo810.magpie.ui.act.IBaseActivity;

public interface IBaseFragment {

    /**
     * 获取布局的id
     *
     * @return
     */
    public int getLayoutId();

    public View findViewById(int id);

    public void initView(@Nullable Bundle savedInstanceState);

    public void initData();

    public void initEvent();

    public IBaseActivity getBaseAct();

    public void startActWithIntent(Intent intent);

    public void startActWithIntent(Intent intent, boolean defaultAnim);

    public void startActWithIntentForResult(Intent intent, int requestCode);

    public void startActWithIntentForResult(Intent intent, int requestCode, boolean defaultAnim);

    public void closeAct();

    public void closeAct(boolean defaultAnimation);

    public void closeAllAct();

    public void overridePendingTransition(int enterAnim, int exitAnim);

    public void updateLanguage();

    public void showLoadingDialog(String title, String msg);

    public void showLoadingDialog(String title, String msg, DialogInterface.OnDismissListener listener);

    public void showLoadingDialog(String title, String msg, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener);

    public void hideLoadingDialog();

    public void showProgressDialog(String title, String msg, OnProgressListener onProgressListener);

    public void hideProgressDialog();

    public void showTwoBtnDialog(String title, String msg, boolean cancelable, OnTwoBtnClick onTwoBtnClick);

    public void showTwoBtnDialog(String title, String msg, boolean cancelable, OnTwoBtnClick onTwoBtnClick, DialogInterface.OnDismissListener onDismissListener);

    public void hideTwoBtnDialog();

    public void showEditDialog(String title, String msg, String hint, boolean cancelable, OnTwoBtnEditClick onTwoBtnEditClick);

    public void showEditDialog(String title, String msg, String hint, boolean cancelable, OnTwoBtnEditClick onTwoBtnEditClick, DialogInterface.OnDismissListener onDismissListener);

    public void hideEditDialog();

    public void showListDialog(List<String> items, boolean cancelable, boolean checkable, OnItemClick onItemClick, DialogInterface.OnDismissListener onDismissListener);

    public void hideListDialog();

    public void showBottomSheet(List<String> items, boolean cancelable, boolean iosStyle);

    public void showBottomSheet(List<String> items, boolean cancelable, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener);

    public void hideBottomSheet();

    public void refreshData(String... params);

    public void loadMoreData(String... params);

    public interface OnItemClick {
        void onItemClick(int position, String item);
    }

    public interface OnTwoBtnEditClick {
        void onOk(String etContent);

        void onCancel();
    }

    public interface OnTwoBtnClick {
        void onOk();

        void onCancel();
    }

    public interface OnProgressListener {
        void onProgress(TextView tvMsg, ProgressBar pb);
    }

    public interface OnOneBtnClick {
        void onClick();
    }


    /**
     * 启动Activity进入动画
     *
     * @return resId
     */
    int openInAnimation();

    /**
     * 启动Activity退出动画
     *
     * @return resId
     */
    int openOutAnimation();

    /**
     * 关闭Activity进入动画
     *
     * @return resId
     */
    int closeInAnimation();

    /**
     * 关闭Activity退出动画
     *
     * @return resId
     */
    int closeOutAnimation();
}
