package me.zhouzhuo810.magpiedemo.api;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import me.jessyan.progressmanager.ProgressManager;
import me.zhouzhuo810.magpiedemo.api.entity.GetWeatherList;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
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
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .addInterceptor(logging)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS);
                    //注册上传或下载监听器
                    OkHttpClient client = ProgressManager.getInstance().with(builder).build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(URL_RETROFIT)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .build();
                    weatherApi = retrofit.create(WeatherApi.class);
                }
            }
        }
        return weatherApi;
    }


}

