package com.auto.practiceproject.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
