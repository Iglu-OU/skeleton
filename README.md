## The Modular Monolith
This is an example how to setup a modular monolith style project with Gradle and Spring Boot.
In short, the key point are the following:

### The java-library plugin
Hide implementation dependencies of modules from dependants.
 
### The dependency-management plugin
Keep dependency versions in sync between modules.

### Main module
One module must be main, with spring boot gradle plugin and a class with a psvm method.

### Default, i.e. package-private visibility
Hide implementation classes from dependants. It is better if you also move implementation classes to a separate package.
