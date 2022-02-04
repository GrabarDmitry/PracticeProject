package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoReleasedYearResponseDTO;
import com.auto.practiceproject.model.AutoReleasedYear;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoReleasedTearDTOConverter {

    public AutoReleasedYearResponseDTO toDTO(AutoReleasedYear autoReleasedYear) {
        log.trace("Convert AutoReleasedYear: {}, to AutoReleasedResponseDTO", autoReleasedYear);
        return new AutoReleasedYearResponseDTO(
                autoReleasedYear.getId(),
                autoReleasedYear.getReleasedYear()
        );
    }

}
