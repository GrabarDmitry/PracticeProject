package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.converter.UserDTOConverter;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDTOConverter userDTOConverter;
    private final AnnouncementDTOConverter announcementDTOConverter;
    private final AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.trace("Controller method called to get User current user with id: {}", userDetails.getUser().getId());
        return new ResponseEntity<>(
                userDTOConverter.toDTO(
                        userDetails.getUser()),
                HttpStatus.OK);
    }

    @GetMapping("/announcement")
    public ResponseEntity<Page<AnnouncementResponseDTO>> getUserAnnouncements(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(name = "filter", required = false) String filter
    ) {
        log.trace("Controller method called to get current user announcements, current user id: {}", userDetails.getUser().getId());
        return new ResponseEntity<>(
                announcementService.findAnnouncementByUserId(
                        userDetails.getUser().getId().toString(),
                                pageable, filter)
                        .map(announcementDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(#announcement,'ALL')")
    @PostMapping("/announcement/{id}/up")
    public ResponseEntity<FullAnnouncementResponseDTO> announcementRatingUp(@PathVariable("id") Announcement announcement) {
        log.trace("Controller method called to update Announcement rating with id: {}", announcement.getId());
        return new ResponseEntity<>(
                announcementDTOConverter.toFullDTO(
                        announcementService.announcementRatingUp(announcement))
                , HttpStatus.OK);
    }

}
