package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.fund.Fund900Response;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.http.AsyncHttpClient;
import com.boc.bocop.sdk.http.AsyncHttpRequest;
import com.boc.bocop.sdk.http.AsyncResponseHandler;
import com.boc.bocop.sdk.http.JsonResponseListenerAdapterHandler;
import com.boc.bocop.sdk.http.RequestParams;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.StringUtil;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.JSON2LIST;
import com.hustunique.bocp.Utils.gesturepasswd.LockPatternView;
import com.hustunique.bocp.Utils.views.MaterialEditText;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chensq on 14-11-11.
 */
public class AccountSettingActivity extends Activity implements View.OnClickListener{

    private LinearLayout cardmanage,gesturepassword,tradelimitation,remainalarm;
    private MaterialMenuView menuView;
    private int Step=1;
    String temp1=null;
    String temp2=null;
    private ImageView addcardbtn;
    final String ADDCARDURL="https://openapi.boc.cn/app/adduserinfo";
    private RequestQueue queue;
    private SharedPreferences sharedPreferences;
    private List<Map<String,Object>> mcardlist;
    private ProgressDialog progDlg=null;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                sharedPreferences=getSharedPreferences("BOCP_APP",MODE_PRIVATE);
                boolean isfirstquerycard=sharedPreferences.getBoolean("BOCP_CARD_TAG",true);
                if(isfirstquerycard){
                    for(int i=0;i<mcardlist.size();i++){
                        Addnewcard(mcardlist.get(i));
                    }
                    sharedPreferences.edit().putBoolean("BOCP_CARD_TAG",false).commit();
                }

