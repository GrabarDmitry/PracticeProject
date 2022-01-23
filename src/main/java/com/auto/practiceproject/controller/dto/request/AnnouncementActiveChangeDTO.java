package com.auto.practiceproject.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnnouncementActiveChangeDTO {

    @NotNull(message = "Is exchange Id should not be null")
    private Boolean isExchange;

}
