package com.insurance_api.insurance_api.offer;

import java.util.List;

import com.insurance_api.insurance_api.loan.Loan;

public class OfferUpdateRequest {

    private List<Loan> lån;
    private int månadskostnad;

    public OfferUpdateRequest(List<Loan> lån, int månadskostnad) {
        this.lån = lån;
        this.månadskostnad = månadskostnad;
    }

    public List<Loan> getLån() {
        return lån;
    }

    public void setLån(List<Loan> loan) {
        this.lån = loan;
    }

    public int getMånadskostnad() {
        return månadskostnad;
    }

    public void setMånadskostnad(int månadskostnad) {
        this.månadskostnad = månadskostnad;
    }
}