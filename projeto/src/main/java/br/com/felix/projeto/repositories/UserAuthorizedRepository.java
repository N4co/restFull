package br.com.felix.projeto.repositories;

import br.com.felix.projeto.model.UserAuthorized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorizedRepository extends JpaRepository<UserAuthorized, Long> {

    @Query("SELECT u FROM UserAuthorized WHERE u.userName =:userName")
    User findByUserName(@Param("userName") String userName);
}
