package cc.ewell.common.data.net.retrofit.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.ewell.common.constants.CommonServerConstant;
import cc.ewell.common.data.net.retrofit.ResponseBaseBody;
import cc.ewell.common.data.net.retrofit.factory.RxServiceFactory;
import cc.ewell.common.data.net.retrofit.func.RetryWhenNetworkException;
import cc.ewell.common.data.net.retrofit.subscriber.HttpResultSubscriber;
import cc.ewell.common.data.net.retrofit.transformer.TransformUtils;
import cc.ewell.common.utils.CommonAccountUtil;
import cc.ewell.common.utils.EncryptionUtil;
import cc.ewell.common.utils.LogUtil;
import cc.ewell.common.utils.UUIDUtil;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by SuperFan on 2016/8/30.
 */
public class RxBaseApi {
    /**
     * 调用RxService 的方法  返回相应的Observeble
     * 例如
     * 1.获取RxService实例
     * RXServiceXX rXServiceXX =
     * RxServiceFactory.createServiceFrom(RXServiceXX.class, host);
     * 2.获取Observeble
     * public Observable<List<News>> getNcuNews(int howmany, int fromwhere){
     * return rXServiceXX.getNcuNews(howmany, fromwhere)
     * .subscribeOn(Schedulers.io())//在io线程进行数据获取
     * .observeOn(AndroidSchedulers.mainThread());//在UI线程使用返回的数据
     * }
     */

    private RxBaseService apiService;
    private static RxBaseApi rxBaseApi;


    private RxBaseApi(Context context, String prefixUrl, Map<String, String> headers) {
        apiService = RxServiceFactory.getInstance().createRxServiceFrom(context, RxBaseService.class, prefixUrl, headers);
    }

    private static String lastPrefixUrl = "";
    private static Map<String,String> lastHeaders = null;

    public static RxBaseApi getDefault(@NonNull Context context, @NonNull String prefixUrl, Map<String, String> headers) {

        //初始话header
        if (headers == null) {
            headers = new HashMap<>();
        }
        String UUID = CommonAccountUtil.getUserId(context);
        String SSID = UUIDUtil.generateShortUuid();
        String SEQ = UUIDUtil.getUUID();
        headers.put(CommonServerConstant.AUTH, "2");
        headers.put(CommonServerConstant.UUID, UUID);
        headers.put(CommonServerConstant.SSID, SSID);
        headers.put(CommonServerConstant.SEQ, SEQ);
        String SIGN = EncryptionUtil.getMD5("UUIDSSIDSEQKEY" + UUID + SSID + SEQ + CommonServerConstant.KEY);
        headers.put(CommonServerConstant.SIGN, SIGN);

        //判断是否需要重新实例化
        if (rxBaseApi == null) {//实例为空
            lastPrefixUrl = prefixUrl;
            lastHeaders = headers;
            rxBaseApi = new RxBaseApi(context, prefixUrl, headers);
        } else if (lastPrefixUrl == null || !lastPrefixUrl.equals(prefixUrl)) {//baseUrl不一样
            lastPrefixUrl = prefixUrl;
            lastHeaders = headers;
            rxBaseApi = new RxBaseApi(context, prefixUrl, headers);
        } else if (!headers.equals(lastHeaders)) {//header有变化
            lastPrefixUrl = prefixUrl;
            lastHeaders = headers;
            rxBaseApi = new RxBaseApi(context, prefixUrl, headers);
        }
        return rxBaseApi;
    }

