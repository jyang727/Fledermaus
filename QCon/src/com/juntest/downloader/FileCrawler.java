package com.juntest.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class FileCrawler {

	private static final String F_SLASH = "/"; 
	private static final String LINK_START = "<a href";
	private static final String LINK_END = ">";
	private static final String DOT = ".";
	
	private URL scheduleUrl;
	private URL slidesUrl;
	private String suffix;
	private String destPath;
	
	//private List<Thread> allThreads = new ArrayList<Thread>();
	
	private List<String> fileQueue = new ArrayList<String>();
	
	public FileCrawler(String scheduleUrl, String slidesUrl, String suffix, String destPath) 
		throws MalformedURLException{
		
		if (scheduleUrl==null || slidesUrl==null || suffix==null || suffix.isEmpty()){
			throw new IllegalArgumentException();
		}
		
		this.scheduleUrl = new URL(scheduleUrl);
		this.slidesUrl = new URL(slidesUrl);
		this.suffix  = suffix.startsWith(DOT) ? suffix : DOT+suffix;	
		this.destPath = destPath;
	}
	
	public void downloadAllFiles(){
		
		System.out.println("Start processing schedule: " + scheduleUrl);
		
		try
		{
			URLConnection conn = scheduleUrl.openConnection();
			
			// 	Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null)
			{
				String link = extractLink(line);
				if (link==null){
					continue;
				}
				
				String fileName = extractFileNameFromLink(link);
				if (fileName==null){
					continue;
				}
				
				fileQueue.add(fileName);
				
				//System.out.println("Done adding: " + fileName);
			}
			rd.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		for (String fname : fileQueue){
			
			//CurlDownloader cd = new CurlDownloader(slidesUrl, fname, destPath, fname);
			//Thread t = new Thread(cd);
			//t.start();
			//allThreads.add(t);
			//cd.doDownload();
			JavaFileDownloader jfd = new JavaFileDownloader(slidesUrl, fname, destPath, fname);
			try {
				jfd.doDownload();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
		}

		/*
		try {
			Thread.sleep(2*60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Thread th : allThreads){
			if (th.isAlive()){
				th.interrupt();
				try {
					th.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		*/
	}
	
	private String extractLink(String line){
		
		int start = line.indexOf(LINK_START);
		if (start<0){
			return null;
		}
		
		int end = line.indexOf(LINK_END, start);
		if (end<0){
			return null;
		}
		
		return line.substring(start, end);
	}
	
	/*
	 * Extract a .pdf file name from a link like:
	 * <a href="/dl/qcon-sanfran-2011/slides/CharlieFineman_StubHubScalingAndInnovatingAtTheWorldsLargestTicketMarketplace.pdf">
	 */
	private String extractFileNameFromLink(String link){
		
		int lastSlashPos = link.lastIndexOf(F_SLASH);
		
		if (lastSlashPos<0){
			return null;
		}
		
		String fileName = link.substring(lastSlashPos+1, link.length()-1);
		
		if (!fileName.endsWith(suffix)){
			int pdfPos = fileName.lastIndexOf(suffix);
			if (pdfPos>0){
				fileName = fileName.substring(0, pdfPos + suffix.length());
			}
			else {
				return null;
			}
		}
		
		return fileName;
	}
	
}
