package com.insurance_api.insurance_api.offer;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.insurance_api.insurance_api.loan.Loan;

import jakarta.transaction.Transactional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer findOfferById(Long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow();
    }

    public OfferResponse createOffer(OfferRequest offerRequest) {
        double insuredAmount = calculateInsuredAmount(offerRequest.getLån());
        double premie = calculatePremium(insuredAmount);

        ZonedDateTime created = ZonedDateTime.now();
        ZonedDateTime validUntil = created.plusDays(30);

        Offer offer = Offer.builder()
                .personnummer(offerRequest.getPersonnummer())
                .lån(offerRequest.getLån())
                .månadskostnad(offerRequest.getMånadskostnad())
                .status(OfferStatus.SKAPAD)
                .premie(premie)
                .försäkratBelopp(insuredAmount)
                .skapad(created)
                .giltigTill(validUntil)
                .build();

        Offer savedOffer = saveOffer(offer);

        return mapToOfferResponse(savedOffer, insuredAmount);

    }

    public OfferResponse acceptOffer(Long offerId) {
        Offer offerDb = findOfferById(offerId);

        Set<OfferStatus> lockedStatuses = Set.of(OfferStatus.TECKNAD, OfferStatus.AVVISAD, OfferStatus.UTGÅNGEN);

        if (lockedStatuses.contains(offerDb.getStatus())) {
            throw new IllegalStateException(
                    "Offerten är redan tecknad, avvisad eller utgången och kan inte längre accepteras.");
        }

        offerDb.setStatus(OfferStatus.TECKNAD);
        Offer savedOffer = offerRepository.save(offerDb);

        return mapToOfferResponse(savedOffer, savedOffer.getFörsäkratBelopp());
    }

    public OfferResponse updateOffer(OfferUpdateRequest offerUpdateRequest, Long offerId) {
        Offer offerDb = findOfferById(offerId);

        double insuredAmount = calculateInsuredAmount(offerUpdateRequest.getLån());
        double premie = calculatePremium(insuredAmount);

        ZonedDateTime created = ZonedDateTime.now();
        ZonedDateTime validUntil = created.plusDays(30);

        offerDb.setLån(offerUpdateRequest.getLån());
        offerDb.setMånadskostnad(offerUpdateRequest.getMånadskostnad());
        offerDb.setStatus(OfferStatus.REDIGERAD);
        offerDb.setPremie(premie);
        offerDb.setFörsäkratBelopp(insuredAmount);
        offerDb.setSkapad(created);
        offerDb.setGiltigTill(validUntil);

        Offer savedOffer = saveOffer(offerDb);

        return mapToOfferResponse(savedOffer, insuredAmount);
    }

    private double calculateInsuredAmount(List<Loan> lån) {
        return lån.stream()
                .mapToDouble(Loan::getBelopp)
                .sum();
    }

    private double calculatePremium(double insuredAmount) {
        return insuredAmount * 0.038;
    }

    private OfferResponse mapToOfferResponse(Offer offer, double insuredAmount) {
        return new OfferResponse(
                offer.getId(),
                offer.getPersonnummer(),
                offer.getLån(),
                offer.getMånadskostnad(),
                insuredAmount,
                offer.getPremie(),
                offer.getStatus(),
                offer.getSkapad(),
                offer.getGiltigTill());
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public int deletePersonalData() {
        return offerRepository.deletePersonalData(ZonedDateTime.now());

    }
}