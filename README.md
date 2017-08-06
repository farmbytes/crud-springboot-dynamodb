# crud-springboot-dynamodb
Sample Spring Boot app to perform CRUD operations using DynamoDB

### Download and run DynamoDB in local
```
$ java -jar -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

Initializing DynamoDB Local with the following configuration:
Port:	8000
InMemory:	false
DbPath:	null
SharedDb:	true
shouldDelayTransientStatuses:	false
CorsParams:	*
```
Refer to CrudSpringbootDynamodbApplication.java to see how we are creating the ProductCatalog data store in DynamoDB. 

### Try the below end-point to successfully retrieve sample data from DynamoDB

http://localhost:8080/catalogService/products

### Sample PUT request with a JSON payload. Make sure that you set 'Content-Type' to 'application/json' in the request headers

http://localhost:8080/catalogService/products/update/1ed4cd6a-41db-4192-a7b2-8e88c251b6e9
```
    {
        "id": "1ed4cd6a-41db-4192-a7b2-8e88c251b6e9",
        "productName": "Shoes",
        "quantity": 100,
        "inventoryLow": false,
        "price": 95.99,
        "currencyCode": "USD"
    }
```
### Sample POST request with a JSON payload. Make sure that you set 'Content-Type' to 'application/json' in the request headers

http://localhost:8080/catalogService/products/add   
```
{
  "productName": "Ink Pen",
  "quantity": 30,
  "inventoryLow": false,
  "price": 15.99,
  "currencyCode": "USD"
}
```
 