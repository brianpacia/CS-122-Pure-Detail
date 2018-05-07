import java.sql.*;

public class CASdb {
    private static Connection con;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
            con = getConnection(); 
	}

	/*constructor
	public CASdb(){
		con = getConnection();
	}*/
	
	public static Connection getConnection() throws Exception{ //connect to mysql db
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
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
	
	public static void insertOrders(int idNo, Date orderDate) throws Exception{ //insert to Order table
		try{
			PreparedStatement newOrder = con.prepareStatement("INSERT INTO orders(idNo,orderDate) VALUES('" + idNo + "', '" + orderDate + "')" );
			newOrder.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertOrders " + e); //in case of any errors;
		}
		finally{
			System.out.println("Insert to Orders completed"); //tester;
		}
	}

	public static void insertOrderItem(int orderNo, int productNo) throws Exception{ //insert to Order Item table
		try{
			PreparedStatement newOrderItem = con.prepareStatement("INSERT INTO orderItem(orderNo,productNo) VALUES('" + orderNo + "', '" + productNo + "')" );
			newOrderItem.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertOrderItem " + e); //in case of any errors;
		}
		finally{
			System.out.println("Insert to OrderItem completed"); //tester;
		}
	}
	
	public static void insertProduct(String name, String station, double price, String remarks, Date date) throws Exception{ //insert to Product table
		try {
			PreparedStatement newProd = con.prepareStatement("INSERT INTO product(productName, station, salesPrice, remarks, dateSold) VALUES('" + name + "', '" + station + "', " + price + ", '" + remarks + "', '" + date + "')" );
			newProd.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertProduct " + e); //in case of any errors;
		}
		finally {
			System.out.println("Insert to Product completed"); //tester;
		}
	}
	
	public static void insertCustomer(String fName, String mName, String lName, double bal, double accDebt) throws Exception{ //insert to Customer table
		try {
			PreparedStatement newCustomer = con.prepareStatement("INSERT INTO customer(firstName, midName, lastName, balance, accumulatedDebt) VALUES('" + fName + "', '" + mName + "', " + lName + ", '" + bal + "', '" + accDebt + "')" );
			newCustomer.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertCustomer " + e); //in case of any errors;
		}
		finally {
			System.out.println("Insert to Customer completed"); //tester;
		}
	}
	
	public static void insertBegInv(int prodNo,int begAmt,int totAmt,int plusAmt, int previousAmt) throws Exception{ //insert to Beginning Inventory table
		try {
			PreparedStatement newBegInv = con.prepareStatement("INSERT INTO beginInv(productNo, beginAmt, totalAmt, addAmt, prevAmt) VALUES('" + prodNo + "', '" + begAmt + "', '" + totAmt + "', " + int plusAmt + ", '" + previousAmt + "')" );
			newBegInv.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error in insertBegInv" + e);
		}
		finally {
			System.out.println("Insert to BegInv completed"); 
		}
	}
	

public static void insertEndInv(int prodNo, int totAmt) throws Exception{  //insert to Ending Inventory table
		try {
			PreparedStatement newEndInv = con.prepareStatement("INSERT INTO endInv(productNo, totalAmt) VALUES('" + prodNo + "', '" + totAmt + "')" );
			newEndInv.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error in insertEndInv" + e);
		}
		finally {
			System.out.println("Insert to EndInv completed"); 
		}
	}
        
        public static void createInventoryUnit() throws Exception{
		try {
			PreparedStatement createProd = con.prepareStatement("CREATE TABLE IF NOT EXISTS product (\n" + 
					"	productNo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" + 
					"	productName VARCHAR(255),\n" + 
					"	station VARCHAR(255),\n" + 
					"	salesPrice DOUBLE,\n" + 
					"	remarks VARCHAR(255),\n" + 
					"	dateSold DATE\n" + 
					")");
			PreparedStatement createBegInv = con.prepareStatement("CREATE TABLE IF NOT EXISTS beginInv (\n" + 
					"	bInvDate DATE NOT NULL PRIMARY KEY,\n" + 
					"	productNo INT,\n" + 
					"	beginAmt INT,\n" + 
					"	totalAmt INT,\n" + 
					"	addAmt INT,\n" + 
					"	prevAmt INT,\n" + 
					"	FOREIGN KEY (productNo) REFERENCES product(productNo)\n" + 
					")");
			PreparedStatement createEndInv = con.prepareStatement("CREATE TABLE IF NOT EXISTS endInv (\n" + 
					"	eInvDate DATE NOT NULL PRIMARY KEY,\n" + 
					"	productNo INT,\n" + 
					"	totalAmt INT,\n" + 
					"	FOREIGN KEY (productNo) REFERENCES product(productNo)\n" + 
					")");
			createProd.executeUpdate();
			createBegInv.executeUpdate();
			createEndInv.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error in createInventoryUnit: " + e);
		}
		finally {
			System.out.println("Inventory unit tables created.");
		}
	}
        
        public static void createCustomerUnit() throws Exception{
		try {
			PreparedStatement createCustomer = con.prepareStatement("CREATE TABLE IF NOT EXISTS customer(\n" +
                                        "	idNo INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,\n" +
                                        "	firstName VARCHAR(255),\n" +
                                        "	midName VARCHAR(255),\n" +
                                        "	lastName VARCHAR(255),\n" +
                                        "	balance DOUBLE DEFAULT '0.0',\n" +
                                        "	accumulatedDebt INT\n" +
                                        ")");
			createCustomer.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in createCustomerUnit" + e); //in case of any errors;
		}
		finally {
			System.out.println("Customer Table created"); //tester;
		}
	}
        
        public static void createOrderUnit() throws Exception{
		try {
			PreparedStatement createOrders = con.prepareStatement("CREATE TABLE IF NOT EXISTS orders(\n" +
                                        "	orderNo INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,\n" +
                                        "	idNo INT,\n" +
                                        "	orderDate DATE,\n" +
                                        "	FOREIGN KEY(idNo) REFERENCES customer(idNo)\n" +
                                        ")");
			PreparedStatement createOrderItem = con.prepareStatement("CREATE TABLE IF NOT EXISTS orderItem(\n" +
                                        "	orderNo INT,\n" +
                                        "	productNo INT,\n" +
                                        "	PRIMARY KEY( orderNo, productNo ),\n" +
                                        "	FOREIGN KEY(orderNo) REFERENCES orders(orderNo),\n" +
                                        "	FOREIGN KEY(productNo) REFERENCES product(productNo)\n" +
                                        ")");
			createOrders.executeUpdate();
			createOrderItem.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error in createOrderUnit: " + e);
		}
		finally {
			System.out.println("Order unit tables created.");
		}
	}
        
}
