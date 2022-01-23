package com.auto.practiceproject.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnnouncementModerationChangeDTO {

    @NotNull(message = "Is moderation should not be null")
    private Boolean isModeration;

}
