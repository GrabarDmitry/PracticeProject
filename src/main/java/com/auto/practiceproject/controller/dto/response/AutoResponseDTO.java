package com.auto.practiceproject.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutoResponseDTO {
    private Long announcementId;
    private Long autoModelId;
    private Long autoTransmissionId;
    private Long autoEngineId;
    private Integer mileage;
    private Integer engineCapacity;
    private String VIM;
}
