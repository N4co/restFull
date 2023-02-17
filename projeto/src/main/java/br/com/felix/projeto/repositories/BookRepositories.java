package br.com.felix.projeto.repositories;

import br.com.felix.projeto.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<Book, Long> {



}
