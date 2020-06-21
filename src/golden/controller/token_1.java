package golden.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.util.Date;

public class token_1 {
  public String getToken(boolean isVip, String username, String name) {
    String token = null;
    try {
      Date expiresAt = new Date(System.currentTimeMillis() + 273600000L);
      token = JWT.create()
        .withIssuer("auth0")
        .withClaim("isVip", Boolean.valueOf(isVip))
        .withClaim("account", username)
        .withClaim("username", name)
        .withExpiresAt(expiresAt)
        
        .sign(Algorithm.HMAC256("mysecret"));
    } catch (JWTCreationException jWTCreationException) {
    
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } 
    return token;
  }
}
