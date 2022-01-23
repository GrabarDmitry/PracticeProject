package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.CommentDAO;
import com.auto.practiceproject.model.Comment;
import com.auto.practiceproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Comment createComment(Comment comment) {
        log.trace("Service method called to create comment, creator user id:{}",
                comment.getUser().getId());
        return commentDAO.save(comment);
    }

}
