package com.ikhokha.techcheck.strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ikhokha.techcheck.IMetricType;
import com.ikhokha.techcheck.MetricType;

public class MetricAnalyzer {
	
	public Map<String, Integer> resultsMap;

	public MetricAnalyzer() {
		resultsMap = new HashMap<>();
	}
	
	public void AnalyzeMetric(MetricType metricType, File collatedCommentFile) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(collatedCommentFile))) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				metricType.Analyze(this, line);
			}
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + collatedCommentFile.getAbsolutePath());
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("IO Error processing file: " + collatedCommentFile.getAbsolutePath());
			e.printStackTrace();
		}
		
	}
	
	public void AnalyzeMetric(IMetricType metricType, File collatedCommentFile) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(collatedCommentFile))) {
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				metricType.Analyze(this, line);
			}
			
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + collatedCommentFile.getAbsolutePath());
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("IO Error processing file: " + collatedCommentFile.getAbsolutePath());
			e.printStackTrace();
		}
		
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
