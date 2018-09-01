package me.zhouzhuo810.magpiedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.utils.BaseUtil;
import me.zhouzhuo810.magpie.utils.NoticeUtil;

public class ToolsActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_tools;
    }

    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                throw new OutOfMemoryError("测试OOM异常");
            }
        }, 2000);
    }

    @Override
    public void initEvent() {

    }

    public void noticeTools(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(BaseUtil.getApp(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        NoticeUtil.showNormalNotice(getString(R.string.app_name), getString(R.string.notice_tools),
                true, false, R.mipmap.ic_launcher,
                true, true, null, intent);
    }
}
