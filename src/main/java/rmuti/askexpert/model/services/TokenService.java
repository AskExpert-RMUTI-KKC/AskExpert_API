package rmuti.askexpert.model.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rmuti.askexpert.model.table.UserName;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(Optional<UserName> user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.get().getUserName())
                .withClaim("email", user.get().getEmail())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

}
