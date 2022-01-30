package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoEngine;
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
public class AutoEngineDAOTest {

    @Autowired
    private AutoEngineDAO autoEngineDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        testEntityManager.persist(new AutoEngine("Petrol"));
        testEntityManager.persist(new AutoEngine("Diesel"));
        testEntityManager.persist(new AutoEngine("Electro"));
    }

    @Test
    public void findAutoEngineByTypeTest() {
        Optional<AutoEngine> autoEngine = autoEngineDAO.findAutoEngineByType("Diesel");

        Assert.assertTrue(autoEngine.isPresent());
        Assert.assertEquals(autoEngine.get().getType(), "Diesel");
    }

    @Test
    public void findAutoEngineByTypeIsNullTest() {
        Optional<AutoEngine> autoEngine = autoEngineDAO.findAutoEngineByType("Test");
        Assert.assertTrue(autoEngine.isEmpty());

    }

}
