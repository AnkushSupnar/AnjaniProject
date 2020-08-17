package kush.POJO;


public class CategoryMaster {

	int id;
	String name,stock;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "CategoryMaster [id=" + id + ", name=" + name + ", stock=" + stock + "]";
	}
}
