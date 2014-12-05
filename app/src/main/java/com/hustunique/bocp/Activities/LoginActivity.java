package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.fund.Fund900Response;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.bean.oauth.OAuthResponseInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.http.AsyncHttpClient;
import com.boc.bocop.sdk.http.JsonResponseListenerAdapterHandler;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.impl.OAuthService;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.BalanceCriteria;
import com.hustunique.bocp.Utils.gesturepasswd.LockActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity {


    public static final String CONSUMER_KEY = "284";
    public static final String CONSUMER_SECRET = "78b930ce450d19747e8cf16e190cc975e96775f9cac6cc12c4";

    /** 登录的地址 */
    public static final String LOGIN_SVR = "https://opendtp.boc.cn";
    /** 注册调用的Html5界面的地址 */
    public static final String SIGNUP_URL = "https://open.boc.cn/wap/register.php";
    /** APP 的key */
    /** 登录端口 */
    public static final int HTTPS_PORT = 443;
    /**编码*/
    public static final String ENCODE = "utf-8";

    private Button loginbtn,logoutbtn;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
               Toast.makeText(LoginActivity.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
                final String uid=msg.obj.toString();
                AppConstants.username=uid;

                {
                    AccessTokenKeeper.keepAccessToken(LoginActivity.this, OAuthService.getOAuthToken());
                }

                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/requestuid/",new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(LoginActivity.this,MainActivityo.class);
                        intent.putExtra("UID",uid);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<String, String>();
                        params.put("username",uid);
                        return params;
                    }
                };
                queue.add(stringRequest);

            }else if(msg.what==0){
                Toast.makeText(LoginActivity.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
            }

        }
    };
    private void authorize() {
        final BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(LoginActivity.this,CONSUMER_KEY, CONSUMER_SECRET);
        // 设置等参数      上下文，           登录地址，          登录端口，          是否显示注册按钮，        注册地址
        bocopSDKApi.initURLIPPort(this,LOGIN_SVR,HTTPS_PORT, false,SIGNUP_URL);
        // 登录SDK回调接口
      bocopSDKApi.authorize(this, new ResponseListener() {
          @Override
          public void onException(Exception arg0) {
          }

          @Override
          public void onError(Error arg0) {
          }

          @Override
          public void onComplete(ResponseBean response) {
              // 这是登陆成功回调
              BOCOPOAuthInfo info = (BOCOPOAuthInfo) response;
              mhandler.obtainMessage(1, info.getUserId()).sendToTarget();
          }

          @Override
          public void onCancel() {
          }
      });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_login);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setDuration(2000);


      /*  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }*/

        loginbtn=(Button)findViewById(R.id.login);
        logoutbtn=(Button)findViewById(R.id.logout);

        loginbtn.setAnimation(alphaAnimation);
        logoutbtn.setAnimation(alphaAnimation);
        alphaAnimation.startNow();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorize();

            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(LoginActivity.this,RegistActivity.class);
               startActivity(intent);

            }
        });

        SharedPreferences preferences = getSharedPreferences(AppConstants.LOCK, MODE_PRIVATE);
        String lockPattenString = preferences.getString(AppConstants.LOCK_KEY, null);


        if (lockPattenString != null) {
            Intent intent = new Intent(this, LockActivity.class);
            startActivity(intent);
        }
       // authorize();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
