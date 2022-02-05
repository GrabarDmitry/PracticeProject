package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Auto;
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
public class AutoServiceTest {

    @Autowired
    private AutoService autoService;

    @MockBean
    private AutoDAO autoDAO;

    private List<Auto> autoData;

    @Before
    public void setUp() {
        autoData = new ArrayList<>();

        Auto auto1 = new Auto();
        auto1.setId(1L);
        Auto auto2 = new Auto();
        auto2.setId(2L);

        autoData.add(auto1);
        autoData.add(auto2);
    }

    @Test
    public void findAutoByIdTest() {
        Mockito.when(autoDAO.findById(1l))
                .thenReturn(Optional.of(autoData.get(0)));

        Auto auto = autoService.findAutoById(1L);

        Assert.assertNotNull(auto);
        Assert.assertEquals(auto.getId().longValue(), 1L);
    }

    @Test(expected = ResourceException.class)
    public void findAutoByIdIsNullTest() {
        Auto auto = autoService.findAutoById(3L);

        Assert.assertNull(auto);

        Exception exception = assertThrows(
                ResourceException.class,
                () -> autoService.findAutoById(3L));
        Assert.assertTrue(exception.getMessage().contains(
                "Auto with Id: 3 not found"
        ));
    }

}
