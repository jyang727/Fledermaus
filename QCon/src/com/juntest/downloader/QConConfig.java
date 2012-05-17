package com.juntest.downloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QConConfig {
	
	private static List<String> years = new ArrayList<String>();
	private static List<String> locations = new ArrayList<String>();
	private static List<String> days = new ArrayList<String>();
	
	private static Map<String, String> scheduleUrl = new HashMap<String, String>();
	private static Map<String, String> slidesUrl = new HashMap<String, String>();
	
	private static QConConfig instance = new QConConfig();
	
	static {
		years.add("2009");
		years.add("2010");
		years.add("2011");
		years.add("2012");
		
		locations.add("sf");
		locations.add("london");
		
		days.add("wednesday");
		days.add("thursday");
		days.add("friday");
		
		scheduleUrl.put("2009-london", "http://qconlondon.com/london-2009/schedule/");
		scheduleUrl.put("2010-london", "http://qconlondon.com/london-2010/schedule/");
		scheduleUrl.put("2011-london", "http://qconlondon.com/london-2011/schedule/");
		scheduleUrl.put("2012-london", "http://qconlondon.com/london-2012/schedule/");
		
		scheduleUrl.put("2009-sf", "http://qconsf.com/sf2009/schedule/");
		scheduleUrl.put("2010-sf", "http://qconsf.com/sf2010/schedule/");
		scheduleUrl.put("2011-sf", "http://qconsf.com/sf2011/schedule/");
		
		slidesUrl.put("2009-london", "http://qconlondon.com/dl/qcon-london-2009/slides/");
		slidesUrl.put("2010-london", "http://qconlondon.com/dl/qcon-london-2010/slides/");
		slidesUrl.put("2011-london", "http://qconlondon.com/dl/qcon-london-2011/slides/");
		slidesUrl.put("2012-london", "http://qconlondon.com/dl/qcon-london-2012/slides/");
		
		slidesUrl.put("2009-sf", "http://qconsf.com/dl/qcon-sanfran-2009/slides/");
		slidesUrl.put("2010-sf", "http://qconsf.com/dl/qcon-sanfran-2010/slides/");
		slidesUrl.put("2011-sf", "http://qconsf.com/dl/qcon-sanfran-2011/slides/");
	}
	
	private QConConfig(){
		
	}
	
	public static QConConfig getInstance(){
		return instance;
	}
	
	public List<String> getYears(){
		return years;
	}
	
	public List<String> getLocations(){
		return locations;
	}
	
	public List<String> getDays(){
		return days;
	}
	
	public String getScheduleUrl(String year, String location){
		return scheduleUrl.get(year + "-" + location);
	}
	
	public String getSlidesUrl(String year, String location){
		return slidesUrl.get(year + "-" + location);
	}
	
}
