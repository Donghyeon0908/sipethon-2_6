package com.sipethon.togather.dto.member;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMemberContentsRes {
    private Long id;
    private String contents;
    private Float lat;
    private Float lng;
    private LocalDateTime date;
    private Integer targetMember;
    private Integer currentMember;
    private String status;
    private String contact;
}
