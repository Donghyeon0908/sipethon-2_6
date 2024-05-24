package com.sipethon.togather.service;

import com.sipethon.togather.common.exception.CustomException;
import com.sipethon.togather.common.exception.ErrorCode;
import com.sipethon.togather.domain.Member;
import com.sipethon.togather.dto.member.*;
import com.sipethon.togather.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public SignUpRes signUp(SignUpReq signUpReq) {

        memberRepository.findByUsername(signUpReq.getUsername()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_USERNAME));;

        Long id = memberRepository.save(
                Member.builder()
                        .username(signUpReq.getUsername())
                        .password(signUpReq.getPassword())
                        .nickname(signUpReq.getNickname())
                        .fcm(signUpReq.getFcm())
                        .build()).getId();

        return SignUpRes.builder()
                .id(id)
                .nickname(signUpReq.getNickname())
                .build();
    }

    public SignInRes signIn(SignInReq signInReq) {

        Optional<Member> member = memberRepository.findByUsername(signInReq.getUsername());

        if (member.isEmpty() || !member.get().getPassword().equals(signInReq.getPassword())) {
            throw new CustomException(ErrorCode.FAILED_SIGN_IN);
        }

        return SignInRes.builder()
                .id(member.get().getId())
                .nickname(member.get().getNickname())
                .build();
    }

    public GetMemberRes getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return GetMemberRes.builder()
                .username(member.getUsername())
                .nickname(member.getNickname())
                .build();
    }

    public GetMemberContentRes getMemberContent(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // TODO Content 정보 불러오기

        return null;
    }
}
