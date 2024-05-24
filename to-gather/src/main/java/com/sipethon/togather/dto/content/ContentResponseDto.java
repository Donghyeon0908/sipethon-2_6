package com.sipethon.togather.dto.content;

import com.sipethon.togather.domain.Status;
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
public class ContentResponseDto {

    private Long id;
    private String contents;
    private LocalDateTime date;
    private Integer target_member;
    private Integer current_member;
    private Float lat;
    private Float lng;
    private Status status;
}