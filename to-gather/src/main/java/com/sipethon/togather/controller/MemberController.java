package com.sipethon.togather.controller;

import com.sipethon.togather.common.ApiResponse;
import com.sipethon.togather.dto.member.SignUpReq;
import com.sipethon.togather.dto.member.SignUpRes;
import com.sipethon.togather.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<SignUpRes> signUp(@RequestBody SignUpReq signUpReq) {
        return ApiResponse.success(memberService.signUp(signUpReq));
    }
}
