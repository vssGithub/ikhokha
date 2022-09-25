package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.MetricAnalyzer;
import com.ikhokha.techcheck.MetricType;

public class ShakerMetric extends MetricType {
	private static final String SHAKER = "shaker";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String targetMetric = SHAKER;
		Pattern pattern = Pattern.compile(targetMetric, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
		}		
	}

}
