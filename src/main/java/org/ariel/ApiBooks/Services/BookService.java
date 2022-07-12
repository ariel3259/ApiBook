package org.ariel.ApiBooks.Services;

import org.ariel.ApiBooks.Model.Book;
import org.ariel.ApiBooks.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository repository;

    public Page<Book> getAll(int index){
        Pageable page = PageRequest.of(index, 10);
        return repository.findAll(page);
    }

    public boolean save(Book book){
        if(repository.existsByCode(book.getCode())) return false;
        repository.save(book);
        return true;
    }

    public boolean update(Book book){
        if(!repository.existsById(book.getId())) return false;
        repository.save(book);
        return true;
    }

    public boolean delete(Long id){
        if(!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }    

}
