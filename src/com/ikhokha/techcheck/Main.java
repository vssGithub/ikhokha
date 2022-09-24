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
		
		
		
		MetricAnalyzer metricAnalyzer = new MetricAnalyzer(new ShakerMetric());
		
		for (File commentFile : commentFiles) {
			//TODO: rework this!
			Map<String, Integer> test = metricAnalyzer.AnalyzeMetric(commentFile);
			//TODO: possibly move the logging of results into MetricAnalyzer? Updated resultsMap is there..method call from here?
			addReportResults(test, totalResults);
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
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
