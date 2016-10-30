package it.redhat.osd.model;

import java.io.Serializable;

public class Product implements Serializable{
	
	    private String id;
	    private String description;
	    private int price;

	    public Product() {
	    }

	    
	    
	    public Product(String id, String description, int price) {
			super();
			this.id = id;
			this.description = description;
			this.price = price;
		}



		public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public int getPrice() {
	        return price;
	    }

	    public void setPrice(int price) {
	        this.price = price;
	    }



		@Override
		public String toString() {
			return "Product [id=" + id + ", description=" + description + ", price=" + price + "]";
		}
	    
	}
