package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	Map<String, Integer> resultsMap;
	
	public CommentAnalyzer() {
		resultsMap = new HashMap<>();
	}
	
	public Map<String, Integer> analyze(File file) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				
				if (line.length() < 15) {
					incOccurrence(resultsMap, "SHORTER_THAN_15");
				} 
				if (line.contains("Mover")) {
					incOccurrence(resultsMap, "MOVER_MENTIONS");
				} 
				if (line.contains("Shaker")) {
					incOccurrence(resultsMap, "SHAKER_MENTIONS");
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
		return resultsMap;
		
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {
		
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}

}
