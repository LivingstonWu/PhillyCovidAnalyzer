package edu.upenn.cit594.ui;

import java.util.Scanner;

import edu.upenn.cit594.processor.Processor;

public class UI {
	
	protected Processor processor;
	protected Scanner in;
	
	public UI(Processor processor) {
		this.processor = processor;
		in = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("Covid Data for Pennsylvania.");
		System.out.println("Enter number 1 to show the total population for all ZIP Codes.");
		System.out.println("Enter number 2 to show the total vaccinations per capita for all ZIP Codes.");
		System.out.println("Enter number 3 to show the average market value for residences in a specified ZIP Code.");
		System.out.println("Enter number 4 to show the average total livable area for residences in a specified ZIP Code.");
		System.out.println("Enter number 5 to show the total residential market value per capita for a specified ZIP Code.");
		System.out.println("Enter number 6 to show the .");    // pending: show the result of custom feature
		System.out.println("Enter number 0 to exit the program.");
		int choice = in.nextInt();
		
		while (choice != 0) {
			
			if (choice == 1) {
				printPopulationAllZip();
			}

			if (choice == 2) {
				printVaccinationPerZip();
			}
			
			if (choice == 3) {
				printAvgMktValue();
			}
			
			if (choice == 4) {
				printAvgLivArea();
			}
			
			if (choice == 5) {
				printResdMktValuePerCap();
			}
			
			if (choice == 6) {
				printCustomFeature();
			}
			
			System.out.println("Please enter the number:");
			choice = in.nextInt();
		}
		
		System.out.println("Program ended.");
		in.close();
	}
	
	
	protected void printPopulationAllZip() {
		System.out.println("Total Population for All ZIP Codes: ");  // pending for edit
	}
	
	protected void printVaccinationPerZip() {
		System.out.println("Are you looking for total partial or full Vaccinations per capitas for each zipcode? Please type in partial/full: ");
		String input = in.next();
		input = input.toLowerCase();
		if (input == "partial") {
			
			System.out.println(String.format("%6s %6s", "ENGIN", "OUTPUT"));
			
			// pending edit for printing data
						
			System.out.println(String.format("%6s %6s", "END", "OUTPUT"));
			
		} else if (input == "full") {
			
			System.out.println(String.format("%6s %6s", "ENGIN", "OUTPUT"));
			
			// pending edit for printing data
						
			System.out.println(String.format("%6s %6s", "END", "OUTPUT"));
			
		} else {
			System.out.println("The input is not valid.");
		}
		
	}
	
	protected void printAvgMktValue() {
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		
		// pending edit for printing data
		
	}
	
	protected void printAvgLivArea() {
		
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		
		// pending edit for printing data
		
	}
	
	protected void printResdMktValuePerCap() {
		System.out.println("Enter a zipcode to show the average market value: ");
		int zip = in.nextInt();
		
		// pending edit for printing data
	}
	
	protected void printCustomFeature() {
		
		// pending
		
	}
	
	
}
