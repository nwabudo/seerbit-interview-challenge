
package com.seerbit.challenge.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.seerbit.challenge.helper.LocalDateTimeDeserializer;
import com.seerbit.challenge.helper.LocalDateTimeSerializer;
import com.seerbit.challenge.validator.DateValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = "Amount Cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value has to be greater than 0.0")
    @Digits(integer = Integer.MAX_VALUE, fraction = 4, message = "Ensure that the fraction is at most 4 decimal place")
    private BigDecimal amount;

    @NotNull(message = "Date Cannot be null")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateValidator
    private LocalDateTime timestamp;

}
