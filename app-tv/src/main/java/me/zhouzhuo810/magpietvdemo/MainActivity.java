package me.zhouzhuo810.magpietvdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    
/*    private void getTestData() {
        String json = JsonUtil.getJson(this, "test.json");
        Gson gson = new Gson();
        MainInfoBean mainInfoBean = gson.fromJson(json, MainInfoBean.class);
        if (mainInfoBean.getCode().equals("1")) {
            String serverIp = KqSharedUtil.getServerIp();
            Glide.with(MainActivity.this).load(serverIp + mainInfoBean.getData().getComInfo().getLogo()).into(ivCom);
            tvCom.setText(mainInfoBean.getData().getComInfo().getName());
            List<MapMachineEntity> list = generateData(mainInfoBean.getData().getMap());
            factoryView.setMapEntity(new MapEntity(mainInfoBean.getData().getDesignHeight(), mainInfoBean.getData().getDesignWidth(), 20, list));
            fillStates(mainInfoBean.getData().getReasons());
            // TODO: 2017/6/19 滚动效果 By lirenhao
            updateList(mainInfoBean.getData().getErrorList());
            voiceBroadcast(mainInfoBean.getData().getErrorList());
        }
    }*/
}
