package com.example.dingding.ben.bean;


import java.io.Serializable;

/**
 * 类名：LoginUpBean.class
 * 描述：登录JavaBean
 * Created by 刘帅 on 2016/9/21.
 */
public class LoginUpBean implements Serializable {
    public String account = null,//用户名
            upwd = null,//用户密码
            userId = null;//用户ID

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginUpBean{" +
                "uname='" + account + '\'' +
                ", upwd='" + upwd + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
