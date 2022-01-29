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
@Table(name = "auto")
public class Auto extends AbstractEntity {

    @Column
    private Integer mileage;

    @Column
    private Integer engineCapacity;

    @Column(length = 45)
    private String VIM;

    @ManyToOne
    @JoinColumn(name = "autoModelId")
    private AutoModel autoModel;

    @ManyToOne
    @JoinColumn(name = "autoEngineId")
    private AutoEngine autoEngine;

    @ManyToOne
    @JoinColumn(name = "autoTransmissionId")
    private AutoTransmission autoTransmission;

}
