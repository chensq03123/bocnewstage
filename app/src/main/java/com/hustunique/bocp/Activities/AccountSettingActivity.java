package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.hustunique.bocp.Fragments.CardManagementFragment;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.gesturepasswd.LockPatternView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chensq on 14-11-11.
 */
public class AccountSettingActivity extends Activity implements View.OnClickListener{

    private LinearLayout cardmanage,gesturepassword,tradelimitation,remainalarm;
    private MaterialMenuView menuView;
    private int Step=1;
    String temp1=null;
    String temp2=null;

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
        final SharedPreferences preferences = getSharedPreferences(AppConstants.LOCK, MODE_PRIVATE);
        final String lockPattenString = preferences.getString(AppConstants.LOCK_KEY, null);

        AlertDialog.Builder bd=new AlertDialog.Builder(AccountSettingActivity.this);
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_gesturepasswdsetting,null);
        final TextView hint=(TextView)v.findViewById(R.id.gp_hint);
        if(lockPattenString==null) {
            hint.setText(R.string.str_inputoriginpasswd);
            Step=2;
        }
        else {
            hint.setText("请输入原密码");
        }
        final LockPatternView gpview=(LockPatternView)v.findViewById(R.id.gesturepasswd_setting);
        gpview.setOnPatternListener(new LockPatternView.OnPatternListener() {
            @Override
            public void onPatternStart() {

            }

            @Override
            public void onPatternCleared() {

            }

            @Override
            public void onPatternCellAdded(List<LockPatternView.Cell> pattern) {

            }

            @Override
            public void onPatternDetected(List<LockPatternView.Cell> pattern) {
                ArrayList<LockPatternView.Cell> choosePattern = null;

                if (pattern.size() < LockPatternView.MIN_LOCK_PATTERN_SIZE) {
                    Toast.makeText(AccountSettingActivity.this,
                            R.string.lockpattern_recording_incorrect_too_short,
                            Toast.LENGTH_LONG).show();
                    gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                    gpview.clearPattern();
                    return;
                }

                if (choosePattern == null) {
                    choosePattern = new ArrayList<LockPatternView.Cell>(pattern);
                    String patternstr = Arrays.toString(choosePattern.toArray());
                    List<LockPatternView.Cell>  lockPattern = LockPatternView.stringToPattern(lockPattenString);
                    switch (Step) {
                        case 1:
                            //List<LockPatternView.Cell>  lockPattern = LockPatternView.stringToPattern(lockPattenString);
                            if (!pattern.equals(lockPattern)){
                                Toast.makeText(AccountSettingActivity.this,
                                        "wrong password"+lockPattenString,
                                        Toast.LENGTH_LONG).show();
                                gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);
                                gpview.clearPattern();
                            }else{
                                gpview.clearPattern();
                                hint.setText("请输入新密码");
                                Step=2;
                            }
                                break;
                        case 2:
                             if(lockPattenString!=null&&pattern.equals(lockPattern)){
                                 Toast.makeText(AccountSettingActivity.this,
                                         "the same with original password",
                                         Toast.LENGTH_LONG).show();
                                 gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                                 gpview.clearPattern();
                             }else{
                                 temp1= LockPatternView.patternToString(pattern);
                                 hint.setText("确认新密码");
                                 Step=3;
                                 gpview.clearPattern();
                             }
                            break;
                        case 3:
                            if(!pattern.equals(LockPatternView.stringToPattern(temp1))){
                                Toast.makeText(AccountSettingActivity.this,
                                        "wrong password",
                                        Toast.LENGTH_LONG).show();
                                gpview.setDisplayMode(LockPatternView.DisplayMode.Wrong);

                                gpview.clearPattern();
                            }else{
                                preferences
                                        .edit()
                                        .putString(AppConstants.LOCK_KEY,
                                                LockPatternView.patternToString(choosePattern))
                                        .commit();
                                Toast.makeText(AccountSettingActivity.this,
                                        "successed",
                                        Toast.LENGTH_LONG).show();
                            }
                            break;
                    }
                }
            }
        });

        bd.setView(v);
        bd.create();
        bd.show();
    }

    private void Tradelimit(){

    }

    private void Remainalarm(){
        AlertDialog.Builder bd=new AlertDialog.Builder(AccountSettingActivity.this);
        View v= LayoutInflater.from(AccountSettingActivity.this).inflate(R.layout.dialog_tradecomfirm,null);
        bd.setView(v);
        bd.create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_cardmanagement:Cardmanage();break;
            case R.id.setting_gesturepasswd:Step=1;temp1=null;temp2=null;GesturePassword();break;
            case R.id.setting_tradelimitation:break;
            case R.id.setting_remainalarm:Remainalarm();break;
        }
    }
}
