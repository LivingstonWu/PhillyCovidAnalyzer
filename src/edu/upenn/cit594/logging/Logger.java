package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

	private PrintWriter out;
    private static final Logger instance = new Logger();
    public String logFileName;

    // private constructor
    private Logger() {

    }


    // singleton accessor method
//    public static Logger getInstance() {
//    	return instance;
//    	}
    
    public static Logger getInstance(String name) {
    	
    	instance.logFileName = name;
    	instance.createLog();
    	
    	return instance;
    	}
    
    
    public void createLog() {
    	File logFile = new File(this.logFileName);
    	
    	try {
    		logFile.createNewFile();
    	} catch (IOException e) {
    		e.printStackTrace();
    		System.out.println("Error: cannot create/open the specific log file");
    		System.exit(0);
    	}
    	   	
        try {
            out = new PrintWriter(new FileOutputStream(logFile, true));
        } catch (Exception e) {
            System.out.println("Error when writing log file.");
        }
    }
    

    // non-static method
    public void logInput(String msg) {
    	
    	String currentTime = String.valueOf(System.currentTimeMillis());

//    	String pattern = "yyyy-MM-dd HH:mm:ss";
//    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	   	
    	out.print(currentTime +"\t");
        out.print(msg + "\n");
        out.flush();
    }
    
    
    
    public void logArgs(String[] args) {
    	
    	String currentTime = String.valueOf(System.currentTimeMillis());

//    	String pattern = "yyyy-MM-dd HH:mm:ss";
//    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	   	
    	out.print(currentTime + "\t");
    	
    	for (String s: args) {
    		out.print(s);
    		out.print("\t");
    		}
    	out.print("\n");
    	       
        out.flush();
    }
    
    
    
    public void logOutPut(String msg) {
    	out.print(msg + "\n");
    }
    
    
    
    public void closeLog() {
    	out.close();

    }

}
