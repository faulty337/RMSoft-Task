package com.example.rmsofttask.Users;

import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    @Transactional
    public void signup(String userId, String name, String email, String phoneNumber, String password) {
        if(usersRepository.existsById(userId)){
           throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }

        if(usersRepository.existsByEmail(userId)){
            throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }

        if(usersRepository.existsByPhoneNumber(userId)){
            throw new CustomException(ErrorCode.DUPLICATE_USERID);
        }

        String encodedPassword = passwordEncoder.encode(password);

        usersRepository.save(new Users(userId, name, email, phoneNumber, encodedPassword));

    }



}
