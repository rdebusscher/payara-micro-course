# MicroStream Demo

MicroStream allows you to write a Java object graph to data storage very efficiently and quickly. This storage can be the disk, blob storage of a database, or any other medium. Since your data is stored in memory, it provides microsecond query time, low-latency data access, and gigantic data throughput because the data are just Java instances. No mapping and conversion to a database system is needed.

## Hello World

See directory _config_.

- Maven dependencies
- Root of the Object Graph
- Define Storage Manager (simplified version)
- Use `store` to store data.

Testing 

Run the `HelloWorld` class.

## Config

See directory _config_.

A typical configuration setting using _properties_, create a foundation out of it (with all the defaults for all the interfaces of MicroStream ), customize it if needed and create the `StorageManager` out of it.

Testing

Run the `BuilderConfiguration` class.

## Lazy

Do not keep all the data in memory.

See directory _lazy_.

- Usage of `Lazy`

Testing

Run the `LazyTest` class.

## Serializer

Use the internal serializer to write byte representation of your object Graph.


See directory _serializer_.

- Added Maven dependency
- Register the Entities
- To and fro byte array.

Testing

Run the `SerializerTest` class.
