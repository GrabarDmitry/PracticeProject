package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.AnnouncementDTOConverter;
import com.auto.practiceproject.controller.converter.UserDTOConverter;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.service.UserService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = {"User"})
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserDTOConverter userDTOConverter;
  private final AnnouncementDTOConverter announcementDTOConverter;
  private final AnnouncementService announcementService;
  private final UserService userService;

  @ApiOperation(value = "Get current user information")
  @GetMapping
  public ResponseEntity<UserResponseDTO> getCurrentUser(
          @ApiIgnore
      @AuthenticationPrincipal UserDetailsImpl userDetails) {
    log.trace("Controller method called to get User: {}", userDetails.getUser());
    return new ResponseEntity<>(userDTOConverter.toDTO(userDetails.getUser()), HttpStatus.OK);
  }

  @ApiOperation(value = "View user information by id")
  @GetMapping("{id}")
  public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    log.trace("Controller method called to get User with id: {}", id);
    return new ResponseEntity<>(
        userDTOConverter.toDTO(userService.findUserById(id)), HttpStatus.OK);
  }

  @PageableSwagger
  @ApiOperation(value = "Get current user announcements")
  @GetMapping("/announcement")
  public ResponseEntity<Page<AnnouncementResponseDTO>> getUserAnnouncements(
      @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)
          Pageable pageable,
      @ApiIgnore
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(name = "filter", required = false) String filter) {
    log.trace(
        "Controller method called to get current user announcements, current user : {}",
        userDetails.getUser());
    return new ResponseEntity<>(
        announcementService
            .findAnnouncementByUserId(userDetails.getUser(), pageable, filter)
            .map(announcementDTOConverter::toDTO),
        HttpStatus.OK);
  }
}
