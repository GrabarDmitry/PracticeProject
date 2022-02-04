package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.RegionDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Region;
import com.auto.practiceproject.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class RegionServiceImpl implements RegionService {

    private final RegionDAO regionDAO;

    @Override
    public Optional<Region> findRegion(Long id) {
        log.trace("Service method called to view Region with id: {}", id);
        return regionDAO.findById(id);
    }

    @Override
    public Page<Region> findAllRegion(Pageable pageable) {
        log.trace("Service method called to find all regions with params: {}", pageable);
        return regionDAO.findAll(pageable);
    }

    @Override
    public Region findRegionById(Long id) {
        log.info("Service method called to find region with id: {}", id);
        return regionDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Region with Id: {} not found", id);
                    throw new ResourceException("Region with Id: " + id + " not found");
                });
    }

}
