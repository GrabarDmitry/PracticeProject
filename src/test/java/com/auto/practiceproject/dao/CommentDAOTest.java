package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Comment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class CommentDAOTest {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        Announcement announcementTest1 = new Announcement();
        Announcement announcementTest2 = new Announcement();
        testEntityManager.persistAndFlush(announcementTest1);
        testEntityManager.persistAndFlush(announcementTest2);

        testEntityManager.persistAndFlush(new Comment("text1", LocalDateTime.now(), null, announcementTest1));
        testEntityManager.persistAndFlush(new Comment("text2", LocalDateTime.now(), null, announcementTest2));
        testEntityManager.persistAndFlush(new Comment("text3", LocalDateTime.now(), null, announcementTest1));
    }

    @Test
    public void findAllByAnnouncementTest() {
        Announcement announcement = new Announcement();
        announcement.setId(1L);
        Page<Comment> comments = commentDAO.findAllByAnnouncement(announcement, null);

        Assert.assertEquals(comments.getSize(), 2);
        Assert.assertEquals(comments.getContent().get(0).getAnnouncement(), announcement);
        Assert.assertEquals(comments.getContent().get(1).getAnnouncement(), announcement);
    }

    @Test
    public void findAllByAnnouncementIsEmptyListTest() {
        Announcement announcement = new Announcement();
        announcement.setId(5L);

        Page<Comment> comments = commentDAO.findAllByAnnouncement(announcement, null);

        Assert.assertTrue(comments.getContent().isEmpty());
    }

}
