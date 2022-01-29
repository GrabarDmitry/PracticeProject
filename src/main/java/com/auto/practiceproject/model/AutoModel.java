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
@Table(name = "auto_model")
public class AutoModel extends AbstractEntity {

    @Column(length = 45)
    private String title;

    @ManyToOne
    @JoinColumn(name = "autoBrandId")
    private AutoBrand autoBrand;

    @ManyToOne
    @JoinColumn(name = "autoReleasedYearId")
    private AutoReleasedYear autoReleasedYear;

}
