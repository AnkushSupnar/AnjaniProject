package kush.POJO;


public class TempTransaction {
	int id;
	String itemName;
	float qty;
	float rate;
	float amt;
	int tableNo;
	int waitorId;
	float printQty;
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
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public int getWaitorId() {
		return waitorId;
	}
	public void setWaitorId(int waitorId) {
		this.waitorId = waitorId;
	}
	public float getPrintQty() {
		return printQty;
	}
	public void setPrintQty(float printQty) {
		this.printQty = printQty;
	}
	@Override
	public String toString() {
		return "TempTransaction [id=" + id + ", itemName=" + itemName + ", qty=" + qty + ", rate=" + rate + ", amt="
				+ amt + ", tableNo=" + tableNo + ", waitorId=" + waitorId + ", printQty=" + printQty + "]";
	}

}
