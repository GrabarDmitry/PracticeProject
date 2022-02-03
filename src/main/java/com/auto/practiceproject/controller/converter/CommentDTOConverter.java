package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.CommentRequestDTO;
import com.auto.practiceproject.controller.dto.response.CommentResponseDTO;
import com.auto.practiceproject.model.Comment;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentDTOConverter {

    private final UserService userService;
    private final AnnouncementService announcementService;

    public CommentResponseDTO toDTO(Comment comment) {
        log.trace("Convert Comment : {}, to CommentResponseDTO", comment);
        return new CommentResponseDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreated(),
                comment.getUser().getId()
        );
    }

    public Comment toEntity(Long id, CommentRequestDTO requestDTO) {
        log.trace("Convert CommentRequestDTO:{}, to Comment entity", requestDTO);
        return new Comment(
                requestDTO.getText(),
                LocalDateTime.now(),
                userService.getCurrentUser().
                        orElse(null),
                announcementService.findAnnouncement(id)
                        .orElse(null)
        );
    }

}
