package com.sipethon.togather.dto.member;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetMemberRes {
    private String username;
    private String nickname;
}
