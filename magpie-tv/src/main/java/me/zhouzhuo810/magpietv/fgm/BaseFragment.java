package me.zhouzhuo810.magpietv.fgm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.zhouzhuo810.magpietv.R;
import me.zhouzhuo810.magpietv.act.IBaseActivity;
import me.zhouzhuo810.magpietv.utils.ScreenAdapterUtil;

import java.util.List;


public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        //屏幕适配
        ScreenAdapterUtil.getInstance().loadView(rootView);

        initView(savedInstanceState);

        initData();

        initEvent();

        return rootView;
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        getBaseAct().overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public View findViewById(int id) {
        if (rootView == null) {
            return null;
        }
        return rootView.findViewById(id);
    }


    @Override
    public IBaseActivity getBaseAct() {
        return (IBaseActivity) getActivity();
    }

    @Override
    public void closeAct() {
        getBaseAct().closeAct();
    }

    @Override
    public void closeAct(boolean defaultAnimation) {
        getBaseAct().closeAct(defaultAnimation);
    }

    @Override
    public void closeAllAct() {

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
    public void showTwoBtnDialog(String title, String msg, boolean cancelable, OnTwoBtnClick onTwoBtnClick, DialogInterface.OnDismissListener onDismissListener) {

    }

    @Override
    public void hideTwoBtnDialog() {

    }

    @Override
    public void showEditDialog(String title, String msg, String hint, boolean cancelable, OnTwoBtnEditClick onTwoBtnEditClick) {

    }

    @Override
    public void showEditDialog(String title, String msg, String hint, boolean cancelable, OnTwoBtnEditClick onTwoBtnEditClick, DialogInterface.OnDismissListener onDismissListener) {

    }

    @Override
    public void hideEditDialog() {

    }

    @Override
    public void showListDialog(List<String> items, boolean cancelable, boolean checkable, OnItemClick onItemClick, DialogInterface.OnDismissListener onDismissListener) {

    }

    @Override
    public void hideListDialog() {

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
    public int closeInAnimation() {
        return R.anim.mp_slide_in_left;
    }

    @Override
    public int closeOutAnimation() {
        return R.anim.mp_side_out_right;
    }

    @Override
    public int openInAnimation() {
        return R.anim.mp_slide_in_right;
    }

    @Override
    public int openOutAnimation() {
        return R.anim.mp_side_out_left;
    }
}
