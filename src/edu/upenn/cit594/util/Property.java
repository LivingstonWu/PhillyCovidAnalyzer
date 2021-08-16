package edu.upenn.cit594.util;

import java.util.Date;

public class Property {

    private String  zipcode;
    private String marketValue;
    private String buildingCode;
    private String totalLivableArea;



    public Property(String zipcode, String marketValue, String buildingCode, String totalLivableArea) {
        this.zipcode = zipcode;
        this.buildingCode = buildingCode;
        this.marketValue = marketValue;
        this.totalLivableArea = totalLivableArea;
    }


    public String getZipcode() {return zipcode;}
    public String getTotalLivableArea() {return totalLivableArea;}
    public String getMarketValue() {return marketValue;}
    public String getBuildingCode() {return buildingCode;}
}
