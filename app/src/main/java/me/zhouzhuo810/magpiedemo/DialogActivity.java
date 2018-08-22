package me.zhouzhuo810.magpiedemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.dialog.BottomSheetDialog;
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.ui.widget.MarkView;
import me.zhouzhuo810.magpie.ui.widget.TitleBar;
import me.zhouzhuo810.magpie.utils.ToastUtil;

/**
 * 对话框Activity
 */
public class DialogActivity extends BaseActivity {

    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        TitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setOnTitleClickListener(new TitleBar.OnTitleClick() {
            @Override
            public void onLeftClick(ImageView ivLeft, MarkView mv, TextView tvLeft) {
                closeAct();
            }

            @Override
            public void onTitleClick(TextView tvTitle) {

            }

            @Override
            public void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight) {

            }
        });
    }

    public void listDialog(View v) {
        String[] items = getResources().getStringArray(R.array.list_test_data);
        showListDialog(getString(R.string.app_name), items, true, false, new ListDialog.OnItemClick() {
            @Override
            public void onItemClick(int position, String item) {
                ToastUtil.showShortToast("position = " + position + ", content = " + item);
            }
        });
    }

    public void twoBtnDialog(View v) {
    }

    public void horizontalProgressDialog(View v) {

    }

    public void loadingDialog(View v) {
        showLoadingDialog(null, getString(R.string.loading_text), false, false);
//        showLoadingDialog(getString(R.string.app_name), getString(R.string.loading_text), false, true);
//        showLoadingDialog(getString(R.string.loading_text));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideLoadingDialog();
            }
        }, 2000);
    }

    public void bottomSheetDialog(View v) {
        String[] items = getResources().getStringArray(R.array.bottom_sheet_test_data);
        showBottomSheet(getString(R.string.app_name), items, true, true, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ToastUtil.showShortToast("dialog canceled");
            }
        }, new BottomSheetDialog.OnItemClick() {
            @Override
            public void onItemClick(int position, String item) {
                ToastUtil.showShortToast("position = " + position + ", content = " + item);
            }
        });
    }

}
