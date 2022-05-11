# MicroProfile Config Demo

MicroProfile configuration specification allows you to configure your application according to the 12-factor application. The configuration is done outside the application deployment itself so that the same artifact can be used in test and production and in any other environment you like.

The specification builds on top of the Jakarta CDI specification.

## E0Resource

Basic usage of retrieving parameters, declarative and programmatic.

- Usage of `@ConfigProperty` for declarative Injection
- Usage of `ConfigProvider`for programmatic access.
- Defaults from _microprofile-config.properties_

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
curl 'localhost:8080/app/api/config/injected'

curl 'localhost:8080/app/api/config/lookup'
```

## E1Resource

By default, Environment and System properties are defined as Configuration Source besides the properties file.

- Make use of System property
- Ordering of Config Sources.


Execute on the runtime

```Shell
java -jar payara-micro.jar <path-to-war>
```

or

```Shell
java -Dconfig.key=System -jar payara-micro.jar <path-to-war>
```


Testing

```Shell
curl 'localhost:8080/app/api/config-sources'
```

## E2Resource

Configuration values must be available at deployment time or deployment fails (fast failure). Values can also be defined as optional using `java.util.Optional`

- Optional configuration value

Testing

```Shell
curl 'localhost:8080/app/api/config/optional'
```

## E3Resource

There are default converters in use to take the value from String representation, including lists. Custom converters can be created.

- Example of a property that is converted to a List.
- Create and define a custom Converter.

Testing

```Shell
curl 'localhost:8080/app/api/config/converter'
```

## E4Resource

The specification defines that values for the configuration is

- Defined at deployment time for required values
- The moment optional values are requested for the first time
- Everytime the value is requested when using the Provider construct.


A caching solution can be provided by the implementation. -> See caching example for Payara Micro.

Testing

```Shell
curl 'localhost:8080/app/api/config/provider'
```

## E5Resource

- Support for variable replacement, composite configuration values.

Testing

```Shell
curl 'localhost:8080/app/api/config/variables'
```
