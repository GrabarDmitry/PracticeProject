package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AnnouncementService {

    Page<Announcement> findAllAnnouncementByModeration(Boolean moderation, Pageable pageable, String filter);

    Announcement createAnnouncement(Announcement announcement);

    Announcement findAnnouncementById(Long id);

    Announcement updateAnnouncement(Announcement announcement);

    Announcement announcementRatingUp(Long id);

    Page<Announcement> findAnnouncementByUserId(User user, Pageable pageable, String filter);

    Optional<Announcement> findAnnouncement(Long id);

}
