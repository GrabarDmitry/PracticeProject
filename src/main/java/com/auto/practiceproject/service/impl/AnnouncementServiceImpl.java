package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.controller.filter.AnnouncementFilter;
import com.auto.practiceproject.controller.filter.FilterFactory;
import com.auto.practiceproject.controller.filter.FilteredService;
import com.auto.practiceproject.dao.AnnouncementDAO;
import com.auto.practiceproject.dao.AutoDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService, FilteredService {

  private final AnnouncementDAO announcementDAO;
  private final AutoDAO autoDAO;
  private final FilterFactory filterFactory;
  private final WalletService walletService;

  @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
  @Override
  public Page<Announcement> findAllAnnouncementByModeration(
      Boolean moderation, Pageable pageable, String filter) {
    log.trace(
        "Service method called to find all moderation Announcement with params: {}", pageable);
    return announcementDAO.findAll(
        applyFilter(
            decodeStringFilter(filter).stream()
                .map(filterFactory.getConverter(AnnouncementFilter.class)::convert)
                .collect(Collectors.toList()),
            getAdditionalSpecifications(moderation)),
        pageable);
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public Announcement createAnnouncement(Announcement announcement) {
    log.trace("Service method called to create Announcement : {}", announcement);
    return announcementDAO.save(announcement);
  }

  @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
  @Override
  public Announcement findAnnouncementById(Long id) {
    log.info("Service method called to find Announcement with id: {}", id);
    return announcementDAO
        .findById(id)
        .orElseThrow(
            () -> {
              log.warn("Announcement with Id: {} not found", id);
              throw new ResourceException("Announcement with Id: " + id + " not found");
            });
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public Announcement updateAnnouncement(Announcement announcement) {
    log.info("Service method called to update Announcement: {}", announcement);
    announcementDAO
        .findById(announcement.getId())
        .ifPresentOrElse(
            u -> {
              announcementDAO.saveAndFlush(announcement);
            },
            () -> {
              log.error("Announcement with Id: {} not found", announcement.getId());
              throw new ResourceException(
                  "Announcement with Id: " + announcement.getId() + " not found");
            });
    return announcement;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public Announcement announcementRatingUp(Long id) {
    log.info("Service method called to update Announcement rating with id: {}", id);
    Announcement announcement = findAnnouncementById(id);
    walletService.payForServices(announcement.getRatingUpPrice(), announcement.getUser());
    announcement.setRating(announcement.getRating() + 1);
    return announcementDAO.saveAndFlush(announcement);
  }

  @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
  @Override
  public Page<Announcement> findAnnouncementByUserId(User user, Pageable pageable, String filter) {
    log.trace(
        "Service method called to find all moderation user announcement with params: {}", pageable);
    return announcementDAO.findAll(
        applyFilter(
            decodeStringFilter(filter).stream()
                .map(filterFactory.getConverter(AnnouncementFilter.class)::convert)
                .collect(Collectors.toList()),
            getAdditionalSpecifications(user)),
        pageable);
  }

  @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
  @Override
  public Optional<Announcement> findAnnouncement(Long id) {
    log.trace("Service method called to view Announcement with id : {}", id);
    return announcementDAO.findById(id);
  }

  private List<Specification> getAdditionalSpecifications(Boolean aBoolean) {
    List<Specification> specifications = new ArrayList<>();

    specifications.add(
        (root, criteriaQuery, criteriaBuilder) -> {
          return criteriaBuilder.equal(root.get("isModeration"), aBoolean);
        });
    return specifications;
  }

  private List<Specification> getAdditionalSpecifications(User user) {
    List<Specification> specifications = new ArrayList<>();

    specifications.add(
        (root, criteriaQuery, criteriaBuilder) -> {
          return criteriaBuilder.equal(root.join("user").get("id"), user.getId());
        });
    return specifications;
  }
}
