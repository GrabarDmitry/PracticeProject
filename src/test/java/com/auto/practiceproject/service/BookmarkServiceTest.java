package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.BookmarkDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkServiceTest {

    @MockBean
    private BookmarkDAO bookmarkDAO;

    @MockBean
    private UserService userService;

    @Autowired
    private BookmarkService bookmarkService;

    @MockBean
    private AnnouncementService announcementService;

    @Before
    public void setUp() {

        User user = new User();
        user.setId(1l);
        Bookmark bookmark = new Bookmark(user, new ArrayList<>());
        bookmark.setId(1L);
        Announcement announcement = new Announcement();
        announcement.setId(1L);

        Mockito.when(bookmarkDAO.findBookmarkByUser(user))
                .thenReturn(Optional.of(bookmark));
        Mockito.when(bookmarkDAO.findById(1L))
                .thenReturn(Optional.of(bookmark));
        Mockito.when(userService.getCurrentUser()).
                thenReturn(Optional.of(user));
        Mockito.when(announcementService.findAnnouncement(1L))
                .thenReturn(Optional.of(announcement));
    }

    @Test
    public void findByUserTest() {

        User user = new User();
        user.setId(1L);

        Mockito.when(userService.getCurrentUser()).
                thenReturn(Optional.of(user));
        Bookmark bookmark = bookmarkService.findByUser();

        Assert.assertNotNull(bookmark);
        Assert.assertEquals(bookmark.getUser(), user);

        Mockito.verify(userService,
                Mockito.times(1)).getCurrentUser();
        Mockito.verify(bookmarkDAO,
                Mockito.times(1)).findBookmarkByUser(user);

    }

    @Test
    public void updateBookmarkTest() {

        Bookmark bookmarkTest = new Bookmark();
        bookmarkTest.setId(1L);

        Mockito.doReturn(bookmarkTest).when(bookmarkDAO).saveAndFlush(any());

        Bookmark bookmark = bookmarkService.updateBookmark(bookmarkTest);


        Assert.assertNotNull(bookmark);

        Mockito.verify(bookmarkDAO,
                Mockito.times(1)).findById(1L);
        Mockito.verify(bookmarkDAO,
                Mockito.times(1)).saveAndFlush(any(Bookmark.class));
    }

    @Test
    public void updateBookmarkFailTest() {

        Bookmark bookmarkTest = new Bookmark();
        bookmarkTest.setId(3L);

        Exception exception = assertThrows(
                ResourceException.class,
                () -> bookmarkService.updateBookmark(bookmarkTest));
        Assert.assertTrue(exception.getMessage().contains(
                "Bookmark with Id: 3 not found"
        ));

        Mockito.verify(bookmarkDAO,
                Mockito.times(1)).findById(3L);
        Mockito.verify(bookmarkDAO,
                Mockito.times(0)).saveAndFlush(any(Bookmark.class));
    }

    @Test
    public void addAnnouncementToBookmarkTest() {

        Announcement announcement = new Announcement();
        announcement.setId(1L);

        Bookmark bookmarkAfterRepo = new Bookmark();
        bookmarkAfterRepo.setAnnouncements(List.of(announcement));
        bookmarkAfterRepo.setId(1L);

        Mockito.doReturn(bookmarkAfterRepo).when(bookmarkDAO).saveAndFlush(any());

        Bookmark bookmark = bookmarkService.addAnnouncementToBookmark(announcement.getId());

        Assert.assertNotNull(bookmark);
        Assert.assertEquals(bookmark.getAnnouncements().get(0).getId().longValue(), 1L);

        Mockito.verify(announcementService,
                Mockito.times(1)).findAnnouncement(1L);
        Mockito.verify(bookmarkDAO,
                Mockito.times(1)).saveAndFlush(any(Bookmark.class));
        Mockito.verify(userService,
                Mockito.times(1)).getCurrentUser();
    }

    @Test
    public void addAnnouncementToBookmarkFailTest() {

        Announcement announcement = new Announcement();
        announcement.setId(1L);

        Bookmark bookmarkWithAnnouncement = new Bookmark();
        bookmarkWithAnnouncement.setAnnouncements(List.of(announcement));
        bookmarkWithAnnouncement.setId(1L);

        Mockito.when(bookmarkDAO.findBookmarkByUser(any(User.class)))
                .thenReturn(Optional.of(bookmarkWithAnnouncement));
        Mockito.when(announcementService.findAnnouncement(1L))
                .thenReturn(Optional.of(announcement));

        Exception exception = assertThrows(
                ResourceException.class,
                () -> bookmarkService.addAnnouncementToBookmark(announcement.getId()));
        Assert.assertTrue(exception.getMessage().contains(
                "This announcement is bookmarked!"
        ));

        Mockito.verify(announcementService,
                Mockito.times(1)).findAnnouncement(1L);
        Mockito.verify(bookmarkDAO,
                Mockito.times(0)).saveAndFlush(any(Bookmark.class));
        Mockito.verify(userService,
                Mockito.times(1)).getCurrentUser();
    }


}