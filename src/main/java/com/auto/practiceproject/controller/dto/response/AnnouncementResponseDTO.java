package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String phoneNumber;
    private Double price;
    private Boolean isActive;
    private Boolean isExchange;
    private Double customsDuty;
    private Long userId;
    private Long regionId;
    private Long autoId;
}
