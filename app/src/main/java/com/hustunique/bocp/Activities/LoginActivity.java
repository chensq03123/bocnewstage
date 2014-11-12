package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.service.AsyncPara;
import com.boc.bocop.sdk.service.BaseService;
import com.boc.bocop.sdk.service.impl.OAuthService;
import com.boc.bocop.sdk.util.AccessTokenKeeper;
import com.boc.bocop.sdk.util.BOCOPEditPwdView;
import com.boc.bocop.sdk.util.BOCOPParameters;
import com.boc.bocop.sdk.util.Logger;
import com.boc.bocop.sdk.util.ParaType;
import com.boc.bocop.sdk.util.ResourceUtil;
import com.boc.bocop.sdk.util.StringUtil;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.NetworkConstant;
import com.hustunique.bocp.Utils.Utility;
import com.boc.bocop.sdk.api.bean.oauth.*;
import com.boc.bocop.sdk.service.impl.OauthContainerRequest;

import java.util.LinkedHashMap;

import cfca.mobile.sip.SipBox;
import cfca.mobile.sip.SipResult;

public class LoginActivity extends Activity {

    private OAuthService oauth=new OAuthService();
    private SipResult sipResult;
    private ResponseListener mListener;

    protected static String mUserId = "";
    private String random_S;
    private String randomId;
    private BOCOPEditPwdView editPwdView;
    private final int OAUTH_ERROR = 2;
    private final int OAUTH_EXCEPTION = 3;
    private ProgressDialog mSpinner;
    private String murl;
    private SipBox bocoPwdView,editView;
    String urlprifx;
    public static String CONSUMER_KEY = "249";
    public static String CONSUMER_SECRET = "03fd9ff7fcb7fcc2002119ce99a2683d93fed92b413549a7ef";
    public static String HTTP_SAP_IP_PBTEST = "https://opendtp.boc.cn";
    public static int HTTP_SAP_PORT_PBTEST = 443;

    private final static int RANDOM_SUCCESS = 1;
    private final static int ERROR = 2;
    private final static int EXCEPTION = 3;
    private final static int BALANCE = 4;//查询余额
    private final static int TRANSFER = 5;//转账成功
    private static OauthHandler oauthHandle =  null;

