package it.redhat.osd.model;

public class Order {

    public String customer_id;
    public String product_id;
    public int quantity;
    public int discount;

    public Order (){
        quantity = 0;
        discount = 0;
    }

    public Order setCustomerId(String id){
        this.customer_id = id;
        return this;
    }

    public Order setProductId(String id){
        this.product_id = id;
        return this;
    }

    public Order setQuantity(int quantity){
        this.quantity = quantity;
        return this;
    }

    public Order setDiscount(int discount){
        this.discount = discount;
        return this;
    }

}
