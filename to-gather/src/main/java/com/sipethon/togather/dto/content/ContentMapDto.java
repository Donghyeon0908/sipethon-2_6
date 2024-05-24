package com.sipethon.togather.dto.content;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContentMapDto {

    private Long id;
    private Float lat;
    private Float lng;
    private String status;
}