    private class OauthHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RANDOM_SUCCESS:
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                    break;
              /*  case BALANCE:
                    if(null != msg.obj){
                        String balance = msg.obj.toString();
                        Toast.makeText(context, "当前卡余额为："+balance+"元", Toast.LENGTH_LONG).show();
                        etLmtamt.setText("");
                    }
                    break;
                case TRANSFER:
                    if(null != msg.obj){
                        String account = msg.obj.toString();
                        Toast.makeText(context, "成功转入"+account+"元", Toast.LENGTH_LONG).show();
                        etLmtamtOut.setText("");
                        etLmtamtIn.setText("");
                        etMoney.setText("");
                    }
                    break;*/
                default:
                    if(null != msg.obj){
                        String strError = msg.obj.toString();
                        Toast.makeText(LoginActivity.this, strError, Toast.LENGTH_LONG).show();
                    }
                    break;
            }

        }
    };

    public static void testOAuth(final Context context, String appKey,
                                 String appSecret, ResponseListener handler) {
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context, appKey,
                appSecret);
        bocopSDKApi.initURLIPPort(context,LoginActivity.HTTP_SAP_IP_PBTEST, LoginActivity.HTTP_SAP_PORT_PBTEST, false, "");
        bocopSDKApi.authorize(context, handler);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        oauthHandle=new OauthHandler();
        testOAuth(LoginActivity.this,CONSUMER_KEY, CONSUMER_SECRET, new ResponseListener() {

            @Override
            public void onException(Exception e) {
                Log.i("liuwenchao", "Exception------"+e.getMessage());
                Message msg = Message.obtain();
                msg.obj = ResourceUtil.parseAssetsString(
                        BOCOPPayApi.getContext(), "ExceptionString");;
                msg.what = EXCEPTION;
                oauthHandle.sendMessage(msg);

            }

            @Override
            public void onError(Error e) {
                Log.i("liuwenchao", "Error------"+e.getMessage());
                if(e instanceof ResponseError)
                {
                    Message msg = Message.obtain();
                    msg.obj = ((ResponseError) e).getRtnmsg();
                    msg.what = ERROR;
                    oauthHandle.sendMessage(msg);}

            }

            @Override
            public void onComplete(ResponseBean response) {
                Message msg = Message.obtain();
                msg.what = RANDOM_SUCCESS;
                oauthHandle.sendMessage(msg);

            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        });

        /*editPwdView=new BOCOPEditPwdView(LoginActivity.this);
        oauthHandle=new OauthHandler();
        BOCOPPayApi bocopPayApi=BOCOPPayApi.getInstance(LoginActivity.this,CONSUMER_KEY,CONSUMER_SECRET);
        mListener=new ResponseListener() {
            @Override
            public void onComplete(ResponseBean responseBean) {
                BOCOPOAuthInfo infor=(BOCOPOAuthInfo)responseBean;
                Log.i("result", infor.getUserId());
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
        };
        bocoPwdView=new SipBox(LoginActivity.this);
        editView = new SipBox(LoginActivity.this);
        editView.setTextSize(14);
        editView.setLongClickable(false);
        editView.setSingleLine();
        editView.setText("sssssssssssss");
        editPwdView.getEditView().setText("111111");
        editPwdView.getEditView().getText();
        Toast.makeText(this, editPwdView.getEditView().getText(),Toast.LENGTH_LONG).show();
        // CFCA sipbox初始化
        editView.setKeyBoardType(0);
        editView.setSipDelegator(editPwdView);
        editView.setTextColor(getResources().getColor(android.R.color.black));
        editView.setPasswordMinLength(0);
        editView.setPasswordMaxLength(16);
        editView.setOutputValueType(2);
        editView.setPasswordRegularExpression("[a-zA-Z0-9]*");
        editView.hideSecurityKeyBoard();
        editView.setHint("");
        editView.setBackgroundColor(0);
        mSpinner = new ProgressDialog(LoginActivity.this);
        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setCanceledOnTouchOutside(false);

        //validation();
        OAuthService.authorizeNoDialogAsr(LoginActivity.this,"cary32_test_391","111111",bocoPwdView,mListener);
        //urlprifx="https://opendtp.boc/oauth/token";
        //authorize();*/
    }
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
                //handler.sendEmptyMessage(R.id.btn_login);
            }
            @Override
            public void onCancel() {
            }
        });
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
    class ResultBOCOPAuthListener implements ResponseListener {

        private OAuthResponseInfo data;
        @Override
        public void onComplete(ResponseBean response) {
            // TODO Auto-generated method stub

            if (response != null && response instanceof OAuthResponseInfo) {
                data = (OAuthResponseInfo) response;

                if (null == OAuthService.getOAuthToken()) {
                    OAuthService.setOAuthToken();
                }

                OAuthService.getOAuthToken().setToken(data.getAccess_token());
                OAuthService.getOAuthToken().setExpiresIn(String.valueOf(data.getExpires_in()));
                OAuthService.getOAuthToken().setRefreshToken(data.getRefresh_token());
                OAuthService.getOAuthToken().setUserId(data.getUser_id());

                AccessTokenKeeper.keepAccessToken(LoginActivity.this, OAuthService.getOAuthToken());
                BOCOPOAuthInfo info = new BOCOPOAuthInfo();
                info.setAccess_token(data.getAccess_token());
                info.setRefresh_token(data.getRefresh_token());
				/* info.setIsmsgfull(data.getIsmsgfull()); */
                info.setUserId(data.getUser_id());
                mListener.onComplete(info);
            }
        }

        @Override
        public void onException(Exception e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onError(Error e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onCancel() {
            // TODO Auto-generated method stub
            //mListener.onCancel();
        }
    }


    public void getRandomNum() {
        //RandomCriteria randomCriteria = new RandomCriteria();
        //final RandomRequest requestTask = OAuthService.getRandomResult(mContext, randomCriteria, new RandomListener());
        //OAuthService.getRandomResult(mContext, randomCriteria, new RandomListener());
        LinkedHashMap<String, String> head = BaseService.genSapHeader();
        //LinkedHashMap<String, String> body = new LinkedHashMap();
        LinkedHashMap<String, String> body = null;

        AsyncPara para = new AsyncPara("https://opendtp.boc.cn"+"/oauth/getrdnum",
                ParaType.HTTPMETHOD_POST, head, body, ParaType.GET_ROMDAM,
                new RandomListener());
        Log.i("RandomService", para.toString());
        final OauthContainerRequest requestTask = new OauthContainerRequest();
        requestTask.execute(para);
    }


    class RandomListener implements ResponseListener{

        @Override
        public void onComplete(ResponseBean response) {
            RandomResponse randomResponse = null;
            if( (null != response) && (response instanceof RandomResponse)){
                randomResponse = (RandomResponse) response;
                Message msg = Message.obtain();
                msg.obj = randomResponse;
                msg.what = RANDOM_SUCCESS;
                oauthHandle.sendMessage(msg);
            }
        }

        @Override
        public void onException(Exception e) {
            Message msg = Message.obtain();
            msg.obj = ResourceUtil.parseAssetsString(
                    BOCOPPayApi.getContext(), "ExceptionString");;
            msg.what = OAUTH_EXCEPTION;
            oauthHandle.sendMessage(msg);
            mListener.onException(e);
        }

        @Override
        public void onError(Error e) {
            if(e instanceof ResponseError)
            {
                Message msg = Message.obtain();
                msg.obj = ((ResponseError) e).getRtnmsg();
                msg.what = OAUTH_ERROR;
                oauthHandle.sendMessage(msg);}

            mListener.onError(e);
        }

        @Override
        public void onCancel() {
            mListener.onCancel();
        }
    }


    private void validation()
    {
        String name ="cary32_test_391";
        String password ="111111";
        mUserId = name;
        String errorMsg = "";
        if (StringUtil.isNullOrEmpty(name)) {
            String errorMsgUser = ResourceUtil.parseAssetsString(
                    LoginActivity.this, "errorMsgUser");
            errorMsg = errorMsgUser;
        }else if (StringUtil.isNullOrEmpty(password)) {
            String errorMsgPwd = ResourceUtil.parseAssetsString(
                    LoginActivity.this, "errorMsgPwd");
            errorMsg = errorMsgPwd;
        }else if (name.trim().length() < 6) {
            String errorMsgMinUserText = ResourceUtil.parseAssetsString(
                    LoginActivity.this, "errorMinUserText");
            errorMsg = errorMsgMinUserText;
        }else if (password.trim().length() < 6) {
            String errorMsgMinPwdText = ResourceUtil.parseAssetsString(
                    LoginActivity.this, "errorMinPwdText");
            errorMsg = errorMsgMinPwdText;
        }
        if (!StringUtil.isNullOrEmpty(errorMsg)) {
            Toast toast = Toast.makeText(LoginActivity.this, errorMsg,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        } else {
            ContainerInfo.setUser(name);
            ContainerInfo.setPassword(password);
            getRandomNum();
        }
    }

    String str="{\"randomid\":\"000000001109214754000789\",\"random\":\"1109214754000789\"}";

   /* private class OauthHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RANDOM_SUCCESS:
                    RandomResponse randomResponse = null;
                    randomResponse = (RandomResponse) msg.obj;
                    random_S =android.util.Base64.encodeToString(randomResponse.getRandom().getBytes(),Base64.DEFAULT);
                    randomId = randomResponse.getRandomid();
                    bocoPwdView.setRandomKey_S(random_S);
                    SipResult sipResult =null;
                    try {
                       sipResult=bocoPwdView.getValue();
                    }catch (Exception e){

                    }
                    if(null != sipResult){
                        startBocopOauth(ContainerInfo.getUser(), sipResult.getEncryptPassword(),
                                sipResult.getEncryptRandomNum(),
                                randomId,AppInfo.getAppKeyValue(), AppInfo.getAppSecretValue(),urlprifx);
                        Logger.d("enpassword-----" + sipResult.getEncryptPassword());
                        Logger.d("enrandom-----"+sipResult.getEncryptRandomNum());	}
                    else{
                        String strInputError =  ResourceUtil.parseAssetsString(LoginActivity.this,
                                "sipinputerror");
                        Toast.makeText(LoginActivity.this, strInputError, Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    if(null != msg.obj){
                        String strError = msg.obj.toString();
                        Toast.makeText(LoginActivity.this, strError, Toast.LENGTH_LONG).show();
                    }
                    break;
            }

        }
    };*/


    public void startBocopOauth(String user, String pwd, String random,String randomId,String appID,
                                String appSecret, String URL) //throws JSONException
    {
        BOCOPParameters parameters = new BOCOPParameters();

        parameters.add(ParaType.KEY_APPID, appID);
        parameters.add(ParaType.KEY_APPSECRET, appSecret);
        parameters.add(ParaType.KEY_USER, user);
        parameters.add(ParaType.KEY_PWD, pwd);
        //add by cindy 增加ASR登录参数设置
        parameters.add(ParaType.KEY_ENCTYP, "0");
        parameters.add(ParaType.KEY_GRANTP, "implicit");

        String url = URL + "?" + Utility.encodeUrl(parameters);

        LinkedHashMap<String, String> head = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> body = new LinkedHashMap<String, String>();
        body.put(ParaType.KEY_ENRANDOM, random);
        body.put(ParaType.KEY_RANDOMID, randomId);
        Logger.d(url);
        Logger.d(random);
        AsyncPara para = null;
        /*if(asrFlag){
            para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head,
                    body, ParaType.OAUTH_CONTAINER, new ResultBOCOPAuthListener());}
        else{*/
            para = new AsyncPara(url, ParaType.HTTPMETHOD_POST, head,
                    body, ParaType.OAUTH_APP, new ResultBOCOPAuthListener());
        //}

        final OauthContainerRequest requestTask = new OauthContainerRequest();
        requestTask.execute(para);
    }




    /*private class OauthHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case RANDOM_SUCCESS:
                    RandomResponse randomResponse = null;
                    randomResponse = (RandomResponse) msg.obj;

                    //String random_S ="{\"randomid\":\"000000001109214754000789\",\"random\":\"1109214754000789\"}"; //android.util.Base64.encodeToString(randomResponse.getRandom().getBytes(), Base64.DEFAULT);
                    //String randomId = randomResponse.getRandomid();

                   // Logger.d("randoms-----" +random_S);
                    //Logger.d("randomid-----" +randomId);

                    random_S = android.util.Base64.encodeToString(randomResponse.getRandom().getBytes(),Base64.DEFAULT);
                    Toast.makeText(LoginActivity.this, random_S, Toast.LENGTH_LONG).show();
                    randomId = randomResponse.getRandomid();
                    SipResult sipResult = null;//editPwdView.getSipResult("MTEwOTIxNDQyMzAwMDc4OA==");
                    editView.setRandomKey_S("qGIPr75Kz7KUL73ZyKQNlw==");


                    try {
                        sipResult = editView.getValue();
                    } catch (CodeException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    String strUrl = "";
                    strUrl =  "https://openapi.boc.cn "+ "/oauth/token";

                    if (null != sipResult) {
                        startBocopOauth(ContainerInfo.getUser(), sipResult.getEncryptPassword(),
                                sipResult.getEncryptRandomNum(), randomId, AppInfo.getAppKeyValue(),
                                AppInfo.getAppSecretValue(), strUrl);
                        Logger.d("enpassword-----" + sipResult.getEncryptPassword());
                        Logger.d("enrandom-----" + sipResult.getEncryptRandomNum());
                    } else {
                        if (mSpinner.isShowing()) {
                            mSpinner.dismiss();
                        }

                        //String strInputError = ResourceUtil.parseAssetsString(LoginActivity.this, "sipinputerror");
                        Toast.makeText(LoginActivity.this,"Erro", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    if (mSpinner.isShowing()) {
                        mSpinner.dismiss();
                    }
                    if (null != msg.obj) {
                        String strError = msg.obj.toString();
                        Toast.makeText(LoginActivity.this, strError, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };*/


}
