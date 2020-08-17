package kush.POJO;



public class PurchaseParty {
int id;
String name,address,contact;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
@Override
public String toString() {
	return "PurchaseParty [id=" + id + ", name=" + name + ", address=" + address + ", contact=" + contact + "]";
}

}
