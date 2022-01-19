package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.service.AnnouncementService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Announcement"})
@RestController
@Slf4j
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final AnnouncementDTOConverter announcementDTOConverter;

    @ApiOperation(value = "Find all announcements")
    @GetMapping
    public ResponseEntity<Page<AnnouncementResponseDTO>> getAllAnnouncement(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "rating",
                    direction = Sort.Direction.DESC) Pageable pageable) {
        log.trace("Controller method called to view all Announcement with params: {}", pageable);
        return new ResponseEntity<>(
                announcementService.findAllAnnouncement(pageable)
                        .map(announcementDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Find announcement by id")
    @GetMapping("/{id}")
    public ResponseEntity<FullAnnouncementResponseDTO> getAnnouncementById(@PathVariable Long id) {
        log.trace("Controller method called to view Announcement with id: {}", id);
        return new ResponseEntity<>(announcementDTOConverter.
                toFullDTO(announcementService.findAnnouncementById(id)),
                HttpStatus.OK);
    }


}
