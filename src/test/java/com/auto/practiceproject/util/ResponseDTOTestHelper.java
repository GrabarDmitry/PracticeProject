package com.auto.practiceproject.util;

import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Component
public class ResponseDTOTestHelper {

    public ResultActions announcementResponseDTOCheck(ResultActions resultActions, AnnouncementResponseDTO responseDTO) throws Exception {
        return resultActions.andExpect(jsonPath("$.id").value(responseDTO.getId()))
                .andExpect(jsonPath("$.title").value(responseDTO.getTitle()))
                .andExpect(jsonPath("$.description").value(responseDTO.getDescription()))
                .andExpect(jsonPath("$.phoneNumber").value(responseDTO.getPhoneNumber()))
                .andExpect(jsonPath("$.price").value(responseDTO.getPrice()))
                .andExpect(jsonPath("$.isActive").value(responseDTO.getIsActive()))
                .andExpect(jsonPath("$.isExchange").value(responseDTO.getIsActive()))
                .andExpect(jsonPath("$.customsDuty").value(responseDTO.getCustomsDuty()))
                .andExpect(jsonPath("$.userId").value(responseDTO.getUserId()))
                .andExpect(jsonPath("$.regionId").value(responseDTO.getRegionId()))
                .andExpect(jsonPath("$.autoId").value(responseDTO.getAutoId()));
    }

    public ResultActions userResponseDTOCheck(ResultActions resultActions, UserResponseDTO userResponseDTO) throws Exception {
        return resultActions.andExpect(jsonPath("$.id").value(userResponseDTO.getId()))
                .andExpect(jsonPath("$.email").value(userResponseDTO.getEmail()))
                .andExpect(jsonPath("$.name").value(userResponseDTO.getName()))
                .andExpect(jsonPath("$.surname").value(userResponseDTO.getSurname()))
                .andExpect(jsonPath("$.walletId").value(userResponseDTO.getWalletId()));
    }

}
