package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.CommentResponseDTO;
import com.auto.practiceproject.model.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentDTOConverter {

    public CommentResponseDTO toDTO(Comment comment) {
        log.trace("Convert Comment with id: {}, to CommentResponseDTO", comment.getId());
        return new CommentResponseDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreated(),
                comment.getUser().getName() + " "
                        + comment.getUser().getSurname()
        );
    }

}
