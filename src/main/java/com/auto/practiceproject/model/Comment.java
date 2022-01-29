package com.auto.practiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends AbstractEntity {

    @Column(length = 1024)
    private String text;

    @Column
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "announcementId")
    private Announcement announcement;

}
