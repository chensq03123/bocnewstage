package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.NetworkConstant;



public class LoginActivity extends Activity {


    private Button loginbtn,logoutbtn;

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
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(this, NetworkConstant.CONSUMER_KEY,
                NetworkConstant.CONSUMER_SECRET);
        // 设置等参数      上下文，           登录地址，          登录端口，          是否显示注册按钮，        注册地址
        bocopSDKApi
                .initURLIPPort(this, NetworkConstant.LOGIN_SVR, NetworkConstant.HTTPS_PORT, true, NetworkConstant.SIGNUP_URL);
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
                NetworkConstant.access_token = info.getAccess_token();// 登录成功产生的token
                NetworkConstant.userid = info.getUserId();// 登录的用户名
                mhandler.obtainMessage(1,info.getUserId()).sendToTarget();
            }
            @Override
            public void onCancel() {
            }
        });
    }


    public void logoutApp() {
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(LoginActivity.this,
                NetworkConstant.CONSUMER_KEY, NetworkConstant.CONSUMER_SECRET);
        bocopSDKApi.delOAuthorize(LoginActivity.this);
        //清空用户及token缓存
        NetworkConstant.access_token ="";
        NetworkConstant.userid ="";
        mhandler.obtainMessage(0,"logout").sendToTarget();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        loginbtn=(Button)findViewById(R.id.login);
        logoutbtn=(Button)findViewById(R.id.logout);

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
            }
        });

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
