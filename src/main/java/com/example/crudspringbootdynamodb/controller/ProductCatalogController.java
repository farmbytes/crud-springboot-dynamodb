package com.example.crudspringbootdynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.crudspringbootdynamodb.dao.ProductCatalogDAO;
import com.example.crudspringbootdynamodb.entity.ProductCatalog;


@Controller
public class ProductCatalogController {

	@Autowired
	private ProductCatalogDAO dao;

	@RequestMapping(path="/catalogService/products")
	public @ResponseBody List<ProductCatalog> findAll() {
		return dao.findAll();
	}
}
