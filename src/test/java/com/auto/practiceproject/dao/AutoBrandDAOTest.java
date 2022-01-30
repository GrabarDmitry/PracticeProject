package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutoBrandDAOTest {

    @Autowired
    private AutoBrandDAO autoBrandDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        testEntityManager.persistAndFlush(new AutoBrand("BMW"));
        testEntityManager.persistAndFlush(new AutoBrand("Audi"));
        testEntityManager.persistAndFlush(new AutoBrand("Mercedes"));
    }

    @Test
    public void findAutoBrandByTitleTest() {
        Optional<AutoBrand> autoBrand = autoBrandDAO.findAutoBrandByTitle("BMW");

        Assert.assertTrue(autoBrand.isPresent());
        Assert.assertEquals(autoBrand.get().getTitle(), "BMW");
    }

    @Test
    public void findAutoBrandByTitleIsNullTest() {
        Optional<AutoBrand> autoBrand = autoBrandDAO.findAutoBrandByTitle("Volkswagen");
        Assert.assertTrue(autoBrand.isEmpty());
    }

}
