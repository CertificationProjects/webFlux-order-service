package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.ProductDto;
import com.orderservice.orderservice.repository.ProductRepository;
import com.orderservice.orderservice.utility.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> findAll(){
        return productRepository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> findById(String id){
        return productRepository.findById(id).map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insert(Mono<ProductDto> productDtoMono){
        return productDtoMono.map(EntityDtoUtil::toEntity)
                .flatMap(productRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> update(String id, Mono<ProductDto> productDtoMono) {
        return productRepository.findById(id)
                .flatMap(p -> productDtoMono.map(EntityDtoUtil::toEntity)
                        .doOnNext(productEntity -> productEntity.setId(id)))
                .flatMap(productRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> delete(String id){
        return productRepository.deleteById(id);
    }
}
