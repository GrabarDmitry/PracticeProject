package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.controller.filter.AutoModelFilter;
import com.auto.practiceproject.controller.filter.FilterFactory;
import com.auto.practiceproject.controller.filter.FilteredService;
import com.auto.practiceproject.dao.AutoModelDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.service.AutoModelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoModelServiceImpl implements AutoModelService, FilteredService {

    private final AutoModelDAO autoModelDAO;
    private final FilterFactory filterFactory;

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    @Override
    public List<AutoModel> findAllAutoModel(String filter) {
        log.trace("Service method called to find all auto models");
        return autoModelDAO.findAll(
                applyFilter(
                        decodeStringFilter(filter).stream()
                                .map(filterFactory.getConverter(AutoModelFilter.class)::convert)
                                .collect(Collectors.toList())
                ));
    }

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

    @Override
    public AutoModel findAutoModelById(Long id) {
        log.info("Service method called to find autoModel with id: {}", id);
        return autoModelDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Auto model with Id: {} not found", id);
                    throw new ResourceException("Auto model with Id: " + id + " not found");
                });
    }

}
