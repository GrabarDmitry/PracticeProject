package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementDAO extends JpaRepository<Announcement, Long> {
}
