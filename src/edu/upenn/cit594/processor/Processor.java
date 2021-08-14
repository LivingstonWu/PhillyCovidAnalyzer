package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.CovidData;

public class Processor {

	protected CovidReader covidReader;
	protected Map<Integer, ArrayList<CovidData>> covidData;
	protected PopulationReader populationReader;
	protected PropertiesReader propertiesReader;
	
	public Processor(CovidReader covidReader, PopulationReader populationReader, PropertiesReader propertiesReader) {
		this.covidReader = covidReader;
		this.covidData = covidReader.getAllCovidData();
		this.populationReader = populationReader;
		this.propertiesReader = propertiesReader;
		// test
	}
	
	
	// get the latest Total Partial Vaccination  Per Capita
	public Map<Integer, Integer> getTotalPartialPerCapita(Map<Integer, ArrayList<CovidData>> covidData) {
		
		Map<Integer, Integer> TotalPartialPerCapita = new HashMap<Integer, Integer>();
		
		for (Integer zip : covidData.keySet()) {
			ArrayList<CovidData> temp = covidData.get(zip);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 1, 1, 1, 1, 1);
			Date latestDate = calendar.getTime();       // initialize date to a historical date
			int TotalPartial = 0;
			for (CovidData dataEntry : temp) {
				Date current_time = dataEntry.getTimestamp();
				if (current_time.after(latestDate)) {
					latestDate = current_time;
					TotalPartial = dataEntry.getPartiallyVaccinated();
				}
			}
			TotalPartialPerCapita.put(zip, TotalPartial);
		}
		
		return TotalPartialPerCapita;
	}
	
	
	// get the latest Total Full Vaccination  Per Capita
	public Map<Integer, Integer> getTotalFullPerCapita(Map<Integer, ArrayList<CovidData>> covidData) {
		
		Map<Integer, Integer> TotalFullPerCapita = new HashMap<Integer, Integer>();
		
		for (Integer zip : covidData.keySet()) {
			ArrayList<CovidData> temp = covidData.get(zip);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 1, 1, 1, 1, 1);
			Date latestDate = calendar.getTime();       // initialize date to a historical date
			int TotalFull = 0;
			for (CovidData dataEntry : temp) {
				Date current_time = dataEntry.getTimestamp();
				if (current_time.after(latestDate)) {
					latestDate = current_time;
					TotalFull = dataEntry.getPartiallyVaccinated();
				}
			}
			TotalFullPerCapita.put(zip, TotalFull);
		}
		
		return TotalFullPerCapita;
	}
	
	
	// get the latest Total Deaths Vaccination Per Capita
	public Map<Integer, Integer> getDeathsPerCapita(Map<Integer, ArrayList<CovidData>> covidData) {
		
		Map<Integer, Integer> DeathsPerCapita = new HashMap<Integer, Integer>();
		
		for (Integer zip : covidData.keySet()) {
			ArrayList<CovidData> temp = covidData.get(zip);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 1, 1, 1, 1, 1);
			Date latestDate = calendar.getTime();       // initialize date to a historical date
			int deaths = 0;
			for (CovidData dataEntry : temp) {
				Date current_time = dataEntry.getTimestamp();
				if (current_time.after(latestDate)) {
					latestDate = current_time;
					deaths = dataEntry.getDeaths();
				}
			}
			DeathsPerCapita.put(zip, deaths);
		}
		
		return DeathsPerCapita;
	}
	
	
	
	// get the latest Total Hospitalized Vaccination Per Capita
	public Map<Integer, Integer> getHospitalizedPerCapita(Map<Integer, ArrayList<CovidData>> covidData) {
		
		Map<Integer, Integer> HospitalizedPerCapita = new HashMap<Integer, Integer>();
		
		for (Integer zip : covidData.keySet()) {
			ArrayList<CovidData> temp = covidData.get(zip);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 1, 1, 1, 1, 1);
			Date latestDate = calendar.getTime();       // initialize date to a historical date
			int hospitalized = 0;
			for (CovidData dataEntry : temp) {
				Date current_time = dataEntry.getTimestamp();
				if (current_time.after(latestDate)) {
					latestDate = current_time;
					hospitalized = dataEntry.getHospitalized();
				}
			}
			HospitalizedPerCapita.put(zip, hospitalized);
		}
		
		return HospitalizedPerCapita;
	}
	
	
	
}
