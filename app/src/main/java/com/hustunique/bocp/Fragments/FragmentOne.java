package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hustunique.bocp.Adapters.TradeRecordBaseAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.views.view.XListView;


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

        RequestQueue newrequest= Volley.newRequestQueue(mContext);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/queryrecord/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("response",response);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("uid","4");
                map.put("cid","1");
                map.put("period","2");
                return map;
            }
        };
        newrequest.add(stringRequest);


       XListView listView = (XListView) mMainView.findViewById(R.id.traderecordlist);
        TradeRecordBaseAdapter adapter=new TradeRecordBaseAdapter(mContext,mlistItems);
        listView.setAdapter(adapter);
        return mMainView;
    }

}
