package com.auto.practiceproject.controller.dto.request;

import com.auto.practiceproject.util.validation.BrandDoesNotExist;
import com.auto.practiceproject.util.validation.ModelDoesNotExist;
import com.auto.practiceproject.util.validation.ModelDoesNotExistWithBrandAndYear;
import com.auto.practiceproject.util.validation.ReleasedYeasDoesNorExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@ToString
@ModelDoesNotExistWithBrandAndYear(field = "model", message = "Auto model with this brand or released year doesn't exist")
public class AnnouncementCreateDTO {

    @NotEmpty(message = "Brand should not be empty")
    @Size(max = 45, message = "Brand must be less than 45 characters")
    @BrandDoesNotExist
    private String brand;

    @NotEmpty(message = "Model should not be empty")
    @Size(max = 45, message = "Model must be less than 45 characters")
    @ModelDoesNotExist
    private String model;

    @NotNull(message = "Released year should not be null")
    @ReleasedYeasDoesNorExist
    private LocalDate releasedYear;

    @NotEmpty(message = "Description should not be empty")
    @Size(max = 1024, message = "Description must be less than 1024 characters")
    private String description;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(max = 13, min = 13, message = "Phone number must be 13 characters")
    private String phoneNumber;

    @NotNull(message = "Price should not be null")
    @Positive(message = "Price should be positive")
    private Double price;

    @NotNull(message = "Is exchange should not be null")
    private Boolean isExchange;

    @NotEmpty(message = "Transmission should not be empty")
    @Size(max = 45, message = "Transmission must be less than 45 characters")
    private String transmission;

    @NotEmpty(message = "Engine should not be empty")
    @Size(max = 45, message = "Engine must be less than 45 characters")
    private String engine;

    @NotNull(message = "Mileage should not be null")
    @Positive(message = "Mileage should be positive")
    private Integer mileage;

    @NotNull(message = "Engine capacity should not be null")
    @Positive(message = "Engine capacity be positive")
    private Integer engineCapacity;

    @NotEmpty(message = "VIM should not be empty")
    @Size(max = 17, min = 17, message = "VIM must be 17 characters")
    private String VIM;

    @NotEmpty(message = "Region should not be empty")
    @Size(max = 45, message = "Region must be less than 45 characters")
    private String region;

    @NotNull(message = "Customs duty capacity should not be null")
    @Positive(message = "Customs duty capacity be positive")
    private Double customsDuty;

}
