package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Comment;
import org.junit.Assert;
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

    @Test
    public void findAllByAnnouncementTest() {

        Announcement announcementTest1 = testEntityManager.persistAndFlush(new Announcement());
        Announcement announcementTest2 = testEntityManager.persistAndFlush(new Announcement());

        testEntityManager.persistAndFlush(new Comment("text1", LocalDateTime.now(), null, announcementTest1));
        testEntityManager.persistAndFlush(new Comment("text2", LocalDateTime.now(), null, announcementTest2));
        testEntityManager.persistAndFlush(new Comment("text3", LocalDateTime.now(), null, announcementTest1));

        Page<Comment> comments = commentDAO.findAllByAnnouncement(announcementTest1, null);

        Assert.assertEquals(comments.getContent().size(), 2);
        Assert.assertEquals(comments.getContent().get(0).getAnnouncement(), announcementTest1);
        Assert.assertEquals(comments.getContent().get(1).getAnnouncement(), announcementTest1);
    }

    @Test
    public void findAllByAnnouncementIsEmptyListTest() {
        Announcement announcement = testEntityManager.persistAndFlush(new Announcement());

        Page<Comment> comments = commentDAO.findAllByAnnouncement(announcement, null);

        Assert.assertTrue(comments.getContent().isEmpty());
    }

}
