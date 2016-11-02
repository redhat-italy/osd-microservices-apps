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

======================================

## Starting locally all services:

### USERS
```
http://localhost:8081/api/users
cd ./go/users/
./users -port=8081
```

### PRODUCTS
```
http://localhost:8082/api/products/
cd ./wildfly-swarm/products/
mvn wildfly-swarm:run 
```

### OFFERS
```
http://localhost:8180/api/offers
http://localhost:8180/api/shipping/countries

[Change the dir to your EAP install dir]
export JBOSS_HOME="/home/xyz/Documents/Demo/osday/jboss-eap-7.0/"
cd $JBOSS_HOME/bin
rm -rf ../offers/
cp -r ../standalone ../offers
./standalone.sh -Djboss.server.base.dir=$JBOSS_HOME/offers -Djboss.socket.binding.port-offset=100

[IN ANOTHER SHELL]
cd ./eap7/offers-ha/
mvn clean install -Dmaven.test.skip=true
mvn wildfly:deploy -Dwildfly.port=10090
```

### ORDER
```
http://localhost:8380/api/brms/ds/order
cd ./spring-boot/brms/
mvn clean install -Dmaven.test.skip=true
mvn spring-boot:run  -Drun.jvmArguments='-Dserver.port=8380'

[TO TEST, IN ANOTHER SHELL]
curl -X POST -H 'Content-Type: application/json' -d '{"customer_id":"Test","product_id":"Test","quantity":"6","discount":0}' 'http://localhost:8380/api/brms/ds/order'
```

### SHIPPING
```
http://localhost:8680/api/shipping/
export JBOSS_HOME="/home/xyz/Documents/Demo/osday/jboss-eap-7.0/"
cd $JBOSS_HOME/bin
rm -rf ../shippingws
cp -r ../standalone ../shippingws
./standalone.sh -Djboss.server.base.dir=$JBOSS_HOME/shippingws -Djboss.socket.binding.port-offset=500


[IN ANOTHER SHELL]
cd ./eap7/shipping-ws/
mvn clean install -Dmaven.test.skip=true
mvn wildfly:deploy -Dwildfly.port=10490
cd ../../fabric8/camel/soap2rest/
mvn exec:java -DSHIPPING_SOAP_ENDPOINT=localhost:8580 -DBINDING_PORT=8680
``


### FRONTEND
```
cd ./vertx/frontend/
mvn clean install -Dmaven.test.skip=true
mvn package
java -jar ./target/simple-web-application-3.3.3-fat.jar 
```







