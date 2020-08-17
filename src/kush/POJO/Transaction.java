package kush.POJO;



public class Transaction {
	int id;
	String ItemName;
	float qty;
	float rate;
	float amt;
	int bill;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", ItemName=" + ItemName + ", qty=" + qty + ", rate=" + rate + ", amt=" + amt
				+ ", bill=" + bill + "]";
	}
}
