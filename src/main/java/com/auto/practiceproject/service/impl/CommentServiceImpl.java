package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AnnouncementDAO;
import com.auto.practiceproject.dao.CommentDAO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Comment;
import com.auto.practiceproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;
    private final AnnouncementDAO announcementDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Page<Comment> findAllByAnnouncement(Long announcementId, Pageable pageable) {
        log.trace("Service method called to find all comment announcementId : {}", announcementId);
        Announcement announcement = announcementDAO.findById(announcementId)
                .orElse(null);
        return commentDAO.findAllByAnnouncement(announcement, pageable);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Comment createComment(Comment comment) {
        log.trace("Service method called to create comment, {}", comment);
        return commentDAO.save(comment);
    }

}
