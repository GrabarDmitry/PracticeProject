package com.auto.practiceproject.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoResponseDTO {
    private Long announcementId;
    private Long autoModelId;
    private Long autoTransmissionId;
    private Long autoEngineId;
    private Integer mileage;
    private Integer engineCapacity;
    private String VIN;
}
