package Database;

import java.util.ArrayList;


import model.Order;
import model.Product;

public class Connect {
	private ArrayList<Product> productList;
	private ArrayList<Order> orderList;
	
	private static volatile Connect instance = null;
	
	private Connect() {
		productList = new ArrayList<>();
		orderList = new ArrayList<>();
	}
	
	public static Connect getInstance() {
		if(instance == null) {
			synchronized (Connect.class) {
				if(instance == null) {
					instance = new Connect();
				}
			}
		}
		return instance;
	}
	
	public void addProduct(Product p) {
		productList.add(p);
		System.out.println("Product Added!!");
	}
	
	public ArrayList<Product> getProductList(){
		return productList;
	}
	
	public void addOrder(Order o) {
		orderList.add(o);
		System.out.println("Order Added!!");
	}
	
	public void deleteOrder(Order o) {
		orderList.remove(o);
		System.out.println("Order Delete");
	}
	
	public ArrayList<Order> getOrderList(){
		return orderList;
	}
}
