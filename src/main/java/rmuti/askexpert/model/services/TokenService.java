package rmuti.askexpert.model.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rmuti.askexpert.model.exception.BaseException;
import rmuti.askexpert.model.exception.UserException;
import rmuti.askexpert.model.repo.UserNameRepository;
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

    @Autowired
    private UserNameRepository userNameRepository;

    public String tokenize(Optional<UserName> user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.get().getUserName())
                //.withClaim("email", user.get().getEmail())
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


    public UserName GetuserformJWT() throws BaseException {
        String userId = this.userId();
        String author = this.author();
        Optional<UserName> opt = userNameRepository.findByUserName(userId);
        if (opt.isEmpty()) {
            throw UserException.notFound();
        }

//        if (!author.equals("["+opt.get().get+"]")){
//            throw UserException.expires();
//        }

//        if (!opt.get().getActive()) {
//            throw UserException.accessDenied();
//        }

        UserName user = opt.get();
        return user;
    }

    public String userId(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return userId;
    }

    public String author(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String author = authentication.getAuthorities().toString();
        return author;
    }

}
