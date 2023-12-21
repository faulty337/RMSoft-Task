package com.example.rmsofttask.Users;

import com.example.rmsofttask.Users.dto.UsersSignupReqDto;
import com.example.rmsofttask.common.response.CustomException;
import com.example.rmsofttask.common.response.ErrorCode;
import com.example.rmsofttask.common.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;


    @PostMapping
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody UsersSignupReqDto reqDto){
        usersService.signup(
                reqDto.getUserId(),
                reqDto.getName(),
                reqDto.getEmail(),
                reqDto.getPhoneNumber(),
                reqDto.getPassword()
        );

        return new ResponseEntity<>(new ResponseDto("회원가입에 성공했습니다.", 200), HttpStatus.OK);
    }
}
