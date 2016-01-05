package com.leakcanary.example;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Main2Activity extends Activity {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class PlaceholderFragment extends BaseFragment {
		
		public PlaceholderFragment() {
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main2,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			produceLeak();
		}
		Object obj;
		private void produceLeak() {
			obj = new Object();
			MApplication.getRefWatcher(getActivity()).watch(obj);
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						System.out.println(obj);
					}
				}
			}).start();
		}
		
	}
}
