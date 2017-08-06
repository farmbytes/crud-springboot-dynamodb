package com.example.crudspringbootdynamodb.dao;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.example.crudspringbootdynamodb.entity.ProductCatalog;

@EnableScan
public interface ProductCatalogDAO extends CrudRepository<ProductCatalog, String>{
	
	ProductCatalog findById(String id);
	
	List<ProductCatalog> findAll();
	

}
