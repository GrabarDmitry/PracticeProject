package com.auto.practiceproject.controller.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentRequestDTO {

    @NotEmpty(message = "text should not be empty")
    @Size(max = 1024, message = "text must be less than 1024 characters")
    private String text;

}
