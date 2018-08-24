package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.widget.MarkView;
import me.zhouzhuo810.magpie.ui.widget.TitleBar;
import me.zhouzhuo810.magpie.utils.FileUtil;
import me.zhouzhuo810.magpie.utils.ToastUtil;
import me.zhouzhuo810.magpiedemo.api.Api;
import okhttp3.ResponseBody;

/**
 * Retrofit下载监听测试，与Magpie框架无关
 */
public class DownloadActivity extends BaseActivity {

    private ProgressBar pb;
    private ImageView ivPic;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
    }

    public void downloadStart(View v) {
        //设置下载进度监听
        ProgressManager.getInstance().addResponseListener(Api.DOWNLOAD_URL, new ProgressListener() {
            @Override
            public void onProgress(ProgressInfo progressInfo) {
                int percent = progressInfo.getPercent();
                pb.setProgress(percent);
            }

            @Override
            public void onError(long id, Exception e) {
                ToastUtil.showShortToast(e.getMessage());
            }
        });
        final String testFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.jpg";
        Api.getApi().downloadUrl(Api.DOWNLOAD_URL)
                .subscribeOn(Schedulers.io())//请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();
                        FileUtil.saveFile(inputStream, testFilePath);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Glide.with(DownloadActivity.this)
                                .applyDefaultRequestOptions(new RequestOptions()
                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                                        .skipMemoryCache(true))
                                .load(testFilePath).into(ivPic);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShortToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_download;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
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
        pb = findViewById(R.id.pb);
        ivPic = findViewById(R.id.iv_pic);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
