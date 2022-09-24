package com.ikhokha.techcheck;

import java.util.regex.Pattern;

public class MoverMetric extends MetricType {
	private static final String MOVER = "mover";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String targetMetric = MOVER;
		Pattern pattern = Pattern.compile(targetMetric, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
		}
	}

}
