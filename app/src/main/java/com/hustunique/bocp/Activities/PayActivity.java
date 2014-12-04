package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.hustunique.bocp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chensq on 14-11-19.
 */
public class PayActivity extends Activity {


    private FragmentManager fragmentManager;
    private Spinner cardselection;
    private String[] mlist;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payactivity);

        MaterialMenuView menuView=(MaterialMenuView)findViewById(R.id.pay_back);
        menuView.setState(MaterialMenuDrawable.IconState.ARROW);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayActivity.this.finish();
            }
        });

        queue= Volley.newRequestQueue(PayActivity.this);

        Intent intent=getIntent();
        if(intent.getStringExtra("QR_TID")!=null){
            String tid=intent.getStringExtra("QR_TID");
            requsttradeinfo(tid);
        }

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

    private void requsttradeinfo(String tid){
        final String mtid=tid;
        StringRequest stringRequest=new StringRequest(Request.Method.POST,"http://104.160.39.34:8000/verifytrade/",new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("requesttradeifo",response);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<String, String>();
                Log.i("paytid",mtid);
                map.put("tid",mtid);
                return map;
            }
        };

        queue.add(stringRequest);

    }

}
