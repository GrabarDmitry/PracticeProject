package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.controller.filter.AnnouncementFilter;
import com.auto.practiceproject.controller.filter.FilterDTO;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService, FilteredService {

    private final AnnouncementDAO announcementDAO;
    private final AnnouncementFilter announcementFilter;

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public Page<Announcement> findAllModerationAnnouncement(Pageable pageable, String filter) {
        log.trace("Service method called to find all moderation Announcement with params: {}", pageable);
        return announcementDAO.findAll(
                applyFilter(
                        announcementFilter,
                        decodeStringFilter(filter),
                        List.of(new FilterDTO("isModeration", String.valueOf(false)))),
                pageable);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Announcement createAnnouncement(Announcement announcement) {
        log.trace("Service method called to create Announcement with title, creator is userID : {}",
                announcement.getTitle(), announcement.getUser().getId());
        return announcementDAO.save(announcement);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public Announcement findAnnouncementById(Long id) {
        log.info("Service method called to find Announcement with id: {}", id);
        return announcementDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Announcement with Id: {} not found", id);
                    throw new ResourceException("Announcement with Id: " + id + " not found");
                });
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Announcement updateAnnouncement(Announcement announcement) {
        log.info("Service method called to update Announcement with id: {}", announcement.getId());
        announcementDAO.findById(announcement.getId())
                .ifPresentOrElse(
                        u -> announcementDAO.saveAndFlush(announcement),
                        () -> {
                            log.error("Announcement with Id: {} not found", announcement.getId());
                            throw new ResourceException("Announcement with Id: " + announcement.getId() + " not found");
                        });
        return announcement;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Announcement announcementRatingUp(Announcement announcement) {
        log.info("Service method called to update Announcement rating with id: {}", announcement.getId());
        Duration duration = Duration.between(announcement.getLastRatingUp(), LocalDateTime.now());
        if (duration.toDays() == 0 && duration.toHours() < 5) {
            log.warn("Announcement with Id: {} can't up rating", announcement.getId());
            throw new ResourceException("Announcement with Id: " + announcement.getId() + " can't up rating" +
                    ",last Announcement rating up at:" + announcement.getLastRatingUp());
        }
        announcement.setRating(announcement.getRating() + 1);
        announcement.setLastRatingUp(LocalDateTime.now());
        return updateAnnouncement(announcement);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public Page<Announcement> findAnnouncementByUserId(String id, Pageable pageable, String filter) {
        log.trace("Service method called to find all moderation user announcement with params: {}", pageable);
        return announcementDAO.findAll(
                applyFilter(
                        announcementFilter,
                        decodeStringFilter(filter),
                        List.of(new FilterDTO("userId", id))),
                pageable);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public Optional<Announcement> findAnnouncement(Long id) {
        log.trace("Service method called to view Announcement with id : {}", id);
        return announcementDAO.findById(id);
    }


}
