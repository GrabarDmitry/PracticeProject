package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.RegionDAO;
import com.auto.practiceproject.model.Region;
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
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    @MockBean
    private RegionDAO regionDAO;

}
