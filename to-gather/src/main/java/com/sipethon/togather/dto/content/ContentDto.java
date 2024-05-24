package com.sipethon.togather.dto.content;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentDto {

    private Long id;
    private String contents;
    private LocalDateTime date;
    private Integer targetMember;
    private Float lat;
    private Float lng;
    private String contact;
}
