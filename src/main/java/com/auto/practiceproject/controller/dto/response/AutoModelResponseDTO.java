package com.auto.practiceproject.controller.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AutoModelResponseDTO {
    private Long id;
    private String title;
    private Long autoBrandId;
    private Long autoReleasedYearId;
}
