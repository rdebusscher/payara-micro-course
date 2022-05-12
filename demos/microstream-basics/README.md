# MicroStream Demo

MicroStream allows you to write a Java object graph to data storage very efficiently and quickly. This storage can be the disk, blob storage of a database, or any other medium. Since your data is stored in memory, it provides microsecond query time, low-latency data access, and gigantic data throughput because the data are just Java instances. No mapping and conversion to a database system is needed.

## Hello World

See directory _config_.

- Maven dependencies
- Root of the Object Graph
- Define Storage Manager (simplified version)
- Use `storeRoot` to store data.

Testing 

Run the `HelloWorld` class.

## Config

See directory _config_.

- Use specific file system (disk here but there exists more Abstract File System - AFS - that are supported)
- Define back directory
- Define number of IO channels (number of threads to read and write data)

Testing

Run the `FullConfiguration` class.

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
