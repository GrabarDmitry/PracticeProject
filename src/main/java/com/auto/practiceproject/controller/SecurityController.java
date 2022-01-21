package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.UserDTOConverter;
import com.auto.practiceproject.controller.dto.request.AuthenticationRequestDTO;
import com.auto.practiceproject.controller.dto.request.UserCreateDTO;
import com.auto.practiceproject.controller.dto.response.AuthenticationResponseDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class SecurityController {

    private final SecurityService securityService;
    private final UserDTOConverter userDTOConverter;

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponseDTO> authentication(@RequestBody @Valid AuthenticationRequestDTO dto) {
        log.trace("Controller method called to authentication user with email: {}", dto.getEmail());
        return ResponseEntity.ok(
                new AuthenticationResponseDTO(securityService.
                        authentication(dto.getEmail(), dto.getPassword()))
        );
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponseDTO> registration(@RequestBody @Valid UserCreateDTO dto) {
        log.trace("Controller method called to registration user  with email: {}", dto.getEmail());
        return new ResponseEntity<>(
                userDTOConverter.toDTO(securityService.
                        registration(userDTOConverter.toEntityCreate(dto))),
                HttpStatus.CREATED
        );
    }


}