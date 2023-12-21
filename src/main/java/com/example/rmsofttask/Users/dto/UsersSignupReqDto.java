package com.example.rmsofttask.Users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UsersSignupReqDto {
    @NotBlank(message = "유저ID가 비어있습니다.")
    private String userId;

    @NotBlank(message = "이름이 비어있습니다.")
    private String name;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotNull(message = "이메일이 비어있습니다.")
    private String email;

    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{3}|\\d{4})-?(\\d{4})$", message = "올바른 전화번호 형식이 아닙니다. ex)010-0000-0000")
    private String phoneNumber;

    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,16}", message = "비밀번호는 8~16자리의 영어 소문자, 숫자를 포함해야 합니다.")
    private String password;
}
