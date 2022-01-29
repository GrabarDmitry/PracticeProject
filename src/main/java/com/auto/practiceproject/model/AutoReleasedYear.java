package com.auto.practiceproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto_released_year")
public class AutoReleasedYear extends AbstractEntity {

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releasedYear;

}
