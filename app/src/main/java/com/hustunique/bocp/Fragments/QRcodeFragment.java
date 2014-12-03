package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hustunique.bocp.Activities.AccountSettingActivity;
import com.hustunique.bocp.Activities.NewTradActivity;
import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-30.
 */
public class QRcodeFragment extends Fragment {

    ImageView qrimg;
    Bitmap bitmap;
    private Context mcontext;
    private NewTradActivity activity;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
        if(activity instanceof NewTradActivity){
            this.activity=(NewTradActivity)activity;
        }
    }

    public void setimg(Bitmap bitmap){
        qrimg.setImageBitmap(bitmap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_qrcode,null);
        qrimg=(ImageView)v.findViewById(R.id.qrimg);
        qrimg.setImageBitmap(this.activity.getbitmap());
        return v;
    }
}
