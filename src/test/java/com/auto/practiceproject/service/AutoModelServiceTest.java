package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoModelDAO;
import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.model.AutoReleasedYear;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoModelServiceTest {

    @Autowired
    private AutoModelService autoModelService;

    @MockBean
    private AutoModelDAO autoModelDAO;

//    @Before
//    public void setUp() {
//        AutoModel autoModel = new AutoModel("X5", new AutoBrand("BMW"),
//                new AutoReleasedYear(LocalDate.parse("2020-01-01")));
//
//        Mockito.when(autoModelDAO.findAutoModelByTitle("X5"))
//                .thenReturn(Optional.of(autoModel));
//    }
//
//    @Test
//    public void findAutoModelByTitleTest() {
//        Optional<AutoModel> autoModel = autoModelService.findAutoModelByTitle("X5");
//        Assert.assertTrue(autoModel.isPresent());
//        Assert.assertEquals(autoModel.get().getTitle(), "X5");
//    }
//
//    @Test
//    public void findAutoModelByTitleNullIsTest() {
//        Optional<AutoModel> autoModel = autoModelService.findAutoModelByTitle("X7");
//        Assert.assertTrue(autoModel.isEmpty());
//    }

}