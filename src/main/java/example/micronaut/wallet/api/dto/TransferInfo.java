package example.micronaut.wallet.api.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Introspected
public class TransferInfo {

    String refid;

    String walletRefid;

    LocalDateTime transferTime;

    BigDecimal amount;

    String coinSymbol;

    String comment;
}
