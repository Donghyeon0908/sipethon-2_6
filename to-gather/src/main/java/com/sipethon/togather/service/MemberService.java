package com.sipethon.togather.service;

import com.sipethon.togather.common.exception.CustomException;
import com.sipethon.togather.common.exception.ErrorCode;
import com.sipethon.togather.domain.Member;
import com.sipethon.togather.dto.member.SignUpReq;
import com.sipethon.togather.dto.member.SignUpRes;
import com.sipethon.togather.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public SignUpRes signUp(SignUpReq signUpReq) {
        Optional<Member> member = memberRepository.findByUsername(signUpReq.getUsername());

        if (member.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

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
}
