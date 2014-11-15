package com.hustunique.bocp.Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Created by chensq on 14-11-15.
 */
public class Mylistview extends ListView{

    private int mTouchSlop;

    private static final int STATE_NORMAL = 0;
    private static final int STATE_READY = 1;
    private static final int STATE_PULL = 2;
    private static final int STATE_UPDATING = 3;
    private static final int INVALID_POINTER_ID = -1;

    private static final int UP_STATE_READY = 4;
    private static final int UP_STATE_PULL = 5;

    private static final int MIN_UPDATE_TIME = 500;

    protected ListHeaderView mListHeaderView;
    protected ListBottomView mListBottomView;

    private int mActivePointerId;
    private float mLastY;

    private int mState;

//    private boolean mPullUpRefreshEnabled = false;

    public Mylistview(Context context){
        super(context);
        initialize();
    }

    public Mylistview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Mylistview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize(){
        final Context context=getContext();
        final ViewConfiguration configuration=ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
       // return super.dispatchTouchEvent(ev);
        final int action=ev.getAction()&MotionEventCompat.ACTION_MASK;
        switch(action){
            case MotionEvent.ACTION_DOWN:
                mActivePointerId=MotionEventCompat.getPointerId(ev,0) ;
                mLastY=ev.getY();
                isFirstViewTop();
                isLastViewBottom();
                break;

            case MotionEvent.ACTION_MOVE:
                if(mActivePointerId==INVALID_POINTER_ID){
                    break;
                }
                if(mState==STATE_NORMAL){
                    isFirstViewTop();
                    isLastViewBottom();
                }

                if(mState==STATE_READY){
                    final int activePointerid=mActivePointerId;
                    final int activePointerindex=MotionEventCompat.findPointerIndex(ev,activePointerid);
                    final float y=MotionEventCompat.getY(ev,activePointerindex);
                    final int deltaY=(int)(y-mLastY);
                    mLastY=y;
                    if(deltaY<=0||Math.abs(y)<mTouchSlop){
                        mState=STATE_NORMAL;
                    }else{
                        mState=STATE_PULL;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(ev);
                    }
                }else if(mState==UP_STATE_READY){
                    final int activePointerId=mActivePointerId;
                    final int activePointerIndex=MotionEventCompat.findPointerIndex(ev,activePointerId);
                    final float y=MotionEventCompat.getY(ev,activePointerIndex);
                    final int deltaY=(int)(y-mLastY);
                    mLastY=y;
                    if (deltaY >= 0 || Math.abs(y) < mTouchSlop) {
                        mState = STATE_NORMAL;
                    } else {
                        mState = UP_STATE_PULL;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                        super.dispatchTouchEvent(ev);
                    }
                }



                if (mState == STATE_PULL) {
                    final int activePointerId = mActivePointerId;
                    final int activePointerIndex = MotionEventCompat
                            .findPointerIndex(ev, activePointerId);
                    final float y = MotionEventCompat.getY(ev, activePointerIndex);
                    final int deltaY = (int) (y - mLastY);
                    mLastY = y;

                    final int headerHeight = mListHeaderView.getHeight();
                    setHeaderHeight(headerHeight + deltaY * 5 / 9);
                    return true;
                } else if (mState == UP_STATE_PULL) {
                    final int activePointerId = mActivePointerId;
                    final int activePointerIndex = MotionEventCompat
                            .findPointerIndex(ev, activePointerId);
                    final float y = MotionEventCompat.getY(ev, activePointerIndex);
                    final int deltaY = (int) (y - mLastY);
                    mLastY = y;
                    final int headerHeight = mListBottomView.getHeight();
                    setBottomHeight(headerHeight - deltaY * 5 / 9);
                    return true;
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mActivePointerId = INVALID_POINTER_ID;
                if (mState == STATE_PULL) {
                    //update();
                } else if (mState == UP_STATE_PULL) {
                   // pullUpUpdate();
                }
                break;
            case MotionEventCompat.ACTION_POINTER_DOWN:
                final int index = MotionEventCompat.getActionIndex(ev);
                final float y = MotionEventCompat.getY(ev, index);
                mLastY = y;
                mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                break;
            case MotionEventCompat.ACTION_POINTER_UP:
               // onSecondaryPointerUp(ev);
                break;
        }
        return super.dispatchTouchEvent(ev);

    }

    private boolean isFirstViewTop() {
        final int count = getChildCount();
        if (count == 0) {
            return true;
        }
        final int firstVisiblePosition = this.getFirstVisiblePosition();
        final View firstChildView = getChildAt(0);
        boolean needs = firstChildView.getTop() == 0
                && (firstVisiblePosition == 0);
        if (needs) {
            mState = STATE_READY;
        }

        return needs;
    }

    private boolean isLastViewBottom() {
        final int count = getChildCount();
        if (count == 0) {
            return false;
        }

        final int lastVisiblePosition = getLastVisiblePosition();
        boolean needs = (lastVisiblePosition == (getAdapter().getCount() - getHeaderViewsCount()))
                && (getChildAt(getChildCount() - 1).getBottom() == (getBottom() - getTop()));
        if (needs) {
            mState = UP_STATE_READY;
        }
        return needs;
    }

    public void setBottomContentView(int id) {
        //mPullUpRefreshEnabled = true;
        final View view = LayoutInflater.from(getContext()).inflate(id,
                mListBottomView, false);
        mListBottomView.addView(view);
        addFooterView(mListBottomView, null, false);
    }

    private void setHeaderHeight(int height) {
        mListHeaderView.setHeaderHeight(height);
    }

    private void setBottomHeight(int height) {
        mListBottomView.setBottomHeight(height);
    }

    void setState(int state) {
        mState = state;
    }


    public static interface OnHeaderViewChangedListener {
        void onViewChanged(View v, boolean canUpdate);
        void onViewUpdating(View v);
        void onViewUpdateFinish(View v);
    }

    public static interface OnBottomViewChangedListener extends
            OnHeaderViewChangedListener {

    }

    public static interface OnPullUpUpdateTask extends OnUpdateTask {

    }

    /** The callback when the updata task begin, doing. or finish. */
    public static interface OnUpdateTask {

        /**
         * will called before the update task begin. Will Run in the UI thread.
         */
        public void onUpdateStart();

        /**
         * Will called doing the background task. Will Run in the background
         * thread.
         */
        public void updateBackground();

        /**
         * Will called when doing the background task. Will Run in the UI
         * thread.
         */
        public void updateUI();

    }

    public void setContentView(int id) {
        final View view = LayoutInflater.from(getContext()).inflate(id,
                mListHeaderView, false);
        mListHeaderView.addView(view);
    }

    public void setOnHeaderViewChangedListener(
            OnHeaderViewChangedListener listener) {
        mListHeaderView.mOnHeaderViewChangedListener = listener;
    }

    public void setOnBottomViewChangedListener(
            OnBottomViewChangedListener listener) {
        mListBottomView.mOnHeaderViewChangedListener = listener;
    }


}
