package com.ikhokha.techcheck;

import java.text.MessageFormat;

public class ShortMessageMetric extends MetricType {
	private static final String SHORT_MESSAGE = "message_below_";
	private static final int MESSAGE_LENGTH = 15;

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		Object[] params = new Object[]{SHORT_MESSAGE, MESSAGE_LENGTH};
		String msg = MessageFormat.format("{0}{1}", params);
		
		if (line.length() < MESSAGE_LENGTH) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, msg);
		}
		
	}

}
