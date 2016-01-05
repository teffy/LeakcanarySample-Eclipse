package com.leakcanary.example;

import android.app.Application;

import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 *  如果要处理上传问题，需要自定义RefWatcher和一个DisplayLeakService的子类LeakUploadService
 *  注意：需在manifest中声明DebugMApplication和LeakUploadService
 */
public class DebugMApplication extends Application {

	protected RefWatcher installLeakCanary() {
		return LeakCanary.install(this, LeakUploadService.class, AndroidExcludedRefs.createAppDefaults().build());
	}

}
