package kush.POJO;

public class ItemStock {

	int Id;
	String ItemName;
	float Stock;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public float getStock() {
		return Stock;
	}
	public void setStock(float stock) {
		Stock = stock;
	}
	@Override
	public String toString() {
		return "ItemStock [Id=" + Id + ", ItemName=" + ItemName + ", Stock=" + Stock + "]";
	}
}
