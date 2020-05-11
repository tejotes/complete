package example.micronaut.wallet.api.dto;


import io.micronaut.core.annotation.Introspected;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
@ToString
@Introspected
public class WalletCreateCommand {

    private String refid;

    private String name;

    private String comment;
}
