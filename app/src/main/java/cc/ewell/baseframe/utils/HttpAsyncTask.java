package cc.ewell.baseframe.utils;

import android.content.Context;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fan on 2016/11/21.
 * <p>
 * 用UrlConnection 和 AsyncTask封装的网络请求工具
 */

public class HttpAsyncTask extends AsyncTask<String, Void, byte[]> {
    public interface OnPostResponseDataListener {
        void onResponseData(byte[] data);
    }
    
    public interface OnPreResponseDataListener {
        void onPreListener();
    }

    private OnPostResponseDataListener onPostResponseDataListener;
    private OnPreResponseDataListener onPreResponseDataListener;

    private Context context;

    public HttpAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onPreResponseDataListener != null) {
            onPreResponseDataListener.onPreListener();
        }
    }
    
    public void setOnPreResponseDataListener(OnPreResponseDataListener onPreResponseDataListener) {
        this.onPreResponseDataListener = onPreResponseDataListener;
    }


    @Override
    protected byte[] doInBackground(String... urls) {
        try {

            // 根据地址创建URL对象
            URL url = new URL(urls[0]);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("GET");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(12000);
            urlConnection.setConnectTimeout(12000);
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();

                return baos.toByteArray();

            } else {
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(byte[] result) {
        super.onPostExecute(result);

        if (result != null) {
            if (onPostResponseDataListener != null) {
                onPostResponseDataListener.onResponseData(result);
            }
        }
    }

    public void setOnPostResponseDataListener(
            OnPostResponseDataListener onPostResponseDataListener) {
        this.onPostResponseDataListener = onPostResponseDataListener;
    }
}
