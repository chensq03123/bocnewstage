package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-19.
 */
public class PayActivity extends Activity {


    private FragmentManager fragmentManager;
    private Spinner cardselection;
    private String[] mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payactivity);
        mlist=new String[20];
        for(int i=0;i<mlist.length;i++){
            mlist[i]=String.valueOf(i);
        }


        cardselection=(Spinner)findViewById(R.id.pay_cardselection);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(PayActivity.this,android.R.layout.simple_list_item_1,mlist){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        cardselection.setAdapter(adapter);
    }
}
