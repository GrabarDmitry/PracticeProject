package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkDAO extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findBookmarkByUser(User user);
}
