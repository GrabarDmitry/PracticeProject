package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Bookmark;
import com.auto.practiceproject.model.User;
import org.junit.Assert;
import org.junit.Before;
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
        locations = "classpath:application-test.properties")
public class BookmarkDAOTest {

    @Autowired
    private BookmarkDAO bookmarkDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUp() {
        User userTest1 = new User();
        userTest1.setEmail("alex@mail.ru");
        User userTest2 = new User();
        userTest2.setEmail("jon@mail.ru");
        testEntityManager.persistAndFlush(new Bookmark(userTest1, Collections.emptyList()));
        testEntityManager.persistAndFlush(new Bookmark(userTest2, Collections.emptyList()));
    }

    @Test
    public void findBookmarkByUserTest() {
        Optional<User> user = userDAO.findUserByEmail("alex@mail.ru");

        Optional<Bookmark> bookmark = bookmarkDAO.
                findBookmarkByUser(user.get());

        Assert.assertTrue(bookmark.isPresent());
        Assert.assertEquals(bookmark.get().getUser().getEmail(), "alex@mail.ru");
    }

    @Test
    public void findBookmarkByUserIsNullTest() {
        User user = new User();
        user.setId(50L);

        Optional<Bookmark> bookmark = bookmarkDAO.
                findBookmarkByUser(user);

        Assert.assertTrue(bookmark.isEmpty());
    }

}
