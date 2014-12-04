package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.Fragments.QRcodeFragment;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.mining.app.zxing.encode.EncodeQR;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensq on 14-11-30.
 */
public class NewTradActivity extends Activity {

    private TextView comfirm;
    private Bitmap mbitmap;
    private RequestQueue queue;
    private ArrayList<Map<String,String>> mcardlist;
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
              //  queue.stop();
                posttradeinfo(msg.obj.toString());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newtrade);
        queue=Volley.newRequestQueue(NewTradActivity.this);

        mcardlist=new ArrayList<Map<String, String>>();

        if(mcardlist.size()==0){
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("text","还没检测到银行卡");
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }
        comfirm=(TextView)findViewById(R.id.newtradecomfirm);
        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/requesttid/",new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         mhandler.obtainMessage(1,response).sendToTarget();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("uid", AppConstants.UID);
                        hashMap.put("cid",AppConstants.cid);
                        return hashMap;
                    }
                };
                queue.add(stringRequest);
                /*QRcodeFragment qRcodeFragment=new QRcodeFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                transaction.replace(R.id.newtradeinfo,qRcodeFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

    }

   private void posttradeinfo(String response){
        try {
            JSONObject Jobj = new JSONObject(response);
            AppConstants.tid=Jobj.getString("tid");
            Log.i("ssssstid",AppConstants.tid);
            mbitmap=EncodeQR.Create2DCode(AppConstants.tid);

            StringRequest strrequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/storerecord/",new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject Jobj = new JSONObject(response);
                        Log.i("ssssssssssss",response+"_____"+Jobj.getString("Stutas"));

                        if(Jobj.getString("Stutas").compareTo("0")==0){
                            QRcodeFragment qRcodeFragment=new QRcodeFragment();
                            FragmentManager fragmentManager=getFragmentManager();
                            FragmentTransaction transaction=fragmentManager.beginTransaction();
                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            transaction.replace(R.id.newtradeinfo,qRcodeFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            qRcodeFragment.setimg(mbitmap);
                        }
                    }catch (Exception e){}
                }
            },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Log.i("stid",AppConstants.tid);
                    HashMap<String,String> map=new HashMap<String, String>();
                    map.put("uid",AppConstants.UID);
                    map.put("cid","01");
                    map.put("tid",AppConstants.tid);
                    map.put("amount","300");
                    map.put("yourcardname","testmain");
                    map.put("yourcardnumber","6217870700000000001");
                    map.put("in_out","1");
                    map.put("way","1");
                    Log.i("stid",map.toString());
                    return map;
                }
            };
            queue.add(strrequest);

        }catch (Exception e){}


      // RequestQueue newqueue=Volley.newRequestQueue(NewTradActivity.this);
      // newqueue.add(strrequest);
    }

    public Bitmap getbitmap(){
        return mbitmap;
    }

    private void httpposthelper (String response) {
      /*  try {
            JSONObject Jobj = new JSONObject(response);
            AppConstants.tid=Jobj.getString("tid");
            Log.i("ssssstid",AppConstants.tid);
        }catch (Exception e){}*/
        String uriAPI = "http://104.160.39.34:8000/storerecord/";
        /*建立HTTP Post联机*/
        HttpPost httpRequest = new HttpPost(uriAPI);
        /*
         * Post运作传送变量必须用NameValuePair[]数组储存
        */

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("uid",AppConstants.UID));
        params.add(new BasicNameValuePair("cid",AppConstants.cid));
        params.add(new BasicNameValuePair("tid",AppConstants.tid));
        params.add(new BasicNameValuePair("amount","300"));
        params.add(new BasicNameValuePair("yourcardname","testmain"));
        params.add(new BasicNameValuePair("yourcardnumber","6217870700000000001"));
        params.add(new BasicNameValuePair("in_out","in"));
        params.add(new BasicNameValuePair("way","1"));

        try {
          /*发出HTTP request*/
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          /*取得HTTP response*/
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
            Log.i("sssssssssssss","ssssssssssssss");
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
            /*取出响应字符串*/
                String strResult = EntityUtils.toString(httpResponse.getEntity());
                Log.i("sssssssssssss",strResult);
            }
            } catch (Exception e) {
        }
    }
}
