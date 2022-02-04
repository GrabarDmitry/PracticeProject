package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoTransmissionDAO;
import com.auto.practiceproject.model.AutoTransmission;
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
public class AutoTransmissionServiceTest {

    @Autowired
    private AutoTransmissionService autoTransmissionService;

    @MockBean
    private AutoTransmissionDAO transmissionDAO;

}