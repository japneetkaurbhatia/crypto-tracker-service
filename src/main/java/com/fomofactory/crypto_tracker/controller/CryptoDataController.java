package com.fomofactory.crypto_tracker.controller;

import com.fomofactory.crypto_tracker.model.db.CryptoData;
import com.fomofactory.crypto_tracker.service.CryptoDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crypto/data")
@Slf4j
public class CryptoDataController {

    private final CryptoDataService cryptoDataService;

    public CryptoDataController(CryptoDataService cryptoDataService) {
        this.cryptoDataService = cryptoDataService;
    }

    @GetMapping("/real-time/{code}")
    public List<CryptoData> getCryptoRealTimeData(@PathVariable String code) {
        log.info("Getting real-time data for crypto {}", code);
        return cryptoDataService.getRecentCryptoData(code);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CryptoData>> getAllCryptoData() {
        List<CryptoData> cryptoDataList = cryptoDataService.getAllCryptoData();
        return ResponseEntity.ok(cryptoDataList);
    }
}