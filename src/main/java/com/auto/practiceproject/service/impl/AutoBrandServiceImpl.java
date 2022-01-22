package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoBrandDAO;
import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.service.AutoBrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoBrandServiceImpl implements AutoBrandService {

    private final AutoBrandDAO autoBrandDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoBrand> findAutoBrandByTitle(String title) {
        log.trace("Service method called to view auto brand with title : {}", title);
        return autoBrandDAO.findAutoBrandByTitle(title);
    }

}
