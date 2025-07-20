package com.insurance_api.insurance_api.statistics;

import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;
import com.insurance_api.insurance_api.offer.OfferRepository;

// Serviceklass för hantering av statistik för offerter

@Service
public class StatisticService {

    private final OfferRepository offerRepository;

    public StatisticService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    // Hämtar statistik för totala och accepterade offerter
    // Hämtar statistik för konverteringsgrad över X antal dagar med RequestParam
    public ConversionStatsResponse getConversionStats(int days) {
        long totalOffers = countTotalOffers();
        long acceptedOffers = countAcceptedOffersWithinXDays(days);
        double conversionRate = calculateConversionRate(totalOffers, acceptedOffers);
        String timeInterval = days + " dagar";

        return new ConversionStatsResponse(totalOffers, acceptedOffers, conversionRate, timeInterval);
    }

    // Returnerar en long med totalt antal offerter
    public long countTotalOffers() {
        return offerRepository.count();
    }

    // Returnerar accepterade offerter inom vald tidsgräns
    // Använder RequestParam för att enkelt kunna välja X antal dagar
    public long countAcceptedOffersWithinXDays(int days) {
        ZonedDateTime withinDays = ZonedDateTime.now().minusDays(days);
        return offerRepository.countAcceptedOffersSince(withinDays);
    }

    // Beräknar konverteringsgrad i procent
    // Felhantera om totalt antal offerter är 0, returnera 0
    public double calculateConversionRate(long total, long accepted) {
        if (total == 0)
            return 0.0;
        return (accepted * 100.0) / total;
    }
}