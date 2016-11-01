package com.example.dingding.ben.bean;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：LoginBean.class
 * 描述：登录JavaBean
 * Created by 刘帅 on 2016/9/21.
 */
public class LoginBean implements Serializable{
    public boolean success;
    public String msg = null,
            userId = null,
            pname = null,
            uname = null;
//    public List<LoginItemBean> items = new ArrayList<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

//    public List<LoginItemBean> getItems() {
//        return items;
//    }
//
//    public void setItems(List<LoginItemBean> items) {
//        this.items = items;
//    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", userId='" + userId + '\'' +
                ", pname='" + pname + '\'' +
                ", uname='" + uname + '\'' +
//                ", items=" + items +
                '}';
    }
}
