package com.ikhokha.techcheck.mediator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.MetricType;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class MetricMediator {
	public MetricAnalyzer MetricAnalyzer;
	Map<String, MetricType> metricMap;
	Map<String, IMetricType> metric2Map;
	File collatedCommentFile;
	
	private static final Boolean USE_ABSTRACT_CLASS = true;
	
	public MetricMediator(MetricAnalyzer metricAnalyzer, File collatedComments) {
		MetricAnalyzer = metricAnalyzer;
		metricMap = new HashMap<>();
		metric2Map = new HashMap<>();
		collatedCommentFile = collatedComments;
	}
	
	public void AddMetric(String metric, MetricType metricType) {
		metricMap.putIfAbsent(metric, metricType);
	}
	
	public void AddMetric(String metric, IMetricType metricType) {
		metric2Map.putIfAbsent(metric, metricType);
	}
	
	public void Handle() {
		if (USE_ABSTRACT_CLASS) {
			System.out.println("Using abstract class" + System.lineSeparator());
			
			for (MetricType metric : metricMap.values()) {
				MetricAnalyzer.AnalyzeMetric(metric, collatedCommentFile);
			}
		}
		else {
			System.out.println("Using interface" + System.lineSeparator());
			
			for (IMetricType metric : metric2Map.values()) {
				MetricAnalyzer.AnalyzeMetric(metric, collatedCommentFile);	//using overloaded method
			}
		}
	}

}
