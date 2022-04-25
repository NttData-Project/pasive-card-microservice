package com.demo.app.product.services;

import com.demo.app.product.entities.FixedTermAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FixedTermAccountService {
    Flux<FixedTermAccount> findAll();
    Mono<FixedTermAccount> save(FixedTermAccount card);
    Flux<FixedTermAccount> saveAll(Flux<FixedTermAccount> cards);
    Flux<FixedTermAccount> findAllByIdentifier(String identifier);
    Mono<FixedTermAccount> findByIdentifierAndAccount(String identifier, String account);
    Mono<Boolean> findByIdentifier(String identifier);
    Mono<FixedTermAccount> update(FixedTermAccount card,String id);
    Mono<Void> delete(String id);
}
