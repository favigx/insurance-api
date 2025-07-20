package com.insurance_api.insurance_api.offer;

import java.time.ZonedDateTime;
import java.util.List;

import com.insurance_api.insurance_api.loan.Loan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;

@Builder
@Entity(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personnummer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Loan> lån;

    private int månadskostnad;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private double premie;
    private double försäkratBelopp;
    private ZonedDateTime skapad;
    private ZonedDateTime giltigTill;
    private ZonedDateTime tecknatDatum;

    public Offer() {
    }

    public Offer(Long id, String personnummer, List<Loan> lån, int månadskostnad, OfferStatus status, double premie,
            double försäkratBelopp, ZonedDateTime skapad, ZonedDateTime giltigTill, ZonedDateTime tecknatDatum) {
        this.id = id;
        this.personnummer = personnummer;
        this.lån = lån;
        this.månadskostnad = månadskostnad;
        this.status = status;
        this.premie = premie;
        this.försäkratBelopp = försäkratBelopp;
        this.skapad = skapad;
        this.giltigTill = giltigTill;
        this.tecknatDatum = tecknatDatum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public double getPremie() {
        return premie;
    }

    public void setPremie(double premie) {
        this.premie = premie;
    }

    public double getFörsäkratBelopp() {
        return försäkratBelopp;
    }

    public void setFörsäkratBelopp(double försäkratBelopp) {
        this.försäkratBelopp = försäkratBelopp;
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