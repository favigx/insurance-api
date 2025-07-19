package com.insurance_api.insurance_api.offer;

import java.util.List;

import com.insurance_api.insurance_api.loan.Loan;

public class OfferRequest {

    private String personnummer;
    private List<Loan> lån;
    private int månadskostnad;

    public OfferRequest() {
    }

    public OfferRequest(String personnummer, List<Loan> lån, int månadskostnad) {
        this.personnummer = personnummer;
        this.lån = lån;
        this.månadskostnad = månadskostnad;
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
}