package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hustunique.bocp.Adapters.CharityBaseAdapter;
import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentThree extends Fragment {

    private Context mcontext;
    protected static ArrayList<Map<String,String>> mlistItems;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
    }

    public FragmentThree() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_charity,null);
        ListView list=(ListView)v.findViewById(R.id.charity_listview);

        mlistItems = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 20; i++) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("name", "name#" + i);
            map.put("sex", i % 2 == 0 ? "male" : "female");
            mlistItems.add(map);
        }

        CharityBaseAdapter adapter=new CharityBaseAdapter(mcontext,mlistItems);
        list.setAdapter(adapter);
        return v;
    }

}