                for(int i=0;i<mcardlist.size();i++){
                    Creditbalsearch(AppConstants.username,mcardlist.get(i).get("lmtamt").toString(),i);
                }


            }else if(msg.what==2){
                mcardlist.get(msg.arg1).put("remain",String.valueOf(Float.parseFloat(msg.obj.toString()) / 100f));
                Log.i("balanceresult",mcardlist.get(msg.arg1).toString());
            }else if(msg.what==3){
                if(progDlg == null || !progDlg.isShowing()){
                    progDlg = new ProgressDialog(AccountSettingActivity.this);
                    progDlg.setMessage("正在获取银行卡，请稍候...");
                }
                progDlg.show();
            }else if(msg.what==4){
               if(progDlg!=null&&progDlg.isShowing())
                   progDlg.dismiss();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_accountsetting);
        queue = Volley.newRequestQueue(AccountSettingActivity.this);
        InitWidgets();

        appfindusrinfo();


       /* AccountsettingFragment asfragment=new AccountsettingFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.accsetting_fram,asfragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
    }

        private void InitWidgets(){
            cardmanage=(LinearLayout)findViewById(R.id.setting_cardmanagement);
            gesturepassword=(LinearLayout)findViewById(R.id.setting_gesturepasswd);
            tradelimitation=(LinearLayout)findViewById(R.id.setting_tradelimitation);
            remainalarm=(LinearLayout)findViewById(R.id.setting_remainalarm);
            cardmanage.setOnClickListener(this);
            gesturepassword.setOnClickListener(this);
            tradelimitation.setOnClickListener(this);
            remainalarm.setOnClickListener(this);
            menuView=(MaterialMenuView)findViewById(R.id.accountsetting_menu);
            menuView.setState(MaterialMenuDrawable.IconState.ARROW);
            menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AccountSettingActivity.this.finish();
                }
            });
        }

    private void Cardmanage(){
        addcardbtn=(ImageView)findViewById(R.id.addnewcard_btn);
        addcardbtn.setVisibility(View.VISIBLE);
        CardManagementFragment asfragment=new CardManagementFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit);
        transaction.replace(R.id.accsetting_fram,asfragment);
        transaction.addToBackStack(null);
        transaction.commit();
        addcardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("add_accesstoken", AccessTokenKeeper.readAccessToken(AccountSettingActivity.this).toString());
                AlertDialog.Builder bd=new AlertDialog.Builder(AccountSettingActivity.this);
                View layout= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_addnewcard,null);
                TextView comfirm=(TextView)layout.findViewById(R.id.addcard_comfirm);
                final MaterialEditText cardnum=(MaterialEditText)layout.findViewById(R.id.addnewcar_number);
                final MaterialEditText subname=(MaterialEditText)layout.findViewById(R.id.addnewcard_subname);
                comfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cardnumstr=cardnum.getText().toString();
                        String subnamestr=subname.getText().toString();
                        Toast.makeText(AccountSettingActivity.this,"clisk",Toast.LENGTH_LONG).show();
                    }
                });
                bd.setView(layout);
                bd.create().show();
            }
        });
    }

    private void GesturePassword(){
        final SharedPreferences preferences = getSharedPreferences(AppConstants.LOCK, MODE_PRIVATE);
        final String lockPattenString = preferences.getString(AppConstants.LOCK_KEY, null);

       final AlertDialog bd=new AlertDialog.Builder(AccountSettingActivity.this).create();
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_gesturepasswdsetting,null);
        final TextView hint=(TextView)v.findViewById(R.id.gp_hint);
        if(lockPattenString==null) {
            hint.setText(R.string.str_inputoriginpasswd);
            Step=2;
        }
        else {
            hint.setText("请输入原密码");
        }
        final LockPatternView gpview=(LockPatternView)v.findViewById(R.id.gesturepasswd_setting);
        gpview.setOnPatternListener(new LockPatternView.OnPatternListener() {
            @Override
            public void onPatternStart() {

            }

            @Override
            public void onPatternCleared() {

            }

            @Override
            public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

            }

            @Override
            public void onPatternDetected(List<LockPatternView.Cell> pattern) {
                ArrayList<LockPatternView.Cell> choosePattern = null;

                if (pattern.size() < LockPatternView.MIN_LOCK_PATTERN_SIZE) {
                    Toast.makeText(AccountSettingActivity.this,
                            R.string.lockpattern_recording_incorrect_too_short,
                            Toast.LENGTH_LONG).show();
                    gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                    gpview.clearPattern();
                    return;
                }

                if (choosePattern == null) {
                    choosePattern = new ArrayList<LockPatternView.Cell>(pattern);
                    String patternstr = Arrays.toString(choosePattern.toArray());

                    List<LockPatternView.Cell> lockPattern=null;
                    if(lockPattenString!=null)
                    lockPattern = LockPatternView.stringToPattern(lockPattenString);
                    switch (Step) {
                        case 1:
                            //List<LockPatternView.Cell>  lockPattern = LockPatternView.stringToPattern(lockPattenString);

                            if (!pattern.equals(lockPattern)){
                                Toast.makeText(AccountSettingActivity.this,
                                        "wrong password"+lockPattenString,
                                        Toast.LENGTH_LONG).show();
                                gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                                gpview.clearPattern();
                            }else{
                                gpview.clearPattern();
                                hint.setText("请输入新密码");
                                Step=2;
                            }
                                break;
                        case 2:
                             if(lockPattenString!=null&&pattern.equals(lockPattern)){
                                 Toast.makeText(AccountSettingActivity.this,
                                         "the same with original password",
                                         Toast.LENGTH_LONG).show();
                                 gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                                 gpview.clearPattern();
                             }else{
                                 temp1= LockPatternView.patternToString(pattern);
                                 hint.setText("确认新密码");
                                 Step=3;
                                 gpview.clearPattern();
                             }
                            break;
                        case 3:
                            if(!pattern.equals(LockPatternView.stringToPattern(temp1))){
                                Toast.makeText(AccountSettingActivity.this,
                                        "wrong password",
                                        Toast.LENGTH_LONG).show();
                                gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                                gpview.clearPattern();
                            }else{
                                preferences
                                        .edit()
                                        .putString(AppConstants.LOCK_KEY,
                                                LockPatternView.patternToString(choosePattern))
                                        .commit();
                                Toast.makeText(AccountSettingActivity.this,
                                        "successed",
                                        Toast.LENGTH_LONG).show();
                                bd.dismiss();
                            }
                            break;
                    }
                }
            }
        });

        bd.setView(v);
        bd.show();
    }

    private void Tradelimit(){
        AlertDialog.Builder bd=new AlertDialog.Builder(AccountSettingActivity.this);
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_budgetsetting,null);
        bd.setView(v);
        bd.create().show();
    }

    private void Addnewcard(Map<String,Object> map){
        final Map<String,Object> mapt=map;
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/requestcid/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 Log.i("Stringresponse",response);
                try {
                    JSONObject Jobj = new JSONObject(response);
                    AppConstants.cid=Jobj.getString("cid");
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
                hashMap.put("uid",AppConstants.UID);
                hashMap.put("cardname",mapt.get("alias").toString());
                hashMap.put("cardnumber",mapt.get("accno").toString());
                hashMap.put("limitamt",mapt.get("lmtamt").toString());
                hashMap.put("limitmoney","5000");
                Log.i("addcardparams",hashMap.toString());
                return hashMap;
            }
        };
        queue.add(stringRequest);
    }

    public void put(String url, LinkedHashMap<String, String> header,
                    LinkedHashMap<String, String> paramsBody,
                    AsyncResponseHandler responseHandler) {
        sendRequest(url, ParaType.HTTPMETHOD_PUT,header,paramsBody,responseHandler);
    }

    private void sendRequest(String url,String method,LinkedHashMap<String, String> header,LinkedHashMap<String, String> paramsBody,AsyncResponseHandler responseHandler) {
        RequestParams params = new RequestParams(url,method,header,paramsBody,responseHandler);
        AsyncHttpRequest request  = new AsyncHttpRequest(params);
        request.execute();
    }

    private void Remainalarm(){
        AlertDialog bd=new AlertDialog.Builder(AccountSettingActivity.this).create();
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_tradecomfirm,null);
        bd.setView(v);
        bd.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_cardmanagement:Cardmanage();break;
            case R.id.setting_gesturepasswd:Step=1;temp1=null;temp2=null;GesturePassword();break;
            case R.id.setting_tradelimitation:Tradelimit();break;
            case R.id.setting_remainalarm:Remainalarm();break;
        }
    }


    public void appfindusrinfo(){
        //RequestQueue requestQueue=Volley.newRequestQueue(AccountSettingActivity.this);
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("userid","cary32_test_391");
        //map.put("accno","");
        //map.put("alias","");
        //map.put("trntyp","");
        //map.put("ifncal","");
        map.put("pageno","1");
        //  map.put("limitamt","2014091500000615");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Constants.httpPrefix+"/debit/appfindusrinfo",new JSONObject(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                   // Log.i("sssstrade",response.toString());
                        mcardlist=JSON2LIST.getList(response.getString("saplist"));
                        mhandler.obtainMessage(1,null).sendToTarget();
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
                return BaseService.genPublicAsrHeader(AccountSettingActivity.this);
            }
        };

        queue.add(jsonObjectRequest);
    }

    public List<Map<String,Object>> getcardlist(){
        return mcardlist;
    }

    private void Creditbalsearch(String uid,String limitamt,int position){
        final int po=position;
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("userid",uid);
        map.put("lmtamt",limitamt);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, Constants.httpPrefix+ "/app/creditbalsearch",new JSONObject(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("balancesearch",response.toString());
                    mhandler.obtainMessage(2,po,0,response.get("balance").toString()).sendToTarget();
                    if(po==mcardlist.size()-1)
                        mhandler.obtainMessage(4,null).sendToTarget();
                }catch (Exception e){}

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return BaseService.genPublicAsrHeader(AccountSettingActivity.this);
            }
        };
        queue.add(jsonObjectRequest);
    }


}
