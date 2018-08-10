package cc.ewell.login.model;

import java.io.Serializable;

/**
 * Created by SuperFan on 2016/9/10.
 */
public class LoginInfo {


    /**
     * createTime : 2016-08-26 11:58:25
     * hosCode : 1
     * userCode : 0810
     * accid : 3f031ad8a1fbda0295198608a1ef5043
     * eq_code : xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
     * state : 102
     * defaultdeptCode : 1
     * mark :
     * userType : 201
     * defaultdeptName : 全科
     * workernumber : 3132
     * lastLogin : 2016-11-21 20:39:49
     * token : 0fc9bc666935a26b7606d9743581e61f
     * nickName : nick
     * userAvr : 12121212\45.jpg
     * userPass : 4gCYkBOaET156EKYiAbPX/CMDTyN98oL69PFAhKMNVw=
     */

    private String createTime;
    private String hosCode;
    private String userCode; //UUID
    private String accid;
    private String eq_code;
    private String state;
    private String defaultdeptCode;
    private String mark;
    private String userType;
    private String defaultdeptName;
    private String workernumber;
    private String lastLogin;
    private String token; // 服务器的token
    private String nickName;
    private String userAvr;
    private String userPass;
    private String acctoken; // 云信的token
    private String name;
    private String userModel;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHosCode() {
        return hosCode;
    }

    public void setHosCode(String hosCode) {
        this.hosCode = hosCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getEq_code() {
        return eq_code;
    }

    public void setEq_code(String eq_code) {
        this.eq_code = eq_code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDefaultdeptCode() {
        return defaultdeptCode;
    }

    public void setDefaultdeptCode(String defaultdeptCode) {
        this.defaultdeptCode = defaultdeptCode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDefaultdeptName() {
        return defaultdeptName;
    }

    public void setDefaultdeptName(String defaultdeptName) {
        this.defaultdeptName = defaultdeptName;
    }

    public String getWorkernumber() {
        return workernumber;
    }

    public void setWorkernumber(String workernumber) {
        this.workernumber = workernumber;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserAvr() {
        return userAvr;
    }

    public void setUserAvr(String userAvr) {
        this.userAvr = userAvr;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getAcctoken() {
        return acctoken;
    }

    public void setAcctoken(String acctoken) {
        this.acctoken = acctoken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserModel() {
        return userModel;
    }

    public void setUserModel(String userModel) {
        this.userModel = userModel;
    }
}
