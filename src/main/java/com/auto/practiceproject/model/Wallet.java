package com.auto.practiceproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private Double balance;

}
