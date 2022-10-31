package br.com.felix.projeto.services;

import br.com.felix.projeto.Exception.ResourceNotFounExceptionHandler;
import br.com.felix.projeto.model.Person;
import br.com.felix.projeto.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private static final long serialVersionUID = 1L;
    private final Logger log = Logger.getLogger(PersonService.class.getName());
    @Autowired
    private PersonRepositories repositories;

    public List<Person> findAll() {

        log.info("search or people");

        return (List<Person>) repositories.findAll();
    }
    public Person findById(Long id) {

        log.info("search or person");

        return repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

    }

    public Person created(Person person) {

        log.info("created or person");

        return repositories.save(person);
    }

    public Person update(Person person) {

        log.info("updating or person");

        var entity = repositories.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        person.setFirstName(person.getFirstName());
        person.setLastName(person.getLastName());
        person.setAddress(person.getAddress());
        person.setGender(person.getGender());
        return repositories.save(person);
    }

    public void  delete (Long id ) {

        log.info("delete or person");

        var entity = repositories.findById(id)
                .orElseThrow(() -> new ResourceNotFounExceptionHandler("No records found for this id"));

        repositories.delete(entity);

    }
}

