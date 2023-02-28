package br.com.felix.projeto.config;


import br.com.felix.projeto.securityJWT.JWTTokenProvider;
import br.com.felix.projeto.securityJWT.JwtConfigurer;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

import static org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry.RequestMatchers.antMatchers;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JWTTokenProvider tokenProvider;
    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashedMap();
        Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256 );
        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder =
                new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder;

    }
    @Bean
    AuthenticationManager authenticationManagerBean (
            AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable()
                .csrf(csrf -> csrf.disable())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/auth/signin",
                        "/auth/refresh",
                        "/api-doc/**/",
                        "/swagger-ui.html**"
                ).permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/users").denyAll()
                .and()
                .cors()
                .and()
                .apply(new JwtConfigurer(tokenProvider));
    }


    }



