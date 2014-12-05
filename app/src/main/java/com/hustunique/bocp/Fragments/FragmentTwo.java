package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.google.gson.JsonObject;
import com.hustunique.bocp.Activities.ProductDetailActivity;
import com.hustunique.bocp.Adapters.EcomanagproAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.JSON2LIST;
import com.hustunique.bocp.Utils.views.view.XListView;
import com.mining.app.zxing.decoding.Intents;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FragmentTwo extends Fragment {

    private Context mcontext;
    private List<Map<String,Object>> mlistItems;
    private XListView mecmangpro;
    private ProgressDialog progDlg=null;
    private  EcomanagproAdapter adapter;
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==2){
               adapter=new EcomanagproAdapter(mcontext,mlistItems);
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
                if (progDlg!=null&&progDlg.isShowing()){
                    progDlg.dismiss();
                }
              // Toast.makeText(mcontext,String.valueOf(mlistItems.size())+ mlistItems.get(0).get("title").toString(),Toast.LENGTH_LONG).show();

            }else if(msg.what==3){
                if(progDlg == null || !progDlg.isShowing()){
                    progDlg = new ProgressDialog(getActivity());
                    progDlg.setMessage("正在加载，请稍候...");
                }
                progDlg.show();
            }
        }
    };


    public FragmentTwo() {
        super();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ecomanapro,null);

        mecmangpro=(XListView)v.findViewById(R.id.ecomanagprolist);
        RequestQueue queue= Volley.newRequestQueue(mcontext);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/queryproducts/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Log.i("proresponse",response);

                try {
                    //JSONObject jsonObject=new JSONObject(response);
                    //AppConstants.cid=jsonObject.getString("cid");
                    mlistItems = JSON2LIST.getList(response);
                    mlistItems.remove(0);
                   // Toast.makeText(mcontext,String.valueOf(mlistItems.size())+ mlistItems.get(0).get("title").toString(),Toast.LENGTH_LONG).show();
                    mhandler.obtainMessage(2,null).sendToTarget();
                }catch (Exception e){}
               }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                mhandler.obtainMessage(3,null).sendToTarget();
                HashMap<String,String> hashMap=new HashMap<String, String>();
                hashMap.put("pid_start","0");
                hashMap.put("pid_end","30");
                hashMap.put("flag","1");
                return hashMap;
            }
        };
        queue.add(stringRequest);

        mecmangpro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ssss","ssssss");
                Intent intent=new Intent(mcontext, ProductDetailActivity.class);
                intent.putExtra(AppConstants.STR_PROURL,mlistItems.get(position).get("href").toString());
                intent.putExtra(AppConstants.STR_PROTILE,mlistItems.get(position).get("title").toString());
                startActivity(intent);
            }
        });




        return v;
    }
    private void onLoad() {
        mecmangpro.stopRefresh();
        mecmangpro.stopLoadMore();
        mecmangpro.setRefreshTime("刚刚");
    }
    }
