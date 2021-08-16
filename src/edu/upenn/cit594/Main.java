package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.CovidCSVReader;
import edu.upenn.cit594.datamanagement.CovidJsonReader;
import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.UI;

public class Main {
    public static void main(String[] args) {
    	
    
    		
    	// check the number of arguments
    	if (args.length < 4) {
			System.out.println("Error: number of arguments is incorrect");
			System.exit(0);
		}
    	
    	
    	//get covid data
		String filename = args[0];
		String[] filenameSplit = filename.split("\\.");
		filenameSplit[1] = filenameSplit[1].toLowerCase();

		
		CovidReader covidReader = null;
		if(filenameSplit[1].equals("csv")) {
			covidReader = new CovidCSVReader(filename);
		} else if (filenameSplit[1].equals("json")){
			covidReader = new CovidJsonReader(filename);
		} else {
			System.out.println("Error: covid data file does not match a recognized extension");
			System.exit(0);
		}    	
    	
		
		// get property data
		
		
		
		
		// get population data
		PopulationReader populationReader = new PopulationReader(args[2]);
		
		
		// process data
//		Processor processor = new Processor(covidReader, populationReader, propertiesReader);
//		
//		
//		// UI for display
//		UI ui = new UI(processor);
//		ui.start();
		
		// create log file
    	
    }
}
