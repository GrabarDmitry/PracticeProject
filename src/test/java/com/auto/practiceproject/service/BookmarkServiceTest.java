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

  @MockBean private BookmarkDAO bookmarkDAO;

  @MockBean private UserService userService;

  @Autowired private BookmarkService bookmarkService;

  @MockBean private AnnouncementService announcementService;

  private List<Bookmark> bookmarkData;

  @Before
  public void setUp() {
    bookmarkData = new ArrayList<>();

    Announcement announcement = new Announcement();
    announcement.setId(1L);
    User user = new User();
    user.setId(1l);
    Bookmark bookmark1 = new Bookmark(user, new ArrayList<>());
    bookmark1.setId(1L);
    Bookmark bookmark2 = new Bookmark(user, List.of(announcement));
    bookmark2.setId(2L);

    bookmarkData.add(bookmark1);
    bookmarkData.add(bookmark2);

    Mockito.when(userService.getCurrentUser()).thenReturn(Optional.of(user));
    Mockito.when(announcementService.findAnnouncement(1L)).thenReturn(Optional.of(announcement));
    Mockito.when(bookmarkDAO.findBookmarkByUser(user)).thenReturn(Optional.of(bookmark1));
  }

  @Test
  public void findByUserTest() {
    User user = new User();
    user.setId(1L);

    Mockito.when(userService.getCurrentUser()).thenReturn(Optional.of(user));
    Mockito.when(bookmarkDAO.findBookmarkByUser(any(User.class)))
        .thenReturn(Optional.ofNullable(bookmarkData.get(0)));

    Bookmark bookmark = bookmarkService.findByUser();

    Assert.assertNotNull(bookmark);
    Assert.assertEquals(bookmark.getUser().getId().longValue(), user.getId().longValue());

    Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
    Mockito.verify(bookmarkDAO, Mockito.times(1)).findBookmarkByUser(any(User.class));
  }

  @Test
  public void updateBookmarkTest() {
    Bookmark bookmarkTest = new Bookmark();
    bookmarkTest.setId(1l);

    Mockito.when(bookmarkDAO.findById(1L)).thenReturn(Optional.ofNullable(bookmarkData.get(0)));
    Mockito.doReturn(bookmarkData.get(0)).when(bookmarkDAO).saveAndFlush(any(Bookmark.class));

    Bookmark bookmark = bookmarkService.updateBookmark(bookmarkTest);

    Assert.assertNotNull(bookmark);

    Mockito.verify(bookmarkDAO, Mockito.times(1)).findById(1L);
    Mockito.verify(bookmarkDAO, Mockito.times(1)).saveAndFlush(any(Bookmark.class));
  }

  @Test
  public void updateBookmarkFailTest() {
    Bookmark bookmarkTest = new Bookmark();
    bookmarkTest.setId(3L);

    Exception exception =
        assertThrows(ResourceException.class, () -> bookmarkService.updateBookmark(bookmarkTest));
    Assert.assertTrue(exception.getMessage().contains("Bookmark with Id: 3 not found"));

    Mockito.verify(bookmarkDAO, Mockito.times(1)).findById(3L);
    Mockito.verify(bookmarkDAO, Mockito.times(0)).saveAndFlush(any(Bookmark.class));
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

    Mockito.verify(announcementService, Mockito.times(1)).findAnnouncement(1L);
    Mockito.verify(bookmarkDAO, Mockito.times(1)).saveAndFlush(any(Bookmark.class));
    Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
  }

  @Test
  public void addAnnouncementToBookmarkFailTest() {
    Announcement announcement = new Announcement();
    announcement.setId(1L);

    Bookmark bookmarkWithAnnouncement = bookmarkData.get(1);

    Mockito.when(bookmarkDAO.findBookmarkByUser(any(User.class)))
        .thenReturn(Optional.of(bookmarkWithAnnouncement));
    Mockito.when(announcementService.findAnnouncement(1L)).thenReturn(Optional.of(announcement));

    Exception exception =
        assertThrows(
            ResourceException.class,
            () -> bookmarkService.addAnnouncementToBookmark(announcement.getId()));
    Assert.assertTrue(exception.getMessage().contains("This announcement is bookmarked!"));

    Mockito.verify(announcementService, Mockito.times(1)).findAnnouncement(1L);
    Mockito.verify(bookmarkDAO, Mockito.times(0)).saveAndFlush(any(Bookmark.class));
    Mockito.verify(userService, Mockito.times(1)).getCurrentUser();
  }
}
