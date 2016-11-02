/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function UsersCtrl($scope, $http, Users) {

    // Define a refresh function, that updates the data from the REST service
    $scope.refreshUsers = function() {
        $scope.members = Users.query();
    };

    $scope.deleteUser = function() {
        $scope.clearMessages();
        var self = this;
        Users.delete({memberId: self.member.id}, function(data) {

            $scope.refreshUsers();


        }, function(result) {
            if ((result.status == 409) || (result.status == 400)) {
                $scope.errors = result.data;
            } else {
                $scope.errorMessages = [ 'Unknown  server error' ];
            }
        });
    }

    // Call the refresh() function, to populate the list of members
    $scope.refreshUsers();




    // Set the default orderBy to the name property
    $scope.orderBy = 'name';
}

function HomeCtrl($scope, $http, $location, Products, Users, Offers, Quote, sharedProperties) {

    $scope.refreshProds = function() {
        $scope.products = Products.query();
        $scope.offers = Offers.query();
    }

     // Define a clearMessages function that resets the values of the error and
        // success messages.
        $scope.clearMessages = function () {
            $scope.successMessages = '';
            $scope.errorMessages = '';
            $scope.errors = {};
        };

        // Define a reset function, that clears the prototype newMember object, and
        // consequently, the form
        $scope.reset = function() {
            // Sets the form to it's pristine state
            if($scope.regForm) {
                $scope.regForm.$setPristine();
            }
            // Clear input fields. If $scope.newMember was set to an empty object {},
            // then invalid form values would not be reset.
            // By specifying all properties, input fields with invalid values are also reset.
            $scope.newMember = {firstname: "", lastname: "", email: "", product: "", quantity: "", offer: ""};
            $scope.newQuote = {customer_id: "", product_id: "", quantity: "", discount: ""};

            // clear messages
            $scope.clearMessages();



        };

        // Define a register function, which adds the member using the REST service,
        // and displays any error messages
        $scope.register = function() {
            $scope.clearMessages();

            $scope.newMember.offer = $scope.selectedOffer.id

            $scope.newQuote.customer_id = $scope.newMember.lastname;
            $scope.newQuote.product_id = $scope.newMember.product;
            $scope.newQuote.quantity = $scope.newMember.quantity;
            $scope.newQuote.discount = $scope.selectedOffer.discount;


            Quote.save($scope.newQuote, function(data) {

                // Clear the form
                //$scope.reset();

                console.log(data);

                $scope.saveShip = sharedProperties.getProperty();

                $scope.saveShip.firstname = $scope.newMember.firstname;
                $scope.saveShip.lastname = $scope.newMember.lastname;

                prod = $scope.products.length;
                console.log("Prodottì: "+prod);

                console.log("searching for products...");
                for(i = 0; i < prod ; i++) {
                    if($scope.products[i].id == data.product_id) {
                        console.log("found"+product);
                        $scope.saveShip.product = $scope.products[i];
                        $scope.saveShip.price = ($scope.products[i].price * data.quantity);
                        $scope.saveShip.price = $scope.saveShip.price - ($scope.saveShip.price * (data.discount / 100));
                    }
                }
                console.log("saved for shipment: "+$scope.saveShip)
                sharedProperties.setProperty($scope.saveShip);

                Users.save($scope.newMember, function(data) {

                        // Clear the form
                        $scope.reset();

                        //change view
                        $location.path("/ship");

                        // mark success on the registration form

                    }, function(result) {
                        if ((result.status == 409) || (result.status == 400)) {
                            $scope.errors = result.data;
                            $scope.errorMessages = [result.data]
                        } else {
                            $scope.errorMessages = [ 'Unknown  server error' ];
                        }
                })

                // mark success on the registration form

                }, function(result) {
                    if ((result.status == 409) || (result.status == 400)) {
                        $scope.errors = result.data;
                        $scope.errorMessages = [result.data]
                    } else {
                        $scope.errorMessages = [ 'Unknown  server error' ];
                    }
                });







        };

        $scope.assignOffer = function(offer) {

            $scope.selectedOffer = offer;
        }

        //Initialize scope variables
        $scope.selectedOffer = "";

        // Initialize newMember here to prevent Angular from sending a request
        // without a proper Content-Type.
        $scope.reset();

        //refresh product list
        $scope.refreshProds();

}

function ShipCtrl($scope, $http, $location, Users, sharedProperties, Countries, Shipping) {

    $scope.refeshCountries = function() {
        $scope.countries = Countries.query();
    }

    $scope.checkShipping = function() {

        var self = this;
        Shipping.get({Country: self.shippingInfo.country}, function(data) {

            $scope.shippingInfo.shippingPrice = data.string;
            $scope.orderEnable = true;

        }
        ,function(result) {
             if ((result.status == 409) || (result.status == 400)) {
                 $scope.errors = result.data;
             } else {
                 $scope.errorMessages = [ 'Unknown  server error' ];
             }
         });
    }

    $scope.order = function() {
        $location.path("/ordered")
    }

    console.log(sharedProperties.getProperty());

    //disable order button
    $scope.orderEnable = false;

    $scope.preShipping = sharedProperties.getProperty();

    $scope.shippingInfo = {firstname: "", lastname: "", product: "", price: "", address_line1: "", code: "", country: ""};
    $scope.shippingInfo.firstname = $scope.preShipping.firstname;
    $scope.shippingInfo.lastname = $scope.preShipping.lastname;
    $scope.shippingInfo.price = $scope.preShipping.price;
    if($scope.preShipping.product != undefined) {
        $scope.shippingInfo.product = $scope.preShipping.product.description;
    }

    $scope.refeshCountries();

}
