package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MetricAnalyzer {
	
	public MetricType MetricType;
	Map<String, Integer> resultsMap;
	
	public MetricAnalyzer(MetricType metricType) {
		MetricType = metricType;
		resultsMap = new HashMap<>();
	}
	
	public Map<String, Integer> AnalyzeMetric(File file) {		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				MetricType.Analyze(this, line);
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
		
		return resultsMap;
	}
	
	public void ProcessMetric(Map<String, Integer> countMap, String key) {
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}

}
