users.go: users microservices using Go
======================================

## Dependencies

```
go get github.com/gorilla/mux
go get github.com/gorilla/handlers
go get github.com/rs/cors
```
## Usage (from source)

```
go run users.go
```

## Usage (from binary)

```
go build users.go
./users
```

If you want to change the default port (8080) just add it as a parameter
```
./users -port=8081
```

## Connecting from a client

Open a browser or use curl to connect to the REST server

```
curl localhost:8080/api/users
curl localhost:8080/api/user/{id}
```

You can use POST and DELETE too.

## Building a Docker image

# Standard Docker image

This image is built using the official golang image
```
sudo docker build .
```

# Minimal Docker image

This image is built just with the binary and is just a few Mb.
```
CGO_ENABLED=0 go build -a -installsuffix cgo -o users .
sudo docker build -f Dockerfile.minimal .
```
