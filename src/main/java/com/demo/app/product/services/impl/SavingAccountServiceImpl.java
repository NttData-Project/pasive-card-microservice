package com.demo.app.product.services.impl;

import com.demo.app.product.entities.SavingAccount;
import com.demo.app.product.repositories.SavingAccountRepository;
import com.demo.app.product.services.SavingAccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SavingAccountServiceImpl implements SavingAccountService {

    private final SavingAccountRepository cardRepository;

    public SavingAccountServiceImpl(SavingAccountRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private static Mono<? extends Boolean> apply(Boolean x) {
        return Boolean.TRUE.equals(x)?Mono.just(true):Mono.just(false);
    }

    @Override
    public Flux<SavingAccount> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Mono<SavingAccount> save(SavingAccount card) {
        return cardRepository.save(card);
    }

    @Override
    public Flux<SavingAccount> saveAll(Flux<SavingAccount> cards) {
        return cardRepository.saveAll(cards);
    }

    @Override
    public Flux<SavingAccount> findAllByDni(String dni) {
        return cardRepository.findAllByDni(dni);
    }

    @Override
    public Mono<SavingAccount> findByDniAndAccount(String dni, String account) {
        return cardRepository.findByDniAndAccountNumber(dni,account);
    }

    @Override
    public Mono<Boolean> findByDni(String dni) {
        return cardRepository.findByDni(dni).hasElement().flatMap(SavingAccountServiceImpl::apply);
    }

    @Override
    public Mono<SavingAccount> update(SavingAccount card, String id) {
        return cardRepository.findById(id).flatMap(x->{
            x.setCvc(card.getCvc());
            x.setAccountNumber(card.getAccountNumber());
            x.setCurrency(card.getCurrency());
            x.setBalance(card.getBalance());
            x.setDni(card.getDni());
            x.setNumberTransactions(card.getNumberTransactions());
            return cardRepository.save(x);
        });
    }

    @Override
    public Mono<Void> delete(String id) {
        return cardRepository.deleteById(id);
    }
}
