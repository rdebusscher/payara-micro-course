# MicroStream CDI integration

A declarative way of using MicroStream objects in an environment that has CDI and MicroProfile Config available.


The highlights

- Added Maven dependency
- Use `@Storage` on the Object that act as data root
- Use `@Store` on methods that modify the Object graph of the data root.
- Configure storage manager through _microprofile-config.properties_ or any other MicroProfile source.

Build the project

```Shell
mvn clean package
```

Execute on the runtime

```Shell
java -jar payara-micro.jar <path-to-war>
```

Testing

List the products

```Shell
curl  'http://localhost:8080/ms/products/'
```

Add products 

```Shell
curl -X POST 'http://localhost:8080/ms/products/' -H 'Content-Type: application/json' -d '{"id": 1, "name": "banana", "description": "a fruit", "rating": 5}'

curl -X POST 'http://localhost:8080/ms/products/' -H 'Content-Type: application/json' -d '{"id": 2, "name": "watermelon", "description": "watermelon sugar ahh", "rating": 4}'
```

Check the product list again with the GET request.

Stop the Payara Micro instance and start it again.

Check if the products are kept from previous run.

Delete a product and check again after restart

```Shell
curl -X DELETE 'http://localhost:8080/ms/products/1'
```
