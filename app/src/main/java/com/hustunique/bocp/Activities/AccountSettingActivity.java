package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.boc.bocop.sdk.util.ParaType;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.gesturepasswd.LockPatternView;
import com.hustunique.bocp.Utils.views.MaterialEditText;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_accountsetting);
        queue = Volley.newRequestQueue(AccountSettingActivity.this);
        InitWidgets();

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

                        Addnewcard("6217870700000000001","testcard","snowlovegood_test_408");
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

    private void Addnewcard(String cardnum,String subname,String userid){


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
                hashMap.put("cardname","testmain");
                hashMap.put("cardnumber","6217870700000000001");
                hashMap.put("limitmoney","1000");
                return hashMap;
            }
        };
        queue.add(stringRequest);
        LinkedHashMap<String,String> param=new LinkedHashMap<String, String>();
        param.put("USRID",userid);
        param.put("ACCNO",cardnum);
        param.put("ALIAS",subname);
        LinkedHashMap<String,String> header= BaseService.genPublicAsrHeader(BOCOPPayApi.getContext());

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.PUT, "https://openapi.boc.cn/app/adduserinfo",new JSONObject(param),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("newcardrep",response.toString());
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
}
