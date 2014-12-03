package com.hustunique.bocp.Activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.text.InputFilter;
import android.util.Log;
import android.util.Property;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.boc.bocop.sdk.api.bean.AppInfo;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.fund.Fund900Response;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.http.AsyncHttpClient;
import com.boc.bocop.sdk.http.AsyncResponseHandler;
import com.boc.bocop.sdk.http.JsonResponseListenerAdapterHandler;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.Oauth2AccessToken;
import com.boc.bocop.sdk.util.StringUtil;
import com.hustunique.bocp.Adapters.Drawermenuadapter;
import com.hustunique.bocp.Adapters.SubListAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.BalanceCriteria;
import com.hustunique.bocp.Fragments.FragmentOne;
import com.hustunique.bocp.Fragments.FragmentThree;
import com.hustunique.bocp.Fragments.FragmentTwo;
import com.hustunique.bocp.Utils.ContactTransCriteria;
import com.hustunique.bocp.Utils.PromotedActionsLibrary;
import com.hustunique.bocp.Utils.UtilTool;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.ryg.fragment.ui.ViewPagerCompat;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivityo extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    private ListView drawerlistmenu,drawerarraylist;
    private ImageView mainaddbtn;
    private ViewPagerCompat compat;
    private String uid=null;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuView materialMenuView;
    private int              materialButtonState;
    private MaterialMenu materialIcon;
    private DrawerLayout     drawerLayout;
    private boolean          direction;
    private int menustate;
    private ObjectAnimator presscircle;
    private ArrayList<Map<String,Integer>> mlist;
    private FrameLayout mainframe;
    private  PromotedActionsLibrary promotedActionsLibrary;
    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(MainActivityo.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }

        //startActivity(intent);
      /*  BalanceCriteria balanceCriteria=new BalanceCriteria();
        balanceCriteria.setUserid("cary32_test_391");
        balanceCriteria.setLmtamt("2014091500000615");
        creditbalsearch(MainActivityo.this,balanceCriteria,new ResponseListener() {
            @Override
            public void onComplete(ResponseBean responseBean) {
                //Log.i("response result",responseBean.getRtnmsg());
            }

            @Override
            public void onException(Exception e) {

            }

            @Override
            public void onError(Error error) {

            }

            @Override
            public void onCancel() {

            }
        });
            */

        Creditbalsearch();

        {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.btn_options);
            mainframe=(FrameLayout)findViewById(R.id.main_framelayout);
            promotedActionsLibrary= new PromotedActionsLibrary();
            LinearLayout mbgn=(LinearLayout)findViewById(R.id.main_mbgn);
// setup library
            promotedActionsLibrary.setup(getApplicationContext(), frameLayout,mbgn);

// customize promoted actions with a drawable
            promotedActionsLibrary.addItem(getResources().getDrawable(R.drawable.paybymoney), new Onpopupitemclicklistener1());
            promotedActionsLibrary.addItem(getResources().getDrawable(R.drawable.paybybarcode),new Onpopupitemclicklistener2());
// create main floating button and customize it with a drawable
            promotedActionsLibrary.addMainItem(getResources().getDrawable(R.drawable.add_btn));
        }

        uid=getIntent().getStringExtra("UID");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                direction = true;
            }

            @Override
            public void onDrawerClosed(android.view.View drawerView) {
                direction = false;
            }
        });

        materialMenuView=(MaterialMenuView)findViewById(R.id.action_bar_menu);
        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menustate = generateState(menustate);
                materialMenuView.animatePressedState(intToState(menustate));
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });

        mlist=new ArrayList<Map<String, Integer>>();
        for(int i=0;i<AppConstants.menuitem.length;i++){
            HashMap<String,Integer> map=new HashMap<String, Integer>();
            map.put(AppConstants.menuitem[i],AppConstants.drawericons[i]);
            mlist.add(map);
        }

        //mainaddbtn=(ImageView)findViewById(R.id.main_addbtn);
        drawerlistmenu=(ListView)findViewById(R.id.drawermenulist);
        drawerarraylist=(ListView)findViewById(R.id.drawerarrylist);
        Drawermenuadapter drawermenuadapter=new Drawermenuadapter(MainActivityo.this, mlist);
        SubListAdapter subadapter= new SubListAdapter(MainActivityo.this, AppConstants.submenulistitem);
       // drawerarraylist.setAdapter(subadapter);
        drawerlistmenu.setAdapter(drawermenuadapter);
        drawerlistmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 0:break;
                  case 1:toAccsettingactivity();break;
                  case 2:NewTrade();
                      break;
              }
            }
        });
        compat=(ViewPagerCompat)findViewById(R.id.pager);

    }

    private void NewTrade(){
          Intent intent=new Intent(MainActivityo.this,NewTradActivity.class);
          startActivity(intent);
    }

    private void getUserinfo(){
        AsyncHttpClient client=new AsyncHttpClient();
        LinkedHashMap<String,Integer> para=new LinkedHashMap<String, Integer>();
        para.put("pageno",1);
        client.post("https://openapi.boc.cn/bocop/appfindusrinfo",BaseService.genPublicAsrHeader(MainActivityo.this),para,new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, new ResponseListener() {
            @Override
            public void onComplete(ResponseBean responseBean) {
                Log.i("response",responseBean.getMsgcde()+"____"+responseBean.getRtnmsg());
            }

            @Override
            public void onException(Exception e) {

            }

            @Override
            public void onError(Error error) {

            }

            @Override
            public void onCancel() {

            }
        }));
    }


    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        tabs.add(new TabInfo(FRAGMENT_ONE, getString(R.string.fragment_one),
                FragmentOne.class));
        tabs.add(new TabInfo(FRAGMENT_TWO, getString(R.string.fragment_two),
                FragmentTwo.class));
        tabs.add(new TabInfo(FRAGMENT_THREE, getString(R.string.fragment_three),
                FragmentThree.class));

        return FRAGMENT_TWO;
    }

    private void toAccsettingactivity(){
        Intent intent=new Intent(MainActivityo.this,AccountSettingActivity.class);
        startActivity(intent);
    }


    public  void mcreditbalsearch(final Context context, String AppKey,String AppSecret,String mLmtamt,ResponseListener handler) {
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context, AppKey, AppSecret);

        BalanceCriteria criteria = new BalanceCriteria();
        criteria.setUserid(uid);
