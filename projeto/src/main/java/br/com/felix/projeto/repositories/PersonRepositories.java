package br.com.felix.projeto.repositories;

import br.com.felix.projeto.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Long> {



}
