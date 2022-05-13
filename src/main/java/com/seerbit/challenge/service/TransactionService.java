package com.seerbit.challenge.service;

import com.seerbit.challenge.requests.TransactionRequest;

public interface TransactionService {

    void saveTransaction(TransactionRequest transactionRequest);

}
