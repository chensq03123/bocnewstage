package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;

import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-19.
 */
public class PayActivity extends Activity {


    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payactivity);

    }
}
