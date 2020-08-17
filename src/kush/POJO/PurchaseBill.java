package kush.POJO;

import java.sql.Date;

public class PurchaseBill {
	int BillNo;
	int PartyId;
	Double Amount;
	Date Date;
	Double GST;
	Double OtherTax;
	String ReffNo;
	String Pay;
	int PayId;

	public int getBillNo() {
		return BillNo;
	}

	public void setBillNo(int billNo) {
		BillNo = billNo;
	}

	public int getPartyId() {
		return PartyId;
	}

	public void setPartyId(int partyId) {
		PartyId = partyId;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public Double getGST() {
		return GST;
	}

	public void setGST(Double gST) {
		GST = gST;
	}

	public Double getOtherTax() {
		return OtherTax;
	}

	public void setOtherTax(Double otherTax) {
		OtherTax = otherTax;
	}

	public String getReffNo() {
		return ReffNo;
	}

	public void setReffNo(String reffNo) {
		ReffNo = reffNo;
	}

	public String getPay() {
		return Pay;
	}

	public void setPay(String pay) {
		Pay = pay;
	}

	public int getPayId() {
		return PayId;
	}

	public void setPayId(int payId) {
		PayId = payId;
	}

	@Override
	public String toString() {
		return "PurchaseBill [BillNo=" + BillNo + ", PartyId=" + PartyId + ", Amount=" + Amount + ", Date=" + Date
				+ ", GST=" + GST + ", OtherTax=" + OtherTax + ", ReffNo=" + ReffNo + ", Pay=" + Pay + ", PayId=" + PayId
				+ "]";
	}
}
