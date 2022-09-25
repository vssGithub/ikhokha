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
			
			// TODO remove
			/*
			threads[thread] = new Thread() {
				@Override public void run() {
					runThread(commentFiles, NUMBER_OF_THREADS, currentThreadIndex, filesPerThread, remainingFiles);
				}
			};
			*/
			
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
	
	// TODO remove
	/*
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
			System.out.println("processing " + file.getName() + " in thread " + Thread.currentThread().getName());
			
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					content += line + System.lineSeparator();	// TODO remove..used to debug
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
		
		System.out.println("content: " + content);	// TODO remove..used to debug
		CloseBufferedWriter(bw);
	}
	
	private static BufferedWriter CreateBufferedWriter() {
		File target = docPath.listFiles((d, n) -> n.endsWith(".log"))[0];
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
	*/
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
