package com.thiruacademy.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.thiruacademy.entity.Product;
import com.thiruacademy.exception.ProductNotFoundException;
import com.thiruacademy.repository.ProductRepository;
import com.thiruacademy.service.ProductService;

@Service
@Primary
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	boolean flag;
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> fetchAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product fetchProduct(Long id) throws ProductNotFoundException {
		Product product = null;
		if(id != null && id != 0) {
			flag = productRepository.existsById(id);
		}
		if(flag) 
			product = productRepository.findById(id).get();
		else
			throw new ProductNotFoundException("Product Not Found");
		return product;
	}

	@Override
	public Product putUpdateProduct(Long id, Product product) throws ProductNotFoundException {
		Product productfromDB = null;
		if(id != null && id != 0) {
			flag = productRepository.existsById(id);
		}else
			throw new ProductNotFoundException("Product not found");
		if(flag) {
			productfromDB = productRepository.findById(id).get();
			productfromDB.setName(product.getName());
			productfromDB.setPrice(product.getPrice());
			productfromDB.setDepartment(product.getDepartment());
		}
		return productRepository.save(productfromDB);
	}

	@Override
	public Product patchUpdateProduct(Long id, Map<String, Object> fields) throws ProductNotFoundException {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProduct.get(), value);
            });
            return productRepository.save(existingProduct.get());
        }
        return null;
    }

	@Override
	public String deleteProduct(Long id) {
		if(id != null && id != 0) {
			flag = productRepository.existsById(id);
		}
		if(flag) 
			productRepository.deleteById(id);
		return "Product deleted";
	}

}
