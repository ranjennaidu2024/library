package com.example.library.service;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BorrowerRepository borrowerRepository;

    public Book registerBook(Book book) {
        List<Book> existingBooks = bookRepository.findByIsbn(book.getIsbn());
        if (!existingBooks.isEmpty()) {
            Book existingBook = existingBooks.get(0);
            if (!existingBook.getTitle().equals(book.getTitle()) || !existingBook.getAuthor().equals(book.getAuthor())) {
                throw new IllegalArgumentException("A book with the same ISBN must have the same title and author");
            }
        }
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book borrowBook(Long bookId, Long borrowerId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        if (book.getBorrower() != null) {
            throw new IllegalStateException("Book is already borrowed");
        }

        Borrower existingBorrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found"));

        book.setBorrower(existingBorrower);
        return bookRepository.save(book);
    }

    public Book returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setBorrower(null);
        return bookRepository.save(book);
    }
}
