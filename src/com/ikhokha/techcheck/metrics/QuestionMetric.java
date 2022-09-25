package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.MetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class QuestionMetric extends MetricType {
	private static final String QUESTION = "question";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String regex = "\\?";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, QUESTION);
		}
		
	}

}
