package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.dto.request.BookmarkAnnouncementChangeDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.service.BookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"Bookmarks"})
@RestController
@Slf4j
@RequestMapping("/api/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

  private final AnnouncementDTOConverter announcementDTOConverter;
  private final BookmarkService bookmarkService;

  @ApiOperation(value = "Get all announcement in bookmark")
  @GetMapping
  public ResponseEntity<List<AnnouncementResponseDTO>> getAllAnnouncementInBookmark() {
    log.trace("Controller method called to view all user Announcement in bookmark");
    return new ResponseEntity<>(
        bookmarkService.findByUser().getAnnouncements().stream()
            .map(announcementDTOConverter::toDTO)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @ApiOperation(value = "Change bookmark announcements")
  @PatchMapping
  public ResponseEntity<List<AnnouncementResponseDTO>> changeBookmarkAnnouncements(
      @RequestBody @Valid BookmarkAnnouncementChangeDTO announcementChangeDTO) {
    log.trace("Controller method called to change announcements in bookmark");
    return new ResponseEntity<>(
        bookmarkService
            .addAnnouncementToBookmark(announcementChangeDTO.getAnnouncementId())
            .getAnnouncements()
            .stream()
            .map(announcementDTOConverter::toDTO)
            .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
