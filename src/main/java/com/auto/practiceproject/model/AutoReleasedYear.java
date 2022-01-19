package com.auto.practiceproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto_released_year")
public class AutoReleasedYear extends AbstractEntity {

    private Date releasedYear;

    @ManyToOne
    @JoinColumn(name = "autoModelId")
    private AutoModel autoModel;

}
