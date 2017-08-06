package com.example.crudspringbootdynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudspringbootdynamodb.dao.ProductCatalogDAO;
import com.example.crudspringbootdynamodb.entity.ProductCatalog;


@RestController
public class ProductCatalogController {

	@Autowired
	private ProductCatalogDAO dao;

	@RequestMapping(path="/catalogService/products", method=RequestMethod.GET)
	public @ResponseBody List<ProductCatalog> findAll() {
		return dao.findAll();
	}
	
	@RequestMapping(path="/catalogService/products/{id}", method=RequestMethod.GET)
	public @ResponseBody ProductCatalog findById(@PathVariable String id) {
		return dao.findById(id);
	}

	@RequestMapping(path="/catalogService/products/delete/{id}", method=RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) {
		dao.delete(id);
	}

	@RequestMapping(path="/catalogService/products/add", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody ProductCatalog addCatalogEntry(@RequestBody ProductCatalog product) {
		ProductCatalog newItem = dao.save(product);
		return newItem;
	}
	
	@RequestMapping(path="/catalogService/products/update/{id}", method=RequestMethod.PUT, headers="Accept=application/json")
	public ResponseEntity<ProductCatalog> updateCatalogEntry(@PathVariable String id, @RequestBody ProductCatalog product) {
		ProductCatalog productCatalog = dao.findById(id);
		
		if (productCatalog == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
		}
		
		productCatalog.setInventoryLow(product.getInventoryLow());
		productCatalog.setPrice(product.getPrice());
		productCatalog.setProductName(product.getProductName());
		productCatalog.setQuantity(product.getQuantity());
		productCatalog.setCurrencyCode(product.getCurrencyCode());
		dao.save(productCatalog);

		return new ResponseEntity<ProductCatalog>(productCatalog, HttpStatus.OK);
	}
}
