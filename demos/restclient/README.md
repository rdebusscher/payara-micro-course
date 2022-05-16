# MicroProfile Rest Client

MicroProfile REST Client API provides a type-safe approach to invoke RESTful services over HTTP.

Instead of the more low-level approach of the JAX-RS client (See JAXRS demo)

```Java
Response response = client.target(uriInfo.getBaseUri())
        .path("/secure")
        .request()
        .header("Authorization", "BASIC " + encoded)
        .get();
```

This specification allows to call a remote service in a more declarative way.  You define the interface where you put JAX-RS annotations on the method signature that match the remote endpoint information.
The system creates a proxy for this interface that can injected in a CDI bean and allows you to make the remote HTTP call. Within Code, it looks like a normal within JVM call.

```Java
@Inject
@RestClient
private Service service;


    // Within a method
     service.doSomething(parameter);

```


The demo consists of 2 services, service a calls service b, and both projects can be found in the respective directory.

## Build and run projects

```Shell
mvn clean package
```

within each subfolder.

Start of services

```Shell
java -jar payara-micro.jar <path-to-war>/service-a.war

java -jar payara-micro.jar --port 8180 <path-to-war>/service-b.war
```

## Testing

```Shell
curl localhost:8080/service-a/data/client/test/MicroStream
```

See `be.rubus.courses.payara.micro.rest.client.ClientController`.

The asynchronous call

```Shell
curl localhost:8080/service-a/data/client-async
```

See `be.rubus.courses.payara.micro.rest.client.async.ClientAsyncController`.
