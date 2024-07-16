package com.fomofactory.crypto_tracker.model.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cryptoData")
public class CryptoData {

    private String code;
    private String name;
    private double price;
    private String image;
    private double allTimeHighPrice;
    private String symbol;
    private LocalDateTime timestamp;
}