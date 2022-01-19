package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AnnouncementResponseDTO {
    private Long id;
    private String title;
    private Double price;
    private String brand;
    private String model;
    private Date releasedYear;
    private String transmission;
    private String engine;
    private Integer engineCapacity;
}
