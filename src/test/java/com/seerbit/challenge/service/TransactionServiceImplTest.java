package com.seerbit.challenge.service;

import com.seerbit.challenge.requests.TransactionRequest;
import com.seerbit.challenge.response.StatisticsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.seerbit.challenge.helper.TestHelper.formRequest;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        this.transactionService = new TransactionServiceImpl();
    }

    @AfterEach
    void tearDown() {
        this.transactionService.fetchAllTransactionList().clear();
    }

    @Test
    void shouldIncrementListSize_onPostTransaction() {
        populateListAndAssert();
    }

    @Test
    void shouldReturnData_onGetStatistics() throws InterruptedException {
        populateListAndAssert();

        // Wait for 7 seconds, this will make some transactions in the requestList stale
        TimeUnit.SECONDS.sleep(7);

        StatisticsResponse response = transactionService.getStatistics();
        assertEquals(4, response.getCount());//Only 4 requests would remain
        assertEquals(new BigDecimal("20.50"), response.getMin(), "Minimum will be 20.50");
        assertEquals(new BigDecimal("50.50"), response.getMax(), "Maximum will be 50.50");
        assertEquals(new BigDecimal("142.00"), response.getSum(), "Sum will be 142");
        assertEquals(new BigDecimal("142.00").divide(BigDecimal.valueOf(4)), response.getAvg(), "Sum will be 142");
    }

    void populateListAndAssert(){
        //Add to the List Once
        TransactionRequest tranRequest = formRequest("30.50", LocalDateTime.now().minusSeconds(27));
        transactionService.saveTransaction(tranRequest);
        assertEquals(1, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should be 1");
        //Add again
        tranRequest = formRequest("50.50", LocalDateTime.now().minusSeconds(15));
        transactionService.saveTransaction(tranRequest);
        assertEquals(2, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = formRequest("20.50", LocalDateTime.now().minusSeconds(25));
        transactionService.saveTransaction(tranRequest);
        assertEquals(3, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = formRequest("50.50", LocalDateTime.now().minusSeconds(25));
        transactionService.saveTransaction(tranRequest);
        assertEquals(4, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = formRequest("20.50", LocalDateTime.now().minusSeconds(5));
        transactionService.saveTransaction(tranRequest);
        assertEquals(5, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = formRequest("50.50", LocalDateTime.now().minusSeconds(9));
        transactionService.saveTransaction(tranRequest);
        assertEquals(6, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");

        //Add again
        tranRequest = formRequest("20.50", LocalDateTime.now().minusSeconds(10));
        transactionService.saveTransaction(tranRequest);
        assertEquals(7, transactionService.fetchAllTransactionList().size(),
                "Size of the Transaction List Should increment by 1");
    }
}