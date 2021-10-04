package com.app.votingsystem.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.votingsystem.models.UserDetialsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtilService {
	
private String SECRET_KEY = "B374A26A71490437AA024E4FADD5B497FDFF1A8EA6FF12F6FB65AF2720B59CCF";

	public Boolean validateToken(String token, UserDetialsImpl userDetials) {
		final String voterId = (String) extractVoterId(token);	
		return (voterId.equals(userDetials.getVoterId()) && !isTokenExpired(token));
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiryDate(token).before(new Date());
	}
	
	public Object extractVoterId(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();	
		return claims.get("id");
	}

	public String extractSubject(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiryDate(String token) {
		return extractClaims(token, Claims::getExpiration);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		final Claims claim = extractAllClaims(token);
		return claimsResolver.apply(claim);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	public String generateToken(UserDetialsImpl userDetials) {
		Map<String, Object> claims = new HashMap<>();
		
		//Hashing and storing the id.
		//claims.put("id", DigestUtils.sha256Hex(userDetials.getVoterId()));
		claims.put("id", userDetials.getVoterId());
		
		return createJwtToken(claims, userDetials);
	}
	
	private String createJwtToken(Map<String, Object> claims, UserDetialsImpl userDetials) {
		
		return Jwts.builder().setClaims(claims).setSubject(userDetials.getUsername())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(20)))
		.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
		.compact();
	}

}
