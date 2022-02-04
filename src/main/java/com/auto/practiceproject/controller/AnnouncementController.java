package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.request.AnnouncementActiveChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.util.PageableSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@Api(tags = {"Announcements"})
@RestController
@Slf4j
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AnnouncementDTOConverter announcementDTOConverter;

    @PageableSwagger
    @ApiOperation(value = "View list of not on moderation announcements")
    @GetMapping
    public ResponseEntity<Page<AnnouncementResponseDTO>> getAllNotOnModerationAnnouncement(
            @ApiIgnore
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "rating",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "filter", required = false) String filter) {
        log.trace("Controller method called to view all Announcement with params: {}", pageable);
        return new ResponseEntity<>(
                announcementService.findAllAnnouncementByModeration(false, pageable, filter)
                        .map(announcementDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Get announcement by id")
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> getAnnouncementById(@PathVariable Long id) {
        log.trace("Controller method called to view Announcement with id: {}", id);
        return new ResponseEntity<>(announcementDTOConverter.
                toDTO(announcementService.findAnnouncementById(id)),
                HttpStatus.OK);
    }

    @ApiOperation(value = "Create announcement")
    @PostMapping
    public ResponseEntity<AnnouncementResponseDTO> createAnnouncement(@RequestBody @Valid AnnouncementRequestDTO createDTO) {
        log.trace("Controller method called to create Announcement with title: {}", createDTO);
        return new ResponseEntity<>(
                announcementDTOConverter.toDTO(
                        announcementService.createAnnouncement(
                                announcementDTOConverter.toEntity(createDTO)
                        )
                )
                , HttpStatus.CREATED);
    }

    @ApiOperation(value = "Announcement rating up")
    @PreAuthorize("hasPermission(#announcement,'ALL')")
    @PostMapping("/{id}/up")
    public ResponseEntity<AnnouncementResponseDTO> announcementRatingUp(
            @PathVariable("id") Announcement announcement
    ) {
        log.trace("Controller method called to update Announcement rating with id: {}", announcement.getId());
        return new ResponseEntity<>(
                announcementDTOConverter.toDTO(
                        announcementService.announcementRatingUp(announcement))
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Change announcement active")
    @PreAuthorize("hasPermission(#announcement,'ALL')")
    @PatchMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> changeAnnouncementActive(
            @PathVariable("id") Announcement announcement,
            @RequestBody @Valid AnnouncementActiveChangeDTO activityChangeDTO
    ) {
        log.trace("Controller method called to update isExchange announcement field with id: {}", announcement.getId());
        return new ResponseEntity<>(
                announcementDTOConverter.toDTO(
                        announcementService.updateAnnouncement(announcementDTOConverter
                                .toDTOWithEditedIsExchange(announcement, activityChangeDTO)))
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Update announcement")
    @PreAuthorize("hasPermission(#announcement,'ALL')")
    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementResponseDTO> updateAnnouncement(
            @PathVariable("id") Announcement announcement,
            @RequestBody @Valid AnnouncementRequestDTO requestDTO
    ) {
        log.trace("Controller method called to update Announcement with id: {}", announcement.getId());
        return new ResponseEntity<>(announcementDTOConverter.toDTO
                (announcementService.updateAnnouncement(
                        announcementDTOConverter.updateToEntity(announcement.getId(), requestDTO))
                )
                , HttpStatus.OK);
    }


}
