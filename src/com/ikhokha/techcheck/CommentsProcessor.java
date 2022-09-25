package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommentsProcessor implements Runnable {
	
	File[] customerCommentFiles;
	int requiredThreads;
	int currentThread;
	int numberFilesPerThread;
	int remainingFilesToAssign;
	static File pathToFile;
	
	private static final Boolean DEBUG_FILES = false;
	private static final Boolean DEBUG_CONTENT = false;
	
	public CommentsProcessor () {}
	
	public CommentsProcessor(File[] commentFiles, int numberOfThreads, int thread, int filesPerThread, int remainingFiles, File docPath) {
		customerCommentFiles = commentFiles;
		requiredThreads = numberOfThreads;
		currentThread = thread;
		numberFilesPerThread = filesPerThread;
		remainingFilesToAssign = remainingFiles;
		pathToFile = docPath;
	}

	@Override
	public void run() {
		runThread(customerCommentFiles, requiredThreads, currentThread, numberFilesPerThread, remainingFilesToAssign);
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
		BufferedWriter bw = CreateBufferedWriter();
		String content = "";	// TODO remove..used to debug
		
		for (File file : commentFiles) {
			if (DEBUG_FILES) {
				System.out.println("PROCESSING " + file.getName() + " in thread " + Thread.currentThread().getName());
			}
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					content += line + System.lineSeparator();
					bw.write(line + System.lineSeparator());
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
		
		if (DEBUG_CONTENT) {
			System.out.println("CONTENT:\n=======" + System.lineSeparator() + content);
		}
		
		CloseBufferedWriter(bw);
	}
	
	private static BufferedWriter CreateBufferedWriter() {
		File target = pathToFile.listFiles((d, n) -> n.endsWith(".log"))[0];
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(target,true);
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return new BufferedWriter(fw);
	}
	
	private static void CloseBufferedWriter(BufferedWriter bw) {
		try {
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
