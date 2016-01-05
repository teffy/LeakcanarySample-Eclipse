package com.leakcanary.example;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MApplication extends Application {


	public static RefWatcher getRefWatcher(Context context) {
		MApplication application = (MApplication) context
				.getApplicationContext();
		return application.refWatcher;
	}

	protected RefWatcher refWatcher;

	@Override
	public void onCreate() {
		super.onCreate();
		enabledStrictMode();
		// LeakCanary.install() 会返回一个预定义的 RefWatcher，同时也会启用一个
		// ActivityRefWatcher，用于自动监控调用 Activity.onDestroy() 之后泄露的 activity。
		refWatcher = installLeakCanary();
	}

	protected RefWatcher installLeakCanary() {
		return LeakCanary.install(this);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	private void enabledStrictMode() {
		if (SDK_INT >= GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
					.detectAll() //
					.penaltyLog() //
					.penaltyDeath() //
					.build());
		}
	}
}
