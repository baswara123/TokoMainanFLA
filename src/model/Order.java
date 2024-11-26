package model;

public class Order {

	private String id;
	private String customer;
	private Product product;
	private int quantity;
	private String paymentType;
	private double price;

	public Order(String id, String customer, Product product, int quantity, String paymentType, double price) {
		super();
		this.id = id;
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.paymentType = paymentType;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
