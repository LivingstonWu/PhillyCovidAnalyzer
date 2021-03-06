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
		
		try {
			while (true) {
				System.out.println("Please enter the number to check covid data: ");
				System.out.println(">");
				System.out.flush();
				String input = in.next();
				log.logInput(input);
				int choice = Integer.parseInt(input);

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
				} else if (choice == 0) {
					System.out.println("Exit program.");
					break;
				} else {
					System.out.println("Error: not valid input.");
					break;
				}

			}
		} catch (Exception ex) {
			System.out.println("Invalid input, program ended.");
			in.close();
		}
		
		System.out.println("Program ended.");
		in.close();
	}
	
	
	protected void printPopulationAllZip() {
		String temp = String.valueOf(this.processor.getTotalPopulation());
		System.out.println("BEGIN OUTPUT");
		System.out.println(temp);
		System.out.println("END OUTPUT");
	}
	
	protected void printVaccinationPerZip() throws Exception {
		System.out.println("Are you looking for total partial or full Vaccinations per capitas for each zipcode? Please type in partial/full: ");
		System.out.println(">");
		System.out.flush();
		String input = in.next();
		log.logInput(input);
		input = input.toLowerCase();

		if (input.equalsIgnoreCase("partial")) {
			
			System.out.println("BEGIN OUTPUT");
			TreeMap<String, Double> partialVaccinationData = this.processor.getPartialVaccinations();
			
	        for (Entry<String, Double> entry : partialVaccinationData.entrySet()) {
	        	String temp = (entry.getKey() + " " + String.format("%1.4f", entry.getValue()));
	        	System.out.println(temp);
	        }
									
	        System.out.println("END OUTPUT");
		} else if (input.equalsIgnoreCase("full")) {
			
			System.out.println("BEGIN OUTPUT");
			TreeMap<String, Double> fullVaccinationData = this.processor.getFullVaccinations();
			
	        for (Entry<String, Double> entry : fullVaccinationData.entrySet()) {
				String temp = (entry.getKey() + " " + String.format("%1.4f", entry.getValue()));
	        	System.out.println(temp);
	        }
						
	        System.out.println("END OUTPUT");
			
		} else {
			System.out.println("The input is not valid.");
			throw new Exception("invalid input, program terminated.");
		}
		
	}
	
	protected void printAvgMktValue() {
		
		while (true) {
			System.out.println("Enter a zipcode to show the average market value: ");
			System.out.println(">");
			System.out.flush();
			String zip = in.next();
			log.logInput(zip);
			int result = this.processor.getAverageMarketValue(zip);
			System.out.println("BEGIN OUTPUT");
			System.out.println(result);
			System.out.println("END OUTPUT");
			break;
		}
		
		

	}
	
	protected void printAvgLivArea() {
		
		while (true) {
			System.out.println("Enter a zipcode to show the average total livable area: ");
			System.out.println(">");
			System.out.flush();
			String zip = in.next();
			log.logInput(zip);
			int result = this.processor.getAverageLivableArea(zip);
			System.out.println("BEGIN OUTPUT");
			System.out.println(result);
			System.out.println("END OUTPUT");
			break;

		}
		

	}
	
	protected void printResdMktValuePerCap() throws Exception {
		
		while (true) {
			System.out.println("Enter a zipcode to show the total residential market per capita: ");
			System.out.println(">");
			System.out.flush();
			String zip = in.next();
			log.logInput(zip);
			int result = this.processor.getTotalResidentialValuePerCapita(zip);
			System.out.println("BEGIN OUTPUT");
			System.out.println(result);
			System.out.println("END OUTPUT");
			break;
		}
		

	}
	
	protected void printCustomFeature() throws Exception {
		
		while (true) {
			System.out.println("Enter a zipcode to show survival market value per capita by zipcpde: ");
			System.out.println(">");
			System.out.flush();
			String zip = in.next();
			log.logInput(zip);
			double result = this.processor.getDeathToAverageLivablePerCapita(zip);
			System.out.println("BEGIN OUTPUT");
			System.out.println(result);
			System.out.println("END OUTPUT");
			break;
		}
	}
	
}
