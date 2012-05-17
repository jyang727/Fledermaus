package com.juntest.downloader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class JavaFileDownloader {

	private static final String F_SLASH = "/";
	
	private URL srcUrl;
	private String srcFileName;
	private String targetPath;
	private String targetFileName;
		
	public JavaFileDownloader(URL srcUrl, String srcFileName, String targetPath, String targetFileName){
		
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
	
	public boolean doDownload() throws IOException{
			
		URL fullURL = new URL(srcUrl.toString() + srcFileName);
		
		Logger.log("Start downloading file: " + fullURL.toString());
			
		URLConnection uc = fullURL.openConnection();
		uc.setReadTimeout(0);
		
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		
		if (!contentType.startsWith("application/pdf") || contentLength == -1) {
			Logger.log("Error: Unsuppoted content type: " + contentType + ", skipping file " + srcFileName);		
			return false;
		}
		
		InputStream raw = uc.getInputStream();
		InputStream in = new BufferedInputStream(raw);
		
		byte[] data = new byte[contentLength];
		int bytesRead = 0;
		int offset = 0;
		
		int count = 0; 
		while (offset < contentLength) {
			bytesRead = in.read(data, offset, data.length - offset);
			if (bytesRead == -1)
				break;
			offset += bytesRead;
			count++;
			if (count % 5000 == 0)
				System.out.print("\n");			
			else if (count % 50 == 0)
				System.out.print(".");

		}
				
		if (count>=50){
			System.out.print("\n");
		}
		
		in.close();

		if (offset != contentLength) {
			throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(targetPath + targetFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		out.write(data);
		out.flush();
		out.close();
		
		Logger.log("Finished downloading file: " + fullURL.toString());
		
		return true;
	}
	
}
