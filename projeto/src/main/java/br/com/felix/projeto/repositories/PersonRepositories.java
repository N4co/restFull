package br.com.felix.projeto.repositories;

import br.com.felix.projeto.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositories extends CrudRepository<Person, Long> {



}
