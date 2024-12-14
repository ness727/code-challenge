package com.megamaker.codechallenge.user.domain;

public interface TokenRepository {
    String save(String value);
    String get(String key);
}
