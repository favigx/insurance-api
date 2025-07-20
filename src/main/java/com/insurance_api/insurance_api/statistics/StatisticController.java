package com.insurance_api.insurance_api.statistics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// REST-Controller för hantering av offert-statistik
// Endpoint för att hämta statistik samt konverteringsgrad i procent

@RestController
@RequestMapping("/stats")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    // Endpoint för att hämta offert-statistik
    @GetMapping("/conversion")
    public ResponseEntity<ConversionStatsResponse> getConversionStats(@RequestParam int days) {
        ConversionStatsResponse conversionStatsResponse = statisticService.getConversionStats(days);
        return ResponseEntity.status(HttpStatus.OK).body(conversionStatsResponse);
    }
}
