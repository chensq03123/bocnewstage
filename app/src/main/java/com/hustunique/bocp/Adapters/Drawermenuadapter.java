package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-11.
 */
public class Drawermenuadapter extends BaseAdapter {

    private String[] mlist;
    private Context mcontext;

    public Drawermenuadapter(Context context,String[] strings){
        this.mcontext=context;
        this.mlist=strings;
    }

    @Override
    public int getCount() {
        return mlist.length;
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
             convertView= LayoutInflater.from(mcontext).inflate(R.layout.drawerlist_item,null);
            holder.mimg=(ImageView)convertView.findViewById(R.id.draweritemimg);
            holder.mt=(TextView)convertView.findViewById(R.id.draweritemtext);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.mt.setText(mlist[position]);

        return convertView;
    }

    class ViewHolder{
        ImageView mimg;
        TextView mt;
    }


}
