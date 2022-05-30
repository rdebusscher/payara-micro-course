# Final Lab (Advanced)

We continue from the Jakarta advanced Lab and add MicroStream CDI functionality on top of the previous code base.

We will rework the Database object so that we have a Storage root containing the list of `Product`'s, `User`s, and `UserItem`'s.  

We will make use of the CDI integration. Ideally, we should not change any method within the `Database` object (expect the init() one) and just need to add some annotations.

Within the `commands.txt` file, the Curl commands are listed that should work against your implementation.

The `solution` branch contains a possible solution for this lab.  You can start from the jakarta_advanced directory within the solution branch if you want to start from an application that has already all JAX-RS functionality.

Things that are expected to be used in this lab.

- Update project dependencies for MicroStream CDI integration
- Create the root object and annotate with `@Storage`
- Inject the Storage Manager as CDI object
- Use the `@Store` annotation to write changes to disk.