package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.util.CovidData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CovidCSVReader implements CovidReader {
	
	
	protected String filename;
	
	
	public CovidCSVReader(String name) {
		this.filename = name;
	}
	

    @Override
    public Map<Integer, ArrayList<CovidData>> getAllCovidData() {
    	
    	Map<Integer, ArrayList<CovidData>> CovidDataMap = new HashMap<Integer, ArrayList<CovidData>>();
    	
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			
			// read header
			String header = br.readLine();
			
			ArrayList<String> headerList = new ArrayList<String>();
			Pattern p = Pattern.compile("\"([^\"]*)\"");
			Matcher m = p.matcher(header);
			while (m.find()) {
				headerList.add(m.group(1));
			}
			
		
			
			// read data entry
			String entry;
			while ((entry = br.readLine()) != null) {
				HashMap<String, String> dataMapping = new HashMap<String, String>();
							
				String[] values = entry.split(",", -1);	

				
				// map data with column name
				for (int i = 0; i < values.length; i++) {					
					dataMapping.put(headerList.get(i), values[i]);
				}
				
				
				String date_raw = dataMapping.get("etl_timestamp");
				date_raw = date_raw.replaceAll("^\"+|\"+$", "");
		    	String pattern = "YYYY-MM-DD hh:mm:ss";
		    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		    	Date timestamp = simpleDateFormat.parse(date_raw);
		    	
				int zipcode = Integer.parseInt(dataMapping.get("zip_code"));
				
				int negTests = 0;
				if (dataMapping.get("NEG_tests").length() != 0) {
					negTests = Integer.parseInt(dataMapping.get("NEG_tests"));
				}
				
				int posTests = 0;
				if(dataMapping.get("POS_tests").length() != 0) {
					posTests = Integer.parseInt(dataMapping.get("POS_tests"));
				}				
				
				int deaths = 0;
				if (dataMapping.get("deaths").length() != 0) {
					deaths = Integer.parseInt(dataMapping.get("deaths"));
				}
				
				int hospitalized = 0;
				if (dataMapping.get("hospitalized").length() != 0) {
					hospitalized = Integer.parseInt(dataMapping.get("hospitalized"));
				}
				
				int partiallyVaccinated = 0;
				if (dataMapping.get("partially_vaccinated").length() != 0) {
					partiallyVaccinated = Integer.parseInt(dataMapping.get("partially_vaccinated"));
				}
				
				int fullyVaccinated = 0;
				if (dataMapping.get("fully_vaccinated").length() != 0) {
					fullyVaccinated = Integer.parseInt(dataMapping.get("fully_vaccinated"));
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
			System.out.println("Error: covid data file is not accessible");
			System.exit(0);
		}

//    	test: 80 unique zip code
//		System.out.println(CovidDataMap.size());
    	
        return CovidDataMap;
    }
}
