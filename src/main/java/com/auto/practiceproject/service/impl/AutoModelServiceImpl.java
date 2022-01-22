package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoModelDAO;
import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.model.AutoReleasedYear;
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
public class AutoModelServiceImpl implements AutoModelService {

    private final AutoModelDAO autoModelDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoModel> findAutoModelByTitle(String title) {
        log.trace("Service method called to view auto model with title: {}", title);
        return autoModelDAO.findAutoModelByTitle(title);
    }

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoModel> findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
            String title,
            AutoBrand brand,
            AutoReleasedYear autoReleasedYear
    ) {
        log.trace("Service method called to view Auto model with brand: {}, model: {}, released year: {}",
                brand.getTitle(),
                title,
                autoReleasedYear.getReleasedYear().getYear());
        return autoModelDAO
                .findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
                        title,
                        brand,
                        autoReleasedYear
                );
    }

}
