package com.demo.app.product.controllers;

import com.demo.app.product.entities.FixedTermAccount;
import com.demo.app.product.services.FixedTermAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fixedTermAccount")
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
public class FixedTermAccountController {
    private final FixedTermAccountService cardService;

    public FixedTermAccountController(FixedTermAccountService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<Flux<FixedTermAccount>> findAll(){
        Flux<FixedTermAccount> card = cardService.findAll();
        return ResponseEntity.ok(card);
    }

    @GetMapping("/all/identifier/{identifier}")
    public Flux<FixedTermAccount> findAllByIdentifier(@PathVariable String identifier){
        return cardService.findAllByIdentifier(identifier);
    }

    @GetMapping("/identifier/{identifier}")
    public Mono<Boolean> findByIdentifier(@PathVariable String identifier){
        return cardService.findByIdentifier(identifier);
    }

    @GetMapping("/identifier/{identifier}/account/{account}")
    public Mono<FixedTermAccount> findByIdentifierAndAccount(@PathVariable String identifier,@PathVariable String account){
        return cardService.findByIdentifierAndAccount(identifier,account);
    }

    @PostMapping
    public ResponseEntity<Mono<FixedTermAccount>> save(@RequestBody FixedTermAccount card){
        return ResponseEntity.ok(cardService.save(card));
    }
    @PostMapping("/all")
    public ResponseEntity<Flux<FixedTermAccount>> saveAll(@RequestBody Flux<FixedTermAccount> cards){
        return ResponseEntity.ok(cardService.saveAll(cards));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FixedTermAccount>> update(@RequestBody FixedTermAccount card, @PathVariable String id){
        return cardService.update(card,id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        return cardService.delete(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
