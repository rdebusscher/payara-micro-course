# Jakarta EE Lab

We will make a small shop kind of application where we can associate products with a user.  This simplified version only has the following features

- Get the list of products, possibly filtered by price and category.
- Adding and removing products for the user.
- Some business rules apply, like at maximum 2 bikes, and 1 for other product categories.

Within the `commands.txt` file, the Curl commands are listed that should work against your implementation (or indicate if they should fail.)

The repository contains already some classes so that not all need to be implemented from scratch
https://github.com/rdebusscher/payara-micro-course/labs/jakarta

The `solution` branch contains a possible solution for this lab.

Things that are expected to be used in this lab.

- Define JAX-RS application
- Define JAX-RS resource classes
- Using different HTTP methods (GET, POST, DELETE)
- Using `List<String>` for a list of Parameters
- Validation of parameters (at JAX-RS level and business level)
- Injection of CDI beans.
