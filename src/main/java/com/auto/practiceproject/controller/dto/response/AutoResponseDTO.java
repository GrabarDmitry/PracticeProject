package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutoResponseDTO {
    private String announcementId;
    private String brand;
    private String model;
    private Date releasedYear;
    private String transmission;
    private String engine;
    private Integer mileage;
    private Integer engineCapacity;
    private String VIM;
}
