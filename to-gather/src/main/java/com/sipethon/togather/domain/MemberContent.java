package com.sipethon.togather.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class MemberContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_content_id")
    private Long id;

//    @Column(name = "member_id")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;

//    @Column(name = "content_id")
    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content contentId;

    @Column
    private Role role;
}
