package com.demo.app.product.services.impl;


import com.demo.app.product.entities.CurrentAccount;
import com.demo.app.product.repositories.CurrentAccountRepository;
import com.demo.app.product.services.CurrentAccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrentAccountServiceImpl implements CurrentAccountService{

    private final CurrentAccountRepository cardRepository;

    public CurrentAccountServiceImpl(CurrentAccountRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private static Mono<? extends Boolean> apply(Boolean x) {
        return Boolean.TRUE.equals(x)?Mono.just(true):Mono.just(false);
    }

    @Override
    public Flux<CurrentAccount> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Mono<CurrentAccount> save(CurrentAccount card) {
        return cardRepository.save(card);
    }

    @Override
    public Flux<CurrentAccount> saveAll(Flux<CurrentAccount> cards) {
        return cardRepository.saveAll(cards);
    }

    @Override
    public Flux<CurrentAccount> findAllByIdentifier(String identifier) {
        return cardRepository.findAllByIdentifier(identifier);
    }

    @Override
    public Mono<CurrentAccount> findByIdentifierAndAccount(String dni, String account) {
        return cardRepository.findByIdentifierAndAccountNumber(dni,account);
    }

    @Override
    public Mono<Boolean> findByIdentifier(String identifier) {
        return cardRepository.findByIdentifier(identifier).hasElement().flatMap(CurrentAccountServiceImpl::apply);
    }

    @Override
    public Mono<CurrentAccount> findById(String id) {
        return cardRepository.findById(id);
    }

    @Override
    public Mono<CurrentAccount> updateByAccountNumberAndIdentifier(CurrentAccount card,String identifier, String account) {
        return cardRepository.findByIdentifierAndAccountNumber(identifier,account).flatMap(x->{
            x.setCvc(card.getCvc());
            x.setAccountNumber(card.getAccountNumber());
            x.setCurrency(card.getCurrency());
            x.setBalance(card.getBalance());
            x.setIdentifier(card.getIdentifier());
            return cardRepository.save(x);
        });
    }

    @Override
    public Mono<CurrentAccount> update(CurrentAccount card, String id) {
        return cardRepository.findById(id).flatMap(x->{
            x.setCvc(card.getCvc());
            x.setAccountNumber(card.getAccountNumber());
            x.setCurrency(card.getCurrency());
            x.setBalance(card.getBalance());
            x.setIdentifier(card.getIdentifier());
            return cardRepository.save(x);
        });
    }

    @Override
    public Mono<Void> delete(String id) {
        return cardRepository.deleteById(id);
    }
}
