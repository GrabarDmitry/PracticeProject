package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.RegionDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Region;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @MockBean
    private RegionDAO regionDAO;

    private List<Region> regionData;

    @Before
    public void setUp() {
        regionData = new ArrayList<>();

        Region region1 = new Region();
        region1.setId(1L);
        Region region2 = new Region();
        region2.setId(2L);
        Region region3 = new Region();
        region3.setId(3L);

        regionData.add(region1);
        regionData.add(region2);
        regionData.add(region3);
    }

    @Test
    public void findAllRegionsTest() {
        Mockito.when(regionDAO.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl(regionData.subList(0, 3)));

        Pageable pageable = Mockito.mock(Pageable.class);

        Page<Region> regionsPage = regionService.findAllRegion(pageable);
        List<Region> regions = regionsPage.getContent();

        Assert.assertEquals(regions.size(), 3);
        Assert.assertEquals(regions.get(0).getId().longValue(), 1L);
        Assert.assertEquals(regions.get(1).getId().longValue(), 2L);
        Assert.assertEquals(regions.get(2).getId().longValue(), 3L);
    }

    @Test
    public void findRegionByIdTest() {
        Mockito.when(regionDAO.findById(1l))
                .thenReturn(Optional.of(regionData.get(0)));

        Region region = regionService.findRegionById(1L);

        Assert.assertNotNull(region);
        Assert.assertEquals(region.getId().longValue(), 1L);
    }

    @Test(expected = ResourceException.class)
    public void findRegionByIdIsNullTest() {
        Region region = regionService.findRegionById(3L);

        Assert.assertNull(region);

        Exception exception = assertThrows(
                ResourceException.class,
                () -> regionService.findRegionById(3L));
        Assert.assertTrue(exception.getMessage().contains(
                "Region with Id: 3 not found"
        ));
    }

    @Test
    public void findRegionTest() {
        Mockito.when(regionDAO.findById(1l))
                .thenReturn(Optional.of(regionData.get(0)));

        Optional<Region> region = regionService.findRegion(1L);
        Assert.assertTrue(region.isPresent());
        Assert.assertEquals(region.get().getId().longValue(), 1l);
    }

    @Test
    public void findRegionIsNullTest() {
        Optional<Region> region = regionService.findRegion(3L);
        Assert.assertTrue(region.isEmpty());
    }

}
