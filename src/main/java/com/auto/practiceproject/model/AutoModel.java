package com.auto.practiceproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto_model")
public class AutoModel extends AbstractEntity {

    @Column(length = 45)
    private String title;

    @ManyToOne
    @JoinColumn(name = "autoBrandId")
    private AutoBrand autoBrand;

}
