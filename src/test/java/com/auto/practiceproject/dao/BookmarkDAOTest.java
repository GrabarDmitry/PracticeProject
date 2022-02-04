package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application_dao_test.properties")
public class BookmarkDAOTest {

    @Autowired
    private BookmarkDAO bookmarkDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findBookmarkByUserTest() {
        User userTest1 = new User();
        userTest1.setEmail("alex@mail.ru");
        userTest1 = testEntityManager.persistAndFlush(userTest1);

        User userTest2 = new User();
        userTest2.setEmail("jon@mail.ru");
        userTest2 = testEntityManager.persistAndFlush(userTest2);

        testEntityManager.persistAndFlush(new Bookmark(userTest1, Collections.emptyList()));
        testEntityManager.persistAndFlush(new Bookmark(userTest2, Collections.emptyList()));

        Optional<Bookmark> bookmark = bookmarkDAO.
                findBookmarkByUser(userTest1);

        Assert.assertTrue(bookmark.isPresent());
        Assert.assertEquals(bookmark.get().getUser().getEmail(), "alex@mail.ru");
    }

    @Test
    public void findBookmarkByUserIsNullTest() {
        User user = testEntityManager.persistAndFlush(new User());

        Optional<Bookmark> bookmark = bookmarkDAO.
                findBookmarkByUser(user);

        Assert.assertTrue(bookmark.isEmpty());
    }

}
