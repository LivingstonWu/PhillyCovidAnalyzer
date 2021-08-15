package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.CovidData;

public class Processor {

	protected CovidReader covidReader;
	protected Map<Integer, ArrayList<CovidData>> covidData;
	protected PopulationReader populationReader;
	protected HashMap<Integer, Integer> populationData;
	protected PropertiesReader propertiesReader;
	
	public Processor(CovidReader covidReader, PopulationReader populationReader, PropertiesReader propertiesReader) {
		this.covidReader = covidReader;
		this.covidData = covidReader.getAllCovidData();
		this.populationReader = populationReader;
		this.populationData = populationReader.getPopulation();
//		this.propertiesReader = propertiesReader;
		
	}
	
	
	// get the latest Total Partial Vaccination  Per Capita
	public Map<Integer, Integer> getTotalPartialPerCapita(Map<Integer, ArrayList<CovidData>> covidData) {
		
		Map<Integer, Integer> TotalPartialPerCapita = new HashMap<Integer, Integer>();
		
		for (Integer zip : covidData.keySet()) {
			ArrayList<CovidData> temp = covidData.get(zip);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 1, 1, 1, 1, 1);
			Date latestDate = calendar.getTime();       // initialize date to a historical date
			Integer TotalPartial = 0;
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
			Integer TotalFull = 0;
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
			Integer deaths = 0;
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
			Integer hospitalized = 0;
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
	
	
	// get total population for all zip code
	public int getTotalPopulation() {
		
		int totalPopulation = 0;
		
		for (int i : populationData.values()) {
			totalPopulation += i;
		}
		
		return totalPopulation;
	}
	

	
	// get full vaccinations per capita
	
	public TreeMap<Integer, Double> getFullVaccinations(){
		
		// get the latest partial vaccinations data from the raw covid data
		Map<Integer, Integer> fullVaccinationTemp = getTotalFullPerCapita(this.covidData);				
		
		TreeMap<Integer, Double> result = new TreeMap<Integer, Double>();
		
		for (Integer zip : fullVaccinationTemp.keySet()) {
			Integer fullVaccination = fullVaccinationTemp.get(zip);
			
			// ignore cases when zip is not listed in the population input file
			// ignore cases when the total aggregate vaccinations is 0
			
			if (populationData.get(zip) != null && fullVaccination != null && fullVaccination != 0 ) {
				Integer population = this.populationData.get(zip);
				Double fullVaccinationpct = (double) fullVaccination / (double) population;	
				fullVaccinationpct = (double)Math.round(fullVaccinationpct * 10000d) / 10000d;
				result.put(zip, fullVaccinationpct);
			}
			
		}				
		return result;
	}
	
	

	// get partial vaccinations per capita
	
	public TreeMap<Integer, Double> getPartialVaccinations(){
		
		// get the latest partial vaccinations data from the raw covid data
		Map<Integer, Integer> partialVaccinationTemp = getTotalPartialPerCapita(this.covidData);
		
		TreeMap<Integer, Double> result = new TreeMap<Integer, Double>();
		
		for (Integer zip : partialVaccinationTemp.keySet()) {
			

			Integer partialVaccination = partialVaccinationTemp.get(zip);
			
			// ignore cases when zip is not listed in the population input file
			//ignore cases when the total aggregate vaccinations is 0
			
			if (populationData.get(zip) != null && partialVaccination != null && partialVaccination != 0 ) {
			
			
			int population = this.populationData.get(zip);
			Double partialVaccinationpct = (double)partialVaccination / (double)population;
			partialVaccinationpct = (double)Math.round(partialVaccinationpct * 10000d) / 10000d;
			result.put(zip, partialVaccinationpct);
			}
			
		}
		return result;
		}
	
	
	
}
