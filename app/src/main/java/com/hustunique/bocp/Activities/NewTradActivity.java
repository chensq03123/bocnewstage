package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.StringUtil;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.Fragments.QRcodeFragment;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.JSON2LIST;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chensq on 14-11-30.
 */
public class NewTradActivity extends Activity {

    private TextView comfirm;
    private Bitmap mbitmap;
    private RequestQueue queue;
    private List<Map<String,Object>> mcardlist;
    private Spinner cardselection;
    private ProgressDialog progDlg=null;
    private String[] mlist;
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
              //  queue.stop();
                posttradeinfo(msg.obj.toString());

            }else if(msg.what==2){

                mlist=new String[mcardlist.size()];
                for(int i=0;i<mcardlist.size();i++){
                    String cardnum=mcardlist.get(i).get("accno").toString();
                    mlist[i]=mcardlist.get(i).get("alias").toString()+" ( 尾号"+cardnum.substring(cardnum.length()-4,cardnum.length())+" )";
                }

                cardselection=(Spinner)findViewById(R.id.newtrade_cardspinner);
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(NewTradActivity.this,android.R.layout.simple_list_item_1,mlist){
                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        return super.getDropDownView(position, convertView, parent);
                    }
                };
                cardselection.setAdapter(adapter);
                if(progDlg!=null&&progDlg.isShowing())
                    progDlg.dismiss();
            }else if(msg.what==3){
                if(progDlg == null || !progDlg.isShowing()){
                    progDlg = new ProgressDialog(NewTradActivity.this);
                    progDlg.setMessage("正在获取银行卡，请稍候...");
                }
                progDlg.show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_newtrade);
        queue=Volley.newRequestQueue(NewTradActivity.this);

        mcardlist=new ArrayList<Map<String,Object>>();

        if(mcardlist.size()==0){
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("text","还没检测到银行卡");
            mcardlist.add(map);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }
        appfindusrinfo();
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
            }
        });

        MaterialMenuView materialMenuView=(MaterialMenuView)findViewById(R.id.newtrade_back);
        materialMenuView.setState(MaterialMenuDrawable.IconState.ARROW);
        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewTradActivity.this.finish();
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
                        Log.i("ssssssssssss",response);

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
                    map.put("remark","test");
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

    private void setCards(String[] list){
        cardselection=(Spinner)findViewById(R.id.newtrade_cardspinner);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(NewTradActivity.this,android.R.layout.simple_list_item_1,list){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        cardselection.setAdapter(adapter);
    }

    public void appfindusrinfo(){
        //RequestQueue requestQueue=Volley.newRequestQueue(AccountSettingActivity.this);
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("userid", AppConstants.username);
        map.put("accno","");
        map.put("alias","");
        map.put("trntyp","");
        map.put("ifncal","");
        map.put("pageno","1");
        //  map.put("limitamt","2014091500000615");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Constants.httpPrefix+"/debit/appfindusrinfo",new JSONObject(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Log.i("sssstrade",response.toString());
                    mcardlist=JSON2LIST.getList(response.getString("saplist"));
                    mhandler.obtainMessage(2,null).sendToTarget();
                    Log.i("sssstrade",mcardlist.get(0).toString());
                    // Toast.makeText(AccountSettingActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                }catch (Exception e){}

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ssssserror",error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                mhandler.obtainMessage(3,null).sendToTarget();
                LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
                map.put("clentid", AppInfo.getAppKeyValue());

                Oauth2AccessToken accessToken = AccessTokenKeeper
                        .readAccessToken(BOCOPPayApi.getContext());
                String mUserId = accessToken.getUserId();
                String token = accessToken.getToken();
//		String token = "87a3ff45-24e0-4758-b7d9-c72e5283569d";
                if (!StringUtil.isNullOrEmpty(mUserId)) {
                    map.put("userid", mUserId);
                }

                if (!StringUtil.isNullOrEmpty(token)) {
                    map.put("acton", token);
                }

                map.put("chnflg", "1");

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMDD");
                // 获取当前时间
                String nowData = format.format(new Date(System.currentTimeMillis()));
                map.put("trandt", nowData);

                SimpleDateFormat formatTime = new SimpleDateFormat("HHmmss");
                // 获取当前时间
                String nowTime = formatTime
                        .format(new Date(System.currentTimeMillis()));
                map.put("trantm", nowTime);
                return map;
            }
        };

        queue.add(jsonObjectRequest);
    }
}
