package com.auto.practiceproject.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announcement")
public class Announcement extends AbstractEntity {

    @Column
    private String title;

    @Column(length = 1024)
    private String description;

    @Column(length = 13)
    private String phoneNumber;

    @Column
    private Double price;

    @Column
    private Boolean isActive;

    @Column
    private Boolean isModeration;

    @Column
    private Integer rating;

    @Column
    private Double ratingUpPrice;

    @Column
    private Boolean isExchange;

    @Column
    private Double customsDuty;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "autoId")
    private Auto auto;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

}
