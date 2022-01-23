package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AnnouncementService {

    Page<Announcement> findAllModerationAnnouncement(Pageable pageable, String filter);

    Announcement createAnnouncement(Announcement announcement);

    Announcement findAnnouncementById(Long id);

    Announcement updateAnnouncement(Announcement announcement);

    Announcement announcementRatingUp(Announcement announcement);

    Page<Announcement> findAnnouncementByUserId(String id, Pageable pageable, String filter);

    Optional<Announcement> findAnnouncement(Long id);

}
