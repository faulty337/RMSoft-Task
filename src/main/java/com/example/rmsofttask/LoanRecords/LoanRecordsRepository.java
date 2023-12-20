package com.example.rmsofttask.LoanRecords;


import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRecordsRepository extends JpaRepository<LoanRecords, Long> {
    List<LoanRecords> findByBooks(Books book);

    List<LoanRecords> findAllByUsersAndReturnedDateIsNull(Users user);

    Optional<LoanRecords> findByBooksAndReturnedDateIsNull(Books book);
}
