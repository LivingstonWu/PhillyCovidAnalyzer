package edu.upenn.cit594.processor;

import java.util.*;
import java.util.Map.Entry;

import edu.upenn.cit594.datamanagement.CovidReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertiesReader;
import edu.upenn.cit594.util.CovidData;
import edu.upenn.cit594.util.Property;

public class Processor {

	protected CovidReader covidReader;
	protected Map<String, ArrayList<CovidData>> covidData;
	protected PopulationReader populationReader;
	protected HashMap<String, Integer> populationData;
	protected PropertiesReader propertiesReader;
	protected Map<String, LinkedList<Property>> propertiesData;
	
	public Processor(CovidReader covidReader, PopulationReader populationReader, PropertiesReader propertiesReader) {
		this.covidReader = covidReader;
		this.covidData = covidReader.getAllCovidData();
		this.populationReader = populationReader;
		this.populationData = populationReader.getPopulation();
		this.propertiesReader = propertiesReader;
		this.propertiesData = propertiesReader.getAllProperties();
		
	}
	
	
	// get the latest Total Partial Vaccination  Per Capita
	public Map<String, Integer> getTotalPartialPerCapita(Map<String, ArrayList<CovidData>> covidData) {
		
		Map<String, Integer> TotalPartialPerCapita = new HashMap<String, Integer>();
		
		for (String zip : covidData.keySet()) {
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
	public Map<String, Integer> getTotalFullPerCapita(Map<String, ArrayList<CovidData>> covidData) {
		
		Map<String, Integer> TotalFullPerCapita = new HashMap<String, Integer>();
		
		for (String zip : covidData.keySet()) {
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
	public Map<String, Integer> getDeathsPerCapita(Map<String, ArrayList<CovidData>> covidData) {
		
		Map<String, Integer> DeathsPerCapita = new HashMap<String, Integer>();
		
		for (String zip : covidData.keySet()) {
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
	public Map<String, Integer> getHospitalizedPerCapita(Map<String, ArrayList<CovidData>> covidData) {
		
		Map<String, Integer> HospitalizedPerCapita = new HashMap<String, Integer>();
		
		for (String zip : covidData.keySet()) {
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
	
	public TreeMap<String, Double> getFullVaccinations(){
		
		// get the latest partial vaccinations data from the raw covid data
		Map<String, Integer> fullVaccinationTemp = getTotalFullPerCapita(this.covidData);				
		
		TreeMap<String, Double> result = new TreeMap<String, Double>();
		
		for (String zip : fullVaccinationTemp.keySet()) {
			Integer fullVaccination = fullVaccinationTemp.get(zip);
			
			// ignore cases when zip is not listed in the population input file
			// ignore cases when the total aggregate vaccinations is 0
			
			if (populationData.get(zip) != null && fullVaccination != null && fullVaccination != 0 ) {
				Integer population = this.populationData.get(zip);
				Double fullVaccinationpct = (double) fullVaccination / (double) population;	
				fullVaccinationpct = (double)Math.round(fullVaccinationpct * 10000d) / 10000d;
				fullVaccinationpct = truncatedDouble(fullVaccinationpct, 4);
				result.put(zip, fullVaccinationpct);
			}
			
		}				
		return result;
	}
	
	

	// get partial vaccinations per capita
	
	public TreeMap<String, Double> getPartialVaccinations(){
		
		// get the latest partial vaccinations data from the raw covid data
		Map<String, Integer> partialVaccinationTemp = getTotalPartialPerCapita(this.covidData);
		
		TreeMap<String, Double> result = new TreeMap<String, Double>();
		
		for (String zip : partialVaccinationTemp.keySet()) {
			

			Integer partialVaccination = partialVaccinationTemp.get(zip);
			
			// ignore cases when zip is not listed in the population input file
			//ignore cases when the total aggregate vaccinations is 0
			
			if (populationData.get(zip) != null && partialVaccination != null && partialVaccination != 0 ) {
			
			
			int population = this.populationData.get(zip);
			Double partialVaccinationpct = (double)partialVaccination / (double)population;
			partialVaccinationpct = truncatedDouble(partialVaccinationpct, 4);
			result.put(zip, partialVaccinationpct);
			}
			
		}
		return result;
	}
	
	
	
	// truncated double
	public Double truncatedDouble(Double value, int decimal) {
		
		value = value * Math.pow(10, decimal);
		value = Math.floor(value);
		value = value / Math.pow(10, decimal);
//		System.out.println(value);
		return value;
	}



	// method3  average market value
	// user input zipcode
	// deal with invalid zipcode(!this.propertiesData.containsKey(zipcode)) input in UI
	public int getAverageMarketValue(String zipcode) {
		AverageCalculator marketValueCalculator = new AverageMarketValueCalculator();
		if (!this.propertiesData.containsKey(zipcode)) return 0;
		return marketValueCalculator.calculateAverage(zipcode, this.propertiesData);
	}

	// method4  average livable area
	// user input zipcode
	// deal with invalid zipcode input in UI
	public int getAverageLivableArea(String zipcode) {
		AverageCalculator livableAreaCalculator  = new AverageLivableAreaCalculator();
		if (!this.propertiesData.containsKey(zipcode)) return 0;
		return livableAreaCalculator.calculateAverage(zipcode, this.propertiesData);
	}


	// method5  total residential market value per capita
	// user input zipcode
	// deal with invalid zipcode input in UI
	public int getTotalResidentialValuePerCapita(String zipcode) {
		double totalResidentialMarketValue = 0;
		if (!populationData.containsKey(zipcode) || !propertiesData.containsKey(zipcode)){
			return 0;
		}
		int totalPopulationAtZipcode = this.populationData.get(zipcode);

		LinkedList<Property> propertiesByZip = propertiesData.get(zipcode);
		for (Property currentProperty : propertiesByZip) {
			try {
				double currentMarketValue = Double.parseDouble(currentProperty.getMarketValue());
				totalResidentialMarketValue += currentMarketValue;
			} catch(Exception e) {
				// logging
			}
		}

		if (totalPopulationAtZipcode == 0 || totalResidentialMarketValue == 0) return 0;
		int perCapita = (int) (totalResidentialMarketValue/totalPopulationAtZipcode);
		return perCapita;
	}


	// method 6: death to "average total livable area" ratio per capita, by zipcpde
	// input: zipcode
	// resulting metric = total death at the zipcode / getAverageLivableArea(zipcode) / population at zipcode
	public int getDeathToAverageLivablePerCapita(String zipcode) {
		if (!populationData.containsKey(zipcode) || !covidData.containsKey(zipcode) || !propertiesData.containsKey(zipcode)){
			return 0;
		}
		int averageLivableArea = getAverageLivableArea(zipcode);
		Map<String, Integer> deathZipMap =  getDeathsPerCapita(this.covidData);
		int deathCount = deathZipMap.get(zipcode);
		int populationAtZip = this.populationData.get(zipcode);
		int result = deathCount/averageLivableArea/populationAtZip;
		return result;

	}


	
}
