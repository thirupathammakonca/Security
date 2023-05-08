package com.thiruacademy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@NotNull(message="Product name should not be null")
	@NotEmpty(message="Product name should not be empty")
	//@Column(name="product_name")
	private String name;
	@NotNull(message="Price name should not be null")
	@NotEmpty(message="Price name should not be empty")
	@Min(value = 10)
	@Max(value = 100000)
	private String price;
	@NotNull(message="Department name should not be null")
	@NotEmpty(message="Department name should not be empty")
	private String department;
	
}
