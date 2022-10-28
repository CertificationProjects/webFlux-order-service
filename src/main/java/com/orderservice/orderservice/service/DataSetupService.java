package com.orderservice.orderservice.service;

import com.orderservice.orderservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {
    @Autowired
    private ProductService service;
    @Override
    public void run(String... args) throws Exception {
        ProductDto productDto1 = ProductDto.builder().description("television").price(1000.0).build();
        ProductDto productDto2 = ProductDto.builder().description("Washing Machine").price(900.0).build();
        ProductDto productDto3 = ProductDto.builder().description("Microwave Oven").price(400.0).build();
        ProductDto productDto4 = ProductDto.builder().description("Air Fryer").price(200.0).build();
        Flux.just(productDto1, productDto2, productDto3, productDto4)
                .flatMap(p -> service.insert(Mono.just(p)))
                .subscribe(System.out::println);
    }
}
