package edu.upenn.cit594.util;

import java.util.Date;

public class CovidData {
	
	private Date timestamp;
	private String zipcode;
	private Integer negTests;
	private Integer posTests;
	private Integer deaths;
	private Integer hospitalized;
	private Integer partiallyVaccinated;
	private Integer fullyVaccinated;
	


	public CovidData(Date timestamp, String zipcode, Integer negTests, Integer posTests, Integer deaths, Integer hospitalized, Integer partiallyVaccinated, Integer fullyVaccinated) {
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
	public String getZipcode() {return zipcode;}
	public Integer getNegTests() {return negTests;}
	public Integer getPosTests() {return posTests;}
	public Integer getDeaths() {return deaths;}
	public Integer getHospitalized() {return hospitalized;}
	public Integer getPartiallyVaccinated() {return partiallyVaccinated;}
	public Integer getFullyVaccinated() {return fullyVaccinated;}
}
