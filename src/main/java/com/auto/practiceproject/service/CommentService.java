package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<Comment> findAllByAnnouncement(Long announcementId, Pageable pageable);

    Comment createComment(Comment comment);

}
