package com.example.rmsofttask.Books;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDto {
    @NotNull(message = "책ID가 비어있습니다.")
    private Long bookId;

    @NotBlank(message = "책 제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "작가가 비어있습니다.")
    private String author;
}
