package kush.POJO;

public class Table {
	int id;
	String TableName, DESCRIPTION;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	@Override
	public String toString() {
		return "Table [id=" + id + ", TableName=" + TableName + ", DESCRIPTION=" + DESCRIPTION + "]";
	}
}
