package com.auto.practiceproject.controller.dto.request;

import com.auto.practiceproject.util.validation.CustomsDutyPriceValidator;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CustomsDutyPriceValidator
public class AutoRequestDTO {
    private Integer mileage;
    private Integer engineCapacity;
    private String VIM;
    private Long autoModelId;
    private Long autoEngineId;
    private Long autoTransmissionId;
}
