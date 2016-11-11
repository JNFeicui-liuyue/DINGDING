package com.example.dingding.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dingding.R;
import com.example.dingding.ben.bean.WorkModel;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public class WorkGridViewAdapter extends BaseAdapter{
    private List<WorkModel> mDatas;
    private LayoutInflater mInflater;

    /**
     * 页数下标，从0开始（当前是第几页）
     */
    private int curIndex;

    /**
     *每一页显示的个数
     */
    private int pageSize;

    public WorkGridViewAdapter(Context context, List<WorkModel> datas, int curIndex, int pageSize) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize，
     * 如果不够，则有几项返回几，(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i + curIndex * pageSize);
    }

    @Override
    public long getItemId(int i) {
        return i + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gridview,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.et = (ImageView) convertView.findViewById(R.id.editText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        int pos = position + curIndex * pageSize;
        viewHolder.tv.setText(mDatas.get(pos).name);
        viewHolder.et.setImageResource(mDatas.get(pos).EditText);
        return convertView;
    }

    class ViewHolder {
        public TextView tv;
        public ImageView et;
    }
}
