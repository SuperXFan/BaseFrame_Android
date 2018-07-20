package cc.ewell.common.data.net.retrofit.subscriber;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import cc.ewell.common.R;
import cc.ewell.common.constants.CommonServerConstant;
import cc.ewell.common.utils.NetWorkUtil;
import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/7/27
 * Time:21:27
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<T> {

    private Context context;
    private String resultMessage;
    private String resultCode;
    private String resultType;
    private String errorMsg;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.getMessage());
        e.printStackTrace();
        errorMsg = e.getMessage();
        if(context != null){
            if(!NetWorkUtil.isNetWorkConnected(context)){//无网络
                errorMsg = context.getString(R.string.no_net_conneted);
            }else if(e instanceof UnknownHostException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof ConnectException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof TimeoutException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof SocketTimeoutException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }
        }
        onFailure(errorMsg);
    }

    @Override
    public void onNext(T t) {
        if (!TextUtils.isEmpty(resultCode) && resultCode.equals(CommonServerConstant.COOKIE_INVALID_CODE)) {
            onCookieInvalid(resultMessage);
            // cookie 失效统一处理, 清除Activity栈, 并且跳转到Login页面
            startToLoginActivity();
        } else if(!TextUtils.isEmpty(resultType) && resultType.equals(CommonServerConstant.SERVER_SUCCESS_RESULT_TYPE)) {
            onSuccess(t);
        } else {
            onFailure(resultMessage);
        }

    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public abstract void onSuccess(T resultData);

    public abstract void onFailure(String errMessage);

    public abstract void onCookieInvalid(String errMessage);

    public void setContext(Context context) {
        this.context = context;
    }

    private void startToLoginActivity(){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        // 设置action, 隐式跳转
        intent.setAction("cc.ewell.emdt.ui.login.loginactivity");
        context.startActivity(intent);
    }
}
