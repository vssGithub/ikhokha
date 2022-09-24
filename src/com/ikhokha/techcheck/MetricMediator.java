package com.ikhokha.techcheck;

import java.util.HashMap;
import java.util.Map;

public class MetricMediator {
	public MetricAnalyzer MetricAnalyzer;
	
	Map<String, MetricType> metricMap;
	
	public MetricMediator(MetricAnalyzer metricAnalyzer) {
		MetricAnalyzer = metricAnalyzer;
		metricMap = new HashMap<>();
	}
	
	public void AddMetric(String metric, MetricType metricType) {
		metricMap.putIfAbsent(metric, metricType);
	}
	
	public void Handle() {
		for (MetricType metric : metricMap.values()) {
			MetricAnalyzer.AnalyzeMetric(metric);
		}
	}

}
