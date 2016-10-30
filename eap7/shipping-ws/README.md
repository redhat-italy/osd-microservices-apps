Shipping SOAP Service: Microservice Using JAX-WS (Java API for RESTful Web Services)
==========================================================================
Level: Basic    
Technologies: CDI, JAX-WS    
Summary: The `Shipping` microservice demonstrates a simple SOAP WS, bundled and deployed as a WAR, that uses *JAX-WS*.    
Target Product: JBoss EAP  
Source: 

## What is it?

=======

The `Shipping` quickstart demonstrates the use of  *JAX-WS* in Red Hat JBoss Enterprise Application Platform.


## System requirements

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later.


## Deploy on EAP

### Start the JBoss EAP Server


1. Open a command prompt and navigate to the root of the JBoss EAP directory.
2. The following shows the command line to start the server:

        For Linux:   EAP7_HOME/bin/standalone.sh
        For Windows: EAP7_HOME\bin\standalone.bat

 
### Build and Deploy the application


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/jboss-helloworld-rs.war` to the running instance of the server.



### Undeploy the Archive


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy
        

## Deploy On Openshift


In order to deploy on Openshift, just create a new eap70-basic-s2i application, pointing it to the correct github location. 
All was tested with Openshift 3.2 and 3.3 . 
        

## Connecting from a client

The application has a welcome page, pointing to the WSDL of the service.
