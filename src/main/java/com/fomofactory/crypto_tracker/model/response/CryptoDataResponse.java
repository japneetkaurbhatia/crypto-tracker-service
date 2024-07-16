package com.fomofactory.crypto_tracker.model.response;

import com.fomofactory.crypto_tracker.model.db.CryptoData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDataResponse {

    List<CryptoData> cryptoData;
}