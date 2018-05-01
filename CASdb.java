package Database;
import java.sql.*;

public class CASdb {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getConnection();
	}
	
	public static Connection getConnection() throws Exception{ //connect to mysql db
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12235385"; // "//localhost OR ip add/port/dbname" //where the db is located
			Class.forName(driver);
			
			//establish connection
			String user = "sql12235385" ;
			String pw = "sRYC1GXARy" ;
			Connection conn = DriverManager.getConnection(url, user, pw);
			System.out.println("Connected successfully."); //tester
			return conn;
		}catch(Exception e) {
			System.out.println("Error in getConnection"); //in case of any errors
		}
		return null;
	}
	
	public static void insertOrderItem() throws Exception{ //insert to Order Item table
		
	}
	
	public static void insertProduct(String name, String station, double price, String remarks, Date date) throws Exception{ //insert to Product table
		try {
			Connection con = getConnection();
			PreparedStatement newProd = con.prepareStatement("INSERT INTO product(prodname, station, salesprice, remarks, datesold) VALUES('" + name + "', '" + station + "', " + price + ", '" + remarks + "', '" + date + "')" );
			newProd.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertProduct"); //in case of any errors;
		}
		finally {
			System.out.println("Insert to Product completed"); //tester;
		}
	}
	
	public static void insertCustomer() throws Exception{ //insert to Customer table
		
	}
	
	public static void insertBegInv() throws Exception{ //insert to Beginning Inventory table
		
	}

	public static void insertEndInv() throws Exception{ //insert to Ending Inventory table
		
	}

}