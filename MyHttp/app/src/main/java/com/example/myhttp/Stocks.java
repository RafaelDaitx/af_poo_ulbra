package com.example.myhttp;

public class Stocks {

    private String nName;
    private String nLocation;
    private String nVariation;

    public Stocks(String nName, String nLocation, String nVariation) {
        this.nName = nName;
        this.nLocation = nLocation;
        this.nVariation = nVariation;
    }

    public String getName() {
        return nName;
    }

    public String getLocation() {
        return "País " + nLocation;

    }

    public String getVariation() {
        return nVariation.toString();
    }

}
