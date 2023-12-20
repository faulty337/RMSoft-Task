package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Users.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LoanRecordsRepositoryTest {

    @Autowired
    private LoanRecordsRepository loanRecordsRepository;

    @Test
    @DisplayName("대출이력 저장 테스트")
    public void testSaveAndFindLoanRecord() {
        Books book = new Books("어린왕자", "생텍쥐페리", BookStatus.AVAILABLE);
        Users user = new Users("faulty337", "김범준", "faulty337@gmail.com", "010-0000-0000", "qwer1234");
        LoanRecords loanRecord = new LoanRecords(book, user);

        LoanRecords savedRecord = loanRecordsRepository.save(loanRecord);

        LoanRecords foundRecord = loanRecordsRepository.findById(savedRecord.getId()).orElse(null);

        assertNotNull(foundRecord);
        assertNotNull(foundRecord.getBooks());
        assertEquals(savedRecord.getBooks(), foundRecord.getBooks());
    }

}
