package com.example.crudspringbootdynamodb;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.example.crudspringbootdynamodb.dao.ProductCatalogDAO;
import com.example.crudspringbootdynamodb.entity.ProductCatalog;

@SpringBootApplication
public class CrudSpringbootDynamodbApplication {

	private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
    
	@Autowired
	ProductCatalogDAO dao;
	
	@PostConstruct
	public void init() {
		
		createProductCatalogTable();
		ProductCatalog product1 = new ProductCatalog("Book", 10, false, new BigDecimal("10.99"), "USD");
		dao.save(product1);
		
		ProductCatalog product2 = new ProductCatalog("Bag", 10, false, new BigDecimal("5.99"), "USD");
		dao.save(product2);		
	}

	public void createProductCatalogTable(){
        try {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductCatalog.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            
        }
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringbootDynamodbApplication.class, args);
	}
}
