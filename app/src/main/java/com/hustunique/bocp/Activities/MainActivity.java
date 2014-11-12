package com.hustunique.bocp.Activities;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.gesturepasswd.LockActivity;
import com.hustunique.bocp.Utils.gesturepasswd.LockSetupActivity;
/*
 * Author: Ruils 心怀产品梦的安卓码农 
 * Blog: http://blog.csdn.net/ruils
 * QQ: 5452781
 * Email: 5452781@qq.com
 */

public class MainActivity extends Activity implements OnClickListener {
    // 12-01 18:26:09.907: I/ActivityManager(519): Displayed
    // com.android.settings/.ChooseLockPattern: +236ms
    // 12-01 19:35:14.870: I/ActivityManager(519): Displayed
    // com.android.settings/.ConfirmLockPattern: +366ms (total +439ms)

    private static final String TAG = "MainActivity";

    public static final String LOCK = "lock";
    public static final String LOCK_KEY = "lock_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(LOCK, MODE_PRIVATE);

        String lockPattenString = preferences.getString(LOCK_KEY, null);

        if (lockPattenString != null) {
            Intent intent = new Intent(this, LockActivity.class);
            startActivity(intent);

        }

        AlertDialog.Builder bd=new AlertDialog.Builder(MainActivity.this);
        View v= LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_moneyremaining,null);
        bd.setView(v);
        bd.create();
        bd.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.lock:
            Intent intent = new Intent(this, LockSetupActivity.class);
            startActivity(intent);
            break;
        case R.id.unlock:
            getSharedPreferences(LOCK, MODE_PRIVATE).edit().clear().commit();
            break;

        default:
            break;
        }

    }

}
