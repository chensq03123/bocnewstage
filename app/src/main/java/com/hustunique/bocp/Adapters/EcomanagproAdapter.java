package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chensq on 14-11-11.
 */
public class EcomanagproAdapter extends BaseAdapter {
    private List<Map<String,Object>> mlist;
    private Context mcontext;

    public EcomanagproAdapter(Context context,List<Map<String,Object>>  list){
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
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_ecomanagprolist,null);
            holder.title=(TextView)convertView.findViewById(R.id.ecptitle);
            holder.date=(TextView)convertView.findViewById(R.id.ecpdate);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
            //holder.date=
        }

        holder.title.setText(mlist.get(position).get("title").toString());
        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView date;
    }
}
