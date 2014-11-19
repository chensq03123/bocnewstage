package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.bocp.R;

/**
 * Created by chensq on 14-11-15.
 */
public class SubListAdapter extends BaseAdapter {


    private String[] mlist;
    private Context mcontext;

    public SubListAdapter(Context context,String[] strings){
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
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_drawersublist,null);
            holder.mt=(TextView)convertView.findViewById(R.id.sublist_text);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        holder.mt.setText(mlist[position]);
        return convertView;
    }
    class ViewHolder{
        TextView mt;
    }

}
