package com.auto.practiceproject.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoResponseDTO {
  private Long id;
  private Integer mileage;
  private Integer engineCapacity;
  private String VIN;
  private Long autoModelId;
  private Long autoTransmissionId;
  private Long autoEngineId;
}
