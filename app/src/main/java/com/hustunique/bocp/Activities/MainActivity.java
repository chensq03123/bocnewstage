package com.hustunique.bocp.Activities;

import android.os.Bundle;
import android.widget.ListView;

import com.hustunique.bocp.Adapters.Drawermenuadapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.Constants;
import com.ryg.fragment.FragmentOne;
import com.ryg.fragment.FragmentThree;
import com.ryg.fragment.FragmentTwo;

import java.util.List;

public class MainActivity extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    private ListView drawerlistmenu,drawerarraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     drawerlistmenu=(ListView)findViewById(R.id.drawermenulist);
     drawerarraylist=(ListView)findViewById(R.id.drawerarrylist);
        Drawermenuadapter drawermenuadapter=new Drawermenuadapter(MainActivity.this, Constants.menuitem);
      drawerlistmenu.setAdapter(drawermenuadapter);


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

}
