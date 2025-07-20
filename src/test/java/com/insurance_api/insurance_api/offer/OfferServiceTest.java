package com.insurance_api.insurance_api.offer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

// Testklass för OfferService
// Tester för acceptOffer-metoden
// Verifierar att en offert accepteras korrekt när status inte är låst
// Verifierar att en IllegalStateException kastas när status är låst

public class OfferServiceTest {

    @Mock
    private OfferRepository offerRepository;

    @InjectMocks
    private OfferService offerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // Testar att acceptOffer sätter status till TECKNAD och returnerar korrekt svar
    // när offerten har en status som inte är låst
    @Test
    void acceptOffer_shouldAcceptOffer_whenStatusIsNotLocked() {
        Long offerId = 1L;
        Offer offer = new Offer();
        offer.setStatus(OfferStatus.SKAPAD);

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(offerRepository.save(any(Offer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OfferResponse response = offerService.acceptOffer(offerId);

        assertNotNull(response);
        assertEquals(OfferStatus.TECKNAD, offer.getStatus());
        assertEquals(OfferStatus.TECKNAD, response.getStatus());
    }

    // Testar att acceptOffer kastar IllegalStateException när offerten redan är
    // i en låst status (tex TECKNAD) och att ingen sparning sker i repository
    @Test
    void acceptOffer_shouldThrowException_whenStatusIsLocked() {
        Long offerId = 1L;
        Offer offer = mock(Offer.class);

        when(offerRepository.findById(offerId)).thenReturn(Optional.of(offer));
        when(offer.getStatus()).thenReturn(OfferStatus.TECKNAD);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            offerService.acceptOffer(offerId);
        });

        assertEquals("Offerten är redan tecknad, avvisad eller utgången och kan inte längre accepteras.",
                exception.getMessage());

        verify(offerRepository, never()).save(any(Offer.class));
    }
}