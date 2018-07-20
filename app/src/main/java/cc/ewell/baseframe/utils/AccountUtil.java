package cc.ewell.baseframe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Set;

import cc.ewell.baseframe.constants.Constant;

/**
 * 用户信息工具类
 */
public class AccountUtil {

    // 当前是专家模式(expert_mode)还是秘书模式(secretary_mode);
    private static final String MODE = "mode";

    private static final String CLIENT_ID = "cleint_id";//个推获取的设备CID

    private static final String USER_CODE = "user_code";//用户的code
//    private static final String TOKEN = "token";
    private static final String WORKER_NUMBER = "worker_number";//用户的工号
    private static final String USER_TYPE = "user_type";//用户的类型
    private static final String USER_PASS = "user_passward";//用户的加密登录密码
    private static final String NICK_NAME = "user_nick_name";//用户的昵称
    private static final String USER_STATE = "user_state";//用户的状态
    private static final String HOSPITAL_CODE = "hospital_code";//医院code
    private static final String HOSPITAL_NAME = "hospital_name"; // 医院名称
    private static final String DEFAULTDEPTNAME = "defaultdeptName";//科室
    private static final String DEFAULTDEPTCODE = "defaultdeptCode";//科室code

    private static final String CREATE_TIME = "create_time";//创建时间
    private static final String LAST_LOGIN_TIME = "last_login_time";//最后登录时间
    private static final String PHONE = "phone"; // 用户的电话
    private static final String SEX = "sex"; /// 用户性别 性别:"301":男 "302":女
    private static final String USERAVR = "userAvr"; // 用户头像地址
    private static final String NAME = "name";
    private static final String MARK = "mark";
    private static final String USER_INTRODUCEVIEW = "user_introduceView";
    private static final String USER_SKILL = "user_skill";

    private static final String GETUI_CID = "getui_cid";
    private static final String IS_SET_CID = "is_set_cid";

    private static final String USER_NIM_ACCID = "user_nim_accid";//云信的id
    private static final String USER_NIM_ACCTOKEN = "user_nim_acctoken";//云信的token

    private static final String USER_AUTHORITY = "user_authority"; // 用户权限
    private static final String IS_LOGIN_SUCCESS = "is_login_success"; // 是否登录成功

    private static final String FINGERPRINT_ACCOUNT = "fingerprint_account"; // 开启指纹的账户名


//    /**
//     * 设置个推的clientId
//     * @param context
//     * @param clientId
//     */
//    public static void setClientId(Context context, String clientId){
//        if (TextUtils.isEmpty(clientId)){
////            PushManager.getInstance().initialize(context.getApplicationContext());
////            PushManager.getInstance().getClientid(context);
//            return;
//        }
//        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString(CLIENT_ID, clientId);
//        edit.apply();
//    }
//    /**
//     * 获取clientId
//     * @param context
//     * @return
//     */
//    public static String getClientId(Context context){
//        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        String cid = sp.getString(CLIENT_ID, "");
//        if(TextUtils.isEmpty(cid)){
////            PushManager.getInstance().initialize(context.getApplicationContext());
////            PushManager.getInstance().getClientid(context);
//        }
//        return cid;
//    }

