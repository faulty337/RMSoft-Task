package com.example.rmsofttask.LoanRecords;


import com.example.rmsofttask.Books.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRecordsRepository extends JpaRepository<LoanRecords, Long> {
    List<LoanRecords> findByBooks(Books book);
}
