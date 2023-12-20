package com.example.rmsofttask.LoanRecords;

import com.example.rmsofttask.Books.BookStatus;
import com.example.rmsofttask.Books.Books;
import com.example.rmsofttask.Users.Users;
import org.apache.catalina.User;
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
    public void testSaveAndFindLoanRecord() {
        // LoanRecord 엔티티 인스턴스 생성 및 초기화
        Books book = new Books("어린왕자", "생텍쥐페리", BookStatus.AVAILABLE);
        Users user = new Users("faulty337", "김범준", "faulty337@gmail.com", "010-0000-0000", "qwer1234");
        LoanRecords loanRecord = new LoanRecords(book, user);
        // 필드 설정 (예: loanRecord.set...(); )

        // 저장
        LoanRecords savedRecord = loanRecordsRepository.save(loanRecord);

        // 조회
        LoanRecords foundRecord = loanRecordsRepository.findById(savedRecord.getId()).orElse(null);

        // 검증
        assertNotNull(foundRecord);
        assertNotNull(foundRecord.getBooks());
        assertEquals(savedRecord.getBooks(), foundRecord.getBooks());
        // 기타 필요한 검증 수행
    }

}
