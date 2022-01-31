package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoTransmission;
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
public class AutoTransmissionDAOTest {

    @Autowired
    private AutoTransmissionDAO autoTransmissionDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        testEntityManager.persistAndFlush(new AutoTransmission("Automatic"));
        testEntityManager.persistAndFlush(new AutoTransmission("Manual"));
    }

    @Test
    public void findAutoTransmissionByTypeTest() {
        Optional<AutoTransmission> autoTransmission = autoTransmissionDAO.
                findAutoTransmissionByType("Manual");

        Assert.assertTrue(autoTransmission.isPresent());
        Assert.assertEquals(autoTransmission.get().getType(), "Manual");
    }

    @Test
    public void findAutoTransmissionByTypeIsNullTest() {
        Optional<AutoTransmission> autoTransmission =
                autoTransmissionDAO.findAutoTransmissionByType("Test");
        Assert.assertTrue(autoTransmission.isEmpty());
    }

}
