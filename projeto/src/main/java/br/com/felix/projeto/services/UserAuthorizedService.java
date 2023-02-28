package br.com.felix.projeto.services;

import br.com.felix.projeto.Exception.ResourceNotFounExceptionHandler;
import br.com.felix.projeto.controller.PersonController;
import br.com.felix.projeto.data.v1.PersonVo;
import br.com.felix.projeto.mapper.DozerConverter;
import br.com.felix.projeto.mapper.custom.PersonMapper;
import br.com.felix.projeto.repositories.UserAuthorizedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserAuthorizedService implements UserDetailsService {
    private final Logger log = Logger.getLogger(UserAuthorizedService.class.getName());
    @Autowired
    private UserAuthorizedRepository repositories;
    @Autowired
    private PersonMapper personMapper;

    public UserAuthorizedService(UserAuthorizedRepository repository) {
        this.repositories = repository;
    }
    public PersonVo findById(Long id) {



       var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));
        var vo = DozerConverter.parseObject(entity, PersonVo.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;

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

