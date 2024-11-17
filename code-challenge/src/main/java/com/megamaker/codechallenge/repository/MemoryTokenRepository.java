package com.megamaker.codechallenge.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryTokenRepository implements TokenRepository {
    private final ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String save(String value) {
        String randomKey = UUID.randomUUID().toString();
        tokenMap.put(randomKey, value);
        return randomKey;
    }

    @Override
    public String get(String key) {
        String value = tokenMap.get(key);
        tokenMap.remove(key);
        return value;
    }
}
