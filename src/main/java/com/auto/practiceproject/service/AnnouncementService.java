package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {

    Page<Announcement> findAllModerationAnnouncement(Pageable pageable, String filter);

    Announcement findAnnouncementById(Long id);

    Announcement updateAnnouncement(Announcement announcement);

    Announcement announcementRatingUp(Long id);

}
