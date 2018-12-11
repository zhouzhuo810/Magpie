package me.zhouzhuo810.magpietvdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import me.zhouzhuo810.magpietv.act.BaseActivity;

public class MainActivity extends BaseActivity {
    
    private Button tvMap;
    private Button tvScrollList;
    private Button tvScrollGrid;
    private View tvDialog;
    private View tvIndicator;
    private View tvTab;
    
    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
    
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvMap = findViewById(R.id.tv_map);
        tvScrollList = findViewById(R.id.tv_scroll_list);
        tvScrollGrid = findViewById(R.id.tv_scroll_grid);
        tvDialog = findViewById(R.id.tv_dialog);
        tvIndicator = findViewById(R.id.tv_indicator);
        tvTab = findViewById(R.id.tv_tab);
    }
    
    @Override
    public void initData() {
    
    }
    
    @Override
    public void initEvent() {
        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(MapActivity.class);
            }
        });
        
        tvScrollList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ScrollListActivity.class);
            }
        });
        
        tvScrollGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(ScrollGridActivity.class);
            }
        });
        
        tvDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(DialogActivity.class);
            }
        });
        
        tvIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(PagerActivity.class);
            }
        });
        
        tvTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(TabActivity.class);
            }
        });
    }
    
    @Override
    public boolean shouldNotInvokeInitMethods(Bundle savedInstanceState) {
        return false;
    }
}
