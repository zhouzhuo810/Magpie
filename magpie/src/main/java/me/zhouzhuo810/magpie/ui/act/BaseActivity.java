package me.zhouzhuo810.magpie.ui.act;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.cons.Cons;
import me.zhouzhuo810.magpie.event.CloseAllActEvent;
import me.zhouzhuo810.magpie.ui.dialog.BottomSheetDialog;
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.ui.dialog.LoadingDialog;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpie.utils.ScreenAdapterUtil;
import me.zhouzhuo810.magpie.utils.SpUtils;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private ListDialog listDialog;
    private BottomSheetDialog bsDialog;
    private LoadingDialog loadingDialog;

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
    public void showLoadingDialog(String msg) {
        showLoadingDialog(null, msg);
    }

    @Override
    public void showLoadingDialog(String title, String msg) {
        showLoadingDialog(title, msg, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable) {
        showLoadingDialog(title, msg, cancelable, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, boolean iosStyle) {
        showLoadingDialog(title, msg, cancelable, iosStyle, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, DialogInterface.OnDismissListener listener) {
        showLoadingDialog(title, msg, cancelable, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener) {
        hideLoadingDialog();
        loadingDialog = new LoadingDialog();
        loadingDialog.setTitle(title)
                .setMsg(msg)
                .setIosStyle(iosStyle)
                .setOnDismissListener(onDismissListener)
                .setCancelable(cancelable);
        loadingDialog.show(getSupportFragmentManager(), getClass().getSimpleName());
    }

    @Override
    public void hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }
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
        showListDialog(null, CollectionUtil.stringToList(items), cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, String[] items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, CollectionUtil.stringToList(items), cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, String[] items, boolean alignLeft, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, CollectionUtil.stringToList(items), alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean alignLeft, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, false, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, ListDialog.OnItemClick onItemClick) {
        hideListDialog();
        listDialog = new ListDialog();
        listDialog
                .setOnItemClick(onItemClick)
                .setOnDismissListener(onDismissListener)
                .setAlignLeft(alignLeft)
                .setTitle(title)
                .setItems(items)
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
    public void showBottomSheet(String title, List<String> items, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, items, false, cancelable, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), false, cancelable, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, List<String> items, boolean alignLeft, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, items, alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean alignLeft, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), alignLeft, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, List<String> items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, BottomSheetDialog.OnItemClick onItemClick) {
        hideBottomSheet();
        bsDialog = new BottomSheetDialog();
        bsDialog
                .setTitle(title)
                .setOnItemClick(onItemClick)
                .setOnDismissListener(onDismissListener)
                .setItems(items)
                .setAlignLeft(alignLeft);
        bsDialog.setCancelable(cancelable);
        bsDialog.show(getSupportFragmentManager(), getClass().getSimpleName());
    }

    @Override
    public void hideBottomSheet() {
        if (bsDialog != null) {
            bsDialog.dismissDialog();
        }
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
    public TextWatcher setEditImageListener(final EditText et, final ImageView iv) {
        if (et == null) {
            throw new NullPointerException("EditText should not be null.");
        }
        if (iv == null) {
            throw new NullPointerException("ImageView should not be null.");
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    iv.setVisibility(View.VISIBLE);
                } else {
                    iv.setVisibility(View.GONE);
                }
            }
        };
        et.addTextChangedListener(textWatcher);
        return textWatcher;

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
