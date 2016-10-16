package main

import (
	"encoding/json"
	"log"
	"net/http"
	"github.com/gorilla/mux"
	"github.com/gorilla/handlers"
	"flag"
)

type User struct {
	ID        string   `json:"id,omitempty"`
	Firstname string   `json:"firstname,omitempty"`
	Lastname  string   `json:"lastname,omitempty"`
	Mail      string `json:"mail,omitempty"`
}

var users []User

func GetPersonEndpoint(w http.ResponseWriter, req *http.Request) {
	params := mux.Vars(req)
	for _, item := range users {
		if item.ID == params["id"] {
			json.NewEncoder(w).Encode(item)
			return
		}
	}
	json.NewEncoder(w).Encode(&User{})
}

func GetPeopleEndpoint(w http.ResponseWriter, req *http.Request) {
	json.NewEncoder(w).Encode(users)
}

func CreatePersonEndpoint(w http.ResponseWriter, req *http.Request) {
	params := mux.Vars(req)
	var user User
	_ = json.NewDecoder(req.Body).Decode(&user)
	user.ID = params["id"]
	users = append(users, user)
	json.NewEncoder(w).Encode(users)
}

func DeletePersonEndpoint(w http.ResponseWriter, req *http.Request) {
	params := mux.Vars(req)
	for index, item := range users {
		if item.ID == params["id"] {
			users = append(users[:index], users[index + 1:]...)
			break
		}
	}
	json.NewEncoder(w).Encode(users)
}

func main() {
	port := flag.String("port", "8080", "HTTP Port")
	flag.Parse()
	router := mux.NewRouter()
	users = append(users, User{ID: "1", Firstname: "Ugo", Lastname: "Landini", Mail: "ulandini@redhat.com"})
	users = append(users, User{ID: "2", Firstname: "Samuele", Lastname: "Dell'Angelo", Mail: "sdellang@redhat.com"})
	users = append(users, User{ID: "3", Firstname: "Andrea", Lastname: "Leoncini", Mail: "aleoncin@redhat.com"})
	users = append(users, User{ID: "4", Firstname: "Giuseppe", Lastname: "Bonocore", Mail: "gbonocor@redhat.com"})
	users = append(users, User{ID: "5", Firstname: "Filippo", Lastname: "Calà", Mail: "fcala@redhat.com"})
	users = append(users, User{ID: "6", Firstname: "Luca", Lastname: "Bigotta", Mail: "lbigotta@redhat.com"})

	router.HandleFunc("/api/users", GetPeopleEndpoint).Methods("GET")
	router.HandleFunc("/api/users/{id}", GetPersonEndpoint).Methods("GET")
	router.HandleFunc("/api/users/{id}", CreatePersonEndpoint).Methods("POST")
	router.HandleFunc("/api/users/{id}", DeletePersonEndpoint).Methods("DELETE")

	log.Fatal(http.ListenAndServe(":" + *port, handlers.CORS(handlers.AllowedMethods([]string{"DELETE", "POST", "GET", "HEAD", "OPTIONS" }))(router)))

}