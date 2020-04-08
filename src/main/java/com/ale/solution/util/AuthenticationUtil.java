package com.ale.solution.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationUtil {

    // 密钥
    public static final String MY_SECRET = "secret";
    // JWT签发主体
    public static final String ISSUER = "alewu";
    // jackson
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        System.out.println("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTAyNDE5MTgsInN1YiI6IntcImdtdENyZWF0ZVwiOm51bGwsXCJnbXRNb2RpZmllZFwiOm51bGwsXCJzaG9wSWRcIjpcIjBlYjg4NDg1MTZiYzQyODlhYzZiZTBjZTk5Nzc4YTkyXCIsXCJtYWxsSWRcIjpudWxsLFwic2hvcE93bmVyTmFtZVwiOm51bGwsXCJqcHVzaEFsaWFzXCI6bnVsbCxcInNob3BPd25lclBob25lXCI6bnVsbCxcInNob3BPd25lclBhc3N3b3JkXCI6XCJcIixcInNob3BJZGVudGlmZXJcIjpudWxsLFwic2hvcE5hbWVcIjpudWxsLFwic2hvcEFkZHJlc3NcIjpudWxsLFwic2hvcERldGFpbGVkQWRkcmVzc1wiOm51bGwsXCJhZE1hY2hpbmVWZXJzaW9uXCI6bnVsbCxcInNob3BTdGF0dXNcIjpudWxsfSIsImlzcyI6ImFsZXd1In0.AoJTGihQ6vUG5vr0ZrNoU3saXXJ4tp8C2AtjXCp56pU".length());
    }

    public static <T> String createJWT(T obj, long maxAge) {
        try {
            JWTCreator.Builder builder = JWT.create();
            builder.withHeader(createHeaderClaims())
                    .withIssuer(ISSUER)
                    .withSubject(mapper.writeValueAsString(obj));
            if (maxAge >= 0) {
                long expMillis = System.currentTimeMillis() + maxAge;
                Date exp = new Date(expMillis);
                builder.withExpiresAt(exp);
            }
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            return builder.sign(algorithm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return null;
    }

    public static <T> T validateJWT(String token, Class<T> clazz) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(MY_SECRET);
            //Reusable verifier instance
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            // JWT的过期时间
            Date exp = jwt.getExpiresAt();
            // Date1.after(Date2), 当Date1大于Date2时，返回TRUE
            if (exp != null && exp.after(new Date())) {
                String subject = jwt.getSubject();
                return mapper.readValue(subject, clazz);
            }

        } catch (JWTVerificationException | IOException exception) {
            //UTF-8 encoding not supported
            exception.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> createHeaderClaims() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        return map;
    }

}
