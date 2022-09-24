package com.ikhokha.techcheck;

public class Main {

	public static void main(String[] args) {
				
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer();
		
		MetricMediator metric = new MetricMediator(metricAnalyzer);
		metric.AddMetric("Mover", new MoverMetric());
		metric.AddMetric("Shaker", new ShakerMetric());
		metric.AddMetric("ShortMessage", new ShortMessageMetric());
		
		metric.Handle();
		
		metricAnalyzer.CreateReport();
		
	}

}
