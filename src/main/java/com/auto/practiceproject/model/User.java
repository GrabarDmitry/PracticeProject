package com.auto.practiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(unique = true)
    private String email;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String surname;

    @Column(length = 512)
    private String password;

    @Column
    private Double balance;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private Set<Role> roles;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "bookmarkId")
    private Bookmark bookmark;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Announcement> announcements;

}
