package com.deivid.boff.stonks;

import java.io.Serializable;

public class Stocks implements Serializable {
    private String nName;
    private String nLocation;
    private Double nPoints;
    private Double nVariation;

    public Stocks(String nName, String nLocation, double nPoints, double nVariation) {
        this.nName = nName;
        this.nLocation = nLocation;
        this.nPoints = nPoints;
        this.nVariation = nVariation;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnLocation() {
        return "Localização: "+nLocation;
    }

    public void setnLocation(String nLocation) {
        this.nLocation = nLocation;
    }

    public String getnPoints() {
        if(nPoints ==0.0){
            return "Sem detalhes de pontos no momento";
        }
        return "Pontos: " + nPoints;
    }

    public void setnPoints(Double nPoints) {
        this.nPoints = nPoints;
    }

    public String getnVariation() {
        return "Variação:" + nVariation + "%";
    }

    public void setnVariation(Double nVariation) {
        this.nVariation = nVariation;
    }
}
