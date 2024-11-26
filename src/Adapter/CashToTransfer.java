package Adapter;

public class CashToTransfer extends Cash{
	private Transfer transfer;

	public CashToTransfer(double price) {
		super(price);
		this.transfer = new Transfer(price);
	}
	
	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return (this.transfer.getPrice() *0.1) + this.transfer.getPrice() ;
	}
	
	
}
