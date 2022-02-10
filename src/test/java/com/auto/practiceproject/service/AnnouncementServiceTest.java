//package com.auto.practiceproject.service;
//
//import com.auto.practiceproject.dao.AnnouncementDAO;
//import com.auto.practiceproject.exception.ResourceException;
//import com.auto.practiceproject.model.Announcement;
//import com.auto.practiceproject.model.User;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AnnouncementServiceTest {
//
//    @Autowired
//    private AnnouncementService announcementService;
//
//    @MockBean
//    private AnnouncementDAO announcementDAO;
//
//    @MockBean
//    private WalletService walletService;
//
//    private List<Announcement> announcementsTestData;
//
//    @Before
//    public void setUp() {
//        announcementsTestData = new ArrayList<>();
//
//        User user = new User();
//        user.setId(1L);
//
//        Announcement announcementTest1 = new Announcement();
//        announcementTest1.setIsModeration(false);
//        announcementTest1.setId(1l);
//        announcementTest1.setUser(user);
//        announcementTest1.setRating(1);
//        announcementTest1.setRatingUpPrice(1D);
//        Announcement announcementTest2 = new Announcement();
//        announcementTest2.setId(2l);
//        announcementTest2.setIsModeration(false);
//        Announcement announcementTest3 = new Announcement();
//        announcementTest3.setId(3l);
//        announcementTest3.setIsModeration(true);
//
//        announcementsTestData.add(announcementTest1);
//        announcementsTestData.add(announcementTest2);
//        announcementsTestData.add(announcementTest3);
//    }
//
//    @Test
//    public void findAllAnnouncementByModerationIsFalse() {
//        Mockito.when(announcementDAO.findAll(any(Specification.class), any(Pageable.class)))
//                .thenReturn(new PageImpl(announcementsTestData.subList(0, 2)));
//
//        Pageable pageable = Mockito.mock(Pageable.class);
//
//        Page<Announcement> announcementPage = announcementService.
//                findAllAnnouncementByModeration(false, pageable, null);
//
//        List<Announcement> announcements = announcementPage.getContent();
//
//        Assert.assertEquals(announcements.size(), 2);
//        Assert.assertEquals(announcements.get(0).getId().longValue(), 1L);
//        Assert.assertEquals(announcements.get(0).getIsModeration(), false);
//        Assert.assertEquals(announcements.get(1).getId().longValue(), 2L);
//        Assert.assertEquals(announcements.get(1).getIsModeration(), false);
//    }
//
//    @Test
//    public void findAllAnnouncementByModerationIsTrue() {
//        Mockito.when(announcementDAO.findAll(any(Specification.class), any(Pageable.class)))
//                .thenReturn(new PageImpl(announcementsTestData.subList(2, 3)));
//
//        Pageable pageable = Mockito.mock(Pageable.class);
//
//        Page<Announcement> announcementPage = announcementService.
//                findAllAnnouncementByModeration(false, pageable, null);
//
//        List<Announcement> announcements = announcementPage.getContent();
//
//        Assert.assertEquals(announcements.size(), 1);
//        Assert.assertEquals(announcements.get(0).getId().longValue(), 3L);
//        Assert.assertEquals(announcements.get(0).getIsModeration(), true);
//    }
//
//    @Test
//    public void announcementCreateTest() {
//        Mockito.doReturn(announcementsTestData.get(0)).when(announcementDAO).save(any(Announcement.class));
//
//        Announcement announcementTest = new Announcement();
//        announcementTest.setIsModeration(false);
//
//        Announcement announcement = announcementService.createAnnouncement(announcementTest);
//
//        Mockito.verify(announcementDAO,
//                Mockito.times(1)).save(any(Announcement.class));
//
//        Assert.assertNotNull(announcement);
//        Assert.assertEquals(announcement.getId().longValue(), 1l);
//    }
//
//    @Test
//    public void findAnnouncementByIdTest() {
//        Mockito.when(announcementDAO.findById(1l))
//                .thenReturn(Optional.of(announcementsTestData.get(0)));
//
//        Announcement announcement = announcementService.findAnnouncementById(1l);
//
//        Assert.assertNotNull(announcement);
//        Assert.assertEquals(announcement.getId().longValue(), 1L);
//    }
//
//    @Test(expected = ResourceException.class)
//    public void findAnnouncementByIdIsNullTest() {
//        Announcement announcement = announcementService.findAnnouncementById(3l);
//
//        Assert.assertNull(announcement);
//
//        Exception exception = assertThrows(
//                ResourceException.class,
//                () -> announcementService.findAnnouncementById(3l));
//        Assert.assertTrue(exception.getMessage().contains(
//                "Announcement with Id: 3 not found"
//        ));
//    }
//
//    @Test
//    public void announcementUpdateTest() {
//        Mockito.when(announcementDAO.findById(1l))
//                .thenReturn(Optional.of(announcementsTestData.get(0)));
//        Mockito.doReturn(announcementsTestData.get(0)).when(announcementDAO).
//                saveAndFlush(any(Announcement.class));
//
//        Announcement announcement = announcementService.updateAnnouncement(announcementsTestData.get(0));
//
//        Mockito.verify(announcementDAO,
//                Mockito.times(1)).findById(1L);
//        Mockito.verify(announcementDAO,
//                Mockito.times(1)).saveAndFlush(any(Announcement.class));
//
//        Assert.assertNotNull(announcement);
//        Assert.assertEquals(announcement.getId().longValue(), 1L);
//    }
//
//    @Test
//    public void announcementUpdateTestFail() {
//        Announcement announcement = new Announcement();
//        announcement.setId(3L);
//
//        Exception exception = assertThrows(
//                ResourceException.class,
//                () -> announcementService.updateAnnouncement(announcement));
//        Assert.assertTrue(exception.getMessage().contains(
//                "Announcement with Id: 3 not found"
//        ));
//
//        Mockito.verify(announcementDAO,
//                Mockito.times(1)).findById(3L);
//        Mockito.verify(announcementDAO,
//                Mockito.times(0)).saveAndFlush(any(Announcement.class));
//    }
//
////    @Test
////    public void announcementRatingUpTest() {
////        Mockito.doReturn(announcementsTestData.get(1))
////                .when(announcementDAO).saveAndFlush(any(Announcement.class));
////
////        Announcement announcementTest = announcementsTestData.get(1);
////        announcementTest.setRating(0);
////
////        Announcement announcement = announcementService.announcementRatingUp(announcementTest);
////
////        Assert.assertNotNull(announcement);
////        Assert.assertEquals(announcement.getRating().intValue(), 1);
////
////        Mockito.verify(announcementDAO,
////                Mockito.times(1)).saveAndFlush(any(Announcement.class));
////        Mockito.verify(walletService,
////                        Mockito.times(1)).
////                payForServices(announcement.getRatingUpPrice(), announcement.getUser());
////    }
//
//    @Test
//    public void findAnnouncementTest() {
//        Mockito.when(announcementDAO.findById(1l))
//                .thenReturn(Optional.of(announcementsTestData.get(0)));
//
//        Optional<Announcement> announcement = announcementService.findAnnouncement(1l);
//        Assert.assertTrue(announcement.isPresent());
//        Assert.assertEquals(announcement.get().getId().longValue(), 1l);
//    }
//
//    @Test
//    public void findAnnouncementIsNullTest() {
//        Optional<Announcement> announcement = announcementService.findAnnouncement(3l);
//        Assert.assertTrue(announcement.isEmpty());
//    }
//
//    @Test
//    public void findAnnouncementByUserId() {
//        Mockito.when(announcementDAO.findAll(any(Specification.class), any(Pageable.class)))
//                .thenReturn(new PageImpl(announcementsTestData.subList(0, 1)));
//
//        User user = new User();
//        user.setId(1L);
//        Pageable pageable = Mockito.mock(Pageable.class);
//
//        Page<Announcement> announcementPage = announcementService.
//                findAnnouncementByUserId(user, pageable, null);
//
//        List<Announcement> announcements = announcementPage.getContent();
//
//        Assert.assertEquals(announcements.size(), 1);
//        Assert.assertEquals(announcements.get(0).getId().longValue(), 1L);
//        Assert.assertEquals(announcements.get(0).getUser(), user);
//    }
//
//}
