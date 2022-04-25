package com.demo.app.product.services;

import com.demo.app.product.entities.CurrentAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountService {
    Flux<CurrentAccount> findAll();
    Mono<CurrentAccount> save(CurrentAccount card);
    Flux<CurrentAccount> saveAll(Flux<CurrentAccount> cards);
    Flux<CurrentAccount> findAllByIdentifier(String identifier);
    Mono<CurrentAccount> findByIdentifierAndAccount(String identifier,String account);
    Mono<Boolean> findByIdentifier(String identifier);
    Mono<CurrentAccount> updateByAccountNumberAndIdentifier(CurrentAccount card,String identifier,String account);
    Mono<CurrentAccount> update(CurrentAccount card,String identifier);
    Mono<Void> delete(String identifier);
}
