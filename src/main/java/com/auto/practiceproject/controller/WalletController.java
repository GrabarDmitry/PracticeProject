package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.MoneyTransferDTO;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@Slf4j
public class WalletController {

    private final WalletService walletService;

    @PostMapping
    public ResponseEntity<HttpStatus> putMoneyToWallet(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody @Valid MoneyTransferDTO moneyTransferDTO
    ) {
        log.trace("Controller method called to put money on wallet, user id: {}",
                userDetails.getUser().getId());
        walletService.putMoney(moneyTransferDTO.getAmount(), userDetails.getUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
