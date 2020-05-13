package example.micronaut.wallet.api.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Introspected
public class TransferCreateCommand {

    private String refid;

    private String walletRefid;

    private LocalDateTime transferTime;

    private BigDecimal amount;

    private String coinSymbol;

    private String comment;
}
