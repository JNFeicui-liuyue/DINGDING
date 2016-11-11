package com.example.dingding.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.example.dingding.R;
import com.example.dingding.ben.Tools.CharacterParserUtil;
import com.example.dingding.ben.Tools.CountryComparator;
import com.example.dingding.ben.Tools.CountrySortAdapter;
import com.example.dingding.ben.Tools.CountrySortModel;
import com.example.dingding.ben.Tools.GetCountryNameSort;
import com.example.dingding.ben.Tools.SideBar;
import com.example.dingding.ben.commons.ActivityUtils;
import com.example.dingding.ben.utils.HMActionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 类简要描述
 * <p/>
 * <p>
 * 类详细描述
 * </p>
 *
 * @author duanbokan
 */
public class CountryActivity extends AutoLayoutActivity {

    String TAG = "CountryActivity";

    private List<CountrySortModel> mAllCountryList;

    private EditText country_edt_search;

    private ListView country_lv_countryList;

    private ImageView country_iv_clearText;

    private CountrySortAdapter adapter;

    private SideBar sideBar;

    private TextView dialog;

    private HMActionBar mHMActionBar;

    private ActivityUtils mActivityUtils;

    private CountryComparator pinyinComparator;

    private GetCountryNameSort countryChangeUtil;

    private CharacterParserUtil characterParserUtil;

    public static Context sContext = null;
    private String[] cityName = new String[]{"中国","美国","日本","中国台湾","中国香港"};
    private String[] cityNumber = new String[]{"+86","+1","+81","+886","+852"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sContext = this.getApplicationContext();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.coogame_country);
        mActivityUtils = new ActivityUtils(this);

        initView();

        setActionBar();

        setListener();

