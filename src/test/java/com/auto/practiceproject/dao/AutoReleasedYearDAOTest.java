package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoReleasedYear;
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

import java.time.LocalDate;
import java.util.Optional;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class AutoReleasedYearDAOTest {

    @Autowired
    private AutoReleasedYearDAO releasedYearDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        testEntityManager.persistAndFlush(new AutoReleasedYear(LocalDate.parse("2010-01-01")));
        testEntityManager.persistAndFlush(new AutoReleasedYear(LocalDate.parse("2020-01-01")));
        testEntityManager.persistAndFlush(new AutoReleasedYear(LocalDate.parse("2022-01-01")));
    }

    @Test
    public void findAutoReleasedYearByReleasedYearTest() {
        Optional<AutoReleasedYear> autoReleasedYear = releasedYearDAO.
                findAutoReleasedYearByReleasedYear(LocalDate.parse("2020-01-01"));

        Assert.assertTrue(autoReleasedYear.isPresent());
        Assert.assertEquals(autoReleasedYear.get().getReleasedYear(), LocalDate.parse("2020-01-01"));
    }

    @Test
    public void findAutoReleasedYearByReleasedYearIsNullTest() {
        Optional<AutoReleasedYear> autoReleasedYear =
                releasedYearDAO.findAutoReleasedYearByReleasedYear(LocalDate.parse("2015-01-01"));
        Assert.assertTrue(autoReleasedYear.isEmpty());

    }

}
