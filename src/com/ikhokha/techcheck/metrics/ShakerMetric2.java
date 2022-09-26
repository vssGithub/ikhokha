package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class ShakerMetric2 implements IMetricType {
	private static final String SHAKER = "shaker";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String targetMetric = SHAKER;
		Pattern pattern = Pattern.compile(targetMetric, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
		}
		
		//System.out.println("We're inside ShakerMetric2 using the IMetricType interface!!!");
	}

}
