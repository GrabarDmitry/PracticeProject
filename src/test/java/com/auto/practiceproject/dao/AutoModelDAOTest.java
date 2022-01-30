package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
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
public class AutoModelDAOTest {

    @Autowired
    private AutoModelDAO autoModelDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AutoBrandDAO autoBrandDAO;

    @Autowired
    private AutoReleasedYearDAO releasedYearDAO;

    @Before
    public void setUp() {
        AutoBrand autoBrand1 = new AutoBrand("BMW");
        AutoBrand autoBrand2 = new AutoBrand("AUDI");
        AutoReleasedYear autoReleasedYear1 = new AutoReleasedYear(LocalDate.parse("2020-01-01"));
        AutoReleasedYear autoReleasedYear2 = new AutoReleasedYear(LocalDate.parse("2012-01-01"));
        testEntityManager.persistAndFlush(new AutoModel("X5", autoBrand1, autoReleasedYear1));
        testEntityManager.persistAndFlush(new AutoModel("100", autoBrand2, autoReleasedYear2));
        testEntityManager.persistAndFlush(new AutoModel("80", autoBrand2, autoReleasedYear2));
    }

    @Test
    public void findAutoModelByTitleTest() {
        Optional<AutoModel> autoModel = autoModelDAO.findAutoModelByTitle("100");

        Assert.assertTrue(autoModel.isPresent());
        Assert.assertEquals(autoModel.get().getTitle(), "100");
    }

    @Test
    public void findAutoModelByTitleIsNUllTest() {
        Optional<AutoModel> autoModel = autoModelDAO.findAutoModelByTitle("B4");
        Assert.assertTrue(autoModel.isEmpty());
    }

    @Test
    public void findAutoModelByTitleAndAutoBrandAndAutoReleasedYearTest() {
        Optional<AutoBrand> autoBrand = autoBrandDAO.findAutoBrandByTitle("BMW");
        Optional<AutoReleasedYear> autoReleasedYear =
                releasedYearDAO.findAutoReleasedYearByReleasedYear(LocalDate.parse("2020-01-01"));

        Optional<AutoModel> autoModel = autoModelDAO.
                findAutoModelByTitleAndAutoBrandAndAutoReleasedYear("X5", autoBrand.get(), autoReleasedYear.get());

        Assert.assertTrue(autoModel.isPresent());
        Assert.assertEquals(autoModel.get().getTitle(), "X5");
        Assert.assertEquals(autoModel.get().getAutoBrand().getTitle(), "BMW");
        Assert.assertEquals(autoModel.get().getAutoReleasedYear().getReleasedYear(), LocalDate.parse("2020-01-01"));
    }

    @Test
    public void findAutoModelByTitleAndAutoBrandAndAutoReleasedYearIsNullTesl() {
        AutoBrand autoBrand1 = new AutoBrand("BMW");
        autoBrand1.setId(3L);
        AutoReleasedYear autoReleasedYear1 = new AutoReleasedYear(LocalDate.parse("2020-01-01"));
        autoReleasedYear1.setId(4L);

        Optional<AutoModel> autoModel = autoModelDAO.
                findAutoModelByTitleAndAutoBrandAndAutoReleasedYear("B3", autoBrand1, autoReleasedYear1);

        Assert.assertTrue(autoModel.isEmpty());
    }

}
