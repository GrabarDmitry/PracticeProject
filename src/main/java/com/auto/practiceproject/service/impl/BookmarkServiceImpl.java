package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.BookmarkDAO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.service.BookmarkService;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<Announcement> findByUserFromBookmark() {
        log.trace("Service method called to view Bookmark with user");
        User user = userService.getCurrentUser()
                .orElse(null);
        return bookmarkDAO.findBookmarkByUser(user).get().getAnnouncements();
    }

}