    /**
     * 将开启指纹的账户存在SharedPreference中
     */
    public static void setFingerprintAccount(Context context, String fingerprintAccount) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(FINGERPRINT_ACCOUNT, fingerprintAccount).apply();
    }

    /**
     * 从SharedPreference中获取开启指纹的账户
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getFingerprintAccount(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(FINGERPRINT_ACCOUNT, "");
    }

    /**
     * 将医院code存在SharedPreference中
     */
    public static void setHospitalCode(Context context, String hospitalCode) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(HOSPITAL_CODE, hospitalCode).apply();
    }

    /**
     * 从SharedPreference中获取医院code
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getHospitalCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(HOSPITAL_CODE, "");
    }

    /**
     * 将用户code存在SharedPreference中
     */
    public static void setUserCode(Context context, String userCode) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_CODE, userCode).apply();
    }

    /**
     * 从SharedPreference中获取用户code
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_CODE, "");
    }

    /**
     * 将用户工号存在SharedPreference中
     */
    public static void setUserWorkerNum(Context context, String workerNum) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(WORKER_NUMBER, workerNum).apply();
    }

    /**
     * 从SharedPreference中获取用户工号
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserWorkerNum(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(WORKER_NUMBER, "");
    }

    /**
     * 将用户类型存在SharedPreference中
     */
    public static void setUserType(Context context, String userType) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_TYPE, userType).apply();
    }

    /**
     * 从SharedPreference中获取用户类型
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserType(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_TYPE, "");
    }

    /**
     * 将用户加密密码存在SharedPreference中
     */
    public static void setUserPassward(Context context, String userPassward) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_PASS, userPassward).apply();
    }

    /**
     * 从SharedPreference中获取用户加密密码
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserPassward(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_PASS, "");
    }

    /**
     * 将用户昵称存在SharedPreference中
     */
    public static void setUserNickName(Context context, String userNickName) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(NICK_NAME, userNickName).apply();
    }

    /**
     * 从SharedPreference中获取用户昵称
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserNickName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(NICK_NAME, "");
    }

    /**
     * 将用户状态存在SharedPreference中
     */
    public static void setUserState(Context context, String userState) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_STATE, userState).apply();
    }

    /**
     * 从SharedPreference中获取用户状态
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserState(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_STATE, "");
    }

    /**
     * 将用户创建时间存在SharedPreference中
     */
    public static void setCreateTime(Context context, String createTime) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(CREATE_TIME, createTime).apply();
    }

    /**
     * 从SharedPreference中获取用户创建时间
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getCreateTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(CREATE_TIME, "");
    }

    /**
     * 将用户最后登录时间存在SharedPreference中
     */
    public static void setLastLoginTime(Context context, String lastLoginTime) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(LAST_LOGIN_TIME, lastLoginTime).apply();
    }

    /**
     * 从SharedPreference中获取用户最后登录时间
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getLastLoginTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(LAST_LOGIN_TIME, "");
    }

    /**
     * 从SharedPreference中获取用户的电话
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(PHONE, "");
    }

    /**
     * 将登录用户的电话存在SharedPreference中
     *
     * @param context
     * @param phone
     */
    public static void setPhone(Context context, String phone) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(PHONE, phone).apply();
    }


    /**
     * 从SharedPreference中获取用户的性别
     *
     * @param context
     * @return
     */
    public static String getSex(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(SEX, "");
    }

    /**
     * 将登录用户的性别存在SharedPreference中
     *
     * @param context
     * @param sex
     */
    public static void setSex(Context context, String sex) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(SEX, sex).apply();
    }

    /**
     * 从SharedPreference中获取用户的头像地址
     *
     * @param context
     * @return
     */
    public static String getUserAvr(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USERAVR, "");
    }

    /**
     * 将登录用户的头像地址存在SharedPreference中
     *
     * @param context
     * @param userAvr
     */
    public static void setUserAvr(Context context, String userAvr) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USERAVR, userAvr).apply();
    }

    /**
     * 从SharedPreference中获取用户的名字
     *
     * @param context
     * @return
     */
    public static String getName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(NAME, "");
    }

    /**
     * 将登录用户的头像地址存在SharedPreference中
     *
     * @param context
     * @param name
     */
    public static void setName(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(NAME, name).apply();
    }


    /**
     * 从SharedPreference中获取用户的医院
     *
     * @param context
     * @return
     */
    public static String getHospitalName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(HOSPITAL_NAME, "");
    }

    /**
     * 将登录用户的医院名称地址存在SharedPreference中
     *
     * @param context
     * @param name
     */
    public static void setHospitalName(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(HOSPITAL_NAME, name).apply();
    }

    /**
     * 将用户科室存在SharedPreference中
     */
    public static void setdefaultdeptName(Context context, String defaultdeptName) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(DEFAULTDEPTNAME, defaultdeptName).apply();
    }

    /**
     * 从SharedPreference中获取用户科室
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getdefaultdeptName(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(DEFAULTDEPTNAME, "");
    }

    /**
     * 将用户最后登录时间存在SharedPreference中
     */
    public static void setdefaultdeptCode(Context context, String defaultdeptName) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(DEFAULTDEPTCODE, defaultdeptName).apply();
    }

    /**
     * 从SharedPreference中获取用户最后登录时间
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getdefaultdeptCode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(DEFAULTDEPTCODE, "");
    }

    /**
     * 设置当前是否是专家模式
     */
    public static void setIsExpertMode(Context context, boolean isExpert) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(MODE, isExpert).apply();
    }

    /**
     * 当前是否是专家模式, 默认是专家模式
     */
    public static boolean getIsExpertMode(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(MODE, true);
    }

