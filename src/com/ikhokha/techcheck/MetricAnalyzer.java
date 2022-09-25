package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetricAnalyzer {
	
	public Map<String, Integer> resultsMap;
	
	File docPath = new File("docs");
	File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
	
	
	public MetricAnalyzer() {
		resultsMap = new HashMap<>();
	}
	
	public Map<String, Integer> AnalyzeMetric(MetricType metricType) {
//		Arrays.stream(commentFiles).forEach(f -> {
//			System.out.println("file: " + f);
//		});
		
		for (File file : commentFiles) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					metricType.Analyze(this, line);
				}
				
			} 
			catch (FileNotFoundException e) {
				System.out.println("File not found: " + file.getAbsolutePath());
				e.printStackTrace();
			} 
			catch (IOException e) {
				System.out.println("IO Error processing file: " + file.getAbsolutePath());
				e.printStackTrace();
			}
		}
		
		return resultsMap;
	}
	
	public void ProcessMetric(Map<String, Integer> countMap, String key) {
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}
	
	public void CreateReport() {
		System.out.println("RESULTS\n=======");
		resultsMap.forEach((k,v) -> System.out.println(k + " : " + v));
	}

}
