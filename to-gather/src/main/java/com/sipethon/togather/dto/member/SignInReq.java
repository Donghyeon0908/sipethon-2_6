package com.sipethon.togather.dto.member;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInReq {
    private String username;
    private String password;
    private String fcm;
}
