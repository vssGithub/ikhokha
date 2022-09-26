package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class QuestionMetric2 implements IMetricType {
	private static final String QUESTION = "question";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String regex = "\\?";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, QUESTION);
		}
		
		//System.out.println("We're inside QuestionMetric2 using the IMetricType interface!!!");
	}

}
