package com.orderservice.orderservice.controller;

import com.orderservice.orderservice.dto.ProductDto;
import com.orderservice.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("all")
    public Flux<ProductDto> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> findProductById(@PathVariable("id") String id){
        return service.findById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return service.insert(productDtoMono);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable("id") String id, @RequestBody Mono<ProductDto> productDtoMono){
        return service.update(id, productDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") String id){
        return service.delete(id);
    }
}
