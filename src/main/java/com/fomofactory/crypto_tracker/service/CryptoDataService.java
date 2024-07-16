package com.fomofactory.crypto_tracker.service;

import com.fomofactory.crypto_tracker.model.db.CryptoData;
import com.fomofactory.crypto_tracker.model.response.CryptoAPIResponse;
import com.fomofactory.crypto_tracker.repository.CryptoDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CryptoDataService {

    private static final List<String> CRYPTO_CODES = List.of("BTC", "ETH", "BNB", "AVAX", "ICP");

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private CryptoDataRepository cryptoDataRepository;

    @Scheduled(fixedRate = 5000)
    public void pollStockData() {
        log.info("Fetching real-time data from livecoinwatch API");
        fetchDataFromApi();
    }

    public void fetchDataFromApi() {
        for (String code : CRYPTO_CODES) {
            String apiUrl = "https://api.livecoinwatch.com/coins/single";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("x-api-key", apiKey);

            String requestJson = String.format("{\n\t\"code\": \"%s\",\n\t\"meta\": true\n}", code);
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

            ResponseEntity<CryptoAPIResponse> cryptoResponse = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, CryptoAPIResponse.class);

            if (cryptoResponse != null) {
                    CryptoData cryptoData = CryptoData.builder().code(code)
                            .name(cryptoResponse.getBody().getName())
                            .price(cryptoResponse.getBody().getRate())
                            .image(cryptoResponse.getBody().getPng64())
                            .symbol(cryptoResponse.getBody().getSymbol())
                            .timestamp(LocalDateTime.now())
                            .allTimeHighPrice(cryptoResponse.getBody().getAllTimeHighUSD()).build();
                    cryptoDataRepository.save(cryptoData);

            }
        }
    }

    public List<CryptoData> getRecentCryptoData(String code) {
        log.info("Fetching recent 20 real-time data for crypto {} from database", code);
        return cryptoDataRepository.findTop20ByCodeOrderByTimestampDesc(code);
    }

    public List<CryptoData> getAllCryptoData() {
        return cryptoDataRepository.findAll();
    }

}
