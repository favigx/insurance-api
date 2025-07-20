package com.insurance_api.insurance_api.statistics;

public class ConversionStatsResponse {

    private long totalaOfferter;
    private long accepteradeOfferter;
    private double konverteringsgrad;
    private String tidsintervall;

    public ConversionStatsResponse(long totalaOfferter, long accepteradeOfferter, double konverteringsgrad,
            String tidsintervall) {
        this.totalaOfferter = totalaOfferter;
        this.accepteradeOfferter = accepteradeOfferter;
        this.konverteringsgrad = konverteringsgrad;
        this.tidsintervall = tidsintervall;
    }

    public long getTotalaOfferter() {
        return totalaOfferter;
    }

    public void setTotalaOfferter(long totalaOfferter) {
        this.totalaOfferter = totalaOfferter;
    }

    public long getAccepteradeOfferter() {
        return accepteradeOfferter;
    }

    public void setAccepteradeOfferter(long accepteradeOfferter) {
        this.accepteradeOfferter = accepteradeOfferter;
    }

    public double getKonverteringsgrad() {
        return konverteringsgrad;
    }

    public void setKonverteringsgrad(double konverteringsgrad) {
        this.konverteringsgrad = konverteringsgrad;
    }

    public String getTidsintervall() {
        return tidsintervall;
    }

    public void setTidsintervall(String tidsintervall) {
        this.tidsintervall = tidsintervall;
    }
}