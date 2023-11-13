package br.com.fesvip.crm.service.security;

import br.com.fesvip.crm.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("web token")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId().toString())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("secreta"));
    }
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("web token")
                .build().verify(token).getSubject();

    }
    public long getRemainingTokenExpiration(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        Instant expiresAt = decodedJWT.getExpiresAt().toInstant();
        Instant now = Instant.now();

        if (expiresAt.isAfter(now)) {
            return ChronoUnit.SECONDS.between(now, expiresAt);
        } else {
            return 0;
        }
    }

}
