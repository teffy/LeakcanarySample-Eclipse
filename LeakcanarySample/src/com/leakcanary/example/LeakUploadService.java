package com.leakcanary.example;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

public class LeakUploadService extends DisplayLeakService {

	@Override
	protected void afterDefaultHandling(HeapDump heapDump,
			AnalysisResult result, String leakInfo) {
		super.afterDefaultHandling(heapDump, result, leakInfo);
		if (!result.leakFound || result.excludedLeak) {
			return;
		}
		//TODO 可以在这里处理上传log的逻辑
		// https://gist.github.com/pyricau/06c2c486d24f5f85f7f0
	}

}
