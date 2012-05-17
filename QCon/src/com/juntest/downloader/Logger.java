package com.juntest.downloader;

import java.util.Date;

public class Logger {

	public static void log(String message){
		Date timestamp = new Date(System.currentTimeMillis());	
		System.out.println("[" + timestamp.toString() + "] " + message);
	}
	
}
