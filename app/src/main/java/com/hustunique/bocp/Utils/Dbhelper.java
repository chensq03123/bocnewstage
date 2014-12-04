package com.hustunique.bocp.Utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Dbhelper {
    public static String path;

    public static SQLiteDatabase createorOpenDatabase(){
        SQLiteDatabase sld=null;
        try{
            sld=SQLiteDatabase.openOrCreateDatabase(path+"/kyapp.db3",null);
        }catch(Exception e){e.printStackTrace();}
        return sld;
    }

    public static void createTable(){
        SQLiteDatabase sld=createorOpenDatabase();
        try{
            sld.execSQL("create table if not exists book(id Integer primary key autoincrement not null,"
                    + "bookname varchar(50) not null,"
                    +"author varchar(50),"
                    + "publisher varchar(100),"
                    + "nofchap Integer,"
                    + "chapcomp Integer,"
                    + "color Integer) ");
            sld.execSQL("create table if not exists chaptable(id Integer primary key autoincrement not null," +
                    "bookid Integer," +
                    "chapname varchar(100)," +
                    "tag Integer," +
                    "color Integer)");

            sld.execSQL("create table if not exists maintable(id Integer primary key autoincrement not null," +
                    "chapname varchar(100)," +
                    "bookname varchar(100)," +
                    "chapid Integer," +
                    "color Integer)");

            sld.close();
        }catch(Exception e){}
    }

    public static boolean updatechaptag(int tag,int id){
        SQLiteDatabase sld=createorOpenDatabase();
        try{
           // sld.execSQL("update chaptable set tag=? where id=?",new Object[]{tag,id});
            ContentValues cv=new ContentValues();
            cv.put("tag",tag);
            sld.update("chaptable",cv,"id=?",new String[]{String.valueOf(id)});
        }catch (Exception e){}
        return false;
    }

    public static boolean insertchap(String chapname,int bookid){
        try{
            String sql="insert into chaptable(bookid,chapname,tag) values("+String.valueOf(bookid)+",\""+chapname+"\","+"0"+")";
            SQLiteDatabase sld=createorOpenDatabase();
            sld.execSQL(sql);
        }catch (Exception e){};
        return false;
    }

    public static boolean delete(String sql){
        SQLiteDatabase sld=createorOpenDatabase();
        try{
            sld.execSQL(sql);
            sld.close();
            return true;
        }catch(Exception e){}
        return false;
    }

    public static boolean cleartable(){
        SQLiteDatabase sld=createorOpenDatabase();
        sld.execSQL("delete from maintable");
        sld.close();
        return false;
    }

    public static ArrayList<Map<String,String>> querybook(String sql,String temp){
        Vector<Vector<String>> result=query(sql);
        ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
        for(int i=0;i<result.size();i++){
            Map<String,String> map=new HashMap<String, String>();
            map.put("id",result.get(i).get(0));
            map.put("bookname", result.get(i).get(1));
            map.put("author", result.get(i).get(2));
            map.put("publisher",result.get(i).get(3));
            map.put("nofchap", result.get(i).get(4));
            map.put("chapcomp", result.get(i).get(5));
            map.put("color",result.get(i).get(6));
            list.add(map);
        }
        return list;
    }

    public static ArrayList<Map<String,String>> querymaintable(String sql,String temp){
        Vector<Vector<String>> result=query(sql);
        ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
        for(int i=0;i<result.size();i++){
            Map<String,String> map=new HashMap<String, String>();
            map.put("id",result.get(i).get(0));
            map.put("bookname", result.get(i).get(2));
            map.put("chapname", result.get(i).get(1));
            map.put("chapid",result.get(i).get(3));
            map.put("color",result.get(i).get(4));
            list.add(map);
        }
        return list;
    }

    public static String querybookid(String sql,String temp){
        Vector<Vector<String>> result=query(sql);
        String id=result.get(result.size()-1).get(0);
        return id;
    }

    public static ArrayList<Map<String,String>> querychapter(String sql,String temp){
        Vector<Vector<String>> result=query(sql);
        Log.i("sql",sql);
        ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
        for(int i=0;i<result.size();i++){
            Map<String,String> map=new HashMap<String, String>();
            map.put("id",result.get(i).get(0));
            map.put("chapname", result.get(i).get(2));
            map.put("tag", result.get(i).get(3));
            list.add(map);
        }
        Log.i("list",String.valueOf(list.size()));
        return list;
    }

    public static Vector<Vector<String>> query(String sql){
        Vector<Vector<String>> vector=new Vector<Vector<String>>();
        SQLiteDatabase sld=createorOpenDatabase();
        try{
            Cursor cur=sld.rawQuery(sql, new String[] {});
            while(cur.moveToNext()){
                Vector<String> v=new Vector<String>();
                int col=cur.getColumnCount();
                for(int i=0;i<col;i++){
                    v.add(cur.getString(i));
                }
                vector.add(v);
            }
            cur.close();
            sld.close();
        }catch(Exception e){}
        return vector;
    }
}