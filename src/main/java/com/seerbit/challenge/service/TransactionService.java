package com.seerbit.challenge.service;

import com.seerbit.challenge.requests.TransactionRequest;
import com.seerbit.challenge.response.StatisticsResponse;

import java.util.List;

public interface TransactionService {

    void saveTransaction(TransactionRequest transactionRequest);

    StatisticsResponse getStatistics();

    List<TransactionRequest> fetchAllTransactionList();
}
