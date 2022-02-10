package com.auto.practiceproject.controller.dto.request;

import com.auto.practiceproject.util.validation.*;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@CustomsDutyPriceValidator
public class AnnouncementRequestDTO {

  @NotNull(message = "Auto model id should not be null")
  @Positive(message = "Auto model id should be positive")
  @AutoModelDoesNotExist
  private Long autoModelId;

  @NotEmpty(message = "Description should not be empty")
  @Size(max = 1024, message = "Description must be less than 1024 characters")
  private String description;

  @NotEmpty(message = "Phone number should not be empty")
  @Size(max = 12, min = 12, message = "Phone number must be 12 characters")
  private String phoneNumber;

  @NotNull(message = "Price should not be null")
  @Positive(message = "Price should be positive")
  private Double price;

  @NotNull(message = "Is exchange should not be null")
  private Boolean isExchange;

  @NotNull(message = "autoTransmissionId should not be null")
  @Positive(message = "autoTransmissionId id should be positive")
  @AutoTransmissionDoesNotExist
  private Long autoTransmissionId;

  @NotNull(message = "autoEngineId should not be null")
  @Positive(message = "autoEngineId id should be positive")
  @AutoEngineDoesNotExist
  private Long autoEngineId;

  @NotNull(message = "Mileage should not be null")
  @Positive(message = "Mileage should be positive")
  private Integer mileage;

  @NotNull(message = "Engine capacity should not be null")
  @Positive(message = "Engine capacity be positive")
  @Min(value = 1800, message = "Engine capacity must be more than 1800")
  @Max(value = 2200, message = "Engine capacity must be less than 2200")
  private Integer engineCapacity;

  @NotEmpty(message = "VIN should not be empty")
  @Size(max = 17, min = 17, message = "VIN must be 17 characters")
  private String vin;

  @NotNull(message = "regionId should not be null")
  @Positive(message = "regionId id should be positive")
  @RegionDoesNotExist
  private Long regionId;

  @NotNull(message = "Customs duty should not be null")
  @Min(value = 0, message = "Customs duty must be equal to or greater than 0")
  private Double customsDuty;
}
