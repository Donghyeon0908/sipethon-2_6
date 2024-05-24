package com.sipethon.togather.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String fcm;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_content_id")
    private List<MemberContent> memberContentList;
}
