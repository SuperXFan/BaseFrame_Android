package cc.ewell.common.data.net.retrofit.factory;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import cc.ewell.common.constants.CommonServerConstant;
import cc.ewell.common.data.net.retrofit.cookies.CookieManger;
import cc.ewell.common.utils.LogUtil;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 配置okHttp  和 retrofit  最后生成相应的service
 * Created by SuperFan on 2016/8/30.
 */
public class RxServiceFactory {


    private static class SingletonHolder {
        private static final RxServiceFactory INSTANCE = new RxServiceFactory();
    }

    public static RxServiceFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public <T> T createRxServiceFrom(@NonNull Context context, @NonNull final Class<T> rxServiceClass, @NonNull String hostUrl,Map<String,String> headers) {

        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

        //证书机构
//        if (CommonServerConstant.ISHTTPS) {
            //添加证书Pinning
            // okBuilder.certificatePinner(new CertificatePinner.Builder()
            //              .add("YOU API.com", "sha1/DmxUShsZuNiqPQsX2Oi9uv2sCnw=")
            //              .add("YOU API..com", "sha1/SXxoaOSEzPC6BgGmxAt/EAcsajw=")
            //              .add("YOU API..com", "sha1/blhOM3W9V/bVQhsWAcLYwPU6n24=")
            //              .add("YOU API..com", "sha1/T5x9IXmcrQ7YuQxXnxoCmeeQ84c=")
            //              .build());

            //支持https
            // okBuilder.socketFactory(HttpsFactroy.getSSLSocketFactory(context,   certificates));//certificates 是raw下证书源ID, int[] certificates = {R.raw.myssl}
            // okBuilder.hostnameVerifier(HttpsFactroy.getHostnameVerifier(hosts));//hosts 列如 String hosts[]`= {“https//:aaaa,com”, “https//:bbb.com”}
//        }


        //打开网络请求响应中的log
//        if (BuildConfig.DEBUG) {
//            okBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
//            okBuilder.addNetworkInterceptor(new StethoInterceptor());
//            okHttpBuilder.addInterceptor(new HttpLoggingInterceptor());
            okBuilder.addInterceptor( new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtil.noFormateInfo(message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));//网络和日志拦截
//        }

        //添加header
        if(headers != null){
            okBuilder = okBuilder.addInterceptor(new BaseInterceptor(headers));
        }

        //加上cookie的管理
        okBuilder = okBuilder.cookieJar(new CookieManger(context));

        //设置缓存
//        File httpCacheDirectory = new File(context.getCacheDir(), "RetrofitCache");
//        okBuilder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));

        //设置超时时间
        okBuilder.connectTimeout(CommonServerConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        okBuilder.writeTimeout(CommonServerConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        okBuilder.readTimeout(CommonServerConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS));
        // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s


        Retrofit retrofit = builder
                .baseUrl(hostUrl)//接口的host 前缀
                .client(okBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .build();


        return retrofit.create(rxServiceClass);
    }



}
