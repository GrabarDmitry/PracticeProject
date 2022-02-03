package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.controller.dto.request.MailDataDTO;
import com.auto.practiceproject.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendDataToEmail(MailDataDTO mailDataDTO) {
        log.info("Service method called to send data on email: {}", mailDataDTO.getRecipient());
        MimeMessagePreparator messageProperties = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("auto.podbor@mail.com");
            messageHelper.setSubject(mailDataDTO.getSubject());
            messageHelper.setTo(mailDataDTO.getRecipient());
            messageHelper.setText(mailDataDTO.getBody());
        };
        mailSender.send(messageProperties);
    }

}
