package com.ryg.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hustunique.bocp.R;

public class FragmentTwo extends Fragment {

    private Context mcontext;

    public FragmentTwo() {
        super();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mcontext=activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_two,null);
        ListView mecmangpro=(ListView)v.findViewById(R.id.ecmanaprolist);

        return v;

    }
}
