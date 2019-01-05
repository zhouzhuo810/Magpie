package me.zhouzhuo810.magpiedemo.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import me.zhouzhuo810.magpie.utils.ApiUtil;
import me.zhouzhuo810.magpiedemo.api.entity.GetWeatherList;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public class Api {
    
    public static final String URL_RETROFIT = "http://wthrcdn.etouch.cn/";
    
    public static final String DOWNLOAD_URL = "http://p2.so.qhimgs1.com/t012a3be3c0d1bb9622.jpg";
    
    private static WeatherApi weatherApi;
    
    public interface WeatherApi {
        @GET("weather_mini")
        Observable<GetWeatherList> getWeatherList(@Query("city") String city);
        
        @Streaming
        @GET
        Observable<ResponseBody> downloadUrl(@Url String url);
    }
    
    public static WeatherApi getApi() {
        if (weatherApi == null) {
            synchronized (Api.class) {
                if (weatherApi == null) {
                    weatherApi = ApiUtil.createApi(
                        WeatherApi.class,
                        URL_RETROFIT,
                        20,
                        TimeUnit.SECONDS,
                        true,
                        false
                    );
                }
            }
        }
        return weatherApi;
    }
    
    
}

