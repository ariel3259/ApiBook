package org.ariel.ApiBooks.Repositories;

import java.util.Optional;

import org.ariel.ApiBooks.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    
    @Query(value = "SELECT * FROM users WHERE email = :email AND state = true", nativeQuery = true)
    public Optional<Users> findByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE users SET state = false WHERE id = :id", nativeQuery = true)
    public void deleteById(@Param("id") Long id);

}
