package com.ikhokha.techcheck;

public class MoverMetric extends MetricType {

	@Override
	public void Analyze(MetricAnalyzer metricAnalyzer, String line) {
		if (line.contains("Mover")) {
			metricAnalyzer.ProcessMetric(metricAnalyzer.resultsMap, "MOVER_MENTIONS");
		}
	}

}
