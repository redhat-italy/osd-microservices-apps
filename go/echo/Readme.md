echo.go: Echo microservice using Go
===================================

## Usage (from source)

```
go run echo.go
```

## Usage (from binary)

```
go build echo.go
./echo
```

If you want to change the default port (8080) just add it as a parameter
```
./echo -port=8081
```

## Connecting from a client

Open a browser or use curl to connect to the REST server

```
curl localhost:8080/api/echo/foo
{"name":"foo"}
```