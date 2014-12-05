package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chensq on 14-12-5.
 */
public class CharityBaseAdapter extends BaseAdapter {

    private ArrayList<Map<String, String>> mlist;
    private Context mcontext;

    public CharityBaseAdapter(Context context, ArrayList<Map<String,String>> list) {
        this.mcontext = context;
        this.mlist = list;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_charityinfo, null);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        return convertView;

    }
    class ViewHolder{
        ImageView mimg;
        TextView mt;
    }
}
