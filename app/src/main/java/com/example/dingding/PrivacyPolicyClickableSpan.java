package com.example.dingding;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.dingding.activity.PrivacyPolicyActivity;

/**
 * Created by Administrator on 2016/10/25.
 */

public class PrivacyPolicyClickableSpan extends ClickableSpan {

    String string;
    Context context;

    public PrivacyPolicyClickableSpan(String str,Context context) {
        super();
        this.string = str;
        this.context = context;

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(context.getResources().getColor(R.color.light_blue));
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(context, PrivacyPolicyActivity.class);
        context.startActivity(intent);
    }
}
