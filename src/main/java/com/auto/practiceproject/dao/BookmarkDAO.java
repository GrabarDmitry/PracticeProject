package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkDAO extends JpaRepository<Bookmark, Long> {
}
