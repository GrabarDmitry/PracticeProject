package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnnouncementDTOConverter {

    public AnnouncementResponseDTO toDTO(Announcement announcement) {
        log.trace("Convert Announcement with id: {}, to AnnouncementResponseDTO", announcement.getId());
        return new AnnouncementResponseDTO(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getPrice(),
                announcement.getAuto().getAutoModel().getAutoBrand().getTitle(),
                announcement.getAuto().getAutoModel().getTitle(),
                announcement.getAuto().getAutoModel().getAutoReleasedYear().getReleasedYear(),
                announcement.getAuto().getAutoTransmission().getType(),
                announcement.getAuto().getAutoEngine().getType(),
                announcement.getAuto().getEngineCapacity()
        );
    }

    public FullAnnouncementResponseDTO toFullDTO(Announcement announcement) {
        log.trace("Convert Announcement with id: {}, to FullAnnouncementResponseDTO", announcement.getId());
        return new FullAnnouncementResponseDTO(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getDescription(),
                announcement.getPhoneNumber(),
                announcement.getPrice(),
                announcement.getIsActive(),
                announcement.getIsExchange(),
                announcement.getCustomsDuty(),
                announcement.getAuto().getAutoModel().getAutoBrand().getTitle(),
                announcement.getAuto().getAutoModel().getTitle(),
                announcement.getAuto().getAutoModel().getAutoReleasedYear().getReleasedYear(),
                announcement.getAuto().getAutoTransmission().getType(),
                announcement.getAuto().getAutoEngine().getType(),
                announcement.getAuto().getMileage(),
                announcement.getAuto().getEngineCapacity(),
                announcement.getAuto().getVIM(),
                announcement.getUser().getName(),
                announcement.getRegion().getTitle()
        );
    }

}
