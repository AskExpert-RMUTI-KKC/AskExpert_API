package rmuti.askexpert.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import rmuti.askexpert.model.config.token.TokenFilterConfiguerer;
import rmuti.askexpert.model.services.TokenService;

import java.util.Arrays; 

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String[] AuthenPath = {
            "/topic/add",
            "/topic/remove",
            "/topic/findMyTopic",
            "/topic/findAll",
            "/topic/read",
            "/topic/findById",

            "/comment/**",

            "/like/setStatus",
            "/like/findMyLike",

            "/user/update",
            "/user/checkJWT",
            "/user/refreshJWT",
            "/user/findById",

            "/transaction/transfer",
            "/transaction/withdraw",
            "/transaction/deposit",
            "/transaction/findMyPay",
            "/transaction/transactionHistory",

            "/verify/**",

            "/expertGroupList/add",
            "/expertGroupList/remove",
            "/expertGroupList/update",

            "/topicGroupList/add",
            "/topicGroupList/remove",
            "/topicGroupList/update",

            "/image/userInfoDataProfile",
            "/image/topicImg",
            "/image/commentImg",
            "/image/verifyImg",

            "/chat/**",
    };
    private final TokenService tokenService;

    public SecurityConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.cors().disable().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            //.and().authorizeRequests().antMatchers(PUBLIC).anonymous()
            //.anyRequest().authenticated()
            .and().authorizeRequests().antMatchers(AuthenPath).authenticated()
            .anyRequest().anonymous()
            .and().apply(new TokenFilterConfiguerer(tokenService));
    }

    // TODO : Carefull config
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors(config -> {
//                    CorsConfiguration corsConfiguration = new CorsConfiguration();
//                    corsConfiguration.setAllowCredentials(true);
////                    Configuration.setAllowedOriginPatterns(Collections.singletonList("http://*"));
//                    corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("http://*"));
//                    corsConfiguration.addAllowedHeader("*");
//                    corsConfiguration.addAllowedMethod("GET");
//                    corsConfiguration.addAllowedMethod("POST");
////                    Configuration.addAllowedMethod("PUT");
////                   Configuration.addAllowedMethod("DELETE");
////                    Configuration.addAllowedMethod("OPTIONS");
//
//                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                    source.registerCorsConfiguration("/**", corsConfiguration);
//
//                    config.configurationSource(source);
//                }).csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                //and().authorizeRequests().antMatchers(PUBLIC).anonymous()
//                //.anyRequest().authenticated()
//                .and().authorizeRequests().antMatchers(PUBLIC).authenticated()
//                .anyRequest().anonymous()
//                .and().apply(new TokenFilterConfiguerer(tokenService));
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
