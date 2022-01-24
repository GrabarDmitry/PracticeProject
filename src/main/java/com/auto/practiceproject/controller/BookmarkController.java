package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final AnnouncementDTOConverter announcementDTOConverter;
    private final BookmarkService bookmarkService;

    @GetMapping
    public ResponseEntity<List<AnnouncementResponseDTO>> getAllAnnouncementInBookmark() {
        log.trace("Controller method called to view all user Announcement in bookmark");
        return new ResponseEntity<>(
                bookmarkService.findByUserFromBookmark()
                        .stream().
                        map(announcementDTOConverter::toDTO)
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

}
