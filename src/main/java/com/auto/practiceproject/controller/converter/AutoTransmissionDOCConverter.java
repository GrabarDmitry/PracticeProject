package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoTransmissionResponseDTO;
import com.auto.practiceproject.model.AutoTransmission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoTransmissionDOCConverter {

    public AutoTransmissionResponseDTO toDTO(AutoTransmission autoTransmission) {
        log.trace("Convert AutoTransmission: {}, to AutoTransmissionResponseDTO", autoTransmission);
        return new AutoTransmissionResponseDTO(
                autoTransmission.getId(),
                autoTransmission.getType()
        );
    }

}
