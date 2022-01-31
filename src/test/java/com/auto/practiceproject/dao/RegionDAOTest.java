package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Region;
import com.auto.practiceproject.model.Role;
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
public class RegionDAOTest {

    @Autowired
    private RegionDAO regionDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        testEntityManager.persistAndFlush(new Region("Brest"));
        testEntityManager.persistAndFlush(new Region("Minsk"));
        testEntityManager.persistAndFlush(new Role("Gomel"));
    }

    @Test
    public void findRegionByTileTest() {
        Optional<Region> region = regionDAO.findRegionByTitle("Minsk");

        Assert.assertTrue(region.isPresent());
        Assert.assertEquals(region.get().getTitle(), "Minsk");
    }

    @Test
    public void findRegionByTitleIsNullTest() {
        Optional<Region> region = regionDAO.findRegionByTitle("Test");
        Assert.assertTrue(region.isEmpty());

    }

}
