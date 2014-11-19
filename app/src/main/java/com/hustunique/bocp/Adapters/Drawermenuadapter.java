package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chensq on 14-11-11.
 */
public class Drawermenuadapter extends BaseAdapter {

    private ArrayList<Map<String,Integer>> mlist;
    private Context mcontext;

    public Drawermenuadapter(Context context,ArrayList<Map<String,Integer>> list){
        this.mcontext=context;
        this.mlist=list;
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
        if(convertView==null){
            holder=new ViewHolder();
             convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_drawerlist,null);
            holder.mimg=(ImageView)convertView.findViewById(R.id.draweritemimg);
            holder.mt=(TextView)convertView.findViewById(R.id.draweritemtext);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.mt.setText(AppConstants.menuitem[position]);
        holder.mimg.setImageResource((mlist.get(position).get(AppConstants.menuitem[position])));
        return convertView;
    }

    class ViewHolder{
        ImageView mimg;
        TextView mt;
    }


}
