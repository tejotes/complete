package example.micronaut.wallet.impl.domain;

import example.micronaut.wallet.api.dto.WalletInfo;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Slf4j
@ToString
@Entity(name = "Wallet")
@Table(name = "WALLET")
public class WalletEntity {

    @Id
    @GeneratedValue
    private Long id;

    LocalDateTime created = LocalDateTime.now();

    LocalDateTime modified;

    private String refid;

    private String name;

    private String comment;

    public WalletEntity() {
    }

    public WalletEntity(@NonNull String refid, String name, String comment) {
        this.refid = refid;
        this.name = name;
        this.comment = comment;
    }

    @PreUpdate
    private void preUpdate() {
        this.modified = LocalDateTime.now();
    }

    public WalletInfo toWalletInfo() {
        return WalletInfo.builder() //
                .refid(refid) //
                .name(name) //
                .comment(comment) //
                .modified(modified) //
                .build();
    }
}
