package com.ikhokha.techcheck.metrics;

import java.util.regex.Pattern;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class SpamMetric2 implements IMetricType {
	private static final String SPAM = "spam";

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
/*--  regex pattern from https://www.geeksforgeeks.org/extract-urls-present-in-a-given-string/   --*/
		
		String regex = "\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:, .;]*[-a-zA-Z0-9+&@#/%=~_|])";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, SPAM);
		}
		
		//System.out.println("We're inside SpamMetric2 using the IMetricType interface!!!");
	}

}
