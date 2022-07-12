package org.ariel.ApiBooks.Repositories;

import org.ariel.ApiBooks.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    public boolean existsByCode(long code);

    @Query(value = "SELECT * FROM book WHERE state = true", nativeQuery = true)
    public Page<Book> findAll(Pageable page); 

    @Modifying
    @Query(value = "UPDATE book SET state = false WHERE id = :id AND state = true", nativeQuery = true)
    public void deleteById(@Param("id") Long id);
}
