package com.seerbit.challenge.service;

import com.seerbit.challenge.requests.TransactionRequest;
import com.seerbit.challenge.response.StatisticsResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final List<TransactionRequest> transactionRequestList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) {
        //Add request to in-memory List
        transactionRequestList.add(transactionRequest);
    }

    @Override
    public StatisticsResponse getStatistics() {
        if(transactionRequestList.isEmpty())
            return new StatisticsResponse();

        final BigDecimal[] max = {new BigDecimal("0.00")};
        final BigDecimal[] min = { null };
        final BigDecimal[] sum = { new BigDecimal("0.00") };

        final long[] count = {0};

        transactionRequestList.stream()
                .filter(tran -> tran.getTimestamp().isAfter(LocalDateTime.now().minusSeconds(30)))
                .forEach(tran -> {
                    if(tran.getAmount().compareTo(max[0]) > 0)
                        max[0] = tran.getAmount();

                    if(min[0] == null || tran.getAmount().compareTo(min[0]) < 0)
                        min[0] = tran.getAmount();

                    sum[0] = sum[0].add(tran.getAmount());
                    ++count[0];
                });

        max[0] = max[0].setScale(2, RoundingMode.HALF_UP);
        min[0] = min[0].setScale(2, RoundingMode.HALF_UP);
        sum[0] = sum[0].setScale(2, RoundingMode.HALF_UP);

        BigDecimal avg = count[0] != 0 ? sum[0].divide(BigDecimal.valueOf(count[0]), RoundingMode.HALF_UP) : new BigDecimal("0.00");
        return StatisticsResponse.builder()
                .max(max[0]).min(min[0]).avg(avg)
                .sum(sum[0]).count(count[0]).build();
    }

    @Override
    public List<TransactionRequest> fetchAllTransactionList() {
        return this.transactionRequestList;
    }

    @Override
    public void deleteTransactions() {
        this.transactionRequestList.clear();
    }
}
