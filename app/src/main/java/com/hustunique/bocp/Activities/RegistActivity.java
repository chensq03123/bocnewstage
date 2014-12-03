package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.views.ProgressWebView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by chensq on 14-11-30.
 */
public class RegistActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_regist);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }

        WebView mwebview=(WebView)findViewById(R.id.registwebview);
       // WebViewClient client=new WebViewClient();
       mwebview.setWebViewClient(new WebViewClient(){
            ProgressDialog progDlg=null;
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("sssssssssssss","webview");
                if(progDlg == null || !progDlg.isShowing()){
                    progDlg = new ProgressDialog(RegistActivity.this);
                    progDlg.setMessage("正在加载，请稍候。。。");
                }
                progDlg.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progDlg.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        mwebview.getSettings().setJavaScriptEnabled(true);
        mwebview.loadUrl("https://open.boc.cn/wap/register.php");
    }
}
