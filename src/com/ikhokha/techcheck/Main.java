package com.ikhokha.techcheck;

import com.ikhokha.techcheck.mediator.MetricMediator;
import com.ikhokha.techcheck.metrics.MoverMetric;
import com.ikhokha.techcheck.metrics.QuestionMetric;
import com.ikhokha.techcheck.metrics.ShakerMetric;
import com.ikhokha.techcheck.metrics.ShortMessageMetric;
import com.ikhokha.techcheck.metrics.SpamMetric;
import com.ikhokha.techcheck.processor.FileProcessor;
import com.ikhokha.techcheck.strategy.MetricAnalyzer;

public class Main {

	public static void main(String[] args) {
		
		FileProcessor.ProcessFiles();
				
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer();
		
		MetricMediator metric = new MetricMediator(metricAnalyzer, FileProcessor.GetCollatedComments());
		metric.AddMetric("Mover", new MoverMetric());
		metric.AddMetric("Shaker", new ShakerMetric());
		metric.AddMetric("ShortMessage", new ShortMessageMetric());
		metric.AddMetric("Question", new QuestionMetric());
		metric.AddMetric("Spam", new SpamMetric());
		
		metric.Handle();
		
		metricAnalyzer.CreateReport();
		
	}

}
