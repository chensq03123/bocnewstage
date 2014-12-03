package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.hustunique.bocp.Adapters.EcomanagproAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
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


        RequestQueue queue= Volley.newRequestQueue(mcontext);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/queryproducts/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("proresponse",response);

                try {
                    //JSONObject jsonObject=new JSONObject(response);
                    //AppConstants.cid=jsonObject.getString("cid");
                    List<Map<String,Object>> list=getList(response);
                    Log.i("proresponse",String.valueOf(list.size())+list.get(0).toString());

                }catch (Exception e){}
               }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<String, String>();
                hashMap.put("pid_start","0");
                hashMap.put("pid_end","30");
                hashMap.put("flag","1");
                return hashMap;
            }
        };

        queue.add(stringRequest);
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

    public static Map<String, Object> getMap(String jsonString)
    {
        JSONObject jsonObject;
        try
        {
            jsonObject = new JSONObject(jsonString);   @SuppressWarnings("unchecked")
        Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext())
            {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把json 转换为 ArrayList 形式
     * @return
     */
    public static List<Map<String, Object>> getList(String jsonString)
    {
        List<Map<String, Object>> list = null;
        try
        {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public String tozhCN(String unicode) {
        StringBuffer gbk = new StringBuffer();
        String[] hex = unicode.split("////u");  // 分割让我想了半天！！不是"//u"，而是 "////u"
        for (int i = 1; i < hex.length; i++) {          // 注意要从 1 开始，而不是从0开始。第一个是空。
            int data = Integer.parseInt(hex[i], 16);  //  将16进制数转换为 10进制的数据。
            gbk.append((char) data);  //  强制转换为char类型就是我们的中文字符了。
        }
        return gbk.toString();
    }

    }
