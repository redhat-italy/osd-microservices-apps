Offers CRUD Service: Microservice Using JAX-RS (Java API for RESTful Web Services)
==========================================================================
Level: Basic    
Technologies: CDI, JAX-RS    
Summary: The `Offers` microservice demonstrates a simple CRUD application, bundled and deployed as a WAR, that uses *JAX-RS* to CRUD offers objects.    
Target Product: JBoss EAP  
Source: 

## What is it?

=======

The `Offers` quickstart demonstrates the use of *CDI* and *JAX-RS* in Red Hat JBoss Enterprise Application Platform.


## System requirements

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later.



## Start the JBoss EAP Server


1. Open a command prompt and navigate to the root of the JBoss EAP directory.
2. The following shows the command line to start the server:

        For Linux:   EAP7_HOME/bin/standalone.sh
        For Windows: EAP7_HOME\bin\standalone.bat

 
## Build and Deploy the Quickstart


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/jboss-helloworld-rs.war` to the running instance of the server.



## Undeploy the Archive


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy
        
## JSON DataModel

JSON object are formatted using following template:

```
{"id":"9","description":"My Fabulous Promo ","discount":30}
```

If object is not found, HTTP 204 will be returned.

        
## Connecting from a client

Open a browser or use curl to connect to the REST service endpoints like follows:

```
localhost:8080/api/offers
localhost:8080/api/offer/{id} 
```

GET, POST and DELETE are implemented.

If passing ID as path param, it must be equals to ID into JSON payload. If not, ID path param will overwrite ID present into JSON payload.

<!-- Build and Deploy the Quickstart to OpenShift - Coming soon! -->
