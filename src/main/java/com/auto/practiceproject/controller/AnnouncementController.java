package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.request.AnnouncementCreateDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Slf4j
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AnnouncementDTOConverter announcementDTOConverter;

    @GetMapping
    public ResponseEntity<Page<AnnouncementResponseDTO>> getAllAnnouncement(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "rating",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(name = "filter", required = false) String filter) {
        log.trace("Controller method called to view all Announcement with params: {}", pageable);
        return new ResponseEntity<>(
                announcementService.findAllModerationAnnouncement(pageable, filter)
                        .map(announcementDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAnnouncementResponseDTO> getAnnouncementById(@PathVariable Long id) {
        log.trace("Controller method called to view Announcement with id: {}", id);
        return new ResponseEntity<>(announcementDTOConverter.
                toFullDTO(announcementService.findAnnouncementById(id)),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FullAnnouncementResponseDTO> createAnnouncement(@RequestBody @Valid AnnouncementCreateDTO createDTO) {
        log.trace("Controller method called to create Announcement with title: {}"
                , createDTO.getBrand() + "" + createDTO.getModel());
        return new ResponseEntity<>(
                announcementDTOConverter.toFullDTO(
                        announcementService.createAnnouncement(
                                announcementDTOConverter.toEntity(createDTO)
                        )
                )
                , HttpStatus.CREATED);
    }

}
