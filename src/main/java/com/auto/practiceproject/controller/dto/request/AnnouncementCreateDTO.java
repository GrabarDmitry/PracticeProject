package com.auto.practiceproject.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class AnnouncementCreateDTO {
    private String brand;
    private String model;
    private LocalDate releasedYear;
    private String description;
    private String phoneNumber;
    private Double price;
    private String transmission;
    private String engine;
    private Integer mileage;
    private Integer engineCapacity;
    private String VIM;
    private String region;
    private Double customsDuty;
}
