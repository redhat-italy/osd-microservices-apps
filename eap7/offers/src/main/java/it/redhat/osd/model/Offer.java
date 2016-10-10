package it.redhat.osd.model;

public class Offer {
	
	private String id;
	private String description;
	private int discount;
	
	
	
	public Offer() {
		super();
	}
	public Offer(String id, String description, int discount) {
		super();
		this.id = id;
		this.description = description;
		this.discount = discount;
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
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", description=" + description + ", discount=" + discount + "]";
	}
	
	

}
