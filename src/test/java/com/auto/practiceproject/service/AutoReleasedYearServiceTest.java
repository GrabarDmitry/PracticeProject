package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoReleasedYearDAO;
import com.auto.practiceproject.exception.ResourceException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoReleasedYearServiceTest {

  @Autowired private AutoReleasedYearService autoReleasedYearService;

  @MockBean private AutoReleasedYearDAO releasedYearDAO;

  private List<AutoReleasedYear> autoReleasedYearData;

  @Before
  public void setUp() {
    autoReleasedYearData = new ArrayList<>();

    AutoReleasedYear autoReleasedYear1 = new AutoReleasedYear();
    autoReleasedYear1.setId(1L);
    AutoReleasedYear autoReleasedYear2 = new AutoReleasedYear();
    autoReleasedYear2.setId(2L);

    autoReleasedYearData.add(autoReleasedYear1);
    autoReleasedYearData.add(autoReleasedYear2);
  }

  @Test
  public void findAutoReleasedYearByIdTest() {
    Mockito.when(releasedYearDAO.findById(1l)).thenReturn(Optional.of(autoReleasedYearData.get(0)));

    AutoReleasedYear autoReleasedYear = autoReleasedYearService.findAutoReleasedYearById(1L);

    Assert.assertNotNull(autoReleasedYear);
    Assert.assertEquals(autoReleasedYear.getId().longValue(), 1L);
  }

  @Test(expected = ResourceException.class)
  public void findAutoReleasedYearByIdIsNullTest() {
    AutoReleasedYear autoReleasedYear = autoReleasedYearService.findAutoReleasedYearById(3L);

    Assert.assertNull(autoReleasedYear);

    Exception exception =
        assertThrows(
            ResourceException.class, () -> autoReleasedYearService.findAutoReleasedYearById(3L));
    Assert.assertTrue(exception.getMessage().contains("Auto released year with Id: 3 not found"));
  }
}
