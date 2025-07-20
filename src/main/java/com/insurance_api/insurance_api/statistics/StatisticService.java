package com.insurance_api.insurance_api.statistics;

import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;
import com.insurance_api.insurance_api.offer.OfferRepository;

@Service
public class StatisticService {

    private final OfferRepository offerRepository;

    public StatisticService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public ConversionStatsResponse getConversionStats(int days) {
        long totalOffers = countTotalOffers();
        long acceptedOffers = countAcceptedOffersWithinXDays(days);
        double conversionRate = calculateConversionRate(totalOffers, acceptedOffers);
        String timeInterval = days + " dagar";

        return new ConversionStatsResponse(totalOffers, acceptedOffers, conversionRate, timeInterval);
    }

    public long countTotalOffers() {
        return offerRepository.count();
    }

    public long countAcceptedOffersWithinXDays(int days) {
        ZonedDateTime withinDays = ZonedDateTime.now().minusDays(days);
        return offerRepository.countAcceptedOffersSince(withinDays);
    }

    public double calculateConversionRate(long total, long accepted) {
        if (total == 0)
            return 0.0;
        return (accepted * 100.0) / total;
    }
}