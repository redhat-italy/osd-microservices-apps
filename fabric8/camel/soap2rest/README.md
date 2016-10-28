Offers CRUD Service: Microservice Using JAX-RS (Java API for RESTful Web Services)
==========================================================================
Level: Basic    
Technologies: CXF, Camel   
Summary: The `soap2rest` microservice demonstrates a simple translation between soap and rest, using a Camel Route.    
Target Product: Fuse Integration Services  
Source: 


        
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
