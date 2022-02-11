package com.auto.practiceproject.util;

import com.auto.practiceproject.controller.dto.request.MoneyTransferDTO;
import org.springframework.stereotype.Component;

@Component
public class ParseToJSONHelper {

  public String moneyTransferDTOToJSON(MoneyTransferDTO moneyTransferDTO) {
    return new String(
            "{\"cartNumber\": \""
                + moneyTransferDTO.getCartNumber()
                + "\", \"cardExpiryDate\":\""
                + moneyTransferDTO.getCardExpiryDate().toString()
                + "\", \"amount\":"
                + moneyTransferDTO.getAmount())
        + "}";
  }
}
