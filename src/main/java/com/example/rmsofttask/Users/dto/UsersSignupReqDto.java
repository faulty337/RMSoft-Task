package com.example.rmsofttask.Users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsersSignupReqDto {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
