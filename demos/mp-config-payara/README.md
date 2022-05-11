# Cache configuration in Payara Micro

By default, MicroProfile config values are cached for 30 seconds in Payara Micro.  This avoids the resolution of the value each time it is requested but allows for updates with dynamic values.

The cache can be configured through the post boot command file. Duration value is in seconds.

```
set-config-cache --duration=10
```

Run the application and configuration.

```Shell
java -jar payara-micro.jar --postbootcommandfile /Users/rubus/education/payara-micro-course/demos/mp-config-payara/postboot.txt /Users/rubus/education/payara-micro-course/demos/mp-config-payara/target/app.war
```

Testing

```Shell
curl 'localhost:8080/app/api/config'
```
