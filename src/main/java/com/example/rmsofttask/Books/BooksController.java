package com.example.rmsofttask.Books;

import com.example.rmsofttask.Books.dto.BookRegistrationReqDto;
import com.example.rmsofttask.Books.dto.BookRegistrationResDto;
import com.example.rmsofttask.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BooksController {

    private final BooksService booksService;

    @PostMapping
    public ResponseEntity<ResponseDto<BookRegistrationResDto>> registerBook(@RequestBody BookRegistrationReqDto reqDto){
        BookRegistrationResDto resDto = booksService.registerBook(
                reqDto.getTitle(),
                reqDto.getAuthor()
        );

        return new ResponseEntity<>(new ResponseDto<>("등록에 성공했습니다.", 200, resDto), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ResponseDto<BookUpdateDto>> updateBook(@RequestBody BookUpdateDto reqDto){
        BookUpdateDto resDto = booksService.updateBook(
                reqDto.getBookId(),
                reqDto.getTitle(),
                reqDto.getAuthor()
        );

        return new ResponseEntity<>(new ResponseDto<>("책이 수정되었습니다.", 200, resDto), HttpStatus.OK);
    }

}
