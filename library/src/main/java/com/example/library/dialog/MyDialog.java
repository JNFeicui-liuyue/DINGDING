package com.example.library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MyDialog extends Dialog {
    public MyDialog(Context context,int width, int height, View layout, int style) {
        this(context,width,height,layout, style,0);
    }
    public MyDialog(Context context,View layout, int style) {
        this(context,layout,style,0);
    }
    
    private MyDialog(Context context, View layout, int style,int defult) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
//     MyDialog(Context context, View layout, int style,int defult,int i) {
//        super(context, style);
//        setContentView(layout);
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.gravity = Gravity.TOP;
//        params.y=55;
//        window.setAttributes(params);
//    }
    private MyDialog(Context context,int width, int height, View layout, int style,int defult) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}