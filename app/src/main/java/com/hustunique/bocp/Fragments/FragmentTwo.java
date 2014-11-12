package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hustunique.bocp.Adapters.EcomanagproAdapter;
import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentTwo extends Fragment {

    private Context mcontext;
    private ArrayList<Map<String,String>> mlistItems;




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
        View v=inflater.inflate(R.layout.ecomanafragmentlayout,null);
        ListView mecmangpro=(ListView)v.findViewById(R.id.ecomanagprolist);

        mlistItems = new ArrayList<Map<String,String>>();
        for (int i = 0; i < 20; i++) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("name", "name#" + i);
            map.put("sex", i % 2 == 0 ? "male" : "female");
            mlistItems.add(map);
        }

        EcomanagproAdapter adapter=new EcomanagproAdapter(mcontext,mlistItems);
        mecmangpro.setAdapter(adapter);

        return v;

    }
}
