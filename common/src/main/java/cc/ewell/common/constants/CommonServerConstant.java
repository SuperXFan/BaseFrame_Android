package cc.ewell.common.constants;

/**
 * Created by fan on 2016/12/26.
 *
 * Common中网络模块用到的常量
 */

public class CommonServerConstant {

    // 服务器的IP和端口
    public static String SERVER_IP_PORT = "http://ruijin.e-yaofang.com:9073";//开发环境  192.168.140.11:8081 外网挂了可以用内网连接

    // 服务器路径
    public static String SERVER_PATH = "/frame-tomb/";//后台服务名称
//    public static final String SERVER_PATH = "/frame-tomb-rj/";//后台服务名称(瑞金演示)

    // 文件服务器地址
    public static String FILE_SERVER_PATH = SERVER_IP_PORT + "/frame-file-server/";//文件服务

    //版本控制地址
    public static String VERSION_CONTROL_PATH = "https://ruijin.e-yaofang.com:2311/load/APPAndroid/baseframe/";//开发环境 192.168.140.11
    public static final String VERSION_CONTROL_URL = "version.json";
    public static final String BANNER_VERSION_CONTROL_URL = "banner.json";
    public static final String FORCE_VERSION_UPDATE_TYPE = "1";//强制更新
    public static final String SELF_VERSION_UPDATE_TYPE = "2";//手动更新
    
    //服务访问设置
    public static boolean ISHTTPS = false;//是否是https服务
    public static final int DEFAULT_TIMEOUT = 12;//连接服务的超时时间（秒）

    //访问服务器成功的标志
    public static String SERVER_SUCCESS_RESULT_TYPE = "success";

    // Cookie失效的code
    public static String COOKIE_INVALID_CODE = "110007";

    // 请求加密用到的参数
    public static final String KEY = "AyHktHydGtFiPJytendierUtrDkSiell";
    public static final String UUID = "UUID";
    public static final String AUTH = "AUTH";
    public static final String SSID = "SSID";
    public static final String SEQ = "SEQ";
    public static final String SIGN = "SIGN";
}
