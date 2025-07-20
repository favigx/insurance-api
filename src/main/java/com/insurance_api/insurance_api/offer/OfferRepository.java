package com.insurance_api.insurance_api.offer;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    // Sätter personnummer till null och ändrar status till 'UTGÅNGEN' för offerter
    // som har passerat giltigTill datum. Ändrar ej offerter som redan är TECKNAD
    // samt där personnummer redan är NULL
    // Används för att anonymisera personuppgifter efter giltighetstidens slut
    // (GDPR)
    @Modifying
    @Transactional
    @Query("UPDATE offers o SET o.personnummer = null, o.status = 'UTGÅNGEN' " +
            "WHERE o.giltigTill < :now " +
            "AND o.status NOT IN ('TECKNAD') " +
            "AND o.personnummer IS NOT NULL")
    int deletePersonalData(@Param("now") ZonedDateTime now);

    // Räknar antalet offerter med status 'TECKNAD' som har accepterats sedan en
    // viss tidpunkt
    // Används för att ta fram statistik på acceptera offerter inom en viss
    // tidsperiod
    @Query("SELECT COUNT(o) FROM offers o WHERE o.status = 'TECKNAD' AND o.tecknatDatum >= :withinDays")
    long countAcceptedOffersSince(@Param("withinDays") ZonedDateTime withinDays);
}