package com.sipethon.togather.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetMemberContentRes {
    private String id;
    private String contents;
    private LocalDateTime date;
    private Float lat;
    private Float lng;
    private String targetMember;
    private String currentMember;
    private String status;
    private String contact;
}
