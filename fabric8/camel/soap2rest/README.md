Offers CRUD Service: Microservice Using JAX-RS (Java API for RESTful Web Services)
==========================================================================
Level: Basic    
Technologies: CXF, Camel   
Summary: The `soap2rest` microservice demonstrates a simple translation between soap and rest, using a Camel Route.
It has been designed to work pointing to the `shipping-ws` microservice  
Target Product: Fuse Integration Services  
Source: 

### Running the application


To run the application locally, just use:

 mvn exec:java -DSHIPPING_SOAP_ENDPOINT={shipping-ws-host}:{port}




## Deploy On Openshift


In order to deploy on Openshift, just create a new fis-java-openshift application, pointing it to the correct github location. 
All was tested with Openshift 3.2 and 3.3 . 
Be sure to add the environment variable SHIPPING_SOAP_ENDPOINT pointing to the correct location (where your shipping-ws is located)
        

        
## Connecting from a client

Open a browser or use curl to connect to the REST service endpoints like follows:

```
localhost:8080/api/shipping/{country}

```

Only GET is implemented.

The service will then bridge the call vs  /ShippingService SOAP service (see "shipping-ws" project)

The answer is a plain simple value containing the shipping costs

The implemented countries right now are :

IT
UK
DE
FR
ES
US
CH

Everything else will come back with a default value