//    public static void setToken(Context context, String token) {
//        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        sp.edit().putString(TOKEN, token).apply();
//    }
//
//    public static String getToken(Context context) {
//        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        return sp.getString(TOKEN, "");
//    }

    /**
     * 设置mark
     */
    public static void setMark(Context context, String mark) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(MARK, mark).apply();
    }

    /**
     * 获取mark
     */
    public static String getMark(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(MARK, "");
    }

    /**
     * 设置个人简介
     */
    public static void setUserIntroduceview(Context context, String userIntroduce) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_INTRODUCEVIEW, userIntroduce).apply();
    }

    /**
     * 获取个人简介
     */
    public static String getUserIntroduceview(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_INTRODUCEVIEW, "");
    }

    /**
     * 设置个人擅长
     */
    public static void setUserSkill(Context context, String userSkill) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_SKILL, userSkill).apply();
    }

    /**
     * 获取个人擅长
     */
    public static String getUserSkill(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_SKILL, "");
    }

    /**
     * 设置个推cid
     */
    public static void setGetuiCid(Context context, String cid) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(GETUI_CID, cid).apply();
    }

    /**
     * 获取cid
     */
    public static String getGetuiCid(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(GETUI_CID, "");
    }

    /**
     * 设置个推cid
     */
    public static void setIsSetCid(Context context, boolean isSet) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(IS_SET_CID, isSet).apply();
    }

    /**
     * 获取是否向服务设置了cid
     */
    public static boolean getIsSetCid(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(IS_SET_CID, false);
    }

    /**
     * 将nim accid存在SharedPreference中
     */
    public static void setUserNimAccid(Context context, String nimAccid) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_NIM_ACCID, nimAccid).apply();
    }

    /**
     * 从SharedPreference中获取nim accid
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserNimAccid(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_NIM_ACCID, "");
    }

    /**
     * 将accToken存在SharedPreference中
     */
    public static void setUserNimAcctoken(Context context, String nimAcctoken) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(USER_NIM_ACCTOKEN, nimAcctoken).apply();
    }

    /**
     * 从SharedPreference中获取accToken
     *
     * @param context
     * @return 如果没有, 则返回""
     */
    public static String getUserNimAcctoken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_NIM_ACCTOKEN, "");
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isUserLogin(Context context) {
        if (!TextUtils.isEmpty(AccountUtil.getUserCode(context)) && AccountUtil.getIsLoginSuccess(context))
            return true;
        return false;
    }

    /**
     * 设置用户权限
     */
    public static void setUserAuthority(Context context, Set<String> authoritys) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putStringSet(USER_AUTHORITY, authoritys).apply();
    }

    /**
     * 获取用户权限
     */
    public static Set<String> getUserAuthority(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getStringSet(USER_AUTHORITY, null);
    }

    /**
     * 设置是否登录成功
     */
    public static void setIsLoginSuccess(Context context, boolean isLoginSuccess){
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(IS_LOGIN_SUCCESS, isLoginSuccess).apply();
    }

    /**
     * 是否登录成功
     * @param context
     * @return
     */
    public static boolean getIsLoginSuccess(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Constant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(IS_LOGIN_SUCCESS, false);
    }
}
