package example.micronaut.wallet.impl.service;

import example.micronaut.wallet.api.dto.TransferCreateCommand;
import example.micronaut.wallet.api.dto.TransferInfo;
import example.micronaut.wallet.api.service.TransferService;
import example.micronaut.wallet.impl.domain.TransferRepository;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;

@Slf4j
@Singleton
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public TransferInfo createTransfer(TransferCreateCommand transferCreateCommand) {
        return transferRepository.save(transferCreateCommand).toTransferInfo();
    }
}
