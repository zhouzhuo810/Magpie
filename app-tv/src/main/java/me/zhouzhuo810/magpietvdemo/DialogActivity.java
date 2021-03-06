package me.zhouzhuo810.magpietvdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.zhouzhuo810.magpietv.act.BaseActivity;
import me.zhouzhuo810.magpietv.dialog.ListDialog;
import me.zhouzhuo810.magpietv.dialog.OneBtnProgressDialog;
import me.zhouzhuo810.magpietv.dialog.TwoBtnTextDialog;
import me.zhouzhuo810.magpietv.utils.RxHelper;
import me.zhouzhuo810.magpietv.utils.ToastUtil;

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
    public boolean shouldNotInvokeInitMethods(Bundle savedInstanceState) {
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
    
    public void listDialog(View v) {
        String[] items = getResources().getStringArray(R.array.list_test_data);
        showListDialog(getString(R.string.app_name), items, true, false, new ListDialog.OnItemClick() {
            @Override
            public void onItemClick(int position, String item) {
                ToastUtil.showShortToast("position = " + position + ", content = " + item);
            }
        });
    }
    
    public void twoBtnTextDialog(View v) {
        //        showTwoBtnDialog(null, getString(R.string.check_update_text), true, new TwoBtnTextDialog.OnTwoBtnClick() {
/*        showTwoBtnTextDialog(getString(R.string.app_name), getString(R.string.check_update_text), true, new TwoBtnTextDialog.OnTwoBtnTextClick() {
            @Override
            public void onLeftClick(TextView v) {
                ToastUtil.showShortToast(v.getText().toString().trim());
            }
            
            @Override
            public void onRightClick(TextView v) {
                ToastUtil.showShortToast(v.getText().toString().trim());
            }
        });*/
        showTwoBtnTextDialog(getString(R.string.app_name), getString(R.string.check_update_text), true, true, new TwoBtnTextDialog.OnTwoBtnTextClick() {
            @Override
            public void onLeftClick(TextView v) {
                ToastUtil.showShortToast(v.getText().toString().trim());
            }
            
            @Override
            public void onRightClick(TextView v) {
                ToastUtil.showShortToast(v.getText().toString().trim());
            }
        });
    }
    
    public void horizontalProgressDialog(View v) {
        final Disposable[] subscribe = new Disposable[1];
        
        showOneBtnProgressDialog(getString(R.string.app_name), getString(R.string.updating_text) + "0%",
            getString(R.string.magpie_cancel_text), false,
            new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (subscribe[0] != null && !subscribe[0].isDisposed()) {
                        subscribe[0].dispose();
                    }
                }
            }, new OneBtnProgressDialog.OnProgressListener() {
                @Override
                public void onStart(final ProgressBar pb, final TextView tvMsg, final TextView tvOk) {
                    //使用RxJava模拟下载
                    subscribe[0] = Observable.intervalRange(0, 101, 0, 100, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                if (pb != null) {
                                    pb.setProgress(aLong.intValue());
                                    if (tvMsg != null) {
                                        tvMsg.setText(getString(R.string.updating_text) + pb.getProgress() + "%");
                                    }
                                }
                                if (aLong >= 100) {
                                    if (tvMsg != null) {
                                        tvMsg.setText("下载完成");
                                    }
                                    if (tvOk != null) {
                                        tvOk.setText("立即安装");
                                    }
                                    
                                }
                            }
                        });
                }
                
                @Override
                public void onBtnClick() {
                    ToastUtil.showShortToast("btn clicked");
                }
            });
    }
    
    public void loadingDialog(View v) {
        //        showLoadingDialog(getString(R.string.app_name), getString(R.string.loading_text), false, true);
        //        showLoadingDialog(getString(R.string.loading_text));
        showLoadingDialog(null, getString(R.string.loading_text), false, false);
        
        RxHelper.countDown(5, new Consumer<Long>() {
            @Override
            public void accept(Long disposable) throws Exception {
                showLoadingDialog("倒计时：" + (5 - disposable - 1) + "s");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                hideLoadingDialog();
            }
        });
        
        
    }
    public void closeAll(View v) {
        closeAllAct();
    }
    
}
