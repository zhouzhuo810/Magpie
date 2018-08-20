package me.zhouzhuo810.magpiedemo;

import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.simple.SimpleCallback;
import com.yanzhenjie.kalle.simple.SimpleResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.ToastUtil;
import me.zhouzhuo810.magpiedemo.api.Api;
import me.zhouzhuo810.magpiedemo.api.entity.GetWeatherList;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etCity;
    private Button btnGo;
    private TextView tvResult;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public View getDecorView() {
        return getWindow().getDecorView();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etCity = (EditText) findViewById(R.id.et_city);
        btnGo = (Button) findViewById(R.id.btn_go);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((i + 1) + "#");
        }
        Collections.shuffle(list);
        Log.e(TAG, "排序前：\n" + list.toString());
        CollectionUtil.sort(list, false);
        Log.e(TAG, "排序后：\n" + list.toString());

    }


    @Override
    public void initEvent() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString().trim();
                Kalle.get(Api.URL)
                        .param("city", city)
                        .perform(new SimpleCallback<GetWeatherList>() {
                            @Override
                            public void onResponse(SimpleResponse<GetWeatherList, String> response) {
                                if (response.isSucceed()) {
                                    tvResult.setText(response.succeed().toString());
                                } else {
                                    ToastUtil.showShortToast(response.failed());
                                }
                            }
                        });
            }
        });
    }


    @Override
    public void updateLanguage() {

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
    protected void onDestroy() {
        super.onDestroy();
    }

}
