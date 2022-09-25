package com.ikhokha.techcheck;

import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public abstract class MetricType {
	public abstract void Analyze(MetricAnalyzer metricAnalyzer, String line);
}
