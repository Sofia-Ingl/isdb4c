package org.example.isdb4c.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String jwtSecret = "help";

    public String generateToken(String login, int accessLvl) {
        String accessLvlStr = Integer.toString(accessLvl);
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login + "@" + accessLvlStr)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt");
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature");
        } catch (Exception e) {
            System.out.println("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String[] loginAndAccessLvl = claims.getSubject().split("@");
        return loginAndAccessLvl[0];
    }

    public Integer getAccessLvlFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String[] loginAndAccessLvl = claims.getSubject().split("@");
        try {
            int accessLvl = Integer.parseInt(loginAndAccessLvl[1]);
            if ((accessLvl > 4) || (accessLvl < 0)) {
                throw new NumberFormatException();
            }
            return  accessLvl;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds: no access lvl specified");
        } catch (NumberFormatException e) {
            System.out.println("Invalid access lvl");
        } catch (Exception e) {
            System.out.println("Error occurred");
        }
        return 0;
    }

    public String getTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

}
