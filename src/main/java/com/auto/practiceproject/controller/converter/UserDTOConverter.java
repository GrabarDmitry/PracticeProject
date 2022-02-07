package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.ModeratorUserCreateDTO;
import com.auto.practiceproject.controller.dto.request.UserCreateDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.service.RoleService;
import com.auto.practiceproject.service.WalletService;
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
    private final WalletService walletService;

    public UserResponseDTO toDTO(User user) {
        log.trace("Convert User: {}, to UserResponseDTO", user);
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                walletService.findWalletByUser(user)
                        .get().getId()
        );
    }

    public User toEntityCreate(UserCreateDTO createDTO) {
        log.trace("Convert UserCreateDTO: {}, to User", createDTO);
        return new User(
                createDTO.getEmail(),
                createDTO.getName(),
                createDTO.getSurname(),
                passwordEncoder.encode(createDTO.getPassword()),
                Set.of(
                        roleService.findRoleByTitle("USER").
                                orElse(null)
                )
        );
    }

    public User toEntityCreate(ModeratorUserCreateDTO createDTO) {
        log.trace("Convert ModeratorUserCreateDTO: {}, to User", createDTO);
        return new User(
                createDTO.getEmail(),
                createDTO.getName(),
                createDTO.getSurname(),
                null,
                Set.of(
                        roleService.findRoleByTitle("MODERATOR").
                                orElse(null),
                        roleService.findRoleByTitle("USER").
                                orElse(null)
                )
        );
    }

}
