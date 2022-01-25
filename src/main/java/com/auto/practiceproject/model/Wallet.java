package com.auto.practiceproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
