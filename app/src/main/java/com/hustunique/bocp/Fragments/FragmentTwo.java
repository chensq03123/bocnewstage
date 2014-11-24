package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.hustunique.bocp.Adapters.EcomanagproAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.views.view.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentTwo extends Fragment {

    private Context mcontext;
    private ArrayList<Map<String,String>> mlistItems;
    XListView mecmangpro;
    private Handler mhandler;


    public FragmentTwo() {
        super();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ecomanapro,null);
         mecmangpro=(XListView)v.findViewById(R.id.ecomanagprolist);

        mlistItems = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 20; i++) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("name", "name#" + i);
            map.put("sex", i % 2 == 0 ? "male" : "female");
            mlistItems.add(map);
        }

        mhandler=new Handler();
        EcomanagproAdapter adapter=new EcomanagproAdapter(mcontext,mlistItems);
        mecmangpro.setAdapter(adapter);
        mecmangpro.setPullLoadEnable(true);
        mecmangpro.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                    }
                }, 2000);
            }
        });

        return v;
    }


    private void onLoad() {
        mecmangpro.stopRefresh();
        mecmangpro.stopLoadMore();
        mecmangpro.setRefreshTime("刚刚");
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
