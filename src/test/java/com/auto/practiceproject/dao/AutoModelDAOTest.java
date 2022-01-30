package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.model.AutoReleasedYear;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutoModelDAOTest {

    @Autowired
    private AutoModelDAO autoModelDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        AutoBrand autoBrand1 = new AutoBrand("BMW");
        autoBrand1.setId(1L);
        AutoBrand autoBrand2 = new AutoBrand("AUDI");
        autoBrand1.setId(2L);
        AutoReleasedYear autoReleasedYear1 = new AutoReleasedYear(LocalDate.parse("2020-01-01"));
        autoReleasedYear1.setId(1L);
        AutoReleasedYear autoReleasedYear2 = new AutoReleasedYear(LocalDate.parse("2012-01-01"));
        autoReleasedYear1.setId(2L);
        testEntityManager.persistAndFlush(new AutoModel("x5", autoBrand1, autoReleasedYear1));
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
        AutoBrand autoBrand1 = new AutoBrand("BMW");
        autoBrand1.setId(1L);
        AutoReleasedYear autoReleasedYear1 = new AutoReleasedYear(LocalDate.parse("2020-01-01"));
        autoReleasedYear1.setId(1L);

        Optional<AutoModel> autoModel = autoModelDAO.
                findAutoModelByTitleAndAutoBrandAndAutoReleasedYear("X5", autoBrand1, autoReleasedYear1);

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
