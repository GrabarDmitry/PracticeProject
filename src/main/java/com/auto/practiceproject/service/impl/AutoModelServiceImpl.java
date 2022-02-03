package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoModelDAO;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.service.AutoModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoModelServiceImpl implements AutoModelService {

    private final AutoModelDAO autoModelDAO;

    @Override
    public Optional<AutoModel> findAutoModelByTitle(String title) {
        log.trace("Service method called to view auto model with title: {}", title);
        return autoModelDAO.findAutoModelByTitle(title);
    }

    @Override
    public Optional<AutoModel> findAutoModel(Long id) {
        log.trace("Service method called to view Auto model with id: {}", id);
        return autoModelDAO.findById(id);
    }

}
