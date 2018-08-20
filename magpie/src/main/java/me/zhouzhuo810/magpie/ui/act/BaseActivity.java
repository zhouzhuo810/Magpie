package me.zhouzhuo810.magpie.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.utils.DisplayUtil;

public abstract class BaseActivity<T> extends AppCompatActivity implements IBaseActivity {

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
    public void closeAct() {
        closeAct(false);
    }

    @Override
    public boolean useSysFinishAnim() {
        return false;
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
    public int openInAnimation() {
        return R.anim.mp_slide_in_right;
    }

    @Override
    public int openOutAnimation() {
        return R.anim.mp_side_out_left;
    }


}
