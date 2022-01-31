package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
        locations = "classpath:application-test.properties")
public class AutoEngineDAOTest {

    @Autowired
    private AutoEngineDAO autoEngineDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findAutoEngineByTypeTest() {
        testEntityManager.persistAndFlush(new AutoEngine("Petrol"));
        testEntityManager.persistAndFlush(new AutoEngine("Diesel"));
        testEntityManager.persistAndFlush(new AutoEngine("Electro"));

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
