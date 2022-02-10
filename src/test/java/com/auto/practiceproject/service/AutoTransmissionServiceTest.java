//package com.auto.practiceproject.service;
//
//import com.auto.practiceproject.dao.AutoTransmissionDAO;
//import com.auto.practiceproject.exception.ResourceException;
//import com.auto.practiceproject.model.AutoTransmission;
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
//public class AutoTransmissionServiceTest {
//
//    @Autowired
//    private AutoTransmissionService autoTransmissionService;
//
//    @MockBean
//    private AutoTransmissionDAO transmissionDAO;
//
//    private List<AutoTransmission> autoTransmissionData;
//
//    @Before
//    public void setUp() {
//        autoTransmissionData = new ArrayList<>();
//
//        AutoTransmission autoTransmission1 = new AutoTransmission();
//        autoTransmission1.setId(1L);
//        AutoTransmission autoTransmission2 = new AutoTransmission();
//        autoTransmission2.setId(2L);
//
//        autoTransmissionData.add(autoTransmission1);
//        autoTransmissionData.add(autoTransmission2);
//    }
//
//    @Test
//    public void findAllAutoTransmission() {
//        Mockito.when(transmissionDAO.findAll())
//                .thenReturn(autoTransmissionData);
//
//        List<AutoTransmission> autoTransmissions = autoTransmissionService.findAllAutoTransmission();
//
//        Assert.assertEquals(autoTransmissions.size(), 2);
//        Assert.assertEquals(autoTransmissions.get(0).getId().longValue(), 1L);
//        Assert.assertEquals(autoTransmissions.get(1).getId().longValue(), 2L);
//    }
//
//    @Test
//    public void findAutoTransmissionByIdTest() {
//        Mockito.when(transmissionDAO.findById(1l))
//                .thenReturn(Optional.of(autoTransmissionData.get(0)));
//
//        AutoTransmission autoTransmission = autoTransmissionService.findAutoTransmissionById(1L);
//
//        Assert.assertNotNull(autoTransmission);
//        Assert.assertEquals(autoTransmission.getId().longValue(), 1L);
//    }
//
//    @Test(expected = ResourceException.class)
//    public void findAutoTransmissionByIdIsNullTest() {
//        AutoTransmission autoTransmission = autoTransmissionService.findAutoTransmissionById(3L);
//
//        Assert.assertNull(autoTransmission);
//
//        Exception exception = assertThrows(
//                ResourceException.class,
//                () -> autoTransmissionService.findAutoTransmissionById(3L));
//        Assert.assertTrue(exception.getMessage().contains(
//                "Auto transmission with Id: 3 not found"
//        ));
//    }
//
//    @Test
//    public void findAutoTransmissionTest() {
//        Mockito.when(transmissionDAO.findById(1l))
//                .thenReturn(Optional.of(autoTransmissionData.get(0)));
//
//        Optional<AutoTransmission> autoTransmission = autoTransmissionService.findAutoTransmission(1L);
//        Assert.assertTrue(autoTransmission.isPresent());
//        Assert.assertEquals(autoTransmission.get().getId().longValue(), 1l);
//    }
//
//    @Test
//    public void findAutoTransmissionIsNullTest() {
//        Optional<AutoTransmission> autoTransmission = autoTransmissionService.findAutoTransmission(3L);
//        Assert.assertTrue(autoTransmission.isEmpty());
//    }
//
//}