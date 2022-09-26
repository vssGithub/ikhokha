package com.ikhokha.techcheck.metrics;

import java.text.MessageFormat;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class ShortMessageMetric2 implements IMetricType {
	private static final String SHORT_MESSAGE = "message_below_";
	private static final int MESSAGE_LENGTH = 15;

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		Object[] params = new Object[]{SHORT_MESSAGE, MESSAGE_LENGTH};
		String msg = MessageFormat.format("{0}{1}", params);
		
		if (line.length() < MESSAGE_LENGTH) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, msg);
		}
		
		//System.out.println("We're inside ShortMessageMetric2 using the IMetricType interface!!!");
	}

}
