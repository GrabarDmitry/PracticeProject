package com.auto.practiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookmark")
public class Bookmark extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "bookmark_announcements",
            joinColumns = {@JoinColumn(name = "bookmarkId")},
            inverseJoinColumns = {@JoinColumn(name = "announcementId")}
    )
    private List<Announcement> announcements;

}
