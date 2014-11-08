package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chensq on 14-11-9.
 */
public class TradeRecordBaseAdapter extends BaseAdapter {

    private ArrayList<Map<String,String>> mlist;
    private Context mcontext;

    public  TradeRecordBaseAdapter(Context context,ArrayList<Map<String,String>> list){
        this.mcontext=context;
        this.mlist=list;
    }

    @Override
    public int getCount() {
        return this.mlist.size();
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
        ViewHolder mholder;
        if(convertView==null){
            mholder=new ViewHolder();
            convertView=(LinearLayout)LayoutInflater.from(mcontext).inflate(R.layout.traderecord_item,null);
            convertView.setTag(mholder);
        }else{
            mholder=(ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView img;
        TextView cost,carttype,tradetype,tradedate,tradetime;
    }

}
