package cc.ewell.common.data.net.retrofit;

import java.io.Serializable;

/**
 * Created by SuperFan on 2016/8/31.
 *
 * {
 "dialogid": " ",    无实际意义
 "type": "success",  success成功 alert失败
 "msg": "",          存放返回消息内容
 "size": "1",        返回msg长度
 "typeName": "成功",
 "message": "刷新成功", 提示内容
 "statusCode": "000006", 消息编码
 "closeCurrent": " ",
 "modelName": "公共模块", 所属模块
 "forward": " ",
 "forwardConfirm": " ",
 "datatype": "singleton",  singleton 单例 array 数组
 "success": false,
 "tabid": " "
 }
 */
public class ResponseBaseBody implements Serializable{

    private String dialogid;
    private String type;
    private String msg;
    private String size;
    private String typeName;
    private String message;
    private String statusCode;
    private String closeCurrent;
    private String modelName;
    private String forward;
    private String forwardConfirm;
    private String datatype;
    private boolean success;
    private String tabid;

    public String getDialogid() {
        return dialogid;
    }

    public void setDialogid(String dialogid) {
        this.dialogid = dialogid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCloseCurrent() {
        return closeCurrent;
    }

    public void setCloseCurrent(String closeCurrent) {
        this.closeCurrent = closeCurrent;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getForwardConfirm() {
        return forwardConfirm;
    }

    public void setForwardConfirm(String forwardConfirm) {
        this.forwardConfirm = forwardConfirm;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTabid() {
        return tabid;
    }

    public void setTabid(String tabid) {
        this.tabid = tabid;
    }
}
