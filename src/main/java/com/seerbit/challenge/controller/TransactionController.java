package com.seerbit.challenge.controller;

import com.seerbit.challenge.requests.TransactionRequest;
import com.seerbit.challenge.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<?> postTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        if(transactionRequest.getTimestamp().isBefore(LocalDateTime.now().minusSeconds(30)))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        //Only save transactions completed less than 30seconds
        transactionService.saveTransaction(transactionRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/statistics")
    public void getTransactionStatistics(){

    }

    @DeleteMapping
    public void deleteTransaction(){

    }
}
