package me.zhouzhuo810.magpie.ui.act;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.cons.Cons;
import me.zhouzhuo810.magpie.event.CloseAllActEvent;
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpie.utils.ScreenAdapterUtil;
import me.zhouzhuo810.magpie.utils.SpUtils;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private ListDialog listDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ScreenAdapterUtil.getInstance().loadView(getDecorView());

        initView(savedInstanceState);

        initData();

        initEvent();
    }

    @Override
    public boolean useSysFinishAnim() {
        return false;
    }

    @Override
    public View getDecorView() {
        return getWindow().getDecorView();
    }

    /**
     * 跳转到目标Activity
     *
     * @param clazz 目标界面
     */
    @Override
    public void startAct(Class<? extends Activity> clazz) {
        startActWithIntent(new Intent(this, clazz));
    }

    @Override
    public void startActWithIntent(Intent intent) {
        startActWithIntent(intent, false);
    }

    @Override
    public void startActWithIntent(Intent intent, boolean defaultAnim) {
        if (defaultAnim) {
            startActivity(intent);
        } else {
            startActivity(intent);
            overridePendingTransition(openInAnimation(), openOutAnimation());
        }
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode) {
        startActWithIntentForResult(intent, requestCode, false);
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode, boolean defaultAnim) {
        if (defaultAnim) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivityForResult(intent, requestCode);
            overridePendingTransition(openInAnimation(), openOutAnimation());
        }
    }

    @Override
    public void restart() {
        recreate();
    }

    @Override
    public void closeAct() {
        closeAct(false);
    }


    @Override
    public void onBackPressed() {
        if (useSysFinishAnim()) {
            super.onBackPressed();
        } else {
            closeAct();
        }
    }

    @Override
    public void closeAct(boolean defaultAnimation) {
        if (defaultAnimation) {
            finish();
        } else {
            finish();
            overridePendingTransition(closeInAnimation(), closeOutAnimation());
        }
    }

    @Override
    public void closeAllAct() {
        EventBus.getDefault().post(new CloseAllActEvent());
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public int closeInAnimation() {
        return R.anim.mp_slide_in_left;
    }

    @Override
    public int closeOutAnimation() {
        return R.anim.mp_side_out_right;
    }

    @Override
    public void showLoadingDialog(String title, String msg) {

    }

    @Override
    public void showLoadingDialog(String title, String msg, DialogInterface.OnDismissListener listener) {

    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener) {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void showProgressDialog(String title, String msg, OnProgressListener onProgressListener) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showTwoBtnDialog(String title, String msg, boolean cancelable, OnTwoBtnClick onTwoBtnClick) {

    }

    @Override
    public void showTwoBtnDialog(String title, String msg, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, OnTwoBtnClick onTwoBtnClick) {

    }

    @Override
    public void hideTwoBtnDialog() {

    }

    @Override
    public void showEditDialog(String title, String msg, String hint, boolean cancelable, OnTwoBtnEditClick onTwoBtnEditClick) {

    }

    @Override
    public void showEditDialog(String title, String msg, String hint, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, OnTwoBtnEditClick onTwoBtnEditClick) {

    }

    @Override
    public void hideEditDialog() {

    }

    @Override
    public void showListDialog(String[] items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(CollectionUtil.stringToList(items), cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(List<String> items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(items, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(List<String> items, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, ListDialog.OnItemClick onItemClick) {
        listDialog = new ListDialog();
        listDialog.setItems(items)
                .setOnItemClick(onItemClick)
                .setCancelable(cancelable);
        listDialog.show(getSupportFragmentManager(), getClass().getSimpleName());
    }

    @Override
    public void hideListDialog() {
        if (listDialog != null) {
            listDialog.dismissDialog();
        }
    }

    @Override
    public void showBottomSheet(List<String> items, boolean cancelable, boolean iosStyle) {

    }

    @Override
    public void showBottomSheet(List<String> items, boolean cancelable, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener) {

    }

    @Override
    public void hideBottomSheet() {

    }

    @Override
    public void refreshData(String... params) {

    }

    @Override
    public void loadMoreData(String... params) {

    }

    @Override
    public int openInAnimation() {
        return R.anim.mp_slide_in_right;
    }

    @Override
    public int openOutAnimation() {
        return R.anim.mp_side_out_left;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        //如果支持多语言，才给切换语言
        if (shouldSupportMultiLanguage()) {
            int language = SpUtils.getInt(Cons.SP_KEY_OF_CHOOSED_LANGUAGE);
            switch (language) {
                case 0:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, Cons.SIMPLIFIED_CHINESE));
                    break;
                case 1:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, Cons.TRADITIONAL_CHINESE));
                    break;
                case 2:
                    super.attachBaseContext(LanguageUtil.attachBaseContext(newBase, Cons.ENGLISH));
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCloseAllActEvent(CloseAllActEvent event) {
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        hideListDialog();
        super.onDestroy();
    }
}
