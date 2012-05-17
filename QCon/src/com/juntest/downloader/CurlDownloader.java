package com.juntest.downloader;

import java.io.IOException;
import java.net.URL;

/**
 * This file downloader uses cygwin installations at c:\cgywin and curl tool to download a file from a URL and save it at local disk.
 *  
 * @author jyang
 *
 */

public class CurlDownloader implements Runnable{

	private static final String CURL_PATH = "c:/cygwin/bin/curl";	
	private static final String OPTIONS = "--O";
	private static final String SPACE = " ";
	private static final String F_SLASH = "/";
	
	private URL srcUrl;
	private String srcFileName;
	private String targetPath;
	private String targetFileName;
		
	public CurlDownloader(URL srcUrl, String srcFileName, String targetPath, String targetFileName){
		
		if (srcUrl==null){
			throw new IllegalArgumentException("Source URL cannot be null.");
		}
		
		this.srcUrl = srcUrl;
		this.srcFileName = srcFileName==null ? "" : srcFileName;
		this.targetPath = targetPath==null ? "" : targetPath;
		this.targetFileName = targetFileName==null? srcFileName : targetFileName;
		
		if (this.targetPath.length()>0 && !this.targetPath.endsWith(F_SLASH)){
			this.targetPath += F_SLASH; 
		}
	}
	
	public boolean doDownload(){
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(CURL_PATH)
		  .append(SPACE)
		  .append(srcUrl.toString())
		  .append(srcFileName)
		  .append(SPACE)
		  .append(OPTIONS)
		  .append(SPACE)
		  .append(targetPath)
		  .append(targetFileName);
		
		String command = sb.toString();
		
		Process p = null;
		
		try {
			p = Runtime.getRuntime().exec(command);
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		} 
		
		/*
		try {
			p.waitFor();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			p.destroy();
		}
		*/
		
		System.out.println("Done downloading: " + srcFileName);
		
		return true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		doDownload();
	}
}
