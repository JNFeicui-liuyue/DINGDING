package com.example.dingding.ui.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dingding.R;
import com.example.dingding.ui.activity.AddLinkmanActivity;
import com.example.dingding.ui.activity.SearchActivity;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LinkmanFragment extends Fragment {

    private ActivityUtils mActivityUtils;

    @Bind(R.id.actionbar_linkman)HMActionBar mHMActionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityUtils = new ActivityUtils(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_linkman_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        setActionBar();
    }

    /**
     * 顶部标题栏
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("联系人");
        mHMActionBar.setIvSearch(R.drawable.cspace_files_search);
        mHMActionBar.setIvSearchOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityUtils.startActivity(SearchActivity.class);
            }
        });
        mHMActionBar.setIvMore(R.drawable.help);
        mHMActionBar.setIvMoreOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityUtils.startActivity(AddLinkmanActivity.class);
            }
        });

    }
}
