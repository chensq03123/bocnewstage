package com.hustunique.bocp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Spinner;
import android.widget.TextView;

import com.hustunique.bocp.R;

public class SplashActivity extends Activity{

	private	Myasynctask task;
	private boolean isfirstrun=true;
	private TextView label;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashlayout);
		new Myasynctask().execute();
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			SplashActivity.this.finish();
			}
		return false;
	}
	
	class Myasynctask extends AsyncTask<Void,Integer,Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
			}
		}
		
	}
