package org.ariel.ApiBooks.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.ariel.ApiBooks.Model.Book;
import org.ariel.ApiBooks.Repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {
    
    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    private Book book = new Book(1, "La Odisea de homero", "", true, 121212);

    private Page<Book> bookPage = new PageImpl<>(Arrays.asList(book));

    @Test
    public void GET_ALL(){
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(bookPage);
        Page<Book> books = service.getAll(1);
        assertEquals(bookPage, books);
    }

    @Test
    public void GET_ALL_BUT_IT_IS_EMPTY(){
        when(repository.findAll(PageRequest.of(1, 10))).thenReturn(null);
        Page<Book> books = service.getAll(1);
        assertNull(books);
    }

    @Test
    public void SAVE(){
        when(repository.existsByCode(book.getCode())).thenReturn(false);
        when(repository.save(book)).thenReturn(book);
        boolean result = service.save(book);
        assertTrue(result);
    }

    @Test
    public void SAVE_BUT_BOOK_ALREADY_EXISTS(){
        when(repository.existsByCode(book.getCode())).thenReturn(true);
        boolean result = service.save(book);
        assertFalse(result);
    }

    @Test
    public void UPDATE(){
        when(repository.existsById(book.getId())).thenReturn(true);
        when(repository.save(book)).thenReturn(book);
        boolean result = service.update(book);
        assertTrue(result);
    }

    @Test
    public void UPDATE_BUT_BOOK_DOES_NOT_EXISTS(){
        when(repository.existsById(book.getId())).thenReturn(false);
        boolean result = service.update(book);
        assertFalse(result);
    }

    @Test
    public void DELETE(){
        when(repository.existsById(book.getId())).thenReturn(true);
        boolean result = service.save(book);
        assertTrue(result);
    }

    @Test
    public void DELETE_BUT_BOOK_DOESN_NOT_EXISTS(){
        when(repository.existsById(book.getId())).thenReturn(false);
        boolean result = service.update(book);
        assertFalse(result);
    }
}
