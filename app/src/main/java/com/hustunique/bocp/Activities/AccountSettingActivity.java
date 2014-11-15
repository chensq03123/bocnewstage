package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;

import com.hustunique.bocp.Fragments.AccountsettingFragment;
import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-11.
 */
public class AccountSettingActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_accountsetting);
       /* AccountsettingFragment asfragment=new AccountsettingFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.accsetting_fram,asfragment);
        transaction.addToBackStack(null);
        transaction.commit();*/


    }
}
