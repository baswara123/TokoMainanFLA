package Adapter;

public class CashToQris extends Cash{
	private Qris qris;

	public CashToQris(double price) {
		super(price);
		this.qris = new Qris(price);
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return (this.qris.getPrice()*0.05) + this.qris.getPrice();
	}
	
}
