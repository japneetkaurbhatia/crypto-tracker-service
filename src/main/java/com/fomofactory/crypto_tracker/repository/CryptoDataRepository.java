package com.fomofactory.crypto_tracker.repository;

import com.fomofactory.crypto_tracker.model.db.CryptoData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoDataRepository extends MongoRepository<CryptoData, String> {
    List<CryptoData> findTop20ByCodeOrderByTimestampDesc(String code);

}
