package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AnnouncementDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Announcement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnouncementServiceTest {

    @Autowired
    private AnnouncementService announcementService;

    @MockBean
    private AnnouncementDAO announcementDAO;

    @Before
    public void setUp() {
        Announcement announcement = new Announcement();
        announcement.setId(1l);

        Mockito.when(announcementDAO.findById(1l))
                .thenReturn(Optional.of(announcement));

    }

    @Test
    public void findAnnouncementByIdTest() {
        Announcement announcement = announcementService.findAnnouncementById(1l);
        Assert.assertNotNull(announcement);
        Assert.assertEquals(announcement.getId().longValue(), 1L);
    }

    @Test(expected = ResourceException.class)
    public void failFindAnnouncementByIdTest() {
        Announcement announcement = announcementService.findAnnouncementById(3l);
        Assert.assertNull(announcement);
        Exception exception = assertThrows(
                ResourceException.class,
                () -> announcementService.findAnnouncementById(3l));
        Assert.assertTrue(exception.getMessage().contains(
                "Announcement with Id: 3 not found"
        ));
    }

    @Test
    public void findAnnouncementTest() {
        Optional<Announcement> announcement = announcementService.findAnnouncement(1l);
        Assert.assertTrue(announcement.isPresent());
        Assert.assertEquals(announcement.get().getId().longValue(), 1l);
    }

    @Test
    public void findAnnouncementNullTest() {
        Optional<Announcement> announcement = announcementService.findAnnouncement(3l);
        Assert.assertTrue(announcement.isEmpty());
    }

}
