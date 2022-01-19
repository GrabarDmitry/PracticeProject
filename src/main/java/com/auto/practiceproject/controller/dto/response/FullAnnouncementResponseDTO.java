package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FullAnnouncementResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String phoneNumber;
    private Double price;
    private Boolean isActive;
    private Boolean isExchange;
    private Double customsDuty;
    private String brand;
    private String model;
    private Date releasedYear;
    private String transmission;
    private String engine;
    private Integer mileage;
    private Integer engineCapacity;
    private String VIM;
    private String seller;
    private String region;
}
