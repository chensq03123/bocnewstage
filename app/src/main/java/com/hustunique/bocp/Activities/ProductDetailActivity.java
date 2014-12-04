package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by chensq on 14-12-4.
 */
public class ProductDetailActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_productdetail);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // Holo light action bar color is #DDDDDD
            int actionBarColor = Color.rgb(0xc6, 0x28, 0x28);
            tintManager.setTintColor(actionBarColor);
        }

        final MaterialMenuView menuView=(MaterialMenuView)findViewById(R.id.prodetail_back);
        menuView.setState(MaterialMenuDrawable.IconState.ARROW);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailActivity.this.finish();
            }
        });

        Intent intent=getIntent();
        String title=intent.getStringExtra(AppConstants.STR_PROTILE);
        String url=intent.getStringExtra(AppConstants.STR_PROURL);

        WebView mwebview=(WebView)findViewById(R.id.product_webview);
        mwebview.setWebViewClient(new WebViewClient(){
            ProgressDialog progDlg=null;
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if(progDlg == null || !progDlg.isShowing()){
                    progDlg = new ProgressDialog(ProductDetailActivity.this);
                    progDlg.setMessage("正在加载，请稍候...");
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
        mwebview.loadUrl(url);
    }
}
