# Osd-microservices-apps

Open Source Day Microservices apps

Here the list of all the exposed endpoints:

======================================

## Users:

### Endpoints

```
localhost:8080/api/users
localhost:8080/api/users{id} 
```

GET, POST and DELETE are implemented.



### JSON DataModel

JSON objects are formatted using following template:

```
{"id":"1","firstname":"Ugo","lastname":"Landini","mail":"ulandini@redhat.com"}
```

If object is not found, HTTP 404 will be returned.

======================================

## Offers:

### Endpoints

```
localhost:8080/api/offers
localhost:8080/api/offer/{id} 
```

GET, POST and DELETE are implemented.

If passing ID as path param, it must be equals to ID into JSON payload. If not, ID path param will overwrite ID present into JSON payload.


### JSON DataModel

JSON objects are formatted using following template:

```
{"id":"9","description":"My Fabulous Promo ","discount":30}
```

If object is not found, HTTP 404 will be returned.

======================================

## Products:

### Endpoints

```
localhost:8080/api/products
localhost:8080/api/products/{id} 
```

GET, POST and DELETE are implemented.

If passing ID as path param, it must be equals to ID into JSON payload. If not, ID path param will overwrite ID present into JSON payload.


### JSON DataModel

JSON objects are formatted using following template:

```
{"id":"3","description":"Description 3","price":300}
```

If object is not found, HTTP 404 will be returned.

======================================

## Orders:

### Endpoints

```
localhost:8080/brms/ds/order
```

only POST is available. You must post an offer object, to have another object with the correct discount, according to the rule


### JSON DataModel

JSON objects are formatted using following template:

```
{"customer_id":1,"product_id":"pippo","quantity":3,"discount":3}
```

======================================

## Soap2Rest (Shipping):


### Endpoints

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


