package com.hustunique.bocp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-12.
 */
public class CardManagementFragment extends Fragment {

    private Context mcontext;

    public CardManagementFragment(){
        super();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cardsmanagement,null);
        return view;
    }
}
