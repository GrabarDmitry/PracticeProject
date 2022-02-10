//package com.auto.practiceproject.service;
//
//import com.auto.practiceproject.dao.AutoEngineDAO;
//import com.auto.practiceproject.exception.ResourceException;
//import com.auto.practiceproject.model.AutoEngine;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertThrows;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AutoEngineServiceTest {
//
//    @Autowired
//    private AutoEngineService autoEngineService;
//
//    @MockBean
//    private AutoEngineDAO autoEngineDAO;
//
//    private List<AutoEngine> autoEngineData;
//
//    @Before
//    public void setUp() {
//        autoEngineData = new ArrayList<>();
//
//        AutoEngine autoBrand1 = new AutoEngine();
//        autoBrand1.setId(1L);
//        AutoEngine autoBrand2 = new AutoEngine();
//        autoBrand2.setId(2L);
//        AutoEngine autoBrand3 = new AutoEngine();
//        autoBrand3.setId(3L);
//
//        autoEngineData.add(autoBrand1);
//        autoEngineData.add(autoBrand2);
//        autoEngineData.add(autoBrand3);
//    }
//
//    @Test
//    public void findAllAutoEngines() {
//        Mockito.when(autoEngineDAO.findAll())
//                .thenReturn(autoEngineData);
//
//        List<AutoEngine> autoEngines = autoEngineService.findAllAutoEngine();
//
//        Assert.assertEquals(autoEngines.size(), 3);
//        Assert.assertEquals(autoEngines.get(0).getId().longValue(), 1L);
//        Assert.assertEquals(autoEngines.get(1).getId().longValue(), 2L);
//        Assert.assertEquals(autoEngines.get(2).getId().longValue(), 3L);
//    }
//
//    @Test
//    public void findAutoEngineByIdTest() {
//        Mockito.when(autoEngineDAO.findById(1l))
//                .thenReturn(Optional.of(autoEngineData.get(0)));
//
//        AutoEngine autoEngine = autoEngineService.findAutoEngineById(1L);
//
//        Assert.assertNotNull(autoEngine);
//        Assert.assertEquals(autoEngine.getId().longValue(), 1L);
//    }
//
//    @Test(expected = ResourceException.class)
//    public void findAutoBrandByIdIsNullTest() {
//        AutoEngine autoEngine = autoEngineService.findAutoEngineById(3L);
//
//        Assert.assertNull(autoEngine);
//
//        Exception exception = assertThrows(
//                ResourceException.class,
//                () -> autoEngineService.findAutoEngineById(3L));
//        Assert.assertTrue(exception.getMessage().contains(
//                "Auto engine with Id: 3 not found"
//        ));
//    }
//
//    @Test
//    public void findAutoEngineTest() {
//        Mockito.when(autoEngineDAO.findById(1l))
//                .thenReturn(Optional.of(autoEngineData.get(0)));
//
//        Optional<AutoEngine> autoEngine = autoEngineService.findAutoEngine(1L);
//        Assert.assertTrue(autoEngine.isPresent());
//        Assert.assertEquals(autoEngine.get().getId().longValue(), 1l);
//    }
//
//    @Test
//    public void findAutoEngineIsNullTest() {
//        Optional<AutoEngine> autoEngine = autoEngineService.findAutoEngine(3L);
//        Assert.assertTrue(autoEngine.isEmpty());
//    }
//
//}
