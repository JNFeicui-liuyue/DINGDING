package com.example.dingding.ben.bean;

/**
 * Created by Administrator on 2016/11/7.
 */

public class WorkModel {

    public String name;
    public int EditText;

    public WorkModel(String name, int editText) {
        this.name = name;
        EditText = editText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEditText() {
        return EditText;
    }

    public void setEditText(int editText) {
        EditText = editText;
    }
}