        getCountryList();

    }

    /**
     * 顶部标题栏
     * @author wangzan
     * @date 2016.06.27.
     */
    private  void setActionBar(){
        mHMActionBar.setLeftText("选择国家和地区代码");
        mHMActionBar.setBackIcon(R.drawable.ic_arrow_back_black_24dp);
        mHMActionBar.setBackIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityUtils.startActivity(LoginActivity.class);
                finish();
            }
        });

    }

    /**
     * 初始化界面
     */
    private void initView() {
        country_edt_search = (EditText) findViewById(R.id.country_et_search);
        country_lv_countryList = (ListView) findViewById(R.id.country_lv_list);
        country_iv_clearText = (ImageView) findViewById(R.id.country_iv_cleartext);
        mHMActionBar = (HMActionBar) findViewById(R.id.actionbar_selectcountry);

        dialog = (TextView) findViewById(R.id.country_dialog);
        sideBar = (SideBar) findViewById(R.id.country_sidebar);
        sideBar.setTextView(dialog);

        mAllCountryList = new ArrayList<CountrySortModel>();
        pinyinComparator = new CountryComparator();
        countryChangeUtil = new GetCountryNameSort();
        characterParserUtil = new CharacterParserUtil();

        // 将联系人进行排序，按照A~Z的顺序
        Collections.sort(mAllCountryList, pinyinComparator);
        adapter = new CountrySortAdapter(this, mAllCountryList);
        country_lv_countryList.addHeaderView(initHeadView());
        country_lv_countryList.setAdapter(adapter);
//        setListViewHeightBasedOnChildren1(country_lv_countryList);


    }

    /****
     * 添加监听
     */
    private void setListener() {
        country_iv_clearText.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                country_edt_search.setText("");
                Collections.sort(mAllCountryList, pinyinComparator);
                adapter.updateListView(mAllCountryList);
            }
        });

        country_edt_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchContent = country_edt_search.getText().toString();
                if (searchContent.equals("")) {
                    country_iv_clearText.setVisibility(View.INVISIBLE);
                } else {
                    country_iv_clearText.setVisibility(View.VISIBLE);
                }

                if (searchContent.length() > 0) {
                    // 按照输入内容进行匹配
                    ArrayList<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil
                            .search(searchContent, mAllCountryList);

                    adapter.updateListView(fileterList);
                } else {
                    adapter.updateListView(mAllCountryList);
                }
                country_lv_countryList.setSelection(0);
            }
        });

        // 右侧sideBar监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    country_lv_countryList.setSelection(position);
                }
            }
        });

        country_lv_countryList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                String countryName = null;
                String countryNumber = null;
                String searchContent = country_edt_search.getText().toString();

                if (searchContent.length() > 0) {
                    // 按照输入内容进行匹配
                    ArrayList<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil
                            .search(searchContent, mAllCountryList);
                    countryName = fileterList.get(position).countryName;
                    countryNumber = fileterList.get(position).countryNumber;
                } else {
                    // 点击后返回
                    countryName = mAllCountryList.get(position).countryName;
                    countryNumber = mAllCountryList.get(position).countryNumber;
                }

                Intent intent = new Intent();
                intent.setClass(CountryActivity.this, LoginActivity.class);
                intent.putExtra("countryName", countryName);
                intent.putExtra("countryNumber", countryNumber);
                setResult(RESULT_OK, intent);
                Log.e(TAG, "countryName: + " + countryName + "countryNumber: " + countryNumber);
                finish();

            }
        });



    }

    /**
     * 获取国家列表
     */
    private void getCountryList() {
System.out.println("获取国家列表长度===================="+getResources().getIntArray(R.array.country_code_list_ch).length);
        String[] countryList = getResources().getStringArray(R.array.country_code_list_ch);

        for (int i = 0, length = countryList.length; i < length; i++) {
            String[] country = countryList[i].split("\\*");

            String countryName = country[0];
            String countryNumber = country[1];
            String countrySortKey = characterParserUtil.getSelling(countryName);
            CountrySortModel countrySortModel = new CountrySortModel(countryName, countryNumber,
                    countrySortKey);
            String sortLetter = countryChangeUtil.getSortLetterBySortKey(countrySortKey);
            if (sortLetter == null) {
                sortLetter = countryChangeUtil.getSortLetterBySortKey(countryName);
            }

            countrySortModel.sortLetters = sortLetter;
            mAllCountryList.add(countrySortModel);
        }

        Collections.sort(mAllCountryList, pinyinComparator);
        adapter.updateListView(mAllCountryList);
        Log.e(TAG, "changdu" + mAllCountryList.size());
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter(); //获得Adapter
        if (listAdapter == null) {  //判断是否为空
            return;
        }
        int totalHeight = 0;  //定义总高度
        //根据listAdapter.getCount()获取当前拥有多少个item项，然后进行遍历对每一个item获取高度再相加最终获得总的高度。
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //获取到list的布局属性
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listview最终高度为item的高度+分隔线的高度，这是重新设置listview的属性
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //将重新设置的params再应用到listview中
        listView.setLayoutParams(params);
    }

    /**
     * 添加头布局，即热门国家和地区
     * @return
     */
    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);
        List<Map<String ,Object>> listItems = new ArrayList<>();
        for (int i = 0; i < cityName.length; i++) {
            Map<String ,Object> listitem = new HashMap<>();
            listitem.put("cityname",cityName[i]);
            listitem.put("citynumber",cityNumber[i]);
            listItems.add(listitem);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.coogame_country_item_hot,
                new String[]{"cityname","citynumber"},
                new int[]{R.id.country_name_hot,R.id.country_number_hot});
        ListView listView = (ListView) headView.findViewById(R.id.country_lv_listhot);
        listView.setAdapter(simpleAdapter);
        setListViewHeightBasedOnChildren(listView);
        //热门国家和地区的点击事件，回传热门国家和地区的号码
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String countryNumber = null;
                String countryName = null;
                countryNumber = cityNumber[position].toString().trim();
                countryName = cityName[position].toString().trim();

                Intent intent = new Intent();
                intent.setClass(CountryActivity.this, LoginActivity.class);
                intent.putExtra("countryNumber", countryNumber);
                intent.putExtra("countryNume", countryName);
                setResult(RESULT_OK, intent);
                Log.e(TAG, "countryName: + " + countryName +  "countryNumber: " + countryNumber);
                finish();
            }
        });
        return headView;
    }
}
