package com.sipethon.togather.dto.member;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRes {
    private Long id;
    private String nickname;
}
