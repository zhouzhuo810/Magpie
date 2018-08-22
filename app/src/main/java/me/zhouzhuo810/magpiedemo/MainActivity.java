package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpie.utils.ToastUtil;
import me.zhouzhuo810.magpiedemo.api.Api;
import me.zhouzhuo810.magpiedemo.api.entity.GetWeatherList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etCity;
    private Button btnGo;
    private TextView tvResult;
    private Button btnLanguage;
    private Button btnDialog;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        btnLanguage = findViewById(R.id.btn_language);
        btnDialog = findViewById(R.id.btn_dialog);
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

        btnLanguage.setOnClickListener(this);

        btnDialog.setOnClickListener(this);

        btnGo.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
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
                break;
            case R.id.btn_language:
                String[] items = getResources().getStringArray(R.array.language);
                showListDialog(getString(R.string.app_name), items, true, false, new ListDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position, String item) {
                        switch (position) {
                            case 0:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);
                                recreate();
                                break;
                            case 1:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.TRADITIONAL_CHINESE);
                                recreate();
                                break;
                            case 2:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.ENGLISH);
                                recreate();
                                break;
                        }
                    }
                });
                break;
            case R.id.btn_dialog:
                startAct(DialogActivity.class);
                break;
        }
    }
}
