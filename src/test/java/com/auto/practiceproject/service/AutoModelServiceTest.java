package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoModelDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.nullable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoModelServiceTest {

  @Autowired private AutoModelService autoModelService;

  @MockBean private AutoModelDAO autoModelDAO;

  private List<AutoModel> autoModelData;

  @Before
  public void setUp() {
    autoModelData = new ArrayList<>();

    AutoModel autoModel1 = new AutoModel();
    autoModel1.setId(1L);
    AutoModel autoModel2 = new AutoModel();
    autoModel2.setId(2L);
    AutoModel autoModel3 = new AutoModel();
    autoModel3.setId(3L);

    autoModelData.add(autoModel1);
    autoModelData.add(autoModel2);
    autoModelData.add(autoModel3);
  }

  @Test
  public void findAllAutoModelsTest() {
    Mockito.when(autoModelDAO.findAll(nullable(Specification.class))).thenReturn(autoModelData);

    List<AutoModel> autoModels = autoModelService.findAllAutoModel(null);

    Assert.assertEquals(autoModels.size(), 3);
    Assert.assertEquals(autoModels.get(0).getId().longValue(), 1L);
    Assert.assertEquals(autoModels.get(1).getId().longValue(), 2L);
    Assert.assertEquals(autoModels.get(2).getId().longValue(), 3L);
  }

  @Test
  public void findAutoModelByIdTest() {
    Mockito.when(autoModelDAO.findById(1l)).thenReturn(Optional.of(autoModelData.get(0)));

    AutoModel autoModel = autoModelService.findAutoModelById(1L);

    Assert.assertNotNull(autoModel);
    Assert.assertEquals(autoModel.getId().longValue(), 1L);
  }

  @Test(expected = ResourceException.class)
  public void findAutoModelByIdIsNullTest() {
    AutoModel autoModel = autoModelService.findAutoModelById(3L);

    Assert.assertNull(autoModel);

    Exception exception =
        assertThrows(ResourceException.class, () -> autoModelService.findAutoModelById(3L));
    Assert.assertTrue(exception.getMessage().contains("Auto model with Id: 3 not found"));
  }

  @Test
  public void findAutoModelTest() {
    Mockito.when(autoModelDAO.findById(1l)).thenReturn(Optional.of(autoModelData.get(0)));

    Optional<AutoModel> autoModel = autoModelService.findAutoModel(1L);
    Assert.assertTrue(autoModel.isPresent());
    Assert.assertEquals(autoModel.get().getId().longValue(), 1l);
  }

  @Test
  public void findAutoModelIsNullTest() {
    Optional<AutoModel> autoModel = autoModelService.findAutoModel(3L);
    Assert.assertTrue(autoModel.isEmpty());
  }
}
