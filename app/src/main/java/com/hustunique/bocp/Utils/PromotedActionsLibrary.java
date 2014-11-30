package com.hustunique.bocp.Utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hustunique.bocp.R;

import java.util.ArrayList;


public class PromotedActionsLibrary {

    Context context;

    FrameLayout frameLayout;
    LinearLayout mbgn;
    ImageView mainImageButton;

    RotateAnimation rotateOpenAnimation;

    RotateAnimation rotateCloseAnimation;

    ArrayList<ImageView> promotedActions;

    ObjectAnimator objectAnimator[];

    private int px;

    private static final int ANIMATION_TIME = 100;
    private  AlphaAnimation openalphaAnimation,closealphaAnimation;
    private boolean isMenuOpened;

    public void setup(Context activityContext, FrameLayout layout,LinearLayout mbgn) {
        context = activityContext;
        promotedActions = new ArrayList<ImageView>();
        frameLayout = layout;
        px = (int) context.getResources().getDimension(R.dimen.dim56dp) + 10;
        openRotation();
        closeRotation();
        this.mbgn=mbgn;
        openalphaAnimation=new AlphaAnimation(0,0.9f);
        closealphaAnimation=new AlphaAnimation(0.9f,0);
        openalphaAnimation.setDuration(200);
        closealphaAnimation.setDuration(200);
        openalphaAnimation.setFillAfter(true);
        openalphaAnimation.setFillEnabled(true);
        closealphaAnimation.setFillAfter(true);
        closealphaAnimation.setFillEnabled(true);
        mbgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMenuOpened){
                    closePromotedActions().start();
                    v.setClickable(false);
                    v.setVisibility(View.GONE);
                    v.startAnimation(closealphaAnimation);
                    isMenuOpened = false;
                }
            }
        });
    }



    public ImageView addMainItem(Drawable drawable) {

        ImageView button = (ImageView) LayoutInflater.from(context).inflate(R.layout.main_promoted_action_button, frameLayout, false);

        button.setImageDrawable(drawable);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isMenuOpened) {
                    closePromotedActions().start();
                    mbgn.startAnimation(closealphaAnimation);
                    mbgn.setClickable(false);
                    mbgn.setVisibility(View.GONE);
                    isMenuOpened = false;
                } else {
                    isMenuOpened = true;
                    mbgn.setClickable(true);
                    mbgn.startAnimation(openalphaAnimation);
                    openPromotedActions().start();
                    mbgn.setVisibility(View.VISIBLE);

                }
            }
        });

        frameLayout.addView(button);

        mainImageButton = button;

        return button;
    }

    public void addItem(Drawable drawable, View.OnClickListener onClickListener) {

        ImageView button = (ImageView) LayoutInflater.from(context).inflate(R.layout.promoted_action_button, frameLayout, false);


        button.setImageDrawable(drawable);

        button.setOnClickListener(onClickListener);

        promotedActions.add(button);
        frameLayout.addView(button);

        return;
    }

    /**
     * Set close animation for promoted actions
     */
    public AnimatorSet closePromotedActions() {

        if (objectAnimator == null){
            objectAnimatorSetup();
        }

        AnimatorSet animation = new AnimatorSet();

        for (int i = 0; i < promotedActions.size(); i++) {

            objectAnimator[i] = setCloseAnimation(promotedActions.get(i), i);
        }

        if (objectAnimator.length == 0) {
            objectAnimator = null;
        }

        animation.playTogether(objectAnimator);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
               // mainImageButton.startAnimation(rotateCloseAnimation);
                mainImageButton.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mainImageButton.setClickable(true);
                hidePromotedActions();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                mainImageButton.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        return animation;
    }

    public AnimatorSet openPromotedActions() {

        if (objectAnimator == null){
            objectAnimatorSetup();
        }



        AnimatorSet animation = new AnimatorSet();

        for (int i = 0; i < promotedActions.size(); i++) {

            objectAnimator[i] = setOpenAnimation(promotedActions.get(i), i);
        }

        if (objectAnimator.length == 0) {
            objectAnimator = null;
        }

        animation.playTogether(objectAnimator);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
               // mainImageButton.startAnimation(rotateOpenAnimation);
                mainImageButton.setClickable(false);
                showPromotedActions();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mainImageButton.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                mainImageButton.setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });


        return animation;
    }

    private void objectAnimatorSetup() {

        objectAnimator = new ObjectAnimator[promotedActions.size()];
    }


    /**
     * Set close animation for single button
     *
     * @param promotedAction
     * @param position
     * @return objectAnimator
     */
    private ObjectAnimator setCloseAnimation(ImageView promotedAction, int position) {

        ObjectAnimator objectAnimator;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            objectAnimator = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, -px * (promotedActions.size() - position), 0f);
            objectAnimator.setRepeatCount(0);
            objectAnimator.setDuration(ANIMATION_TIME * (promotedActions.size() - position));

        } else {

            objectAnimator = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, -px * (promotedActions.size() - position), 0f);
            objectAnimator.setRepeatCount(0);
            objectAnimator.setDuration(ANIMATION_TIME * (promotedActions.size() - position));
        }



        return objectAnimator;
    }

    /**
     * Set open animation for single button
     *
     * @param promotedAction
     * @param position
     * @return objectAnimator
     */
    private ObjectAnimator setOpenAnimation(ImageView promotedAction, int position) {

        ObjectAnimator objectAnimator;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            objectAnimator = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, 0f, -px * (promotedActions.size() - position));
            objectAnimator.setRepeatCount(0);
            objectAnimator.setDuration(ANIMATION_TIME * (promotedActions.size() - position));

        } else {
            objectAnimator = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, 0f, -px * (promotedActions.size() - position));
            objectAnimator.setRepeatCount(0);
            objectAnimator.setDuration(ANIMATION_TIME * (promotedActions.size() - position));
        }
        objectAnimator.setInterpolator(new OvershootInterpolator(1.2f));
        return objectAnimator;
    }

    private void hidePromotedActions() {

        for (int i = 0; i < promotedActions.size(); i++) {
            promotedActions.get(i).setVisibility(View.GONE);
        }
    }

    private void showPromotedActions() {

        for (int i = 0; i < promotedActions.size(); i++) {
            promotedActions.get(i).setVisibility(View.VISIBLE);
        }
    }

    private void openRotation() {

        rotateOpenAnimation = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateOpenAnimation.setFillAfter(true);
        rotateOpenAnimation.setFillEnabled(true);
        rotateOpenAnimation.setDuration(ANIMATION_TIME);
    }

    private void closeRotation() {

        rotateCloseAnimation = new RotateAnimation(45, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateCloseAnimation.setFillAfter(true);
        rotateCloseAnimation.setFillEnabled(true);
        rotateCloseAnimation.setDuration(ANIMATION_TIME);
    }

    public void closepopup(){
        closePromotedActions().start();
        mbgn.startAnimation(closealphaAnimation);
        mbgn.setClickable(false);
        mbgn.setVisibility(View.GONE);
        isMenuOpened = false;
    }

    public boolean Ispopup(){
        return isMenuOpened;
    }

}
