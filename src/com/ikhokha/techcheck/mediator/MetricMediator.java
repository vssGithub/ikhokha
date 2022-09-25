package com.ikhokha.techcheck.mediator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.ikhokha.techcheck.MetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class MetricMediator {
	public MetricAnalyzer MetricAnalyzer;
	Map<String, MetricType> metricMap;
	File collatedCommentFile;
	
	public MetricMediator(MetricAnalyzer metricAnalyzer, File collatedComments) {
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
