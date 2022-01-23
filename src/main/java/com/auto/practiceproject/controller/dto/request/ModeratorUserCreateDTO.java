package com.auto.practiceproject.controller.dto.request;

import com.auto.practiceproject.util.validation.UserExistWithEmail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModeratorUserCreateDTO {

    @Email(message = "email should be valid")
    @NotEmpty(message = "email should not be empty")
    @Size(max = 255, message = "email must be less than 255 characters")
    @UserExistWithEmail
    private String email;

    @NotEmpty(message = "name should not be empty")
    @Size(max = 45, message = "name must be less than 45 characters")
    private String name;

    @NotEmpty(message = "surname should not be empty")
    @Size(max = 45, message = "surname must be less than 45 characters")
    private String surname;

}
