package com.auto.practiceproject.service;

import com.auto.practiceproject.controller.dto.request.MailDataDTO;

public interface MailService {

    void sendDataToEmail(MailDataDTO mailDataDTO);

}
