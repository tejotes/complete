package example.micronaut.wallet.api.service;

import example.micronaut.wallet.api.dto.TransferCreateCommand;
import example.micronaut.wallet.api.dto.TransferInfo;

public interface TransferService {

    TransferInfo createTransfer(TransferCreateCommand transferCreateCommand);
}
