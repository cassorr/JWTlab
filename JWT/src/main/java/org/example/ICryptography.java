package org.example;

public interface ICryptography {
    String hmacSHA256(String message, String secret);
    String encode(String rawString);
    String decode(String encodedString);
}
