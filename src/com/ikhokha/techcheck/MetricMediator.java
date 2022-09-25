package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MetricMediator {
	public MetricAnalyzer MetricAnalyzer;
	Map<String, MetricType> metricMap;
	File collatedCommentFile;
	
	public MetricMediator(com.ikhokha.techcheck.MetricAnalyzer metricAnalyzer, File collatedComments) {
		MetricAnalyzer = metricAnalyzer;
		metricMap = new HashMap<>();
		collatedCommentFile = collatedComments;
	}
	
	public void AddMetric(String metric, MetricType metricType) {
		metricMap.putIfAbsent(metric, metricType);
	}
	
	public void Handle() {
		for (MetricType metric : metricMap.values()) {
			MetricAnalyzer.AnalyzeMetric(metric, collatedCommentFile);
		}
	}

}
