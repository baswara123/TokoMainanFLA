package View;

import java.util.Random;
import java.util.Scanner;

import Adapter.Cash;
import Adapter.CashToQris;
import Adapter.CashToTransfer;
import Database.Connect;
import Factory.OrderFactory;
import Factory.ProductFactory;
import model.Order;
import model.Product;

public class Main {
	static Scanner scan = new Scanner(System.in);
	static Random rand = new Random();
	static Connect db = Connect.getInstance();

	private void cls() {
		for (int i = 0; i < 10; i++) {
			System.out.println("\n\n\n");
		}
	}

	public void productmanagement() {
		cls();
		int choose;
		do {
			System.out.println("Product Managment");
			System.out.println("1. Add Product");
			System.out.println("2. View Product");
			System.out.println("3. Update Product Stock");
			System.out.println("4. Back");
			System.out.print(">>");
			choose = scan.nextInt();
			switch (choose) {
			case 1:
				addProduct();
				break;
			case 2:
				cls();
				viewProduct();
				break;
			case 3:
				updateStock();
				break;
			}
		} while (choose != 4);
	}

	public void orderManagement() {
		cls();
		int choose;
		do {
			System.out.println("Order Managment");
			System.out.println("1. Add Order");
			System.out.println("2. View Order");
			System.out.println("3. Back");
			System.out.print(">>");
			choose = scan.nextInt();
			switch (choose) {
			case 1:
				addOrder();
				break;
			case 2:
				viewOrder();
				break;
			}
		} while (choose != 3);
	}

	public void addProduct() {
		cls();
		String id, name, type;
		int stock;
		double price;
		int battery;

		int randomInt = rand.nextInt(100);
		String randomIntFormatted = String.format("%03d", randomInt);

		do {
			System.out.print("Input Product Name [5-30 Character]: ");
			name = scan.next();
		} while (name.length() < 5 || name.length() > 30);

		do {
			System.out.print("Input Product Type [Electronic | NonElectronic][Case Sensitive]: ");
			type = scan.next();
		} while (!type.equals("Electronic") && !type.equals("NonElectronic"));

		if (type.equals("Electronic")) {
			do {
				System.out.println("Input Battery [must be more than 1]: ");
				battery = scan.nextInt();
			} while (battery < 1);
		} else {
			battery = 0;
		}

		char firstid = Character.toUpperCase(name.charAt(0));
		char secid = Character.toUpperCase(name.charAt(1));
		char thirdid = Character.toUpperCase(type.charAt(0));

		id = "" + firstid + secid + thirdid + randomIntFormatted;

		do {
			System.out.print("Input Stock [Must be Positive Integer]: ");
			stock = scan.nextInt();
		} while (stock <= 0);

		do {
			System.out.print("Input Price [1.0-10000.0] :");
			price = scan.nextDouble();
		} while (price < 1 || price > 10000);

		Product p = ProductFactory.createProduct(type, name, id, stock, battery, price);
		db.addProduct(p);
		System.out.println("ID : " + id);
	}

	public void viewProduct() {
		ProductFactory.viewProduct();
	}

	public void updateStock() {
		
		boolean foundid = false;
		String id;
		int stock;

		if (db.getProductList().isEmpty()) {
			cls();
			System.out.println("No Product");
			System.out.println();

		} else {
			cls();
			do {
				System.out.print("Enter Product ID: ");
				id = scan.next();

				for (int i = 0; i < db.getProductList().size(); i++) {
					if (id.equals(db.getProductList().get(i).getId())) {
						foundid = true;
					}

					else if (!foundid) {
						System.out.println("Product not found, try again.");
					}
				}

			} while (!foundid);

			do {
				System.out.print("Input Update Stock [Must be Positive Integer]: ");
				stock = scan.nextInt();
			} while (stock <= 0);
			
			ProductFactory.updateProduct(id, stock);
			System.out.println("PRoduct Updated");
			
		}
	}

	public void addOrder() {
		if (db.getProductList().isEmpty()) {
			cls();
			System.out.println("Cannot Make Order There no Product Here");
			System.out.println();

		} else {
			cls();
			viewProduct();
			boolean found = false;
			boolean validQuantity = false;
			String name, id, paymentType, productName;
			int quantity;
			Product selectedProduct = null;
			double price, finalPrice;

			int randomInt = rand.nextInt(100);
			String randomIntFormatted = String.format("%03d", randomInt);

			do {
				System.out.print("Enter Customer Name [Length 3-10] : ");
				name = scan.next();
			} while (name.length() < 3 || name.length() > 10);

			do {
				System.out.print("Enter Product Name: ");
				productName = scan.next();

				for (int i = 0; i < db.getProductList().size(); i++) {
					if (productName.equals(db.getProductList().get(i).getName())) {
						found = true;
						selectedProduct = db.getProductList().get(i);

						break;
					}
					else if (!found) {
						System.out.println("Product not found, try again.");
					}
				}
			} while (!found);
			price = selectedProduct.getPrice();

			do {
				System.out.print("Enter Quantity: ");
				quantity = scan.nextInt();

				if (quantity > selectedProduct.getStock()) {
					System.out.println("Not enough stock available. Available stock: " + selectedProduct.getStock());

				} else {
					validQuantity = true;
				}
			} while (!validQuantity);

			do {
				System.out.print("What kind of payment [Cash | Transfer | Qris][case sensitive]: ");
				paymentType = scan.next();
			} while (!paymentType.equals("Cash") && !paymentType.equals("Transfer") && !paymentType.equals("Qris"));

			char firstid = Character.toUpperCase(name.charAt(0));
			char secid = Character.toUpperCase(selectedProduct.getName().charAt(0));
			char thirdid = Character.toUpperCase(paymentType.charAt(0));
			id = "" + firstid + secid + thirdid + randomIntFormatted;

			Cash cash = null;
			switch (paymentType) {
			case "Cash":
				cash = new Cash(price);
				break;
			case "Transfer":
				cash = new CashToTransfer(price);
				break;
			case "Qris":
				cash = new CashToQris(price);
				break;
			default:
				System.out.println("Invalid payment type!");
				return;
			}
			finalPrice = cash.getPrice() * quantity;

			Order o = OrderFactory.createOrder(id, name, selectedProduct, quantity, paymentType, finalPrice);
			db.addOrder(o);
			System.out.println("Order ID : " + id);
			System.out.println();
		}

	}

	public void viewOrder() {
		OrderFactory.viewOrder();
	}

	public Main() {
		int choice;
		do {
			cls();
			System.out.println("Toy Store :D");
			System.out.println("==============================");
			System.out.println("1. Product Management");
			System.out.println("2. Order Management");
			System.out.println("3. Exit");
			System.out.print(">>");
			choice = scan.nextInt();
			switch (choice) {
			case 1:
				productmanagement();
				break;
			case 2:
				orderManagement();
				break;
			case 3:
				System.out.println("Bye bye");
				break;
			default:
				System.out.println("Wrong input");
				break;
			}
		} while (choice != 3);
	}

	public static void main(String[] args) {
		new Main();
	}

}
