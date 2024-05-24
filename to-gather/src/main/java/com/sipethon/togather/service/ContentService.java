package com.sipethon.togather.service;

import static com.sipethon.togather.domain.Role.HOST;
import static com.sipethon.togather.domain.Role.PARTICIPANT;
import static com.sipethon.togather.domain.Status.SUCCESS;

import com.sipethon.togather.common.exception.CustomException;
import com.sipethon.togather.common.exception.ErrorCode;
import com.sipethon.togather.domain.Content;
import com.sipethon.togather.domain.Member;
import com.sipethon.togather.domain.MemberContent;
import com.sipethon.togather.domain.Status;
import com.sipethon.togather.dto.content.ContentMapDto;
import com.sipethon.togather.dto.content.ContentRequestDto;
import com.sipethon.togather.dto.content.ContentResponseDto;
import com.sipethon.togather.dto.content.JoinRequestDto;
import com.sipethon.togather.repository.ContentRepository;
import com.sipethon.togather.repository.MemberContentRepository;
import com.sipethon.togather.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final MemberContentRepository memberContentRepository;
    private final MemberRepository memberRepository;

    public void createContent(ContentRequestDto contentRequestDto) {
        Optional<Member> memberOpt = memberRepository.findById(contentRequestDto.getId());
        if (memberOpt.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        Content content = Content.builder()
            .contents(contentRequestDto.getContents())
            .date(contentRequestDto.getDate())
            .lat(contentRequestDto.getLat())
            .lng(contentRequestDto.getLng())
            .targetMember(contentRequestDto.getTargetMember())
            .contact(contentRequestDto.getContact())
            .currentMember(1)
            .status(Status.INPROGRESS)
            .build();

        Long contentId = contentRepository.save(content).getId();

        MemberContent memberContent = MemberContent.builder()
            .memberId(contentRequestDto.getId())
            .contentId(contentId)
            .role(HOST)
            .build();
        memberContentRepository.save(memberContent);
    }

    public ContentResponseDto getContent(Long id) {
        Content content = contentRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CONTENT));
        return new ContentResponseDto(content.getId(), content.getContents(), content.getDate(),
            content.getTargetMember(), content.getCurrentMember(), content.getLat(),
            content.getLng(), content.getStatus().name());
    }

    public List<ContentMapDto> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        return contents.stream()
            .filter(content -> content.getDate().isAfter(now))
            .map(content -> new ContentMapDto(content.getId(), content.getLat(), content.getLng(),
                content.getStatus().name()))
            .collect(Collectors.toList());
    }

    public void joinContent(Long contentId, JoinRequestDto joinRequestDto) {
        Content content = contentRepository.findById(contentId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CONTENT));

        MemberContent memberContent = MemberContent.builder()
            .memberId(joinRequestDto.getMemberId())
            .contentId(content.getId())
            .role(PARTICIPANT)
            .build();

        memberContentRepository.save(memberContent);

        content.incrementCurrentMember();

        if (content.getCurrentMember().equals(content.getTargetMember())) {
            content.setStatus(SUCCESS);
        }
        contentRepository.save(content);


    }

}