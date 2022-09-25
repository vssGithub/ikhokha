package com.ikhokha.techcheck;

import com.ikhokha.techcheck.metrics.MoverMetric;
import com.ikhokha.techcheck.metrics.QuestionMetric;
import com.ikhokha.techcheck.metrics.ShakerMetric;
import com.ikhokha.techcheck.metrics.ShortMessageMetric;
import com.ikhokha.techcheck.metrics.SpamMetric;

public class Main {

	public static void main(String[] args) {
				
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer();
		
		MetricMediator metric = new MetricMediator(metricAnalyzer);
		metric.AddMetric("Mover", new MoverMetric());
		metric.AddMetric("Shaker", new ShakerMetric());
		metric.AddMetric("ShortMessage", new ShortMessageMetric());
		metric.AddMetric("Question", new QuestionMetric());
		metric.AddMetric("Spam", new SpamMetric());
		
		metric.Handle();
		
		metricAnalyzer.CreateReport();
		
		//Fileprocessor added to test threading		
		FileProcessor.ProcessFiles();
		
	}

}
