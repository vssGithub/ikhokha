package com.ikhokha.techcheck;

import java.util.regex.Pattern;

public class MoverMetric extends MetricType {

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		String regex = "Mover";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		boolean lineContainsMatch = pattern.matcher(line).find();
		
		if (lineContainsMatch) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, "MOVER_MENTIONS");
		}
	}

}
