package com.example.rmsofttask.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    private String userId;

    private String name;

    private String email;

    private String phoneNumber;

    private String password;
}
