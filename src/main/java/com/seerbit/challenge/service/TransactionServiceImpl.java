package com.seerbit.challenge.service;

import com.seerbit.challenge.requests.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final List<TransactionRequest> transactionRequestList = new ArrayList<>();

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        //Add request to in-memory List
        transactionRequestList.add(transactionRequest);
    }
}
