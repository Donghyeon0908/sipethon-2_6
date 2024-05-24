package com.sipethon.togather.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;
    @Column
    private String contents;
    @Column
    private LocalDateTime date;
    @Column
    private Integer targetMember;
    @Column
    private Integer currentMember;
    @Column
    private Float lat;
    @Column
    private Float lng;
    @Column
    private String contact;
    @Column
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_content_id")
    private List<MemberContent> memberContentList;

    public void incrementCurrentMember() {
        if (this.currentMember == null) {
            this.currentMember = 0;
        }
        this.currentMember++;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
