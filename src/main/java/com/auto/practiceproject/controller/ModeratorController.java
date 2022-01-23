package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.request.AnnouncementModerationChangeDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/moderator/announcement")
@RequiredArgsConstructor
public class ModeratorController {

    private final AnnouncementService announcementService;
    private final AnnouncementDTOConverter announcementDTOConverter;

    @PreAuthorize("hasPermission(null ,'MODERATOR')")
    @GetMapping
    public ResponseEntity<Page<AnnouncementResponseDTO>> getAllNotModerationAnnouncement(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "filter", required = false) String filter) {
        log.trace("Controller method called to view all not moderation Announcement with params: {}", pageable);
        return new ResponseEntity<>(
                announcementService.findAllAnnouncementByModeration(true, pageable, filter)
                        .map(announcementDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(null ,'MODERATOR')")
    @PatchMapping("/{id}")
    public ResponseEntity<FullAnnouncementResponseDTO> changeAnnouncementModeration(
            @PathVariable("id") Announcement announcement,
            @RequestBody @Valid AnnouncementModerationChangeDTO moderationChangeDTO
    ) {
        log.trace("Controller method called to update isModeration announcement field with id: {}", announcement.getId());
        return new ResponseEntity<>(
                announcementDTOConverter.toFullDTO(
                        announcementService.updateAnnouncement(announcementDTOConverter
                                .toDTOWithEditedIsModeration(announcement, moderationChangeDTO)))
                , HttpStatus.OK);
    }

}