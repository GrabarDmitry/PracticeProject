package com.auto.practiceproject.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnnouncementActiveChangeDTO {

  @NotNull(message = "Is exchange should not be null")
  private Boolean isActive;
}
