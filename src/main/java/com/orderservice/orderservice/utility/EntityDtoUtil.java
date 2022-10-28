package com.orderservice.orderservice.utility;

import com.orderservice.orderservice.dto.ProductDto;
import com.orderservice.orderservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static Product toEntity(ProductDto dto){
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }

    public static ProductDto toDto(Product product){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
