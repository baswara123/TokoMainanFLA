package Factory;

import Database.Connect;
import model.Electronic;
import model.NonElectronic;
import model.Product;

public class ProductFactory {
	
	static Connect db = Connect.getInstance();
	
	//create
	public static Product createProduct(String type,String name,String id,int stock,int battery,double price) {
		if(type.equals("Electronic")) {
			return new Electronic(id, name, price, stock, battery);
			
		}else if(type.equals("NonElectronic")) {
			return new NonElectronic(id, name, price, stock);
		}
		return null;
	}
	
	//read
	public static void viewProduct() {
		System.out.println("==============================================");
		if(db.getProductList().isEmpty()) {
			System.out.println("None Product !!!!");
			System.out.println("==============================================");
			return;
		}else {
			for(Product p : db.getProductList()) {
				System.out.println("ID : "+p.getId());
				System.out.println("Name :"+p.getName());
				System.out.println("Price : $"+ p.getPrice());
				System.out.println("Stock : "+ p.getStock());
				System.out.println("Type: " + p.getClass().getSimpleName());
				if(p instanceof Electronic) {
					Electronic e = (Electronic) p;
					System.out.println("Battery : "+e.getBattery());
				}else {
					System.out.println("Battery: - ");
				}
				System.out.println("==============================================");
			}
		}
	}
	
	//update
	
	public static void updateProduct(String id, int stock) {
		for(Product p: db.getProductList()) {
			if(p.getId().equals(id)) {
				p.setStock(stock);
				System.out.println("Updated Product Details:");
		        System.out.println("ID: " + p.getId());
		        System.out.println("Name: " + p.getName());
		        System.out.println("Stock: " + p.getStock());
	            break;
			}
		}
	}
	
	
	
	
}
