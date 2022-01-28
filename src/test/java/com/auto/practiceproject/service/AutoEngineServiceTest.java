package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoEngineDAO;
import com.auto.practiceproject.model.AutoEngine;
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
public class AutoEngineServiceTest {

    @Autowired
    private AutoEngineService autoEngineService;

    @MockBean
    private AutoEngineDAO autoEngineDAO;

    @Before
    public void setUp() {
        AutoEngine autoEngine = new AutoEngine("Electro");

        Mockito.when(autoEngineDAO.findAutoEngineByType("Electro"))
                .thenReturn(Optional.of(autoEngine));

    }

    @Test
    public void findAutoEngineTest() {
        Optional<AutoEngine> autoEngine = autoEngineService.findAutoEngineByType("Electro");
        Assert.assertTrue(autoEngine.isPresent());
        Assert.assertEquals(autoEngine.get().getType(), "Electro");
    }

    @Test
    public void findAutoEngineIsNullTest() {
        Optional<AutoEngine> autoEngine = autoEngineService.findAutoEngineByType("Diesel");
        Assert.assertTrue(autoEngine.isEmpty());
    }

}
