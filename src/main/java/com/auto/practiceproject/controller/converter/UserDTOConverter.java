package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.UserCreateDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDTOConverter {

    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserResponseDTO toDTO(User user) {
        log.trace("Convert User with id: {}, to UserResponseDTO", user.getId());
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getBalance()
        );
    }

    public User toEntityCreate(UserCreateDTO createDTO) {
        log.trace("Convert UserCreateDTO with email: {}, to User", createDTO.getEmail());
        return new User(
                createDTO.getEmail(),
                createDTO.getName(),
                createDTO.getSurname(),
                passwordEncoder.encode(createDTO.getPassword()),
                0D,
                Set.of(
                        //TODO
                        roleService.findRoleByTitle("USER").get()
                ),
                new Bookmark()
        );
    }

}
