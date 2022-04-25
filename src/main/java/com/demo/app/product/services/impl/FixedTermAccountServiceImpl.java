package com.demo.app.product.services.impl;

import com.demo.app.product.entities.FixedTermAccount;
import com.demo.app.product.repositories.FixedTermAccountRepository;
import com.demo.app.product.services.FixedTermAccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedTermAccountServiceImpl implements FixedTermAccountService {

    private final FixedTermAccountRepository cardRepository;

    public FixedTermAccountServiceImpl(FixedTermAccountRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private static Mono<? extends Boolean> apply(Boolean x) {
        return Boolean.TRUE.equals(x)?Mono.just(true):Mono.just(false);
    }

    @Override
    public Flux<FixedTermAccount> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Mono<FixedTermAccount> save(FixedTermAccount card) {
        return cardRepository.save(card);
    }

    @Override
    public Flux<FixedTermAccount> saveAll(Flux<FixedTermAccount> cards) {
        return cardRepository.saveAll(cards);
    }

    @Override
    public Flux<FixedTermAccount> findAllByIdentifier(String identifier) {
        return cardRepository.findAllByIdentifier(identifier);
    }

    @Override
    public Mono<FixedTermAccount> findByIdentifierAndAccount(String identifier, String account) {
        return cardRepository.findByIdentifierAndAccountNumber(identifier,account);
    }

    @Override
    public Mono<Boolean> findByIdentifier(String identifier) {
        return cardRepository.findByIdentifier(identifier).hasElement().flatMap(FixedTermAccountServiceImpl::apply);
    }

    @Override
    public Mono<FixedTermAccount> findById(String id) {
        return cardRepository.findById(id);
    }

    @Override
    public Mono<FixedTermAccount> update(FixedTermAccount card, String id) {
        return cardRepository.findById(id).flatMap(x->{
            x.setCvc(card.getCvc());
            x.setAccountNumber(card.getAccountNumber());
            x.setCurrency(card.getCurrency());
            x.setBalance(card.getBalance());
            x.setIdentifier(card.getIdentifier());
            x.setNumberTransactions(card.getNumberTransactions());
            return cardRepository.save(x);
        });
    }

    @Override
    public Mono<Void> delete(String id) {
        return cardRepository.deleteById(id);
    }
}
