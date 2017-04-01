package test.narendra.com.gopettingpractical.rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.narendra.com.gopettingpractical.BuildConfig;

/**
 * <h1>Api url and send and get request for server</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class ApiService {

    private static long CONNECTION_TIMEOUT = 30;

    private static String API_URL = "https://guidebook.com/service/";


    public static WebService getService() {
        OkHttpClient client = getOkHttpClient();
        WebService gitHub = new Retrofit.Builder().baseUrl(API_URL).client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(WebService.class);
        return gitHub;
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okClientBuilder.addInterceptor(httpLoggingInterceptor);
        if (BuildConfig.DEBUG) {
            okClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        }
        okClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okClientBuilder.writeTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        return okClientBuilder.build();
    }

}
