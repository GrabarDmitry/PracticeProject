package com.auto.practiceproject.controller.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoneyTransferDTO {

    @NotEmpty(message = "Card number should not be null")
    @CreditCardNumber
    private String cartNumber;

    @NotNull(message = "Card expire date should not be null")
    private LocalDate cardExpiryDate;

    @NotNull(message = "Amount should not be null")
    @Positive(message = "Amount should be positive")
    private Double amount;
}
