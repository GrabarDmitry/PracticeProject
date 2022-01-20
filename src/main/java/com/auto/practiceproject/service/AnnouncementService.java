package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnnouncementService {

    Page<Announcement> findAllAnnouncement(Pageable pageable, String filter);

    Announcement findAnnouncementById(Long id);

}
