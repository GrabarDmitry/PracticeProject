package com.auto.practiceproject.controller.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoReleasedYearResponseDTO {
  private Long id;
  private LocalDate releasedYear;
}
