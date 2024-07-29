package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterBook() {
        Borrower borrower = new Borrower(1L, "Ranjen Naidu", "ranjen@example.com");
        Book book = new Book("1234567890", "Book Title 1", "Author 1", borrower);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = bookService.registerBook(book);

        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getIsbn()).isEqualTo("1234567890");
        assertThat(savedBook.getTitle()).isEqualTo("Book Title 1");
    }

    @Test
    void testGetBookById() {
        Borrower borrower = new Borrower(1L, "Ranjen Naidu", "ranjen@example.com");
        Book book = new Book(1L, "1234567890", "Book Title 1", "Author 1", borrower);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.getBookById(1L);

        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getId()).isEqualTo(1L);
    }
}
