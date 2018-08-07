package cc.ewell.common.data.net.retrofit.service;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by SuperFan on 2016/8/30.
 */
public interface RxBaseService {

    /**
     * @GET @POST 等方法的申明
     * 例如：
     *      @GET("/NewIndex2013/AjaxGetList.ashx?partID=394")
            Observable<List<News>> getNcuNews(@Query("pagesize") int howmany, @Query("pageindex") int fromwhere);

     * 该子类的实例由RxServiceFactory生成
     *
     */

    public static final String Base_URL = "";

    //普通get请求
    @GET
    Observable<Response<Object>> executeGet(
            @Url String url,
            @QueryMap Map<String, String> maps);


    //普通post请求
    @FormUrlEncoded
    @POST
    Observable<Response<Object>> executePost(
            @Url String url,
            @FieldMap Map<String, String> maps);

    //上传单个文件
    @Multipart
    @POST
    Observable<Response<Object>> upLoadFile(
            @Url String url,
            @Part("image\\\"; filename=\\\"image.jpg") RequestBody avatar);


    //上传多个文件
    @Multipart
    @POST
    Observable<Response<Object>> uploadFiles(
            @Url String url,
            @PartMap() Map<String, RequestBody> maps);

    //下载文件
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);


}
