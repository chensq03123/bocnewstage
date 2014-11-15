package com.hustunique.bocp.Utils;

/**
 * 
 * @author Yao Changwei(yaochangwei@gmail.com)
 * 
 *        Pull To  Refresh List View Demo, Including the arrow and text change.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hustunique.bocp.R;


public class PullToRefreshListView extends Mylistview {

    public PullToRefreshListView(Context context){
        super(context);
        addPullDownRefreshFeature(context);
    }

	public PullToRefreshListView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		addPullDownRefreshFeature(context);

		/*if you do not need the pull up to refresh, just uncomment follow line.*/
		// addPullUpRefreshFeature(context);
	}

	private void addPullDownRefreshFeature(final Context context) {

		setContentView(R.layout.pull_to_refresh);
		mListHeaderView.setBackgroundColor(0xffe0e0e0);
		setOnHeaderViewChangedListener(new OnHeaderViewChangedListener() {

			@Override
			public void onViewChanged(View v, boolean canUpdate) {

			}

			@Override
			public void onViewUpdating(View v) {
			}

			@Override
			public void onViewUpdateFinish(View v) {

			}

		});
	}

	private void addPullUpRefreshFeature(final Context context) {
		this.setBottomContentView(R.layout.pull_to_refresh_bottom);
		mListBottomView.setBackgroundColor(0xffe0e0e0);
		this.setOnBottomViewChangedListener(new OnBottomViewChangedListener() {

			@Override
			public void onViewChanged(View v, boolean canUpdate) {
			}

			@Override
			public void onViewUpdating(View v) {

			}

			@Override
			public void onViewUpdateFinish(View v) {

			}

		});
	}

}
