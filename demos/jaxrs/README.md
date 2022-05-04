# JAX-RS Demo

A Jakarta EE API specification that provides support in creating web services according to the Representational State Transfer architectural pattern. (REST)

## E0Hello

Hello World, needs DemoApplication (as all applications that make use of JAX-RS)

- Configuration
- Creating GET Endpoint and mapping to Java Method
- Path and Query Parameters

Build the project

```Shell
mvn clean package
```

Execute on the runtime

```Shell
java -jar payara-micro.jar <path-to-war>
```

Testing 

```Shell
curl 'localhost:8080/rest/api/hello
```

Testing with Path and Query parameters

```Shell
curl 'localhost:8080/rest/api/hello/MicroStream?language=en'
```

## JSON Support

- The JSONB support (JSON Binding - automatic converting between Java and JSON) is integrated. Use appropriate Media Types
- The JSONP support (JSON Processing - Programmatic reading and writing JSON) is integrated.
- POST type of endpoints
 
Testing

```Shell
curl 'localhost:8080/rest/api/person'

curl -X POST localhost:8080/rest/api/person -H 'Content-Type: application/json' -d '{"age":24,"name":"John Doe"}'  

curl 'localhost:8080/rest/api/jsonp'
```

## Define Response Status

- Use _Response Builder_ to define the status

```Shell
curl 'localhost:8080/rest/api/evenValue/4'
```

## Conversion and validation

- Automatic conversion to primitive, enums, collections
- Integration with Jakarta BeanValidation like `@NotNull`, `@PositiveOrZero`, `@Size`, `@Past`, `@Future`, `@Pattern`, ... 

```Shell
curl 'localhost:8080/rest/api/validate?day=4?dayOfWeek=WEDNESDAY'
```

## CDI integration

- JAX-RS resource can inject CDI beans or can be turned into CDI beans.


```Shell
curl 'localhost:8080/rest/api/cdi/Rudy?language=nl'
```

## ExceptionMapper

- Exception from the 'backend' can be turned into JAX-RS responses to keep JAX-RS specific code outside of generic backend.

```Shell
curl -v 'localhost:8080/rest/api/exception/42'
```
