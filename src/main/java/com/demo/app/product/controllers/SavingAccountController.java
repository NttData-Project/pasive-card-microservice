package com.demo.app.product.controllers;

import com.demo.app.product.entities.SavingAccount;
import com.demo.app.product.services.SavingAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/savingAccount")
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
public class SavingAccountController {
    private final SavingAccountService cardService;

    public SavingAccountController(SavingAccountService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<Flux<SavingAccount>> findAll(){
        Flux<SavingAccount> card = cardService.findAll();
        return ResponseEntity.ok(card);
    }

    @GetMapping("/all/identifier/{identifier}")
    public Flux<SavingAccount> findAllByIdentifier(@PathVariable String identifier){
        return cardService.findAllByIdentifier(identifier);
    }
    @GetMapping("/{id}")
    public Mono<SavingAccount> findById(@PathVariable String id){
        return cardService.findById(id);
    }

    @GetMapping("/identifier/{identifier}")
    public Mono<Boolean> findByIdentifier(@PathVariable String identifier){
        return cardService.findByIdentifier(identifier);
    }

    @GetMapping("/identifier/{identifier}/account/{account}")
    public Mono<SavingAccount> findByIdentifierAndAccount(@PathVariable String identifier, @PathVariable String account){
        return cardService.findByIdentifierAndAccount(identifier,account);
    }

    @PostMapping
    public ResponseEntity<Mono<SavingAccount>> save(@RequestBody SavingAccount card){
        return ResponseEntity.ok(cardService.save(card));
    }
    @PostMapping("/all")
    public ResponseEntity<Flux<SavingAccount>> saveAll(@RequestBody Flux<SavingAccount> cards){
        return ResponseEntity.ok(cardService.saveAll(cards));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SavingAccount>> update(@RequestBody SavingAccount card, @PathVariable String id){
        return cardService.update(card,id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return cardService.delete(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
