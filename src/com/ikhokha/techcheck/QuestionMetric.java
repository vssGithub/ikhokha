package com.ikhokha.techcheck;

import java.util.regex.Pattern;

public class QuestionMetric extends MetricType {
	private static final String QUESTION = "question";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String targetMetric = QUESTION;
		Pattern pattern = Pattern.compile("\\?", Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, targetMetric);
		}
		
	}

}
