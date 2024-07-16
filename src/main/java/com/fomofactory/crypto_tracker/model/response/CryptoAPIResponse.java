package com.fomofactory.crypto_tracker.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CryptoAPIResponse {
    private String symbol;
    private String name;
    private double rate;
    private String png64;
    private double allTimeHighUSD;
    private String code;
}
