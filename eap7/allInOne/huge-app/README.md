AllInONe Huge App: A demo legacy app, providing all the other microservices in a  monolithic approach
==========================================================================

## System requirements

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later.



## Start the JBoss EAP Server


1. Open a command prompt and navigate to the root of the JBoss EAP directory.
2. The following shows the command line to start the server:

        For Linux:   EAP7_HOME/bin/standalone.sh
        For Windows: EAP7_HOME\bin\standalone.bat
        
In order to start it, pass the correct env variable:
./standalone.sh -DSHIPPING_SOAP_ENDPOINT=localhost:8080

 
## Build and Deploy the Quickstart


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `target/ROOT.war` to the running instance of the server.



## Undeploy the Archive


1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy
        
## JSON DataModel and exposed endpoints

See "README_Endpoints"
