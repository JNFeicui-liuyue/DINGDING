package com.example.dingding.ui.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.dingding.R;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ui.fragment.LinkmanFragment;
import com.example.dingding.ui.fragment.MessageFragment;
import com.example.dingding.ui.fragment.MineFragment;
import com.example.dingding.ui.fragment.WorkFragment;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AutoLayoutActivity {

    private ActivityUtils mActivityUtils;
    private List<Map<String ,Object>> data_list;
    private SimpleAdapter mSimpleAdapter;

    private int[] imageIds = new int[]{R.drawable.search_icon_home_person,R.drawable.search_icon_home_group,
                                        R.drawable.search_tip_icon_chat,R.drawable.search_icon_home_ding,
                                        R.drawable.session_msg_empty,R.drawable.search_icon_home_space,
                                        R.drawable.search_tip_icon_chat,R.drawable.search_icon_home_app};

    private String [] name = {"联系人","群组","聊天记录","DING","功能","钉盘","钉邮","微应用"};

    @Bind(R.id.grid01)GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mActivityUtils = new ActivityUtils(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        //新建list
        data_list = new ArrayList<Map<String, Object>>();
        //获取数据
        getData();
        //新建适配器
        String[] from = {"image","text"};
        int[] to = {R.id.image,R.id.text};
        mSimpleAdapter = new SimpleAdapter(this,data_list,R.layout.gridview_item,from,to);
        //适配适配器
        mGridView.setAdapter(mSimpleAdapter);
    }

    public List<Map<String ,Object>> getData(){
        for (int i = 0; i < imageIds.length; i++) {
            Map<String ,Object> map = new HashMap<>();
            map.put("image",imageIds[i]);
            map.put("text",name[i]);
            data_list.add(map);
        }
        return data_list;
    }

    /**
     * 回退图标的监听事件
     */
    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }
}
