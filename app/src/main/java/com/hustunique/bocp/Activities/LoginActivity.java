package com.hustunique.bocp.Activities;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.gesturepasswd.LockActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;


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

    private Button loginbtn;
    private TextView logoutbtn;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what==1){
                Toast.makeText(LoginActivity.this,msg.obj.toString(),Toast.LENGTH_LONG).show();

                Intent intent=new Intent(LoginActivity.this,MainActivityo.class);
                intent.putExtra("UID",msg.obj.toString());
                startActivity(intent);
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
              Log.i("result", info.getAccess_token() + "_____" + info.getRefresh_token());
              //OAuthService.getOAuthToken().setToken(info.getAccess_token());
              //  NetworkConstant.access_token = info.getAccess_token();// 登录成功产生的token
              //NetworkConstant.userid = info.getUserId();// 登录的用户名
              // AccessTokenKeeper.keepAccessToken(LoginActivity.this,info.getAccess_token());
              mhandler.obtainMessage(1, info.getUserId()).sendToTarget();
              // bocopSDKApi.Addnewcard(LoginActivity.this,"5765825000366567638","testcard","snowlovegood_test_408");
          }

          @Override
          public void onCancel() {
          }
      });
      /*   bocopSDKApi.authorizeNoDialog(LoginActivity.this,"snowlovegood_test_408","111111",new SipBox(LoginActivity.this),new ResponseListener() {
            @Override
            public void onComplete(ResponseBean responseBean) {
                BOCOPOAuthInfo info = (BOCOPOAuthInfo) responseBean;
                Log.i("result",info.getAccess_token()+"_____"+info.getRefresh_token());
                //OAuthService.getOAuthToken().setToken(info.getAccess_token());
                //  NetworkConstant.access_token = info.getAccess_token();// 登录成功产生的token
                //NetworkConstant.userid = info.getUserId();// 登录的用户名
                // AccessTokenKeeper.keepAccessToken(LoginActivity.this,info.getAccess_token());
                mhandler.obtainMessage(1,info.getUserId()).sendToTarget();
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
        });*/

    }




    public void logoutApp() {
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(LoginActivity.this,
                "284", "78b930ce450d19747e8cf16e190cc975e96775f9cac6cc12c4");
        bocopSDKApi.delOAuthorize(LoginActivity.this);
        //清空用户及token缓存
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_login);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }

        loginbtn=(Button)findViewById(R.id.login);
        logoutbtn=(TextView)findViewById(R.id.logout);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorize();

            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutApp();
               // Intent intent=new Intent(LoginActivity.this,MainActivityo.class);
                //startActivity(intent);
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
