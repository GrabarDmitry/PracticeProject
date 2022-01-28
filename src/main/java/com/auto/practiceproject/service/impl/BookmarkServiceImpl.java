package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.BookmarkDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.service.AnnouncementService;
import com.auto.practiceproject.service.BookmarkService;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkDAO bookmarkDAO;
    private final UserService userService;
    private final AnnouncementService announcementService;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Bookmark findByUser() {
        log.trace("Service method called to view Bookmark with user");
        User user = userService.getCurrentUser()
                .orElse(null);
        return bookmarkDAO.findBookmarkByUser(user).
                orElse(null);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Bookmark updateBookmark(Bookmark bookmark) {
        log.info("Service method called to update Announcements in Bookmark ,bookmark id: {}", bookmark.getId());
        bookmarkDAO.findById(bookmark.getId())
                .ifPresentOrElse(
                        u -> bookmarkDAO.saveAndFlush(bookmark),
                        () -> {
                            log.error("Bookmark with Id: {} not found", bookmark.getId());
                            throw new ResourceException("Bookmark with Id: " + bookmark.getId() + " not found");
                        });
        return bookmark;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Bookmark addAnnouncementToBookmark(Long announcementId) {
        log.info("Service method called to add announcement in Bookmark");
        Announcement announcement = announcementService.findAnnouncement(announcementId)
                .orElse(null);
        Bookmark bookmark = findByUser();
        List list = bookmark.getAnnouncements();
        if (list.contains(announcement)) {
            log.error("Announcement with Id: {} is bookmarked", announcement.getId());
            throw new ResourceException("This announcement is bookmarked!");
        } else {
            list.add(announcement);
            bookmark.setAnnouncements(list);
            return bookmarkDAO.saveAndFlush(bookmark);
        }
    }

}
