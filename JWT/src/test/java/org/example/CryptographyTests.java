package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CryptographyTests {

    private Cryptography cryptography;

    @Before
    public void setUp() {
        this.cryptography = new Cryptography();
    }

    @Test
    public void testDecode() {
        String encodedString = "cCI6IkpXVCJ9";
        String decodedString = cryptography.decode(encodedString);
        assertEquals("p\":\"JWT\"}", decodedString);
    }

    @Test
    public void testEncode(){
        String rawString = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String encodedString = cryptography.encode(rawString);
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9", encodedString);

        rawString = "{\"name\":\"DevDay23\",\"iat\":1684245600}";
        encodedString = cryptography.encode(rawString);
        assertEquals("eyJuYW1lIjoiRGV2RGF5MjMiLCJpYXQiOjE2ODQyNDU2MDB9", encodedString);
    }

    @Test
    public void testHmacSHA256() {
        String headerEncoded = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        String payloadEncoded = "eyJuYW1lIjoiRGV2RGF5MjMiLCJpYXQiOjE2ODQyNDU2MDB9";
        String secret = "useauth0byoktatobuildyourcustomidentitypipeline";
        String data = headerEncoded + "." + payloadEncoded;
        String signature = cryptography.hmacSHA256(data, secret);
        String jwtToken = data + "." + signature;

        System.out.println(jwtToken);
        assertEquals("PtEH73_fTnj4vbd6js8G66H3Me69RqSE-0w3bevBfNE", signature);
    }
}
