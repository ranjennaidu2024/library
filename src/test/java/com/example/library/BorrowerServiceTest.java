package com.example.library;

import com.example.library.model.Borrower;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BorrowerServiceTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerService borrowerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterBorrower() {
        Borrower borrower = new Borrower("Ranjen Naidu", "ranjen@example.com");
        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);

        Borrower savedBorrower = borrowerService.registerBorrower(borrower);

        assertThat(savedBorrower).isNotNull();
        assertThat(savedBorrower.getName()).isEqualTo("Ranjen Naidu");
        assertThat(savedBorrower.getEmail()).isEqualTo("ranjen@example.com");
    }

    @Test
    void testGetBorrowerById() {
        Borrower borrower = new Borrower(1L, "Ranjen Naidu", "ranjen@example.com");
        when(borrowerRepository.findById(1L)).thenReturn(Optional.of(borrower));

        Optional<Borrower> foundBorrower = borrowerService.getBorrowerById(1L);

        assertThat(foundBorrower).isPresent();
        assertThat(foundBorrower.get().getId()).isEqualTo(1L);
    }
}
