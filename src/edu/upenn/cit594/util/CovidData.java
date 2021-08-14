package edu.upenn.cit594.util;

import java.util.Date;

public class CovidData {
	
	private Date timestamp;
	private int zipcode;
	private int negTests;
	private int posTests;
	private int deaths;
	private int hospitalized;
	private int partiallyVaccinated;
	private int fullyVaccinated;
	


	public CovidData(Date timestamp, int zipcode, int negTests, int posTests, int deaths, int hospitalized, int partiallyVaccinated, int fullyVaccinated) {
		this.timestamp = timestamp;
		this.zipcode = zipcode;
		this.negTests = negTests;
		this.posTests = posTests;
		this.deaths = deaths;
		this.hospitalized = hospitalized;
		this.partiallyVaccinated = partiallyVaccinated;
		this.fullyVaccinated = fullyVaccinated;
	}
	
	
	public Date getTimestamp() {return timestamp;}
	public int getZipcode() {return zipcode;}
	public int getNegTests() {return negTests;}
	public int getPosTests() {return posTests;}
	public int getDeaths() {return deaths;}
	public int getHospitalized() {return hospitalized;}
	public int getPartiallyVaccinated() {return partiallyVaccinated;}
	public int getFullyVaccinated() {return fullyVaccinated;}
}
