package com.ikhokha.techcheck;

import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public interface IMetricType {
	void Analyze(MetricAnalyzer metricAnalyzer, String line);
}
