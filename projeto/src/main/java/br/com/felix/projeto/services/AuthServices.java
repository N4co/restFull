package br.com.felix.projeto.services;

import br.com.felix.projeto.data.v1.security.AccountCredentialsVO;
import br.com.felix.projeto.data.v1.security.TokenVO;
import br.com.felix.projeto.model.UserAuthorized;
import br.com.felix.projeto.repositories.UserAuthorizedRepository;
import br.com.felix.projeto.securityJWT.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider tokenProvider;
    @Autowired
    private UserAuthorizedRepository repository;

    @Autowired
    private UserAuthorized userAuthorized;
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUserName(username);
            var tokenResponse = new TokenVO();
            return ResponseEntity.ok(tokenResponse);
            if(user != null) {
                tokenResponse = tokenProvider.createAcessToken(username, userAuthorized.getRoles());

            }else {
                throw new UsernameNotFoundException("Username" + username + "not Found!");
            }

        }catch (Exception e) {
            throw new BadCredentialsException("Invalid Username/password supllied!");

        }
    }
}
