package com.leakcanary.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.leakcanary.watcher.RefWatcher;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RefWatcher refWatcher = MApplication.getRefWatcher(this);
	    refWatcher.watch(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startAsyncTask();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	  private void startAsyncTask() {
		    // This async task is an anonymous class and therefore has a hidden reference to the outer
		    // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
		    // the activity instance will leak.
		    new AsyncTask<Void, Void, Void>() {
		      @Override protected Void doInBackground(Void... params) {
		        // Do some slow work in background
		        SystemClock.sleep(20000);
		        return null;
		      }
		    }.execute();
		  }
}
