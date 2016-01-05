package com.leakcanary.example;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BaseFragment extends Fragment {

	@Override
	public void onDestroy() {
		super.onDestroy();
		MApplication.getRefWatcher(getActivity()).watch(this);
	}
}
