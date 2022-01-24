package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementResponseDTO {
    private Long id;
    private String title;
    private Double price;
    private String brand;
    private String model;
    private LocalDate releasedYear;
    private String transmission;
    private String engine;
    private Integer engineCapacity;
}
