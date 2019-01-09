package me.zhouzhuo810.magpietv.act;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.zhouzhuo810.magpietv.R;
import me.zhouzhuo810.magpietv.cons.Cons;
import me.zhouzhuo810.magpietv.utils.CopyUtil;
import me.zhouzhuo810.magpietv.utils.ShareUtil;
import me.zhouzhuo810.magpietv.utils.ToastUtil;

/**
 * 复制通知栏Url
 */
public class CopyUrlActivity extends BaseActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra(Cons.NOTICE_ACTION);
        String url = getIntent().getStringExtra(Cons.NOTICE_URL);
        if ("copy".equals(action)) {
            CopyUtil.copyPlainText("", url);
            ToastUtil.showShortToast(getString(R.string.magpie_copy_ok));
        } else if ("share".equals(action)) {
            ShareUtil.shareTextToOther(this, url, null);
        }
        closeAct();
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_copy_url;
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
    
    }
    
    @Override
    public void initEvent() {
    
    }
}
