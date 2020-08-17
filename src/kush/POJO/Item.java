package kush.POJO;

public class Item {
	int ID;
	String ItemName;
	int Catid;
	float Rate;
	int ItemCode;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public int getCatid() {
		return Catid;
	}
	public void setCatid(int catid) {
		Catid = catid;
	}
	public float getRate() {
		return Rate;
	}
	public void setRate(float rate) {
		Rate = rate;
	}
	public int getItemCode() {
		return ItemCode;
	}
	public void setItemCode(int itemCode) {
		ItemCode = itemCode;
	}
	@Override
	public String toString() {
		return "Item [ID=" + ID + ", ItemName=" + ItemName + ", Catid=" + Catid + ", Rate=" + Rate + ", ItemCode="
				+ ItemCode + "]";
	}
}
