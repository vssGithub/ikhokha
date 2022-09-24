package com.ikhokha.techcheck;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		
		/*
		CommentAnalyzer commentAnalyzer = new CommentAnalyzer();
		
		for (File commentFile : commentFiles) {
			Map<String, Integer> fileResults = commentAnalyzer.analyze(commentFile);
			addReportResults(fileResults, totalResults);
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
		*/
		
		
		
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer();
		//metricAnalyzer.AddMetric("Mover", new MoverMetric());
		//metricAnalyzer.AddMetric("Shaker", new ShakerMetric());
		
		MetricMediator metric = new MetricMediator(metricAnalyzer);
		metric.AddMetric("Mover", new MoverMetric());
		metric.AddMetric("Shaker", new ShakerMetric());
		
		metric.Handle();
		
		metricAnalyzer.CreateReport();
		

	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.put(entry.getKey(), entry.getValue());
		}
		
	}

}
