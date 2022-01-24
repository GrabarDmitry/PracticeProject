package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByAnnouncement(Announcement announcement, Pageable pageable);
}
