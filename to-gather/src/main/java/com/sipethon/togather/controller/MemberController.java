package com.sipethon.togather.controller;

import com.sipethon.togather.common.ApiResponse;
import com.sipethon.togather.dto.member.*;
import com.sipethon.togather.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<SignUpRes> signUp(@RequestBody SignUpReq signUpReq) {
        return ApiResponse.success(memberService.signUp(signUpReq));
    }

    @PostMapping("/sign-in")
    public ApiResponse<SignInRes> signIn(@RequestBody SignInReq signInReq) {
        return ApiResponse.success(memberService.signIn(signInReq));
    }

    @GetMapping("/members/{id}")
    public ApiResponse<GetMemberRes> getMember(@PathVariable("id") Long id) {
        return ApiResponse.success(memberService.getMember(id));
    }

    @GetMapping("/members/{id}/content")
    public ApiResponse<GetMemberContentRes> getMemberContent(@PathVariable("id") Long id) {
        return ApiResponse.success(memberService.getMemberContent(id));
    }
}
