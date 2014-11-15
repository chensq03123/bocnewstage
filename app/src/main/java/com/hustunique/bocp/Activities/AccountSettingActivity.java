package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-11.
 */
public class AccountSettingActivity extends Activity implements View.OnClickListener{

    private LinearLayout cardmanage,gesturepassword,tradelimitation,remainalarm;
    private MaterialMenuView menuView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_accountsetting);
        InitWidgets();

       /* AccountsettingFragment asfragment=new AccountsettingFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.accsetting_fram,asfragment);
        transaction.addToBackStack(null);
        transaction.commit();*/
    }

        private void InitWidgets(){
            cardmanage=(LinearLayout)findViewById(R.id.setting_cardmanagement);
            gesturepassword=(LinearLayout)findViewById(R.id.setting_gesturepasswd);
            tradelimitation=(LinearLayout)findViewById(R.id.setting_tradelimitation);
            remainalarm=(LinearLayout)findViewById(R.id.setting_remainalarm);
            cardmanage.setOnClickListener(this);
            gesturepassword.setOnClickListener(this);
            tradelimitation.setOnClickListener(this);
            remainalarm.setOnClickListener(this);
            menuView=(MaterialMenuView)findViewById(R.id.accountsetting_menu);
            menuView.setState(MaterialMenuDrawable.IconState.ARROW);
            menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuView.animatePressedState(MaterialMenuDrawable.IconState.BURGER);
                }
            });
        }

    private void Cardmanage(){
        CardManagementFragment asfragment=new CardManagementFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.accsetting_fram,asfragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void GesturePassword(){
        AlertDialog.Builder bd=new AlertDialog.Builder(AccountSettingActivity.this);
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_gesturepasswdsetting,null);
        bd.setView(v);
        bd.create();
        bd.show();
    }

    private void Tradelimit(){

    }

    private void Remainalarm(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_cardmanagement:Cardmanage();break;
            case R.id.setting_gesturepasswd:GesturePassword();break;
            case R.id.setting_tradelimitation:break;
            case R.id.setting_remainalarm:break;
        }
    }
}
