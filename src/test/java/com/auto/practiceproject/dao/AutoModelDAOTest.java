package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoModel;
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

import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application_dao_test.properties")
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
        testEntityManager.persistAndFlush(new AutoModel("100", null, null));
        testEntityManager.persistAndFlush(new AutoModel("X5", null, null));
        testEntityManager.persistAndFlush(new AutoModel("80", null, null));
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

}
