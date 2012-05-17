package com.juntest.downloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QConSlidesScruber {
	
	private static final String suffix = "pdf";
	private static final String comma = ",";
	
	public static void main(String[] args){
	
		if (args.length==0){
			System.out.println("Please type \"QConSlidesScruber -help\" for usage");
			System.exit(0);
		}
		
		if (args.length==1){
			if (args[0].equalsIgnoreCase("-help")){
				printHelpMessage();
				System.exit(0);
			}
			else {
				System.out.println("Please type \"QConSlidesScruber -help\" for usage");
				System.exit(0);				
			}
		}
			
		if (args.length>=2){
			
			for (int i=0; i<args.length; i+=2){
			
				System.out.println("arg=" + args[i]);
				System.out.println("arg=" + args[i+1]);
				
				if (args[i].equalsIgnoreCase("-year")){
					parseArgs(years, args[i+1]);
				}
				else if (args[i].equalsIgnoreCase("-location")){
					parseArgs(locations, args[i+1]);
				}
				else if (args[i].equalsIgnoreCase("-day")){
					parseArgs(days, args[i+1]);
				}		
				else if (args[i].equalsIgnoreCase("-rootdir")){
					rootDir = args[i+1];
				}
			}
			
			System.out.println("rootDir = " + rootDir);
			
			years = validate(years, QConConfig.getInstance().getYears());
			locations = validate(locations, QConConfig.getInstance().getLocations());
			days = validate(days, QConConfig.getInstance().getDays());
			
			
			if (years.isEmpty()){
				years = QConConfig.getInstance().getYears();
				System.out.println("No years info entered, assuming 2009, 2010 and 2011.");
			}
			
			if (locations.isEmpty()){
				locations = QConConfig.getInstance().getLocations();
				System.out.println("No locations info entered, assuming london and sf.");
			}

			if (days.isEmpty()){
				days = QConConfig.getInstance().getDays();
				System.out.println("No days info entered, assuming Wednesday, Thursday and Friday.");
			}
		}
		
		QConSlidesScruber scrubber = new QConSlidesScruber(rootDir);
		
		scrubber.execute();
	}
	
	private static void printHelpMessage(){
		System.out.println("Usage:\n");
		System.out.println("-Year: the years you wish to donwload the slides, comma separated, e.g. 2009,2010,2011");
		System.out.println("-Year: the locations you wish to donwload the slides, comma separated, e.g. london,sf");
		System.out.println("-Year: the days you wish to donwload the slides, comma separated, e.g. wednesday,thursday,friday");
		System.out.println("-RootDir: the top level directory in your local disk to store the slides, e.g. d:/qcon");
	}
	
	private static String rootDir;
	private static List<String> years = new ArrayList<String>();
	private static List<String> locations = new ArrayList<String>();
	private static List<String> days = new ArrayList<String>();
	
	public QConSlidesScruber(String rootDir){
		this.rootDir = rootDir;
	}
			
	public void execute()
	{
		for (String year : years){
			for (String location : locations){

				String slidesUrl = QConConfig.getInstance().getSlidesUrl(year, location);
				for (String day : days){
					String scheduleUrl = QConConfig.getInstance().getScheduleUrl(year, location);
					scheduleUrl = scheduleUrl + day + ".jsp";
					
					String destPath = rootDir + "/" + year + "/" + location + "/" + day + "/";
									
					System.out.println("Processing: [Year=" + year + "][Location=" + location + "][Day=" + day + "]");					
					
					boolean dirOK = createDir(rootDir, year, location, day);
					
					if (!dirOK){
						continue;
					}
					
					try {
						FileCrawler fc = new FileCrawler(scheduleUrl, slidesUrl, suffix, destPath);
						fc.downloadAllFiles();
					}
					catch (Exception e){
						
					}
				}
				
			}
		}
		
		System.out.println("Download completed");
	}
	
	private static void parseArgs(List<String> args, String argsStr){
		
		StringTokenizer st = new StringTokenizer(argsStr, comma);
		
		while (st.hasMoreTokens()){
			String t = st.nextToken();
			args.add(t);
		}
	}
	
	private static List<String> validate(List<String> usrInput, List<String> defaultValues){
		
		List<String> ret = new ArrayList<String>();
		
		for(String s : usrInput){
			if (defaultValues.contains(s)){
				ret.add(s);
			}
			else {
				System.out.println("Invalid user input found: " + s);
			}
		}
		
		return ret;
	}
	
	private boolean createDir(String base, String year, String location, String day){
		
		System.out.println("CreateDir: base= " + base);
		File baseDir = new File(base);
		
		boolean success = true;
		if (!baseDir.exists()){
			success = baseDir.mkdir();
		}
		
		if (!success){
			return false;
		}
		
		File yearDir = new File(baseDir+"/"+year);
		if (!yearDir.exists()){
			success = yearDir.mkdir();
		}
		
		if (!success){
			return false;
		}
		
		File locationDir = new File(baseDir+"/"+year+"/"+location);
		if (!locationDir.exists()){
			success = locationDir.mkdir();
		}
		
		if (!success){
			return false;
		}
		
		File dayDir = new File(baseDir+"/"+year+"/"+location+"/"+day);
		if (!dayDir.exists()){
			success = dayDir.mkdir();
		}
		
		if (!success){
			return false;
		}
		
		return true;
	}
}
