package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.MetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class MoverMetric extends MetricType {
	private static final String MOVER = "mover";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		/*--  pattern matching from https://www.baeldung.com/java-case-insensitive-string-matching   --*/
		
		String targetMetric = MOVER;
		Pattern pattern = Pattern.compile(targetMetric, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
		}
	}

}
