package br.com.felix.projeto.services;

import br.com.felix.projeto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {
    private final Logger log = Logger.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository repositories;
    public UserService(UserRepository repository) {
        this.repositories = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Finding one user by name " + username + "!");
        var user = repositories.findByUserName(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

    }
}

