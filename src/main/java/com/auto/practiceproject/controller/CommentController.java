package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.converter.CommentDTOConverter;
import com.auto.practiceproject.controller.dto.request.CommentRequestDTO;
import com.auto.practiceproject.controller.dto.response.CommentResponseDTO;
import com.auto.practiceproject.service.CommentService;
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
@RequestMapping("/api/announcement/{announcementId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentDTOConverter commentDTOConverter;

    @GetMapping
    public ResponseEntity<Page<CommentResponseDTO>> getAllCommentByAnnouncement(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long announcementId) {
        log.trace("Controller method called to view all comment with params: {}", pageable);
        return new ResponseEntity<>(
                commentService.findAllByAnnouncement(announcementId, pageable)
                        .map(commentDTOConverter::toDTO)
                , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody @Valid CommentRequestDTO requestDTO,
            @PathVariable Long announcementId) {
        log.trace("Controller method called to create comment");
        return new ResponseEntity<>(
                commentDTOConverter.toDTO(
                        commentService.createComment(
                                commentDTOConverter.toEntity(announcementId, requestDTO)
                        )
                )
                , HttpStatus.CREATED);
    }

}
