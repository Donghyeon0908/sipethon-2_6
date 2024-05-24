package com.sipethon.togather.dto.member;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpReq {
    private String username;
    private String password;
    private String nickname;
    private String fcm;
}
