package com.auto.practiceproject.controller.converter;

import com.auto.practiceproject.controller.dto.request.AnnouncementActiveChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementModerationChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.FullAnnouncementResponseDTO;
import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.model.Auto;
import com.auto.practiceproject.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnnouncementDTOConverter {

    private final UserService userService;
    private final RegionService regionService;
    private final AutoEngineService autoEngineService;
    private final AutoTransmissionService autoTransmissionService;
    private final AutoModelService autoModelService;
    private final AutoReleasedYearService autoReleasedYearService;
    private final AutoBrandService autoBrandService;
    private final AnnouncementService announcementService;

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

    public Announcement toEntity(AnnouncementRequestDTO createDTO) {
        log.trace("AnnouncementRequestDTO: {}, to Announcement", createDTO);
        return new Announcement(
                createDTO.getBrand() + createDTO.getModel(),
                createDTO.getDescription(),
                createDTO.getPhoneNumber(),
                createDTO.getPrice(),
                true,
                true,
                1,
                5.0,
                createDTO.getIsExchange(),
                createDTO.getCustomsDuty(),
                new Auto(
                        createDTO.getMileage(),
                        createDTO.getEngineCapacity(),
                        createDTO.getVim(),
                        autoModelService.findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
                                createDTO.getModel(),
                                autoBrandService.findAutoBrandByTitle(createDTO.getBrand())
                                        .orElse(null),
                                autoReleasedYearService.findAutoReleasedYearByReleased(createDTO.getReleasedYear())
                                        .orElse(null)
                        ).orElse(null),
                        autoEngineService.findAutoEngineByType(createDTO.getEngine())
                                .orElse(null),
                        autoTransmissionService.findAutoTransmissionByType(createDTO.getTransmission())
                                .orElse(null)
                ),
                userService.getCurrentUser()
                        .orElse(null),
                regionService.findRegionByTitle(createDTO.getRegion())
                        .orElse(null)
        );
    }

    public Announcement updateToEntity(
            Long id,
            AnnouncementRequestDTO updateDTO
    ) {
        log.trace("AnnouncementRequestDTO: {}, to Announcement", updateDTO);
        Announcement announcement = toEntity(updateDTO);
        announcement.setId(id);
        return announcement;
    }

    public Announcement toDTOWithEditedIsExchange(
            Announcement announcement,
            AnnouncementActiveChangeDTO activeChangeDTO
    ) {
        log.trace("AnnouncementActivityChangeDTO: {}, to Announcement", activeChangeDTO);
        announcement.setIsExchange(activeChangeDTO.getIsExchange());
        return announcement;
    }

    public Announcement toDTOWithEditedIsModeration(
            Announcement announcement,
            AnnouncementModerationChangeDTO moderationChangeDTO
    ) {
        log.trace("AnnouncementModerationChangeDTO: {}, to announcement", moderationChangeDTO);
        announcement.setIsModeration(moderationChangeDTO.getIsModeration());
        return announcement;
    }

}
