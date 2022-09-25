package com.ikhokha.techcheck;

import java.io.File;
import java.io.IOException;

public class FileProcessor {
	
	private static final int NUMBER_OF_THREADS = 3;
	static File docPath = new File("docs");
	static File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
	
	public FileProcessor() {
	}
	
	public static void ProcessFiles() {
		CreateCollatedResultFile();
		
		Thread[] threads = new Thread[NUMBER_OF_THREADS];
		
		final int filesPerThread = GetFilesPerThread();
		final int remainingFiles = GetRemainingFiles();
		
		// assign files dynamically to each thread
		for (int thread = 0; thread < NUMBER_OF_THREADS; thread++) {
			final int currentThreadIndex = thread;
			
			threads[thread] = new Thread(new CommentsProcessor(commentFiles, NUMBER_OF_THREADS, currentThreadIndex, filesPerThread, remainingFiles, docPath));
		}
		
        System.out.println("File Processing Started");
		
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
		
        System.out.println("File Processing Complete" + System.lineSeparator());
	}

	private static int GetFilesPerThread() {
		return commentFiles.length / NUMBER_OF_THREADS;
	}
	
	private static int GetRemainingFiles() {
		return commentFiles.length % NUMBER_OF_THREADS;
	}
	
	public static File GetCollatedComments() {
		return docPath.listFiles((d, n) -> n.endsWith(".log"))[0];
	}
	
	private static void CreateCollatedResultFile() {
		String collatedFile = docPath.getAbsolutePath();
		
		try {
			File resultFile = new File(collatedFile + "\\collatedComments.log");
			
			if (resultFile.exists()) {
				resultFile.delete();
			}
			
			resultFile.createNewFile();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
