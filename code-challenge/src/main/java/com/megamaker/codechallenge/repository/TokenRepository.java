package com.megamaker.codechallenge.repository;

public interface TokenRepository {
    String save(String value);
    String get(String key);
}
