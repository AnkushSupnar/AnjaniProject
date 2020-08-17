package kush.POJO;

public class Bill {
	int BillNO;
	float BillAmt, Discount;
	int CUstomerID, Waitorid, Tableno, userid;
	String BillDate, Paymode, status, Time;
	public int getBillNO() {
		return BillNO;
	}
	public void setBillNO(int billNO) {
		BillNO = billNO;
	}
	public float getBillAmt() {
		return BillAmt;
	}
	public void setBillAmt(float billAmt) {
		BillAmt = billAmt;
	}
	public float getDiscount() {
		return Discount;
	}
	public void setDiscount(float discount) {
		Discount = discount;
	}
	public int getCUstomerID() {
		return CUstomerID;
	}
	public void setCUstomerID(int cUstomerID) {
		CUstomerID = cUstomerID;
	}
	public int getWaitorid() {
		return Waitorid;
	}
	public void setWaitorid(int waitorid) {
		Waitorid = waitorid;
	}
	public int getTableno() {
		return Tableno;
	}
	public void setTableno(int tableno) {
		Tableno = tableno;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBillDate() {
		return BillDate;
	}
	public void setBillDate(String billDate) {
		BillDate = billDate;
	}
	public String getPaymode() {
		return Paymode;
	}
	public void setPaymode(String paymode) {
		Paymode = paymode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	@Override
	public String toString() {
		return "Bill [BillNO=" + BillNO + ", BillAmt=" + BillAmt + ", Discount=" + Discount + ", CUstomerID="
				+ CUstomerID + ", Waitorid=" + Waitorid + ", Tableno=" + Tableno + ", userid=" + userid + ", BillDate="
				+ BillDate + ", Paymode=" + Paymode + ", status=" + status + ", Time=" + Time + "]";
	}
}
