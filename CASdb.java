import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CASdb {
    private static Connection con;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
            con = getConnection();
            LocalDate ld = LocalDate.of( 2026 , 1 , 23 );
//            insertProduct("Pancit Bihon", "cooked food", 35.00, "1 cup per order");
//            insertProduct("Adobong Manok", "cooked food", 50.00, "3 pieces of meat per order");
//            insertCustomer("Jeff", "T.", "Andawi", 1500.00, 0.00);
//            insertCustomer("Owen", "S.", "Medina", 1250.00, 0.00);
	}

	/*constructor
	public CASdb(){
		con = getConnection();
	}*/
	
	//confirmed
	public static Connection getConnection() throws Exception{ //connect to mysql db
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/casdb"; // "//localhost OR ip add/port/dbname" //where the db is located
			Class.forName(driver);
			
			//establish connection
			String user = "root" ;
			String pw = "" ;
			Connection conn = DriverManager.getConnection(url, user, pw);
			System.out.println("Connected successfully."); //tester
			return conn;
		}catch(Exception e) {
			System.out.println("Error in getConnection: " + e); //in case of any errors
		}
		return null;
	}
	
	//confirmed
	public static void insertOrders(int idNo, LocalDate ld) throws Exception{ //insert to Order table
		try{
			PreparedStatement newOrder = con.prepareStatement("INSERT INTO orders(idNo,orderDate) VALUES('" + idNo + "', '" + ld + "')" );
			newOrder.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertOrders " + e); //in case of any errors;
		}
		finally{
			System.out.println("Insert to Orders completed"); //tester;
		}
	}

	//confirmed
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
	
	//confirmed
	public static void insertProduct(String name, String station, double price, String remarks) throws Exception{ //insert to Product table
		try {
			PreparedStatement newProd = con.prepareStatement("INSERT INTO product(productName, station, salesPrice, remarks) VALUES('" + name + "', '" + station + "', " + price + ", '" + remarks + "')" );
			newProd.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertProduct " + e); //in case of any errors;
		}
		finally {
			System.out.println("Insert to Product completed"); //tester;
		}
	}
	
	//fixed, confirmed
	public static void insertCustomer(String fName, String mName, String lName, double bal, double accDebt) throws Exception{ //insert to Customer table
		try {
			PreparedStatement newCustomer = con.prepareStatement("INSERT INTO customer(firstName, midName, lastName, balance, accumulatedDebt) VALUES('" + fName + "', '" + mName + "', '" + lName + "', " + bal + ", " + accDebt + ")" );
			newCustomer.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertCustomer " + e); //in case of any errors;
		}
		finally {
			System.out.println("Insert to Customer completed"); //tester;
		}
	}
	
	//fixed, confirmed
	public static void insertBegInv(LocalDate date, int prodNo,int begAmt,int totAmt,int plusAmt, int previousAmt) throws Exception{ //insert to Beginning Inventory table
		try {
			PreparedStatement newBegInv = con.prepareStatement("INSERT INTO beginInv VALUES('" + date + "', " + prodNo + ", " + begAmt + ", " + totAmt + ", " + plusAmt + ", " + previousAmt + ")" );
			newBegInv.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error in insertBegInv" + e);
		}
		finally {
			System.out.println("Insert to BegInv completed"); 
		}
	}
	
	//fixed, confirmed
	public static void insertEndInv(LocalDate date, int prodNo, int totAmt) throws Exception{  //insert to Ending Inventory table
		try {
			PreparedStatement newEndInv = con.prepareStatement("INSERT INTO endInv VALUES('" + date + "', " + prodNo + ", " + totAmt + ")" );
			newEndInv.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error in insertEndInv" + e);
		}
		finally {
			System.out.println("Insert to EndInv completed"); 
		}
	}
	
	//confirmed
	public static ArrayList<String> getOrder(int orderNo) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT o.orderDate, cust.firstName, cust.lastName, prod.productName, oi.quantity FROM product prod, customer cust, orders o, orderItem oi WHERE o.idNo = cust.idNo AND oi.orderNo = o.orderNo AND oi.productNo = prod.productNo AND o.orderNo = " + orderNo);

			ResultSet result = command.executeQuery();

			ArrayList<String> results = new ArrayList<String>();

			while(result.next()){
				System.out.println(result.getString("o.orderDate") + " " + result.getString("cust.firstName") + " " + result.getString("cust.lastName") + " " + result.getString("prod.productName") + " " + result.getString("oi.quantity"));
				results.add(result.getString("o.orderDate"));
				results.add(result.getString("cust.firstName"));
				results.add(result.getString("cust.lastName"));
				results.add(result.getString("prod.productName"));
				results.add(result.getString("oi.quantity"));
			}
			return results;
		}catch(Exception e){
			System.out.println("Error in getOrder: " + e);
		}
		return null;
	}
	
	//confirmed
	public static ArrayList<String> getPrice(int prodNo) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT productName, salesPrice FROM product WHERE productNo = " + prodNo);

			ResultSet result = command.executeQuery();

			ArrayList<String> results = new ArrayList<String>();

			while(result.next()){
				System.out.println(result.getString("productName") + " " + result.getString("salesPrice"));
				results.add(result.getString("salesPrice"));
			}
			return results;
		}catch(Exception e){
			System.out.println("Error in getPrice: " + e);
		}
		return null;
	}
	
	//confirmed
	public static ArrayList<String> getCustInfo(int id) throws Exception{
		try{
			String col1 = "balance";
			String col2 = "accumulatedDebt";
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT " + col1 + ", " + col2 + " FROM customer WHERE idNo = " + id);

			ResultSet result = command.executeQuery();

			ArrayList<String> results = new ArrayList<String>();

			while(result.next()){
				System.out.println(result.getString(col1) + " " + result.getString(col2));
				results.add(result.getString(col1));
				results.add(result.getString(col2));
			}
			return results;
		}catch(Exception e){
			System.out.println("Error in getCustInfo: " + e);
		}
		return null;
	}
	
	//fixed, confirmed
	public static ArrayList<String> getInv(LocalDate date, int prodNo) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT beg.totalAmt, end.totalAmt FROM beginInv beg, endInv end WHERE beg.productNo = " + prodNo + " AND end.productNo = " + prodNo + " AND beg.bInvDate = '" + date + "' AND end.eInvDate = '" + date + "'");

			ResultSet result = command.executeQuery();

			ArrayList<String> results = new ArrayList<String>();

			while(result.next()){
				System.out.println(result.getString("beg.totalAmt") + " " + result.getString("end.totalAmt"));
				results.add(result.getString("beg.totalAmt"));
				results.add(result.getString("end.totalAmt"));
			}
			return results;
		}catch(Exception e){
			System.out.println("Error in getInv: " + e);
		}
		return null;
	}
    
	//please check!
	public static ArrayList<String> getMemWithDebt(int prodNo) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT idNo, CONCAT(firstname,' ', lastname),accumulatedDebt FROM customer WHERE accumulatedDebt IS NOT NULL");

			ResultSet result = command.executeQuery();

			ArrayList<String> results = new ArrayList<String>();

			while(result.next()){
				System.out.println(result.getString("idNo") + " " + result.getString("firstname") + " " + result.getString("lastname") + " " + result.getString("accumulatedDebt"));
				results.add(result.getString("accumulatedDebt"));
			}
			return results;
		}catch(Exception e){
			System.out.println("Error in getMemWithDebt: " + e);
		}
		return null;
	}

	//UPDATE METHODS

	//confirmed
	public static void updatePrice(String prod, double newPrice) throws Exception{
		try {
			PreparedStatement updatePrice = con.prepareStatement("UPDATE product SET salesPrice = " + newPrice + " WHERE productName LIKE '%" + prod + "%'");
			updatePrice.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updatePrice" + e); //in case of any errors;
		}
		finally {
			System.out.println("Price of " + prod + "updated to " + newPrice); //tester;
		}
	}

	//confirmed
	public static void updateStation(String prod, String newStation) throws Exception{
		try {
			PreparedStatement updateStation = con.prepareStatement("UPDATE product SET station ='" + newStation + "' WHERE productName LIKE '%" + prod + "%'");
			updateStation.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateStation" + e); //in case of any errors;
		}
		finally {
			System.out.println("Station of " + prod + "updated to " + newStation); //tester;
		}
	}

	//confirmed
	public static void updateRemarks(String prod, String remarks) throws Exception{
		try {
			PreparedStatement updateRemarks = con.prepareStatement("UPDATE product SET remarks = '" + remarks + "' WHERE productName LIKE '%" + prod + "%'");
			updateRemarks.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateRemarks" + e); //in case of any errors;
		}
		finally {
			System.out.println("Remarks of " + prod + "updated to " + remarks); //tester;
		}
	}

	//confirmed
	public static void updateBal(int idNo, double amt) throws Exception{
		try {
			PreparedStatement updateBal = con.prepareStatement("UPDATE customer SET balance = balance + " + amt + " WHERE idNo = " + idNo);
			updateBal.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateBal" + e); //in case of any errors;
		}
		finally {
			System.out.println("Bal of " + idNo + "updated"); //tester;
		}
	}

	//confirmed
	public static void updateDebt(int idNo, double amt) throws Exception{
		try {
			PreparedStatement updateDebt = con.prepareStatement("UPDATE customer SET accumulatedDebt = accumulatedDebt + " + amt + " WHERE idNo = " + idNo);
			updateDebt.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateDebt" + e); //in case of any errors;
		}
		finally {
			System.out.println("Debt of " + idNo + "updated"); //tester;
		}
	}


	//confirmed
	public static void createInventoryUnit() throws Exception{
		try {
			PreparedStatement createProd = con.prepareStatement("CREATE TABLE IF NOT EXISTS product (\n" + 
					"	productNo INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" + 
					"	productName VARCHAR(255),\n" + 
					"	station VARCHAR(255),\n" + 
					"	salesPrice DOUBLE,\n" + 
					"	remarks VARCHAR(255)\n" + 
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
    
	//confirmed
	public static void createCustomerUnit() throws Exception{
		try {
			PreparedStatement createCustomer = con.prepareStatement("CREATE TABLE IF NOT EXISTS customer(\n" +
                                        "	idNo INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,\n" +
                                        "	firstName VARCHAR(255),\n" +
                                        "	midName VARCHAR(255),\n" +
                                        "	lastName VARCHAR(255),\n" +
                                        "	balance DOUBLE DEFAULT '0.0',\n" +
                                        "	accumulatedDebt DOUBLE DEFAULT '0.0'\n" +
                                        ")");
			createCustomer.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in createCustomerUnit" + e); //in case of any errors;
		}
		finally {
			System.out.println("Customer Table created"); //tester;
		}
	}
    
	//fixed, confirmed
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
                                        "	quantity INT,\n" +
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
