date-service
============

Rest-based date/timezone service to demonstrate various JEE techniques. It also doubles as a small kitchen-sink example to store configuration and examples.

This module contains the following examples:
* CDI injection
* Jackson JSON/object mapping
* JAX-RS providing a simple REST GET service
* JAX-RS client API
* JodaTime libraries in place of existing java.util.Date
* JPA Entity Model / Database configuration
* Startup/Singleton EJB providing basic datase bootstrap
* Arquillian for integration tests
* Groovy/JUnit for testing

The intent for this is to be as easy as possible to pull and test the system. To this end, an embedded JEE server (Glassfish) and database (H2) were used. However this came with limitations. Glassfish Embedded is provided as an uberjar, which causes conflicts with existing jars the project might choose to use. For example, Glassfish uses Guava v13 and will conflict with later versions. I also noted an inability to inject a PersistenceContext into an ApplicationScoped bean (which is possible in JBoss AS).
