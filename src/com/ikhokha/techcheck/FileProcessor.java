package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
	
	private static final int NUMBER_OF_THREADS = 3;
	static File docPath = new File("docs");
	static File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
	
	public FileProcessor() {
	}
	
	public static void ProcessFiles() {
		Thread[] threads = new Thread[NUMBER_OF_THREADS];
		
		final int filesPerThread = GetFilesPerThread();
		final int remainingFiles = GetRemainingFiles();
		
		// assign files dynamically to each thread
		for (int thread = 0; thread < NUMBER_OF_THREADS; thread++) {
			final int currentThreadIndex = thread;
			
			threads[thread] = new Thread() {
				@Override public void run() {
					runThread(commentFiles, NUMBER_OF_THREADS, currentThreadIndex, filesPerThread, remainingFiles);
				}
			};
		}
		
		for (Thread thread : threads) {
			thread.start();			//start processing all thread
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();		//to wait until all threads complete execution
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("processing complete");
	}

	private static int GetFilesPerThread() {
		return commentFiles.length / NUMBER_OF_THREADS;
	}
	
	private static int GetRemainingFiles() {
		return commentFiles.length % NUMBER_OF_THREADS;
	}
	
	private static void runThread(File[] commentFiles, int numberOfThreads, int thread, int filesPerThread, int remainingFiles) {
		List<File> customerComments = new ArrayList<>();
		
		SplitFiles(commentFiles, thread, filesPerThread, customerComments);
		AssignRemaining(commentFiles, numberOfThreads, thread, remainingFiles, customerComments);		
		ReadFiles(customerComments);
	}

	private static void SplitFiles(File[] commentFiles, int thread, int filesPerThread, List<File> customerComments) {
		// split files between each thread
		for (int i = thread*filesPerThread; i < (thread+1)*filesPerThread; i++) {
			customerComments.add(commentFiles[i]);
		}
	}
	
	private static void AssignRemaining(File[] commentFiles, int numberOfThreads, int thread, int remainingFiles, List<File> customerComments) {
		// assign remaining file to the last thread
		if (thread == numberOfThreads-1 && remainingFiles>0) {
			for (int j = commentFiles.length-remainingFiles; j < commentFiles.length; j++) {
				customerComments.add(commentFiles[j]);
			}
		}
	}
	
	private static void ReadFiles(List<File> commentFiles) {
		String content = "";	// TODO remove..used to debug
		
		for (File file : commentFiles) {
			System.out.println("processing " + file.getName() + " in thread " + Thread.currentThread().getName());
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					content += line + System.lineSeparator();	// TODO remove..used to debug
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
		
		System.out.println("content: " + content);	// TODO remove..used to debug
	}

}
