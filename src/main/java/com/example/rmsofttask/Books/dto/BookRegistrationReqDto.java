package com.example.rmsofttask.Books.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRegistrationReqDto {
    @NotBlank(message = "책 제목이 비어있습니다.")
    private String title;
    @NotBlank(message = "작가가 비어있습니다.")
    private String author;
}
