package kush.POJO;



public class PurchaseTransaction {
int id;
String itemName;
int qty;
float rate;
float amount;
int billNo;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public float getRate() {
	return rate;
}
public void setRate(float rate) {
	this.rate = rate;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public int getBillNo() {
	return billNo;
}
public void setBillNo(int billNo) {
	this.billNo = billNo;
}
@Override
public String toString() {
	return "PurchaseTransaction [id=" + id + ", itemName=" + itemName + ", qty=" + qty + ", rate=" + rate + ", amount="
			+ amount + ", billNo=" + billNo + "]";
}
}
