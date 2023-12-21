package com.example.rmsofttask.Users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIdReqDto {
    @NotBlank(message = "유저ID가 비어있습니다.")
    private String userId;
}
