package com.auto.practiceproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auto_released_year")
public class AutoReleasedYear extends AbstractEntity {

    @Column
    private Date releasedYear;

}
