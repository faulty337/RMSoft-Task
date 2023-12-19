package com.example.rmsofttask.Users;


import com.example.rmsofttask.common.response.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UsersService usersService;

    @MockBean
    private UsersRepository usersRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    public void testSignupSuccess() {
        when(usersRepository.existsById(anyString())).thenReturn(false);
        when(usersRepository.existsByEmail(anyString())).thenReturn(false);
        when(usersRepository.existsByPhoneNumber(anyString())).thenReturn(false);

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        usersService.signup("testUserId", "testName", "testEmail", "testPhone", "testPassword");

        verify(usersRepository, times(1)).save(any(Users.class));
    }


    @Test
    @DisplayName("유저ID 중복 테스트")
    public void testSignupWithUserIDDuplicateId() {
        when(usersRepository.existsById(anyString())).thenReturn(true);

        assertThrows(CustomException.class, () -> {
            usersService.signup("testUserId", "testName", "testEmail", "testPhone", "testPassword");
        });

        verify(usersRepository, never()).save(any(Users.class));
    }

    @Test
    @DisplayName("이메일 중복 테스트")
    public void testSignupWithEmailDuplicateId() {
        when(usersRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(CustomException.class, () -> {
            usersService.signup("testUserId", "testName", "testEmail", "testPhone", "testPassword");
        });

        verify(usersRepository, never()).save(any(Users.class));
    }

    @Test
    @DisplayName("전화번호 중복 테스트")
    public void testSignupWithPhoneNumberDuplicateId() {
        when(usersRepository.existsByPhoneNumber(anyString())).thenReturn(true);

        assertThrows(CustomException.class, () -> {
            usersService.signup("testUserId", "testName", "testEmail", "testPhone", "testPassword");
        });

        verify(usersRepository, never()).save(any(Users.class));
    }

}