    /**
     * 执行get请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executeGet(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        return createGetObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createGetObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executeGet(url, request)
                .map(new Func1<Response<Object>, T>() {
                    @Override
                    public T call(Response<Object> response) {
                        T resultData = null;

                        if (response != null) {

                            //获取body
                            ResponseBaseBody responseBaseBody = null;
                            try {
                                responseBaseBody = new Gson().fromJson(response.body().toString(),ResponseBaseBody.class);
                            }catch (Exception e){

                            }
                            if (responseBaseBody == null) {
                                return resultData;
                            }

                            //设置返回参数
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                                //获取服务器给的message提示
                                subscriber.setResultMessage(responseBaseBody.getMessage());
                                // 获取服务器给的resultCode
                                subscriber.setResultCode(responseBaseBody.getStatusCode());
                                //获取服务的访问结果类型
                                subscriber.setResultType(responseBaseBody.getType());
                            }

                            //如果访问成功就解析数据
                            if (!TextUtils.isEmpty(responseBaseBody.getType()) && responseBaseBody.getType().equals(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE)) {
                                try {
                                    resultData = new Gson().fromJson(responseBaseBody.getMsg(), resultType);
                                } catch (Throwable t) {
                                    Logger.i(resultType.getName() + "解析出错 msg= " + responseBaseBody.getMsg());
                                }
                            }

                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers())
                .retryWhen(new RetryWhenNetworkException());
    }


    /**
     * 执行post请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executePost(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {

        // MD5加密
//        //注册或者登陆 不执行该方法
//        if (!url.contains(CommonServerConstant.LOGIN_PATH) && !url.contains(CommonServerConstant.REGIST_PATH)) {//所有不执行加密的方法
            request.put(CommonServerConstant.UUID, CommonAccountUtil.getUserId(context));
//            List<Map.Entry<String, String>> paramList = new ArrayList<Map.Entry<String, String>>(request.entrySet());
//            Collections.sort(paramList, new Comparator<Map.Entry<String, String>>() {
//                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                    return (o1.getKey()).toString().compareTo(o2.getKey());
//                }
//            });
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < paramList.size(); i++) {
//                sb.append(paramList.get(i).toString() + "&");
//            }
//
//            String path = sb.toString().substring(0, sb.length() - 1);
//
//            request.put(CommonServerConstant.SIGN, EncryptionUtil.getMD5(path + CommonAccountUtil.getToken(context)));
//        }

        return createPostObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createPostObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executePost(url, request)
                .observeOn(Schedulers.io())
                .map(new Func1<Response<Object>, T>() {
                    @Override
                    public T call(Response<Object> response) {

                        T resultData = null;
                        //返回的服务器对象
                        if (response != null) {

                            //获取body
                            ResponseBaseBody responseBaseBody = null;
                            try {
                                responseBaseBody = new Gson().fromJson(response.body().toString(),ResponseBaseBody.class);
                            }catch (Exception e){

                            }
                            if (responseBaseBody == null) {
                                return resultData;
                            }

                            //设置返回参数
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                                //获取服务器给的message提示
                                subscriber.setResultMessage(responseBaseBody.getMessage());
                                // 获取服务器给的resultCode
                                subscriber.setResultCode(responseBaseBody.getStatusCode());
                                //获取服务的访问结果类型
                                subscriber.setResultType(responseBaseBody.getType());
                            }

                            //如果访问成功就解析数据
                            if (!TextUtils.isEmpty(responseBaseBody.getType()) && responseBaseBody.getType().equals(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE)) {
                                try {
                                    resultData = new Gson().fromJson(responseBaseBody.getMsg(), resultType);
                                    if (resultData != null) {
                                        LogUtil.i(resultData.toString());
                                    }
                                } catch (Exception t) {
                                    Logger.i(resultType.getName() + "解析出错 msg= " + responseBaseBody.getMsg());
                                }
                            }

                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    /**
     * 执行post请求
     *
     * @param context
     * @param subscriber
     * @param url
     * @param request
     * @return
     */
    public <T> Subscription executePostList(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {

        // MD5加密
        //注册或者登陆 不执行该方法

//        if (!url.contains(CommonServerConstant.LOGIN_PATH) && !url.contains(CommonServerConstant.REGIST_PATH)) {//所有不执行加密的方法
            request.put(CommonServerConstant.UUID, CommonAccountUtil.getUserId(context));
//            List<Map.Entry<String, String>> paramList = new ArrayList<Map.Entry<String, String>>(request.entrySet());
//            Collections.sort(paramList, new Comparator<Map.Entry<String, String>>() {
//                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                    return (o1.getKey()).toString().compareTo(o2.getKey());
//                }
//            });
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < paramList.size(); i++) {
//                sb.append(paramList.get(i).toString() + "&");
//            }
//
//            String path = sb.toString().substring(0, sb.length() - 1);
//
//            request.put(CommonServerConstant.SIGN, EncryptionUtil.getMD5(path + CommonAccountUtil.getToken(context)));
//        }

        return createPostStringObservable(context, subscriber, resultType, url, request)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createPostStringObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, String> request) {
        subscriber.setContext(context);
        return apiService.executePost(url, request)
                .observeOn(Schedulers.io())
                .map(new Func1<Response<Object>, T>() {
                    @Override
                    public T call(Response<Object> response) {

                        T resultData = null;
                        //返回的服务器对象
                        if (response != null) {

                            //获取body
                            ResponseBaseBody responseBaseBody = null;
                            try {
                                responseBaseBody = new Gson().fromJson(response.body().toString(),ResponseBaseBody.class);
                            }catch (Exception e){

                            }
                            if (responseBaseBody == null) {
                                return resultData;
                            }

                            //设置返回参数
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                                //获取服务器给的message提示
                                subscriber.setResultMessage(responseBaseBody.getMessage());
                                // 获取服务器给的resultCode
                                subscriber.setResultCode(responseBaseBody.getStatusCode());
                                //获取服务的访问结果类型
                                subscriber.setResultType(responseBaseBody.getType());
                            }


                            //如果访问成功就解析数据
                            if (!TextUtils.isEmpty(responseBaseBody.getType()) && responseBaseBody.getType().equals(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE)) {
                                try {
                                    resultData = (T) responseBaseBody.getMsg();
                                    if (resultData != null)
                                        LogUtil.i(resultData.toString());
                                } catch (Exception t) {
                                    Logger.i(resultType.getName() + "list解析出错 msg= " + responseBaseBody.getMsg());
                                }
                            }

                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }


    /**
     * 上传多个文件
     */
    public <T> Subscription uploadMulFiles(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, RequestBody> maps) {


        return createUploadMulFileObservable(context, subscriber, resultType, url, maps)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createUploadMulFileObservable(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, Map<String, RequestBody> maps) {
        subscriber.setContext(context);
        return apiService.uploadFiles(url, maps)
                .observeOn(Schedulers.io())
                .map(new Func1<Response<Object>, T>() {
                    @Override
                    public T call(Response<Object> response) {

                        T resultData = null;
                        //返回的服务器对象
                        if (response != null) {

                            //获取body
                            ResponseBaseBody responseBaseBody = null;
                            try {
                                responseBaseBody = new Gson().fromJson(response.body().toString(),ResponseBaseBody.class);
                            }catch (Exception e){

                            }
                            if (responseBaseBody == null) {
                                return resultData;
                            }

                            //设置返回参数
                            if (subscriber != null) {
                                // 设置上下文
                                subscriber.setContext(context);
                                //获取服务器给的message提示
                                subscriber.setResultMessage(responseBaseBody.getMessage());
                                // 获取服务器给的resultCode
                                subscriber.setResultCode(responseBaseBody.getStatusCode());
                                //获取服务的访问结果类型
                                subscriber.setResultType(responseBaseBody.getType());
                            }

                            //如果访问成功就解析数据
                            if (!TextUtils.isEmpty(responseBaseBody.getType()) && responseBaseBody.getType().equals(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE)) {
                                try {
                                    resultData = (T) responseBaseBody.getMsg();
                                    if (resultData != null)
                                        LogUtil.i(resultData.toString());
                                } catch (Exception t) {
                                    Logger.i(resultType.getName() + "list解析出错 msg= " + responseBaseBody.getMsg());
                                }
                            }

                        }
                        return resultData;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    public <T> Subscription downloadFile(final Context context, final HttpResultSubscriber subscriber, final Class<T> resultType, String url, final String filePath) {

        return createDownloadFileObservable(context,subscriber,url, filePath)
                .subscribe(subscriber);
    }

    public <T> Observable<T> createDownloadFileObservable(final Context context,final HttpResultSubscriber subscriber, String url, final String filePath) {
        subscriber.setContext(context);
        return apiService.downloadFile(url)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, T>() {
                    @Override
                    public T call(ResponseBody responseBody) {
                        try {
                            if (subscriber != null) {
                                subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                            }
                            
                            InputStream inputStream = responseBody.byteStream();

                            String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
                            File dir = new File(dirPath);
                            dir.mkdirs();
                            File file = new File(filePath);
                            file.createNewFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            int len;
                            byte[] bytes = new byte[1024];
                            while ((len = inputStream.read(bytes)) != -1) {
                                fos.write(bytes, 0, len);
                            }
                            fos.flush();
                            fos.close();
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .compose(TransformUtils.<T>defaultSchedulers());
    }

    public Subscription downloadFile2(final Context context, final HttpResultSubscriber subscriber, String url) {
        subscriber.setContext(context);
        return apiService.downloadFile(url)
                .observeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        if (subscriber != null) {
                            subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                        }
                        InputStream inputStream = responseBody.byteStream();
                        return inputStream;
                    }
                })
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(subscriber);
    }

    /**
     * zip
     */
    public <T, K, M> Subscription zip(rx.Observable<T> o1, rx.Observable<K> o2, final HttpResultSubscriber subscriber, final IParseData<T, K, M> parseDataListener) {
        return rx.Observable.zip(o1, o2, new Func2<T, K, M>() {
            @Override
            public M call(T t, K k) {
                if (subscriber != null)
                    subscriber.setResultType(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE);
                return parseDataListener.parseData(t, k);
            }
        }).subscribe(subscriber);
    }

    public interface IParseData<T, K, M> {
        M parseData(T t, K k);
    }
}
