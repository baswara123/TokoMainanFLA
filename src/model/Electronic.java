package model;

public class Electronic extends Product {

	private int battery;

	public Electronic(String id, String name, double price, int stock, int battery) {
		super(id, name, price, stock);
		this.battery = battery;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

}
