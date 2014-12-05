package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.StringUtil;
import com.google.gson.JsonObject;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.JSON2LIST;
import com.hustunique.bocp.Utils.views.MaterialEditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensq on 14-11-19.
 */
public class PayActivity extends Activity {


    private FragmentManager fragmentManager;
    private Spinner cardselection;
    private String[] mlist;
    private RequestQueue queue;
    private HashMap<String,Object> mrecordlist;
    private List<Map<String,Object>> mcardlist;
    private ProgressDialog progDlg=null;
    private TextView comfimbtn;
    private MaterialEditText toacc,toamount,totag;
    String tid;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Log.i("pay_tradeinfo",msg.obj.toString());

                mrecordlist=(HashMap<String,Object>)msg.obj;
                toacc.setText(mrecordlist.get("yourcardnumber").toString());
                toacc.setEnabled(false);
                toamount.setText(mrecordlist.get("amount").toString());
                toamount.setEnabled(false);
                if(mrecordlist.get("remark")!=null){
                    totag.setText(mrecordlist.get("remark").toString());
                    totag.setEnabled(false);
                }
            }else if(msg.what==2){

                mlist=new String[mcardlist.size()];
                for(int i=0;i<mcardlist.size();i++){
                    String cardnum=mcardlist.get(i).get("accno").toString();
                    mlist[i]=mcardlist.get(i).get("alias").toString()+"(尾号"+cardnum.substring(cardnum.length()-4,cardnum.length())+")";
                }

                cardselection=(Spinner)findViewById(R.id.pay_cardselection);
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(PayActivity.this,android.R.layout.simple_list_item_1,mlist){
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
                    progDlg = new ProgressDialog(PayActivity.this);
                    progDlg.setMessage("正在获取银行卡，请稍候...");
                }
                progDlg.show();
            }else if(msg.what==4){
                comfirmTrade();
            }else if(msg.what==5){
                if(progDlg!=null&&progDlg.isShowing())
                    progDlg.dismiss();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payactivity);

        InitWidgets();

        MaterialMenuView menuView=(MaterialMenuView)findViewById(R.id.pay_back);
        menuView.setState(MaterialMenuDrawable.IconState.ARROW);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.finish();
            }
        });

        queue= Volley.newRequestQueue(PayActivity.this);

        Intent intent=getIntent();
        if(intent.getStringExtra("QR_TID")!=null){
            tid=intent.getStringExtra("QR_TID");
            requsttradeinfo(tid);
        }else{
            Toast.makeText(PayActivity.this,"开发阶段暂不支持直接转账，请扫码",Toast.LENGTH_LONG).show();
            comfimbtn.setEnabled(false);
        }

        comfimbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(progDlg!=null){
                    progDlg.show();
                }
                cardtransfer();
            }
        });
       /* mlist=new String[20];
        for(int i=0;i<mlist.length;i++){
            mlist[i]=String.valueOf(i);
        }
        cardselection=(Spinner)findViewById(R.id.pay_cardselection);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(PayActivity.this,android.R.layout.simple_list_item_1,mlist){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        cardselection.setAdapter(adapter);
            */
        appfindusrinfo();
    }

    private void requsttradeinfo(String tid){
        final String mtid=tid;
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/verifytrade/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                   // JSONObject jsonObject=new JSONObject(response);
                   Log.i("tradeinfo",response);
                    if(response.length()<=20){
                            Toast.makeText(PayActivity.this, "交易已失效", Toast.LENGTH_LONG).show();
                            comfimbtn.setEnabled(false);
                        }
                    else{
                        List<Map<String,Object>> list=JSON2LIST.getList(response);
                        String status=list.get(0).get("Status").toString();
                        if (status.compareTo("0") == 0)
                            mhandler.obtainMessage(1,list.get(1)).sendToTarget();
                    }
                    }catch (Exception e){}
                }


        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("errormsg",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String, String>();
                Log.i("paytid",mtid);
                map.put("tid",mtid);
                return map;
            }
        };

        queue.add(stringRequest);

    }

    public void cardtransfer(){
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("userid","cary32_test_391");
        // map.put("lmtamtout",)
        map.put("lmtamtout","2014092200000923");
        map.put("cardnumin","6217870700000000001");
        map.put("amount","200");
        map.put("currency","001");
        map.put("username","zhangsan");
        //post("http://openapi.boc.cn/bocop/base/asr/cardtransfer",BaseService.genPublicAsrHeader(MainActivityo.this),map,);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, Constants.httpPrefix + "/base/asr/cardtransfer",new JSONObject(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("sssstttttttt",response.toString());
                    mhandler.obtainMessage(4,null).sendToTarget();
                    //Toast.makeText(MainActivityo.this,response.getString("balance"),Toast.LENGTH_LONG).show();
                }catch (Exception e){}

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("respon",error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return BaseService.genPublicAsrHeader(PayActivity.this);
            }
        };

        queue.add(jsonObjectRequest);
    }

    private void comfirmTrade(){
        StringRequest stringRequest =new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/endtrade/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("endtrade",response);
                addNotificaction(PayActivity.this);
                mhandler.obtainMessage(5,null).sendToTarget();
                Toast.makeText(PayActivity.this,"支付完成",Toast.LENGTH_LONG).show();
                PayActivity.this.finish();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String, String>();
                map.put("tid",tid);
                return map;
            }
        };
        queue.add(stringRequest);
    }

    private void InitWidgets(){
        toamount=(MaterialEditText)findViewById(R.id.pay_amount);
        toacc=(MaterialEditText)findViewById(R.id.pay_account);
        totag=(MaterialEditText)findViewById(R.id.pay_tag);
        comfimbtn=(TextView)findViewById(R.id.pay_comfirm);
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


    private void addNotificaction(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // 创建一个Notification
        Notification notification = new Notification();
        // 设置显示在手机最上边的状态栏的图标
        notification.icon =R.drawable.applogo;
        // 当当前的notification被放到状态栏上的时候，提示内容
        notification.tickerText = "学僧记账有新通知";

        /***
         * notification.contentIntent:一个PendingIntent对象，当用户点击了状态栏上的图标时，该Intent会被触发
         * notification.contentView:我们可以不在状态栏放图标而是放一个view
         * notification.deleteIntent 当当前notification被移除时执行的intent
         * notification.vibrate 当手机震动时，震动周期设置
         */
        long[] vibreate= new long[]{0,150,150,150};
        notification.vibrate=vibreate;
        // 添加声音提示
        notification.defaults=Notification.DEFAULT_SOUND;
        // audioStreamType的值必须AudioManager中的值，代表着响铃的模式
        notification.audioStreamType= android.media.AudioManager.ADJUST_LOWER;
        notification.flags|=Notification.FLAG_AUTO_CANCEL;

        //下边的两个方式可以添加音乐
        //notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
        //notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");
        Intent intent = new Intent(context, MainActivityo.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        // 点击状态栏的图标出现的提示信息设置
        notification.setLatestEventInfo(context, "BOC+", "成功转账", pendingIntent);
        manager.notify(1, notification);

    }

}
