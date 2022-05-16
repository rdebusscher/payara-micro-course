# Integration Testing

We use the Testcontainer project to perform integration testing. It allows you to run your application and required external components in Docker containers. This makes setting up your environment straightforward, even complex ones with multiple dependencies.

There is a single demo that tests the endpoints of the application we used for the MicroStream CDi integration with Payara Micro.

Build the project

```Shell
mvn clean package
```

Run the integration test

```Shell
mvn integration-test
```
