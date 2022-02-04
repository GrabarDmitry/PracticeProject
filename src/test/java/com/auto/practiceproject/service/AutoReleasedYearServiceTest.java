package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.AutoReleasedYearDAO;
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
public class AutoReleasedYearServiceTest {

    @Autowired
    private AutoReleasedYearService autoReleasedYearService;

    @MockBean
    private AutoReleasedYearDAO releasedYearDAO;

}