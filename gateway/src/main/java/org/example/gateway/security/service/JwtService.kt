package org.example.gateway.security.service


import com.nimbusds.jose.JOSEObjectType.JWT
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoder
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.security.Signature
import java.util.Date
import kotlin.io.encoding.Base64


@Service
class JwtService {

    //В будущем уберу в env файл
    val SECRET_KEY = "4F635166546A576E5A7234753778214125442A462D4A614E645267556B587032"

    fun generateRefreshToken(claims: Map<String, Any>, userDetails: UserDetails): String {



        return Jwts.builder()
            .claims()
            .issuer("Bank_gateway_service")
            .subject(userDetails.username)
            .audience().add()



//                   .setClaims(claims)
//                   .setSubject(userDetails.username)
//                   .setIssuedAt(Date(System.currentTimeMillis()))
//                   .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
//                   .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                   .compact()
    }

    fun getSignInKey() : Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY))
    }

//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}

//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }
//        return Jwts
//                .builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 часа
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
