package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.CovidCSVReader;
import edu.upenn.cit594.datamanagement.CovidJsonReader;
import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.processor.Processor;

public class Main {
    public static void main(String[] args) {
    	
    	// check the number of arguments
    	if (args.length < 3) {
			System.out.println("Error: number of arguments is incorrect");
			System.exit(0);
		}
    	
    	
		String filename = args[0];
		String[] filenameSplit = filename.split("\\.");
		filenameSplit[1] = filenameSplit[1].toLowerCase();

		
		CovidReader reader = null;
		if(filenameSplit[1].equals("csv")) {
			reader = new CovidCSVReader(filename);
		} else if (filenameSplit[1].equals("json")){
			reader = new CovidJsonReader(filename);
		} else {
			System.out.println("Error: covid data file does not match a recognized extension");
			System.exit(0);
		}    	
    	
		
//		Processor processor = new Processor(covidReader, populationReader, propertiesReader);
		
		// temp: testing for csv input		
		reader.getAllCovidData();
		
    	
    }
}
