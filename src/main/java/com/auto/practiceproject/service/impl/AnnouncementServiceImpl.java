package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.controller.filter.AnnouncementFilter;
import com.auto.practiceproject.controller.filter.FilteredService;
import com.auto.practiceproject.dao.AnnouncementDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
@Slf4j
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService, FilteredService {

    private final AnnouncementDAO announcementDAO;
    private final AnnouncementFilter announcementFilter;

    public Page<Announcement> findAllAnnouncement(Pageable pageable, String filter) {
        log.trace("Service method called to find all Announcement with params: {}", pageable);
        return announcementDAO.findAll(applyFilter(announcementFilter, decodeStringFilter(filter)), pageable);
    }

    @Override
    public Announcement findAnnouncementById(Long id) {
        log.info("Service method called to find Announcement with id: {}", id);
        return announcementDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Announcement with Id: {} not found", id);
                    throw new ResourceException("Announcement with Id: " + id + " not found");
                });
    }


}
