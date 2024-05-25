package com.sipethon.togather.dto.member;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRes {
    private Long id;
    private String nickname;
}
