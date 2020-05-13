package example.micronaut.wallet.impl.domain;

import example.micronaut.domain.BearbeitungStatus;
import example.micronaut.wallet.api.dto.TransferCreateCommand;
import example.micronaut.wallet.api.dto.TransferInfo;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Slf4j
@ToString
@Entity(name = "Transfer")
@Table(name = "TRANSFER")
public class TransferEntity {

    @Id
    @GeneratedValue
    private long id;

    @Version
    private long tanum;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime modified;

    @Column(nullable = false)
    private String refid;

    @Column(nullable = false)
    private String walletRefid;

    @Column(nullable = false)
    private LocalDateTime transferTime;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String coinSymbol;

    private String comment;

    @Enumerated(EnumType.STRING)
    private BearbeitungStatus status = BearbeitungStatus.ANGELEGT;

    public TransferEntity() {
    }

    public TransferEntity(@NonNull TransferCreateCommand transferCreateCommand) {
        this.refid = (transferCreateCommand.getRefid() != null) ? transferCreateCommand.getRefid() : UUID.randomUUID().toString();
        this.walletRefid = transferCreateCommand.getWalletRefid();
        this.transferTime = transferCreateCommand.getTransferTime();
        this.amount = transferCreateCommand.getAmount();
        this.coinSymbol = transferCreateCommand.getCoinSymbol();
        this.coinSymbol = transferCreateCommand.getComment();
    }

    public TransferInfo toTransferInfo() {
        return TransferInfo.builder() //
                .refid(refid) //
                .walletRefid(walletRefid) //
                .transferTime(transferTime) //
                .amount(amount) //
                .coinSymbol(coinSymbol) //
                .comment(comment) //
                .build(); //
    }
}
