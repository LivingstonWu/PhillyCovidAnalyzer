package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.CovidData;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CovidJsonReader implements CovidReader {
	
	protected String filename;
	
	public CovidJsonReader (String name) {
		filename = name;
	}
	
	
    @Override
    public Map<String, ArrayList<CovidData>> getAllCovidData() {
    	
    	Map<String, ArrayList<CovidData>> CovidDataMap = new HashMap<String, ArrayList<CovidData>>();
    	
    	JSONParser jsonparser = new JSONParser();
    	
    	String pattern = "yyyy-MM-dd HH:mm:ss";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	
    	try {
			JSONArray data = (JSONArray) jsonparser.parse(new FileReader(filename));
			
			for (Object i : data) {
				JSONObject dataentry = (JSONObject) i;
				
				// read data from json object
				String date_raw = (String) dataentry.get("etl_timestamp");					
		    	Date timestamp = simpleDateFormat.parse(date_raw);

		    	String zipcode = String.valueOf(dataentry.get("zip_code"));
		    	
				
		    	Integer negTests = null;
				if (dataentry.get("NEG_tests") != null) {
					negTests = ((Long) dataentry.get("NEG_tests")).intValue();
				} 
				
				
				Integer posTests = null;
				if (dataentry.get("POS_tests") != null) {
					posTests = ((Long) dataentry.get("POS_tests")).intValue();
				} 			
				
				
				Integer deaths = null;
				if (dataentry.get("deaths") != null) {
					deaths = ((Long) dataentry.get("deaths")).intValue();
				} 		
				
				Integer hospitalized = null;
				if (dataentry.get("hospitalized") != null) {
					hospitalized = ((Long) dataentry.get("hospitalized")).intValue();
				} 
				
				
				Integer partiallyVaccinated = null;
				if (dataentry.get("partially_vaccinated") != null) {
					partiallyVaccinated = ((Long) dataentry.get("partially_vaccinated")).intValue();
				} 
				
				
				Integer fullyVaccinated = null;
				if (dataentry.get("fully_vaccinated") != null) {
					fullyVaccinated = ((Long) dataentry.get("fully_vaccinated")).intValue();
				} 
				
				// create a new CovidData object
				CovidData covid_data = new CovidData(timestamp, zipcode, negTests, posTests, deaths, hospitalized, partiallyVaccinated, fullyVaccinated);
								
				// push data to hashmap
				if (!CovidDataMap.containsKey(zipcode)) {
					ArrayList<CovidData> dataList = new ArrayList<CovidData>();
					dataList.add(covid_data);
					CovidDataMap.put(zipcode, dataList);
					
				} else {
					ArrayList<CovidData> dataList = CovidDataMap.get(zipcode);
					dataList.add(covid_data);
					CovidDataMap.put(zipcode, dataList);
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: covid data (json file) is not accessible");
			System.exit(0);
		}
    	
//    	test: 80 unique zip code
//		System.out.println(CovidDataMap.size());
    	
    	
        return CovidDataMap;
    }
}
