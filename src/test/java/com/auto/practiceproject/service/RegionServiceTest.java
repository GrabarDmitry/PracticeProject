package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.RegionDAO;
import com.auto.practiceproject.model.Region;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @MockBean
    private RegionDAO regionDAO;

    @Before
    public void setUp() {
        Region region = new Region("Brest");
        region.setId(1l);

        Mockito.when(regionDAO.findRegionByTitle("Brest"))
                .thenReturn(Optional.of(region));

    }

    @Test
    public void findRegionTest() {
        Optional<Region> region = regionService.findRegionByTitle("Brest");
        Assert.assertTrue(region.isPresent());
        Assert.assertEquals(region.get().getTitle(), "Brest");
    }

    @Test
    public void findRegionIsNullTest() {
        Optional<Region> region = regionService.findRegionByTitle("Minsk");
        Assert.assertTrue(region.isEmpty());
    }

}
