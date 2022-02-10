package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoBrandDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoBrand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoBrandServiceTest {

    @Autowired
    private AutoBrandService autoBrandService;

    @MockBean
    private AutoBrandDAO autoBrandDAO;

    private List<AutoBrand> autoBrandData;

    @Before
    public void setUp() {
        autoBrandData = new ArrayList<>();

        AutoBrand autoBrand1 = new AutoBrand();
        autoBrand1.setId(1L);
        AutoBrand autoBrand2 = new AutoBrand();
        autoBrand2.setId(2L);
        AutoBrand autoBrand3 = new AutoBrand();
        autoBrand3.setId(3L);

        autoBrandData.add(autoBrand1);
        autoBrandData.add(autoBrand2);
        autoBrandData.add(autoBrand3);
    }

    @Test
    public void findAllAutoBrand() {
        Mockito.when(autoBrandDAO.findAll())
                .thenReturn(autoBrandData);

        List<AutoBrand> autoBrands = autoBrandService.findAll();

        Assert.assertEquals(autoBrands.size(), 3);
        Assert.assertEquals(autoBrands.get(0).getId().longValue(), 1L);
        Assert.assertEquals(autoBrands.get(1).getId().longValue(), 2L);
        Assert.assertEquals(autoBrands.get(2).getId().longValue(), 3L);
    }

    @Test
    public void findAutoBrandByIdTest() {
        Mockito.when(autoBrandDAO.findById(1l))
                .thenReturn(Optional.of(autoBrandData.get(0)));

        AutoBrand autoBrand = autoBrandService.findAutoBrandById(1L);

        Assert.assertNotNull(autoBrand);
        Assert.assertEquals(autoBrand.getId().longValue(), 1L);
    }

    @Test(expected = ResourceException.class)
    public void findAutoBrandByIdIsNullTest() {
        AutoBrand autoBrand = autoBrandService.findAutoBrandById(3l);

        Assert.assertNull(autoBrand);

        Exception exception = assertThrows(
                ResourceException.class,
                () -> autoBrandService.findAutoBrandById(3L));
        Assert.assertTrue(exception.getMessage().contains(
                "Auto brand with Id: 3 not found"
        ));
    }

}
