package edu.upenn.cit594.ui;

import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

public class UI {
	
	protected Processor processor;
	protected Scanner in;
	protected Logger log;
	
	public UI(Processor processor, Logger log) {
		this.processor = processor;
		in = new Scanner(System.in);
		this.log = log;
	}
	
	public void start() {
		System.out.println("Covid Data for Pennsylvania.");
		System.out.println("Enter number 1 to show the total population for all ZIP Codes.");
		System.out.println("Enter number 2 to show the total vaccinations per capita for all ZIP Codes.");
		System.out.println("Enter number 3 to show the average market value for residences in a specified ZIP Code.");
		System.out.println("Enter number 4 to show the average total livable area for residences in a specified ZIP Code.");
		System.out.println("Enter number 5 to show the total residential market value per capita for a specified ZIP Code.");
		System.out.println("Enter number 6 to show the custom attribute.");  
		System.out.println("Enter number 0 to exit the program.");
		System.out.println("Please enter the number to check covid data: ");
		int choice = in.nextInt();
		log.logInput(String.valueOf(choice));
		
		while (choice != 0) {
			
			if (choice == 1) {
				printPopulationAllZip();
			} else if (choice == 2) {
				printVaccinationPerZip();
			} else if (choice == 3) {
				printAvgMktValue();
			} else if (choice == 4) {
				printAvgLivArea();
			} else if (choice == 5) {
				printResdMktValuePerCap();
			} else if (choice == 6) {
				printCustomFeature();
			} else {
				System.out.println("Error: not valid input.");
				break;
			}
			
			System.out.println("Please enter the number to check covid data:");
			choice = in.nextInt();
			log.logInput(String.valueOf(choice));
		}
		
		System.out.println("Program ended.");
		in.close();
	}
	
	
	protected void printPopulationAllZip() {
		System.out.println("Total Population for All ZIP Codes: " + this.processor.getTotalPopulation());  // pending for edit
	}
	
	protected void printVaccinationPerZip() {
		System.out.println("Are you looking for total partial or full Vaccinations per capitas for each zipcode? Please type in partial/full: ");
		String input = in.next();
		log.logInput(input);
		input = input.toLowerCase();

		if (input.equalsIgnoreCase("partial")) {
			
			System.out.println(String.format("%5s %5s", "ENGIN", "OUTPUT"));
			
			TreeMap<String, Double> partialVaccinationData = this.processor.getPartialVaccinations();
			
	        for (Entry<String, Double> entry : partialVaccinationData.entrySet()) {
	        	System.out.printf( "%5s %1.4f \n", entry.getKey(), entry.getValue());
	        }
									
			System.out.println(String.format("%5s %5s", "END", "OUTPUT"));
			
		} else if (input.equalsIgnoreCase("full")) {
			
			System.out.println(String.format("%5s %5s", "BEGIN", "OUTPUT"));
			
			TreeMap<String, Double> fullVaccinationData = this.processor.getFullVaccinations();
			
	        for (Entry<String, Double> entry : fullVaccinationData.entrySet()) {
	        	System.out.printf( "%5s %1.4f \n", entry.getKey(), entry.getValue());
	        }
						
			System.out.println(String.format("%3s %5s", "END", "OUTPUT"));
			
		} else {
			System.out.println("The input is not valid.");
		}
		
	}
	
	protected void printAvgMktValue() {
		System.out.println("Enter a zipcode to show the average market value: ");
		String zip = in.next();
		log.logInput(zip);
		int result = this.processor.getAverageMarketValue(zip);
		System.out.println(result);
	}
	
	protected void printAvgLivArea() {
		System.out.println("Enter a zipcode to show the average total livable area: ");
		String zip = in.next();
		log.logInput(zip);
		int result = this.processor.getAverageLivableArea(zip);
		System.out.println(result);
	}
	
	protected void printResdMktValuePerCap() {
		System.out.println("Enter a zipcode to show the total residential market per capita: ");
		String zip = in.next();
		log.logInput(zip);
		int result = this.processor.getTotalResidentialValuePerCapita(zip);
		System.out.println(result);
	}
	
	protected void printCustomFeature() {
		System.out.println("Enter a zipcode to show death to average total livable area ratio per capita: ");
		String zip = in.next();
		log.logInput(zip);
		double result = this.processor.getDeathToAverageLivablePerCapita(zip);
		System.out.println(result);
	}
	
	
}
