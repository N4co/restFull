package br.com.felix.projeto.repositories;

import br.com.felix.projeto.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    org.springframework.security.core.userdetails.User findByUserName(@Param("userName") String userName);
}
