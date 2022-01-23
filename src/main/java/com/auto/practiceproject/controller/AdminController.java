package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.UserDTOConverter;
import com.auto.practiceproject.controller.dto.request.ModeratorUserCreateDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SecurityService securityService;
    private final UserDTOConverter userDTOConverter;

    @PreAuthorize("hasPermission(#createDTO,'ADMIN')")
    @PostMapping("/moderatorUser")
    public ResponseEntity<UserResponseDTO> createModeratorUser(@RequestBody @Valid ModeratorUserCreateDTO createDTO) {
        log.trace("Controller method called to create moderator user with email: {}"
                , createDTO.getEmail());
        return new ResponseEntity<>(
                userDTOConverter.toDTO(
                        securityService.createModeratorUser(
                                userDTOConverter.toEntityCreate(createDTO)
                        )
                )
                , HttpStatus.CREATED);
    }

}
