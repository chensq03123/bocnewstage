package com.hustunique.bocp.Adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustunique.bocp.R;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by chensq on 14-11-22.
 */
public class CardsExpandableListAdapter extends BaseExpandableListAdapter {

    ArrayList<Map<String,String>> mparentlist;
    String[] childlist;
    Context mcontext;

    public CardsExpandableListAdapter(Context context,ArrayList<Map<String,String>> parentlist,String[] childlist){
        this.mcontext=context;
        this.childlist=childlist;
        this.mparentlist=parentlist;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return mparentlist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childlist.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_parentlist,null);
            holder.carsubname=(TextView)convertView.findViewById(R.id.car_subname);
            holder.moneyremain=(TextView)convertView.findViewById(R.id.car_remain);
            holder.totleimg=(ImageView)convertView.findViewById(R.id.expandicon);
            convertView.setTag(holder);
        }else
            holder=(ViewHolder)convertView.getTag();

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        childlistViewHolder holder;
        if(convertView==null){
            holder=new childlistViewHolder();
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_childlist,null);
            holder.t=(TextView)convertView.findViewById(R.id.childlist_options);
            convertView.setTag(holder);
        }else
            holder=(childlistViewHolder)convertView.getTag();
            holder.t.setText(childlist[childPosition]);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private class ViewHolder{
        TextView carsubname,moneyremain;
        ImageView totleimg;
    }

    private class childlistViewHolder{
        TextView t;
    }


}
