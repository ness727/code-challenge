package com.megamaker.codechallenge.domain.login;

public interface TokenRepository {
    String save(String value);
    String get(String key);
}
