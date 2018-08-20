package me.zhouzhuo810.magpie.ui.act;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public interface IBaseActivity {

    /**
     * 是否使用系统默认的finish动画
     *
     * @return 默认false，即使用框架提供的动画
     */
    public boolean useSysFinishAnim();

    /**
     * 获取布局的id
     *
     * @return
     */
    public int getLayoutId();

    /**
     * 屏幕适配需要返回getWindow().getDecorView();
     *
     * @return getWindow().getDecorView()
     */
    public View getDecorView();

    public void initView(@Nullable Bundle savedInstanceState);

    public void initData();

    public void initEvent();

    public void closeAct();

    public void closeAct(boolean defaultBack);

    public void closeAllAct();

    public void startActWithIntent(Intent intent);

    public void startActWithIntent(Intent intent, boolean defaultAnim);

    public void startActWithIntentForResult(Intent intent, int requestCode);

    public void startActWithIntentForResult(Intent intent, int requestCode, boolean defaultAnim);

    public void overridePendingTransition(int enterAnim, int exitAnim);


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

}
