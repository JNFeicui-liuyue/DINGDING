package com.example.dingding.connection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.dingding.ben.bean.LoginBean;
import com.example.dingding.ben.commons.Validate;
import com.example.dingding.general.Contact;
import com.example.dingding.general.General;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/31.
 */

public class LoginConnection extends Thread {

    private String data = null;
    private boolean flag = false;
    private String msg = null,
            _rev = null,
            userId = null,
            pname = null;
    private LoginBean info = null;
//    private static List<LoginItemBean> itemlist = null;
    private Handler handler, mhandler;
    private Bundle bundle;
    private Message mesg;

    public LoginConnection(String json, Handler _handler) {
        data = json;
        handler = _handler;
        start();
    }

    public void run() {
        Looper.prepare();
        bundle = new Bundle();
        mesg = new Message();
        mhandler = new Handler() {// 接收webclient回传的meissage，处理并跳转
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x1://连接异常
                        _rev = msg.obj.toString();
                        mesg.what = 1;
                        if (Validate.noNull(msg.obj + "")) {// 异常信息
                            _rev = msg.obj.toString();
                            bundle.putString("msg", _rev);// 把失败原因传回去，有利于查找导致失败的原因的代码
                        } else {// 未与后台建立任何连接导致
                            bundle.putString("msg", "建立连接失败！");// 把失败原因传回去，有利于查找导致失败的原因的代码
                        }
                        mesg.setData(bundle);
                        handler.sendMessage(mesg);
                        break;
                    case 0x2://连接成功
                        if (Validate.noNull(msg.obj + "")) {//返回了json
                            _rev = msg.obj.toString();
                            process(_rev);
                        } else {// 后台没有传值过来导致失败
                            mesg.what = 1;
                            bundle.putString("msg", "服务器传参异常！");
                            mesg.setData(bundle);
                            handler.sendMessage(mesg);
                        }
                        break;
                }
            }
        };
        if (General.IsContectNet) {
            String _path = MessageFormat.format(Contact.format, Contact.SERVER_ADDRESS);
            String _action = Contact.loginaction;
            String _json = data;
//            KL.d(_path);
//            KL.json("LoginConnection_json========", _json);
            OkHttpHelper.getInstance().postConn(_path,_action, _json,mhandler,"String");
        } else {
            _rev = "{success:true,msg:登录成功!!,userId:用户GID,pname:\"用户名:张丽  部门:教务处\",items:[" +
                    "{splx:1,sjhqlj:\"www.baidu.com1\",viewtype:4,dspsl:0},"+
                    "{splx:2,sjhqlj:\"www.baidu.com2\",viewtype:4,dspsl:0},"+
                    "{splx:3,sjhqlj:\"www.baidu.com3\",viewtype:4,dspsl:0},"+
                    "{splx:7,sjhqlj:\"www.baidu.com7\",viewtype:4,dspsl:0},"+
                    "{splx:8,sjhqlj:\"www.baidu.com8\",viewtype:4,dspsl:0},"+
                    "{splx:9,sjhqlj:\"www.baidu.com9\",viewtype:4,dspsl:0},"+
                    "{splx:20,sjhqlj:\"www.baidu.com10\",viewtype:4,dspsl:0},"+
                    "{splx:21,sjhqlj:\"www.baidu.com11\",viewtype:4,dspsl:0}]}" ;
            process(_rev);
        }
        Looper.loop();
    }

    /**
     * 上传成功返回的json串在这里处理
     *
     * @param _rev
     */
    public void process(String _rev) {
//        KL.json(_rev);
        info = new LoginBean();
//        itemlist = new ArrayList<LoginItemBean>();
        Type listType = new TypeToken<LoginBean>() {
        }.getType();
        Gson gson = new Gson();
        info = gson.fromJson(_rev, listType);
        flag = info.isSuccess();
        msg = info.getMsg();
        if (flag) {//数据获取成功
            userId = info.getUserId();
            pname = info.getPname();
            General.pname = pname;
            General.userId = userId;
//            itemlist = info.getItems();
//            General.list = info.getItems();
            mesg.what = 2;
            bundle.putString("msg", msg);
            mesg.setData(bundle);
            handler.sendMessage(mesg);
        } else {//数据获取失败
            mesg.what = 3;
            bundle.putString("msg", msg);
            mesg.setData(bundle);
            handler.sendMessage(mesg);
        }
    }

//    public static List<LoginItemBean> getlist(List<LoginItemBean> list) {
//        list = itemlist;
//        return list;
//    }
}
