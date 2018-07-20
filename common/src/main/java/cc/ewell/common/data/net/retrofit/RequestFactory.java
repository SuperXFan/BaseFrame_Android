package cc.ewell.common.data.net.retrofit;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cc.ewell.common.utils.EncryptionUtil;

/**
 * 对请求的数据做处理
 * Created by SuperFan on 2016/8/31.
 */
public class RequestFactory {

    /**
     * 进行数据加密
     * @param request
     * @return
     */
    public static  Map<String,String> creatMD5RequestData(Map<String,String> request){
        Map<String,String> result = null;
        if(request != null){
            result = new HashMap<>();
            Set<String> keys = request.keySet();
            for (String key : keys) {
                result.put(key, EncryptionUtil.getMD5(request.get(key)));
            }
        }

        return result;
    }
}
