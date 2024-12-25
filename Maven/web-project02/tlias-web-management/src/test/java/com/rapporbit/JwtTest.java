package com.rapporbit;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateToken() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        dataMap.put("dddd", "sdsds");
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "cmFwcG9yYml0")
                .addClaims(dataMap)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .compact();
        System.out.println(token);
    }

//    @Test
//    public void testParseToken() {
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZGRkZCI6InNkc2RzIiwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTczMzY2NTQ2M30.yI7uf2XK37bRPh3jHq8UFSAbBuwCwViZ_z87_B8Vlnc";
//        Map<String, Object> body = Jwts.parser()
//                .setSigningKey("cmFwcG9yYml0")
//                .parseClaimsJws(token)
//                .getBody();
//        System.out.println(body);
//    }
}
