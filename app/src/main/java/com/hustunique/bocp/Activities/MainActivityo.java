package com.hustunique.bocp.Activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenu;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.common.Constants;
import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.fund.Fund900Response;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.http.AsyncHttpClient;
import com.boc.bocop.sdk.http.JsonResponseListenerAdapterHandler;
import com.boc.bocop.sdk.service.BaseService;
import com.hustunique.bocp.Adapters.Drawermenuadapter;
import com.hustunique.bocp.Adapters.SubListAdapter;
import com.hustunique.bocp.R;
import com.hustunique.bocp.Utils.AppConstants;
import com.hustunique.bocp.Utils.BalanceCriteria;
import com.hustunique.bocp.Fragments.FragmentOne;
import com.hustunique.bocp.Fragments.FragmentThree;
import com.hustunique.bocp.Fragments.FragmentTwo;
import com.hustunique.bocp.Utils.NetworkConstant;
import com.hustunique.bocp.Utils.PromotedActionsLibrary;
import com.ryg.fragment.ui.ViewPagerCompat;

import java.util.List;
import java.util.Random;

public class MainActivityo extends IndicatorFragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    private ListView drawerlistmenu,drawerarraylist;
    private ImageView mainaddbtn;
    private ViewPagerCompat compat;
    private String uid=null;
    private DrawerLayout mDrawerLayout;
    private MaterialMenuView materialMenuView;
    private int              materialButtonState;
    private MaterialMenu materialIcon;
    private DrawerLayout     drawerLayout;
    private boolean          direction;
    private int menustate;
    private ObjectAnimator presscircle;

    private Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(MainActivityo.this,msg.obj.toString(),Toast.LENGTH_LONG).show();
            }

        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.btn_options);

            PromotedActionsLibrary promotedActionsLibrary = new PromotedActionsLibrary();

// setup library
            promotedActionsLibrary.setup(getApplicationContext(), frameLayout);

// create onClickListener for each promoted action
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Do something
                }
            };

// customize promoted actions with a drawable
            promotedActionsLibrary.addItem(getResources().getDrawable(R.drawable.add_btn), onClickListener);
            promotedActionsLibrary.addItem(getResources().getDrawable(R.drawable.add_btn), onClickListener);
            promotedActionsLibrary.addItem(getResources().getDrawable(R.drawable.add_btn), onClickListener);

// create main floating button and customize it with a drawable
            promotedActionsLibrary.addMainItem(getResources().getDrawable(R.drawable.add_btn));

        }



        uid=getIntent().getStringExtra("UID");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                direction = true;
            }

            @Override
            public void onDrawerClosed(android.view.View drawerView) {
                direction = false;
            }
        });

        materialMenuView=(MaterialMenuView)findViewById(R.id.action_bar_menu);
        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menustate = generateState(menustate);
                materialMenuView.animatePressedState(intToState(menustate));
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
        //mainaddbtn=(ImageView)findViewById(R.id.main_addbtn);
        drawerlistmenu=(ListView)findViewById(R.id.drawermenulist);
        drawerarraylist=(ListView)findViewById(R.id.drawerarrylist);
        Drawermenuadapter drawermenuadapter=new Drawermenuadapter(MainActivityo.this, AppConstants.menuitem);
        SubListAdapter subadapter= new SubListAdapter(MainActivityo.this, AppConstants.submenulistitem);
        drawerarraylist.setAdapter(subadapter);
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

       /*  mainaddbtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            ObjectAnimator a1= ObjectAnimator.ofFloat(mainaddbtn,"scaleX",1.0f,2f);
             ObjectAnimator a2=ObjectAnimator.ofFloat(mainaddbtn,"scaleY",1.0f,2f);
           //  ObjectAnimator a3=ObjectAnimator.ofFloat(mainaddbtn,"")
             AnimatorSet set=new AnimatorSet();
             set.setDuration(2000);
             set.setInterpolator(new DecelerateInterpolator());
             set.play(a1).with(a2);
             set.start();
         }
        });*/

        compat=(ViewPagerCompat)findViewById(R.id.pager);

        final Animation animation3= AnimationUtils.loadAnimation(MainActivityo.this,R.anim.animation_in);
        final Animation animation4=AnimationUtils.loadAnimation(MainActivityo.this,R.anim.animation_out);

        final ScaleAnimation animation1 =new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final ScaleAnimation animation2 =new ScaleAnimation(1.0f, (1.0f/1.4f), 1.0f, (1.0f/1.4f),
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation1.setDuration(2000);
        animation2.setDuration(2000);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation2.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation1.startNow();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation1.setRepeatMode(Animation.REVERSE);
       // mainaddbtn.setAnimation(animation2);
        animation2.startNow();

       /* mainaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // compat.setVisibility(View.VISIBLE);
                //compat.startAnimation(animation3);
               /*mcreditbalsearch(MainActivityo.this, NetworkConstant.CONSUMER_KEY,NetworkConstant.CONSUMER_SECRET,"2014091500000615",new ResponseListener() {
                    @Override
                    public void onComplete(ResponseBean responseBean) {
                        BOCOPOAuthInfo info=(BOCOPOAuthInfo)responseBean;
                        info.getRtnmsg();
                        mhandler.obtainMessage(1,info.getRtnmsg().toString()).sendToTarget();
                    }

                    @Override
                    public void onException(Exception e) {

                    }

                    @Override
                    public void onError(Error error) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                */
              /*  LinearLayout layout=(LinearLayout)findViewById(R.id.btn_options);
                layout.setVisibility(View.VISIBLE);
                layout.startAnimation(animation3);
            }
        });*/

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
        Intent intent=new Intent(MainActivityo.this,AccountSettingActivity.class);
        startActivity(intent);
    }


    public  void mcreditbalsearch(final Context context, String AppKey,String AppSecret,String mLmtamt,ResponseListener handler) {
        BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context, AppKey, AppSecret);

        BalanceCriteria criteria = new BalanceCriteria();
        criteria.setUserid(uid);
//		criteria.setLmtamt("2014091500000615");
        criteria.setLmtamt(mLmtamt);
        creditbalsearch(context, criteria, handler);
    }

    public  void creditbalsearch(Context context,BalanceCriteria criteria, ResponseListener listener) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Constants.httpPrefix+ "/app/creditbalsearch", BaseService.genPublicAsrHeader(context), criteria, new JsonResponseListenerAdapterHandler<Fund900Response>(Fund900Response.class, listener));
    }

    public static int generateState(int previous) {
        int generated = new Random().nextInt(4);
        return generated != previous ? generated : generateState(previous);
    }

    public static MaterialMenuDrawable.IconState intToState(int state) {
        switch (state) {
            case 0:
                return MaterialMenuDrawable.IconState.BURGER;
            case 1:
                return MaterialMenuDrawable.IconState.ARROW;
            case 2:
                return MaterialMenuDrawable.IconState.X;
            case 3:
                return MaterialMenuDrawable.IconState.CHECK;
        }
        throw new IllegalArgumentException("Must be a number [0,3)");
    }
}