//		criteria.setLmtamt("2014091500000615");
        criteria.setLmtamt("2014091500000615");
        creditbalsearch(context, criteria, handler);
    }

    public  void creditbalsearch(Context context,BalanceCriteria criteria, ResponseListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Constants.httpPrefix+ "/app/creditbalsearch", BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));

    }

    private void Creditbalsearch(){
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivityo.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Constants.httpPrefix+ "/app/creditbalsearch",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 Log.i("sssresssssss",response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return BaseService.genPublicAsrHeader(MainActivityo.this);
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
                map.put("userid","cary32_test_391");
                map.put("lmtamt","2014091500000615");
                return map;
            }
        };

        HashMap<String,String> map=new HashMap<String, String>();
        map.put("userid","cary32_test_391");
        map.put("lmtamt","2014091500000615");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Constants.httpPrefix+ "/app/creditbalsearch",new JSONObject(map),new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i("sssstttttttt",response.getString("balance"));
                }catch (Exception e){}

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return BaseService.genPublicAsrHeader(MainActivityo.this);
            }
        };

        requestQueue.add(jsonObjectRequest);
    }

    public static int generateState(int previous) {
        int generated = new Random().nextInt(4);
        return generated != previous ? generated : generateState(previous);
    }

    public static MaterialMenuDrawable.IconState intToState(int state) {
        switch (state) {
            case 0:
                return MaterialMenuDrawable.IconState.BURGER;
            case 1:
                return MaterialMenuDrawable.IconState.ARROW;
            case 2:
                return MaterialMenuDrawable.IconState.X;
            case 3:
                return MaterialMenuDrawable.IconState.CHECK;
        }
        throw new IllegalArgumentException("Must be a number [0,3)");
    }

    class Onpopupitemclicklistener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            promotedActionsLibrary.closepopup();
           // Intent intent=new Intent(MainActivityo.this,PayActivity.class);
            //startActivity(intent);
            RequestQueue requestQueue=Volley.newRequestQueue(MainActivityo.this);
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("userid","cary32_test_391");
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
                        Log.i("sssstrade",response.toString());
                        Toast.makeText(MainActivityo.this,response.toString(),Toast.LENGTH_LONG).show();
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

            requestQueue.add(jsonObjectRequest);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(promotedActionsLibrary.Ispopup()){
                promotedActionsLibrary.closepopup();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    class Onpopupitemclicklistener2 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            promotedActionsLibrary.closepopup();
           // Intent intent=new Intent(MainActivityo.this,MipcaActivityCapture.class);
            //startActivity(intent);
            RequestQueue requestQueue=Volley.newRequestQueue(MainActivityo.this);
            HashMap<String,String> map=new HashMap<String, String>();
            map.put("userid","cary32_test_391");
           // map.put("lmtamtout",)
            map.put("lmtamtout","2014092200000923");
            map.put("cardnumin","6217870700000000001");
            map.put("amount","200");
            map.put("currency","001");
            map.put("username","zhangsan");
            //post("http://openapi.boc.cn/bocop/base/asr/cardtransfer",BaseService.genPublicAsrHeader(MainActivityo.this),map,);
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Constants.httpPrefix + "/base/asr/cardtransfer",new JSONObject(map),new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.i("sssstttttttt",response.toString());
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
                    return BaseService.genPublicAsrHeader(MainActivityo.this);
                }
            };

            requestQueue.add(jsonObjectRequest);
            ContactTransCriteria criteria=new ContactTransCriteria();
            criteria.setUserid("cary32_test_391");
            criteria.setAmount("300");
            criteria.setCardnumin("6217870700000000001");
            criteria.setCurrency("001");
            criteria.setUsername("zhangsan");
            criteria.setLmtamtout("2014092200000923");
            /*commoncardtransfer(MainActivityo.this, criteria, new ResponseListener() {
                @Override
                public void onComplete(ResponseBean responseBean) {
                    Log.i("responsetrans",responseBean.getRtnmsg());
                }

                @Override
                public void onException(Exception e) {

                }

                @Override
                public void onError(Error error) {

                }

                @Override
                public void onCancel() {

                }
            });
                */
        }
    }

    public static void commoncardtransfer(Context context,ContactTransCriteria criteria, ResponseListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Constants.httpPrefix + "/base/asr/cardtransfer", BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));
    }

}
