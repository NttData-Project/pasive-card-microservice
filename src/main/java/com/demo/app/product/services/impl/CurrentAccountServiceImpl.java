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
    public Flux<CurrentAccount> findAllByDni(String dni) {
        return cardRepository.findAllByDni(dni);
    }

    @Override
    public Mono<CurrentAccount> findByDniAndAccount(String dni, String account) {
        return cardRepository.findByDniAndAccountNumber(dni,account);
    }

    @Override
    public Mono<Boolean> findByDni(String dni) {
        return cardRepository.findByDni(dni).hasElement().flatMap(CurrentAccountServiceImpl::apply);
    }

    @Override
    public Mono<CurrentAccount> update(CurrentAccount card, String id) {
        return cardRepository.findById(id).flatMap(x->{
            x.setCvc(card.getCvc());
            x.setAccountNumber(card.getAccountNumber());
            x.setCurrency(card.getCurrency());
            x.setBalance(card.getBalance());
            x.setDni(card.getDni());
            return cardRepository.save(x);
        });
    }

    @Override
    public Mono<Void> delete(String id) {
        return cardRepository.deleteById(id);
    }
}
