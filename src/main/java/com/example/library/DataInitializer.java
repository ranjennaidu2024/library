package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insert Borrower Data and retrieve the generated IDs
        Borrower borrower1 = borrowerRepository.save(new Borrower(null, "Ranjen Naidu", "ranjen@example.com"));
        Borrower borrower2 = borrowerRepository.save(new Borrower(null, "Britney Spears", "britney@example.com"));

        // Insert Book Data with the borrower IDs
        Book book1 = new Book(null, "1234567890", "Book Title 1", "Author 1", borrower1);
        Book book2 = new Book(null, "0987654321", "Book Title 2", "Author 2", borrower2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
