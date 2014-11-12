package com.hustunique.bocp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.hustunique.bocp.Adapters.Drawermenuadapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.Constants;
import com.hustunique.bocp.Fragments.FragmentOne;
import com.hustunique.bocp.Fragments.FragmentThree;
import com.hustunique.bocp.Fragments.FragmentTwo;

import java.util.List;

public class MainActivity extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    private ListView drawerlistmenu,drawerarraylist;
    private ImageView mainaddbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     mainaddbtn=(ImageView)findViewById(R.id.main_addbtn);
     drawerlistmenu=(ListView)findViewById(R.id.drawermenulist);
     drawerarraylist=(ListView)findViewById(R.id.drawerarrylist);
        Drawermenuadapter drawermenuadapter=new Drawermenuadapter(MainActivity.this, Constants.menuitem);
      drawerlistmenu.setAdapter(drawermenuadapter);

      drawerlistmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch (position){
                  case 0:break;
                  case 1:toAccsettingactivity();break;
                  case 2:break;
              }
          }
      });

        mainaddbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         }
     });


    }

    @Override
    protected int supplyTabs(List<TabInfo> tabs) {
        tabs.add(new TabInfo(FRAGMENT_ONE, getString(R.string.fragment_one),
                FragmentOne.class));
        tabs.add(new TabInfo(FRAGMENT_TWO, getString(R.string.fragment_two),
                FragmentTwo.class));
        tabs.add(new TabInfo(FRAGMENT_THREE, getString(R.string.fragment_three),
                FragmentThree.class));

        return FRAGMENT_TWO;
    }

    private void toAccsettingactivity(){
        Intent intent=new Intent(MainActivity.this,AccountSettingActivity.class);
        startActivity(intent);
    }

}
