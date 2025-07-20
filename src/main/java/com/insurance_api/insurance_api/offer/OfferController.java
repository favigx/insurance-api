package com.insurance_api.insurance_api.offer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(@RequestBody OfferRequest offerRequest) {
        OfferResponse offerResponse = offerService.createOffer(offerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(offerResponse);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<OfferResponse> acceptOffer(@PathVariable Long id) {
        OfferResponse updatedOffer = offerService.acceptOffer(id);
        return ResponseEntity.ok(updatedOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResponse> updateOffer(
            @RequestBody OfferUpdateRequest offerUpdateRequest,
            @PathVariable Long id) {
        OfferResponse offerResponse = offerService.updateOffer(offerUpdateRequest,
                id);
        return ResponseEntity.ok(offerResponse);
    }
}