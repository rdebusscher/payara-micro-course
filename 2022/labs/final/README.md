# Lab

We continue from the Jakarta Lab and add MicroStream functionality on top of the previous code base.

We will rework the Database object so that we have a Storage root containing the list of `Product`'s as `UserItem`'s.  The MicroStream Storage Manager can be initialised from the init method taking a MicroProfile Config property defining the location of the database on disk.

Various methods within `Database` object need to be adapted to store the changes we make.

Within the `commands.txt` file, the Curl commands are listed that should work against your implementation.

The `solution` branch contains a possible solution for this lab.  You can start from the Jakarta directory within the solution branch if you want to start from an application that has already all JAX-RS functionality.

Things that are expected to be used in this lab.

- Update project dependencies for MicroStream and MicroProfile
- Use MicroProfile Config parameter
- Start a MicroStream `EmbeddedStorage`
- Store changed instances from the Object graph.

