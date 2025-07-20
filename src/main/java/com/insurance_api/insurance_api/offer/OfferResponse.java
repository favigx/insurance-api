package com.insurance_api.insurance_api.offer;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.insurance_api.insurance_api.loan.Loan;

public class OfferResponse {

    private Long offerId;
    private String personnummer;
    private List<Loan> lån;
    private int månadskostnad;
    private double försäkratBelopp;
    private double premie;
    private OfferStatus status;
    private ZonedDateTime skapad;
    private ZonedDateTime giltigTill;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime tecknatDatum;

    public OfferResponse() {
    }

    public OfferResponse(Long offerId, String personnummer, List<Loan> lån, int månadskostnad, double försäkratBelopp,
            double premie, OfferStatus status, ZonedDateTime skapad, ZonedDateTime giltigTill,
            ZonedDateTime tecknatDatum) {
        this.offerId = offerId;
        this.personnummer = personnummer;
        this.lån = lån;
        this.månadskostnad = månadskostnad;
        this.försäkratBelopp = försäkratBelopp;
        this.premie = premie;
        this.status = status;
        this.skapad = skapad;
        this.giltigTill = giltigTill;
        this.tecknatDatum = tecknatDatum;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public List<Loan> getLån() {
        return lån;
    }

    public void setLån(List<Loan> lån) {
        this.lån = lån;
    }

    public int getMånadskostnad() {
        return månadskostnad;
    }

    public void setMånadskostnad(int månadskostnad) {
        this.månadskostnad = månadskostnad;
    }

    public double getFörsäkratBelopp() {
        return försäkratBelopp;
    }

    public void setFörsäkratBelopp(double försäkratBelopp) {
        this.försäkratBelopp = försäkratBelopp;
    }

    public double getPremie() {
        return premie;
    }

    public void setPremie(double premie) {
        this.premie = premie;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public ZonedDateTime getSkapad() {
        return skapad;
    }

    public void setSkapad(ZonedDateTime skapad) {
        this.skapad = skapad;
    }

    public ZonedDateTime getGiltigTill() {
        return giltigTill;
    }

    public void setGiltigTill(ZonedDateTime giltigTill) {
        this.giltigTill = giltigTill;
    }

    public ZonedDateTime getTecknatDatum() {
        return tecknatDatum;
    }

    public void setTecknatDatum(ZonedDateTime tecknatDatum) {
        this.tecknatDatum = tecknatDatum;
    }
}