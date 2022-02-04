package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AutoModelResponseDTO;
import com.auto.practiceproject.model.AutoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutoModelDTOConverter {

    public AutoModelResponseDTO toDTO(AutoModel autoModel) {
        log.trace("Convert AutoModel: {}, to AutoModelResponseDTO", autoModel);
        return new AutoModelResponseDTO(
                autoModel.getId(),
                autoModel.getTitle(),
                autoModel.getAutoBrand().getId(),
                autoModel.getAutoReleasedYear().getId()
        );
    }

}
