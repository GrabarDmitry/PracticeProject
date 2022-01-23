package com.auto.practiceproject.service;

import com.auto.practiceproject.controller.dto.MailDataDTO;

public interface MailService {

    void sendDataToEmail(MailDataDTO mailDataDTO);

}
