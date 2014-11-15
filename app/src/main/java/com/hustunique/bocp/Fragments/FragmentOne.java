package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hustunique.bocp.Adapters.TradeRecordBaseAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentOne extends Fragment {


    protected View mMainView;
    protected static ArrayList<Map<String,String>> mlistItems;
    protected Context mContext;
    public FragmentOne() {
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.fragment_traderecord, container, false);

        mlistItems = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 20; i++) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("name", "name#" + i);
            map.put("sex", i % 2 == 0 ? "male" : "female");
            mlistItems.add(map);
        }

        ListView listView = (ListView) mMainView.findViewById(R.id.traderecordlist);
        TradeRecordBaseAdapter adapter=new TradeRecordBaseAdapter(mContext,mlistItems);
        listView.setAdapter(adapter);
        return mMainView;
    }

}
