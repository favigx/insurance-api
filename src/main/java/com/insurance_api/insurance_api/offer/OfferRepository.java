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

    @Modifying
    @Transactional
    @Query("UPDATE offers o SET o.personnummer = null, o.status = 'UTGÅNGEN' " +
            "WHERE o.giltigTill < :now " +
            "AND o.status NOT IN ('TECKNAD') " +
            "AND o.personnummer IS NOT NULL")
    int deletePersonalData(@Param("now") ZonedDateTime now);
}