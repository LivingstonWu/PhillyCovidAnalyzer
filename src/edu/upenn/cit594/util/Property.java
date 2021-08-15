package edu.upenn.cit594.util;

import java.util.Date;

public class Property {

    private int zipcode;
    private int marketValue;
    private String buildingCode;
    private int totalLivableArea;



    public Property(int zipcode, int marketValue, String buildingCode, int totalLivableArea) {
        this.zipcode = zipcode;
        this.buildingCode = buildingCode;
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
    }


    public int getZipcode() {return zipcode;}
    public int getTotalLivableArea() {return totalLivableArea;}
    public int getMarketValue() {return marketValue;}
    public String getBuildingCode() {return buildingCode;}
}
