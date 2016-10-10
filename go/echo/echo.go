package main

import (
	"flag"
	"encoding/json"
	"log"
	"net/http"
	"github.com/gorilla/mux"
)

type Person struct {
	Name string   `json:"name,omitempty"`
}

func GetPersonEndpoint(w http.ResponseWriter, req *http.Request) {
	params := mux.Vars(req)
	json.NewEncoder(w).Encode(Person{Name: params["name"]})
}

func main() {
	port := flag.String("port", "8080", "HTTP Port")
	flag.Parse()
	router := mux.NewRouter()
	router.HandleFunc("/api/echo/{name}", GetPersonEndpoint).Methods("GET")
	log.Fatal(http.ListenAndServe(":" + *port, router))
}
