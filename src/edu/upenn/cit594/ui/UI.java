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

		if (input.equals("partial")) {
			
			System.out.println(String.format("%5s %5s", "ENGIN", "OUTPUT"));
			
			TreeMap<String, Double> partialVaccinationData = this.processor.getPartialVaccinations();
			
	        for (Entry<String, Double> entry : partialVaccinationData.entrySet()) {
	        	System.out.printf( "%5d %5f \n", entry.getKey(), entry.getValue());
	        }
									
			System.out.println(String.format("%5s %5s", "END", "OUTPUT"));
			
		} else if (input.equals("full")) {
			
			System.out.println(String.format("%5s %5s", "ENGIN", "OUTPUT"));
			
			TreeMap<String, Double> fullVaccinationData = this.processor.getFullVaccinations();
			
	        for (Entry<String, Double> entry : fullVaccinationData.entrySet()) {
	        	System.out.printf( "%5d %5f \n", entry.getKey(), entry.getValue());
	        }
						
			System.out.println(String.format("%5s %5s", "END", "OUTPUT"));
			
		} else {
			System.out.println("The input is not valid.");
		}
		
	}
	
	protected void printAvgMktValue() {
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		log.logInput(String.valueOf(zip));
		
		// pending edit for printing data
		
	}
	
	protected void printAvgLivArea() {
		
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		log.logInput(String.valueOf(zip));
		
		// pending edit for printing data
		
	}
	
	protected void printResdMktValuePerCap() {
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		log.logInput(String.valueOf(zip));
		// pending edit for printing data
	}
	
	protected void printCustomFeature() {
		
		// pending
		
	}
	
	
}
