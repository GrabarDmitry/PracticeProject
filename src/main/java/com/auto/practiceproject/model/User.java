package com.auto.practiceproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`user`")
@ToString(exclude = {"password"})
public class User extends AbstractEntity {

    @Column(unique = true)
    private String email;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String surname;

    @Column(length = 512)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")}
    )
    private Set<Role> roles;

}
