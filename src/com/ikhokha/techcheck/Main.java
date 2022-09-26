package com.ikhokha.techcheck;

import com.ikhokha.techcheck.mediator.MetricMediator;
import com.ikhokha.techcheck.metrics.MoverMetric;
import com.ikhokha.techcheck.metrics.MoverMetric2;
import com.ikhokha.techcheck.metrics.QuestionMetric;
import com.ikhokha.techcheck.metrics.QuestionMetric2;
import com.ikhokha.techcheck.metrics.ShakerMetric;
import com.ikhokha.techcheck.metrics.ShakerMetric2;
import com.ikhokha.techcheck.metrics.ShortMessageMetric;
import com.ikhokha.techcheck.metrics.ShortMessageMetric2;
import com.ikhokha.techcheck.metrics.SpamMetric;
import com.ikhokha.techcheck.metrics.SpamMetric2;
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
		
		metric.AddMetric("Mover2", new MoverMetric2());						//using overloaded method
		metric.AddMetric("Shaker2", new ShakerMetric2());					//using overloaded method
		metric.AddMetric("ShortMessage2", new ShortMessageMetric2());		//using overloaded method
		metric.AddMetric("Question2", new QuestionMetric2());				//using overloaded method
		metric.AddMetric("Spam2", new SpamMetric2());						//using overloaded method
		
		metric.Handle();
		
		metricAnalyzer.CreateReport();
		
	}

}
