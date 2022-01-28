package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoBrandDAO;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoBrandServiceTest {

    @Autowired
    private AutoBrandService autoBrandService;

    @MockBean
    private AutoBrandDAO autoBrandDAO;

    @Before
    public void setUp() {
        AutoBrand autoBrand = new AutoBrand("BMW");
        autoBrand.setId(1l);

        Mockito.when(autoBrandDAO.findAutoBrandByTitle("BMW"))
                .thenReturn(Optional.of(autoBrand));

    }

    @Test
    public void findAutoBrandTest() {
        Optional<AutoBrand> autoBrand = autoBrandService.findAutoBrandByTitle("BMW");
        Assert.assertTrue(autoBrand.isPresent());
        Assert.assertEquals(autoBrand.get().getTitle(), "BMW");
    }

    @Test
    public void findAutoBrandIsNullTest() {
        Optional<AutoBrand> autoBrand = autoBrandService.findAutoBrandByTitle("AUDI");
        Assert.assertTrue(autoBrand.isEmpty());
    }

}
