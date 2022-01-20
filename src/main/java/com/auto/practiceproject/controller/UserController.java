package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.converter.UserDTOConverter;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDTOConverter userDTOConverter;
    private final AnnouncementDTOConverter announcementDTOConverter;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.trace("Controller method called to get User current user with id: {}", userDetails.getUser().getId());
        return new ResponseEntity<>(
                userDTOConverter.toDTO(
                        userDetails.getUser()),
                HttpStatus.OK);
    }

    @GetMapping("/announcement")
    public ResponseEntity<List<AnnouncementResponseDTO>> getUserAnnouncements(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.trace("Controller method called to get current user announcements, current user id: {}", userDetails.getUser().getId());
        return new ResponseEntity<>(
                userDetails.getUser().getAnnouncements().stream()
                        .map(announcementDTOConverter::toDTO)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

}
