package ankush;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class CommonMethods {
	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	public static PreparedStatement p = null;
	public static Font font = new Font("Times New Roman", Font.BOLD, 16);
	public static String fontName;
	public static String head = "Developed By Just Logic Software Private Limited ";

	// method to load driver and make connection
	public static void main(String[] args) {
		CommonMethods.openConnection();
		if (CommonMethods.con == null) {
			CommonMethods.closeConnections();
			CommonMethods.openConnection();
			System.out.println("not Connected");
			return;
		}

	}

	public static Properties loadPropertiesFile() throws Exception {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("D:\\Hotel Software\\hotel.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public static int openConnection() {
		try {
			if (con == null) {
				// System.out.println("Not Connected");
				// new ApplicationSetting();
			}

			// Connect with mysql
			Properties prop = CommonMethods.loadPropertiesFile();
			String driverClass = prop.getProperty("MYSQLJDBC.driver");
			String url = prop.getProperty("MYSQLJDBC.url");
			String username = prop.getProperty("MYSQLJDBC.username");
			String password = prop.getProperty("MYSQLJDBC.password");
			fontName = prop.getProperty("Bill.Font");
			Class.forName(driverClass).newInstance();
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, username, password);
			// con = DriverManager.getConnection("jdbc:mysql://ankush-PC:3306/hotel",
			// "root", "2355");
			if (con != null) {
				System.out.println("connection created successfully using properties file\nTo " + url);
			} else {
				System.out.println(" unable to create connection");
			}

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
			return 0;
		} finally {
			try {
				if (con != null) {
					// con.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return 1;
	}

	public static String getIP() {
		String ip = "";
		try {
			File file = new File("D:\\Hotel Software\\license.txt");
			file.mkdirs();
			if (!file.exists()) {
				// System.out.println("New File Create");
				JOptionPane.showMessageDialog(null, "Please Contact Mr. Ankush Supnar\n call- 9960855742",
						"Licence Not Found", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
				// file.createNewFile();
			} else {
				// System.out.println("File already exists");
			}
			BufferedReader read = new BufferedReader(new FileReader(file));
			ip = read.readLine().toString();

			// out.close();
			read.close();
		} catch (Exception e) {
			System.out.println("error in Check Status " + e.getMessage());
			e.printStackTrace();
			return "";
		}

		return ip;
	}

	public static void closeConnections() {
		try {
			con.close();

		} catch (Exception e) {
			System.out.println("Error in close Connection " + e);
			e.printStackTrace();
		}
	}

	public static int updateRecord(String query) {
		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			return 1;
		} catch (Exception e) {
			System.out.println("Error   " + query + "\n " + e.getMessage());

			// e.printStackTrace();
		}
		return 0;
	}

	// method to selecting whole table records
	public static ResultSet selectQuery(String query) {

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Error in Select Query...(" + query + " \n" + e);
		}

		return rs;
	}

	public static String getpassword(String name) {
		try {
			ResultSet rs = selectQuery("select password from login where username='" + name + "'");
			rs.next();
			return rs.getString(1);
		} catch (Exception e) {
			System.out.println("Error while getting password " + e);
			return "";
		}
	}

	public static int getId(String query) {
		int id = 0, flag;
		try {

			stmt = con.createStatement();

			rs = stmt.executeQuery(query);

			try {
				while (rs.next()) {
					flag = id = rs.getInt(1);
					if (flag > id)
						id = flag;
				}
				id++;

			} catch (Exception e) {
				System.out.println(e);
				id = 1;
			}

			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.out.println("Error in getId   " + query + "===>" + e);
			id = 1;
		}

		return id;
	}

	public static int addRecord(String query) {
		try {
			stmt = con.createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Error in Adding Record \n" + query + "\n" + e);
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public static void delete(int id) {
		String sql = "DELETE FROM TempTransaction WHERE id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			// set the corresponding param
			pstmt.setInt(1, id);
			// execute the delete statement
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error in delete " + e.getMessage());
		}
	}

	public static void deleteBill(int billno) {
		String sql = "DELETE FROM Transaction WHERE bill = ?";

		try {

			PreparedStatement pstmt = con.prepareStatement(sql);

			// set the corresponding param
			pstmt.setInt(1, billno);
			// execute the delete statement
			pstmt.executeUpdate();

			pstmt.close();
			sql = "DELETE FROM Bill WHERE billno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, billno);
			pstmt.executeUpdate();
			pstmt.close();
			sql = "DELETE FROM passbook WHERE Tid = ? and Mode='Credit'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, billno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error in delete " + e.getMessage());
		}

	}

	public static void deleteTransaction(int billno) {
		String sql = "DELETE FROM Transaction WHERE bill = ?";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			// set the corresponding param
			pstmt.setInt(1, billno);
			// execute the delete statement
			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in delete " + e.getMessage());
		}

	}

	public static void deletePurchaseTransaction(int billno) {
		String sql = "DELETE FROM purchasetransaction WHERE billNo = ?";

		try {

			PreparedStatement pstmt = con.prepareStatement(sql);

			// set the corresponding param
			pstmt.setInt(1, billno);
			// execute the delete statement
			pstmt.executeUpdate();

			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in delete " + e.getMessage());
		}

	}

	public static void deleteFromPassbook(int tid) {
		String sql = "DELETE FROM Passbook WHERE Tid = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			// set the corresponding param
			pstmt.setInt(1, tid);
			// execute the delete statement
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in delete " + e.getMessage());
		}
	}
	public static int addCashFromAccount(int bankId, Double cash) {
		try {
			return CommonMethods
					.updateRecord("update bankdetails set BankBalance=BankBalance+" + cash + " where Id=" + bankId);
		} catch (Exception e) {
			System.out.println("Error in Adding Cash In Cash Account " + e.getMessage());
			return 0;
		}
	}

	public static int reduceCashFromAccount(int bankId, Double cash) {
		try {
			return CommonMethods
					.updateRecord("update bankdetails set BankBalance=BankBalance-" + cash
							+ " where  Id=" + bankId);
		} catch (Exception e) {
			System.out.println("Error in Adding Cash In Cash Account " + e.getMessage());
			return 0;
		}
	}

	public static double getAccountBalance(int bankId) {
		try {
			rs = CommonMethods.selectQuery("select BankBalance from BankDetails where Id=" + bankId);
			if (rs.next()) {
				return rs.getDouble(1);
			} else
				return 0.0;
		} catch (Exception e) {
			System.out.println("Error in Getting Bank Balance " + e.getMessage());
			return 0.0;
		}
	}

	public static int deleteBankTransaction(int id) {
		try {
			String sql = "delete from BankTransaction where id=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			return 1;
		} catch (Exception e) {
			System.out.println("Error in Deleting BankTransaction " + e.getMessage());
			return 0;
		}
	}

	public static void addCashCashAccount(double amount) {
		try {
			CommonMethods.updateRecord(
					"update BankDetails set" + " BankBalance=BankBalance+" + amount + " where BankCode='CASH'");
		} catch (Exception e) {
			System.out.println("Error in Adding cash In Cash Account " + e.getMessage());

		}

	}

	public static void reduceCashCashAccount(double Amount) {
		try {
			CommonMethods.updateRecord(
					"update BankDetails set" + " BankBalance=BankBalance-" + Amount + " where BankCode='CASH'");
		} catch (Exception e) {
			System.out.println("Error in Adding cash In Cash Account " + e.getMessage());

		}

	}
}
