package Factory;

import Database.Connect;
import model.Order;
import model.Product;

public class OrderFactory {
	static Connect db = Connect.getInstance();
	
	
	//create
	public static Order createOrder(String id,String customer,Product product,int quantity,String paymentType,double price) {
		return new Order(id, customer, product, quantity, paymentType, price);
	}
	
	//read
	public static void viewOrder() {
		System.out.println();
		if(db.getOrderList().isEmpty()) {
			System.out.println("None Order Made");
			System.out.println();
			return;
		}else {
			for(Order o: db.getOrderList()) {
				System.out.println("Order ID: "+ o.getId());
				System.out.println("Customer Name: "+o.getCustomer());
				System.out.println("Product Name: "+o.getProduct().getName());
				System.out.println("Quantity : "+o.getQuantity());
				System.out.println("PaymentType : " + o.getPaymentType());
				System.out.println("Price: $"+o.getPrice());
				System.out.println();
			}
		}
	}
	
	public static Order executeOrder(String ID) {
		for(int i = 0;i<db.getOrderList().size();i++) {
			if(ID.equals(db.getOrderList().get(i).getId())) {
				return db.getOrderList().get(i);
			}
		}
		return null;	
	}
	
	
}
