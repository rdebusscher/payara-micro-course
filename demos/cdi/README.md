# CDI Demo

Jakarta Contexts Dependency Injection specifies a means for obtaining objects in such a way as to maximize reusability, testability and maintainability compared to traditional approaches such as constructors, factories, and service locators.


This demo runs on Java SE. Within runtimes, the container must not be started but is started automatically by the runtime. All other aspects stay the same.


## App0

- How to start the CDI container (only needed when using plain Java SE)
- All beans in the container
- Configuration through _beans.xml_

## App1

- Select programmatically a _bean_ from container.
- Initialization of the instance
- Notion of Scope
- Criteria to retrieve beans from Container (Class, interface, ...)

## App2

- Inject dependencies
- Qualifier usage to distinguish between instances
- Optional or multiple beans.

## App3

- Producer methods of CDI beans (for Java built-in, initialization, and customization)

## App4

- CDI Event system

## App5

- Decorator mechanism

## App6

- Interceptor mechanism