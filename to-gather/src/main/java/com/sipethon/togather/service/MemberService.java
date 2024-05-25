package com.sipethon.togather.service;

import com.sipethon.togather.common.exception.CustomException;
import com.sipethon.togather.common.exception.ErrorCode;
import com.sipethon.togather.domain.*;
import com.sipethon.togather.dto.member.*;
import com.sipethon.togather.repository.ContentRepository;
import com.sipethon.togather.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ContentRepository contentRepository;

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

    public List<GetMemberContentsRes> getMemberContent(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        List<GetMemberContentsRes> contentList = new ArrayList<>();

        for (MemberContent memberContent : member.getMemberContentList()) {

            Content content = contentRepository.findById(memberContent.getContentId().getId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CONTENT));

            if (!content.getTargetMember().equals(content.getCurrentMember()) && content.getDate().isBefore(LocalDateTime.now())) {
                continue;
            }

            System.out.println("memberContent.getRole() = " + memberContent.getRole());



            if ((content.getStatus().equals(Status.INPROGRESS) && memberContent.getRole().equals(Role.HOST)) || content.getStatus().equals(Status.SUCCESS)) {
                contentList.add(GetMemberContentsRes.builder()
                        .id(content.getId())
                        .contents(content.getContents())
                        .lat(content.getLat())
                        .lng(content.getLng())
                        .date(content.getDate())
                        .targetMember(content.getTargetMember())
                        .currentMember(content.getCurrentMember())
                        .contact(content.getContact())
                        .status(content.getStatus().name())
                        .build());
            } else if (content.getStatus().equals(Status.INPROGRESS)) {
                contentList.add(GetMemberContentsRes.builder()
                        .id(content.getId())
                        .contents(content.getContents())
                        .lat(content.getLat())
                        .lng(content.getLng())
                        .date(content.getDate())
                        .targetMember(content.getTargetMember())
                        .currentMember(content.getCurrentMember())
                        .status(content.getStatus().name())
                        .build());
            }
        }

        return contentList;
    }
}
