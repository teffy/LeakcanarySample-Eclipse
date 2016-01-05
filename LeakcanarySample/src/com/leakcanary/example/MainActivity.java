package com.leakcanary.example;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {
	private RefWatcher refWatcher;
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		refWatcher = MApplication.getRefWatcher(this);
		findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startAsyncTask();
			}
		});
		findViewById(R.id.gotofragment).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(MainActivity.this, Main2Activity.class));
					}
				});
	}
	private void startAsyncTask() {
		// This async task is an anonymous class and therefore has a hidden
		// reference to the outer
		// class MainActivity. If the activity gets destroyed before the task
		// finishes (e.g. rotation),
		// the activity instance will leak.
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				// Do some slow work in background
				SystemClock.sleep(20000);
				return null;
			}
		}.execute();
	}
}
