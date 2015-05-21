package com.leakcanary.example;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.watcher.RefWatcher;

public class MApplication extends Application {

	public static RefWatcher getRefWatcher(Context context) {
		MApplication application = (MApplication) context
				.getApplicationContext();
		return application.refWatcher;
	}

	private RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();
		refWatcher = LeakCanary.install(this);
	}
}
