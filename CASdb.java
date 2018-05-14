import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CASdb {
    private static Connection con;
	//public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
            //con = getConnection();
            //createCustomerUnit();
            //createOrderUnit();
            //createInventoryUnit();
	//}

	//constructor
	public CASdb() throws Exception{
		con = getConnection();
	}
	
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
	
	//fixed 5/13/2018
	public static void insertOrder(String fName, String mName, String lName, int year, int month, int day) throws Exception{ //insert to Order table
		try{
			LocalDate ld = LocalDate.of(year, month, day);
			PreparedStatement newOrder = con.prepareStatement("INSERT INTO orders(idNo,orderDate) VALUES(" + "(SELECT idNo FROM customer WHERE firstName = '" + fName + "' AND midName = '" + mName + "' AND lastName = '" + lName + "')" + ", '" + ld + "')" );
			newOrder.executeUpdate(); //execute the insert
		}catch(Exception e) {
			System.out.println("Error in insertOrders " + e); //in case of any errors;
		}
		finally{
			System.out.println("Insert to Orders completed"); //tester;
		}
	}

	//fixed 5/13/2018
	public static void insertOrderItem(String fName, String mName, String lName, String productName, int quantity, int year, int month, int day) throws Exception{ //insert to Order Item table
		try{
			PreparedStatement newOrderItem = con.prepareStatement("INSERT INTO orderItem(orderNo,productNo,quantity) VALUES(" + "(SELECT orders.orderNo FROM orders, customer WHERE orders.idNo = customer.idNo AND customer.firstName = '" + fName + "' AND customer.midName = '" + mName + "' AND customer.lastName = '" + lName + "' ORDER BY orders.orderNo DESC LIMIT 1)" + ", " + "(SELECT productNo FROM product WHERE productName = '" + productName + "' ), " + quantity + ")" );
			newOrderItem.executeUpdate(); //execute the insert
			updateEndInv(year, month, day, productName, quantity);
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
	
	//fixed 5/13/2018
	public static void insertBegInv(int year, int month, int day, String prodName,int begAmt, int plusAmt, int prevAmt) throws Exception{ //insert to Beginning Inventory table
		try {
			LocalDate ld = LocalDate.of(year, month, day);
			PreparedStatement newBegInv = con.prepareStatement("INSERT INTO beginInv VALUES('" + ld + "', " + "(SELECT productNo FROM product WHERE productName = '" + prodName + "')" + ", " + begAmt + ", " + (begAmt+plusAmt+prevAmt) + ", " + plusAmt + ", " + prevAmt + ")" );
			newBegInv.executeUpdate();
			insertEndInv(year,month,day,prodName,(begAmt+plusAmt+prevAmt));
		}catch(Exception e) {
			System.out.println("Error in insertBegInv" + e);
		}
		finally{
			System.out.println("Insert to BegInv completed"); 
		}
	}
	
	//fixed, confirmed
	public static void insertEndInv(int year, int month, int day, String prodName, int totAmt) throws Exception{  //insert to Ending Inventory table
		try {
			LocalDate ld = LocalDate.of(year, month, day);
			PreparedStatement newEndInv = con.prepareStatement("INSERT INTO endInv VALUES('" + ld + "', " + "(SELECT productNo FROM product WHERE productName = '" + prodName + "')" + ", " + totAmt + ")" );
			newEndInv.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error in insertEndInv" + e);
		}
		finally {
			System.out.println("Insert to EndInv completed"); 
		}
	}
	
	//fixed 5/13/2018
	public static ArrayList<String> getOrder(int year, int month, int day, String fName, String mName, String lName) throws Exception{
                ArrayList<String> results = new ArrayList<String>();
		try{
			LocalDate ld = LocalDate.of(year, month, day);
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT o.orderDate, cust.firstName, cust.lastName, prod.productName, oi.quantity FROM product prod, customer cust, orders o, orderItem oi WHERE o.idNo = cust.idNo AND oi.orderNo = o.orderNo AND oi.productNo = prod.productNo AND o.orderDate = '" + ld + "' AND cust.firstName = '" + fName + "' AND cust.midName = '" + mName + "' AND cust.lastName = '" + lName + "'");

			ResultSet result = command.executeQuery();

			while(result.next()){
				System.out.println(result.getString("o.orderDate") + " " + result.getString("cust.firstName") + " " + result.getString("cust.lastName") + " " + result.getString("prod.productName") + " " + result.getString("oi.quantity"));
				results.add(result.getString("o.orderDate"));
 				results.add(result.getString("cust.firstName"));
 				results.add(result.getString("cust.lastName"));
 				results.add(result.getString("prod.productName"));
 				results.add(result.getString("oi.quantity"));
			}
		}catch(Exception e){
			System.out.println("Error in getOrder: " + e);
		}
		return results;
	}
	
	//fixed 5/13/2018
	public static ArrayList<String> getPrice(String prodName) throws Exception{
                ArrayList<String> results = new ArrayList<String>();
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT productName, salesPrice FROM product WHERE productName = '" + prodName + "'");

			ResultSet result = command.executeQuery();

			

			while(result.next()){
				System.out.println(result.getString("productName") + " " + result.getString("salesPrice"));
				results.add(result.getString("productName"));
				results.add(result.getString("salesPrice"));
			}
		}catch(Exception e){
			System.out.println("Error in getPrice: " + e);
		}
		return results;
	}
	
	//fixed 5/13/2018 - ASK ABOUT THIS METHOD
	public static ArrayList<String> getCustInfo(String fName, String mName, String lName) throws Exception{
                ArrayList<String> results = new ArrayList<String>();
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT idNo, CONCAT(firstName,' ', midName, ' ', lastName), balance, accumulatedDebt FROM customer WHERE firstName = '" + fName + "' AND midName = '" + mName + "' AND lastName = '" + lName + "'");

			ResultSet result = command.executeQuery();


			while(result.next()){
				System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString("balance") + " " + result.getString("accumulatedDebt"));
				results.add(result.getString(1));
				results.add(result.getString(2));
				results.add(result.getString("balance"));
				results.add(result.getString("accumulatedDebt"));
			}
		}catch(Exception e){
			System.out.println("Error in getCustInfo: " + e);
		}
		return results;
	}
	
	//fixed 5/13/2018
	public static ArrayList<String> getInv(int year, int month, int day, String prodName) throws Exception{
                ArrayList<String> results = new ArrayList<String>();
		try{
			LocalDate ld = LocalDate.of(year, month, day);
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT beg.totalAmt, end.totalAmt FROM beginInv beg, endInv end, product prod WHERE beg.productNo = prod.productNo AND end.productNo = prod.productNo AND prod.productName = '" + prodName + "' AND beg.bInvDate = '" + ld + "' AND end.eInvDate = '" + ld + "'");

			ResultSet result = command.executeQuery();

			

			while(result.next()){
				System.out.println(prodName + " " + result.getString("beg.totalAmt") + " " + result.getString("end.totalAmt"));
				results.add(prodName);
				results.add(result.getString("beg.totalAmt"));
				results.add(result.getString("end.totalAmt"));
			}
		}catch(Exception e){
			System.out.println("Error in getInv: " + e);
		}
		return results;
	}
    
	//fixed 5/13/2018
	public static ArrayList<String> getCustWithDebt() throws Exception{
                ArrayList<String> results = new ArrayList<String>();
		try{
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT CONCAT(firstName,' ', midName, ' ', lastName),accumulatedDebt FROM customer WHERE accumulatedDebt <> 0");

			ResultSet result = command.executeQuery();

			while(result.next()){
				System.out.println(result.getString(1) + " " + result.getString("accumulatedDebt"));
				results.add(result.getString(1));
				results.add(result.getString("accumulatedDebt"));
			}
		}catch(Exception e){
			System.out.println("Error in getCustWithDebt: " + e);
		}
		return results;
	}

	public static ArrayList<String> getProfit(int year, int month, int day) throws Exception{
		ArrayList<String> results = new ArrayList<String>();
		try{
			LocalDate ld = LocalDate.of(year, month, day);
			Connection con = getConnection();
			PreparedStatement command = con.prepareStatement("SELECT orders.orderDate, SUM(orderItem.quantity * product.salesPrice) FROM ((orders INNER JOIN orderItem ON orders.orderNO = orderItem.orderNo) INNER JOIN product ON orderItem.productNo = product.productNo) WHERE orders.orderDate = '" +ld+"'");

			ResultSet result = command.executeQuery();

			

			while(result.next()){
				System.out.println(result.getString("orders.orderDate") + " " + result.getString("SUM(orderItem.quantity * product.salesPrice)"));
				results.add(result.getString("orders.orderDate"));
				results.add(result.getString("SUM(orderItem.quantity * product.salesPrice)"));
			}
		}catch(Exception e){
			System.out.println("Error in getProfit: " + e);
		}
		return results;
	}

	//UPDATE METHODS

	//fixed 5/13/2018
	public static void updatePrice(String prod, double newPrice) throws Exception{
		try {
			PreparedStatement updatePrice = con.prepareStatement("UPDATE product SET salesPrice = " + newPrice + " WHERE productName = '" + prod + "'");
			updatePrice.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updatePrice" + e); //in case of any errors;
		}
		finally {
			System.out.println("Price of " + prod + "updated to " + newPrice); //tester;
		}
	}

	//fixed 5/13/2018
	public static void updateStation(String prod, String newStation) throws Exception{
		try {
			PreparedStatement updateStation = con.prepareStatement("UPDATE product SET station ='" + newStation + "' WHERE productName = '" + prod + "'");
			updateStation.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateStation" + e); //in case of any errors;
		}
		finally {
			System.out.println("Station of " + prod + "updated to " + newStation); //tester;
		}
	}

	//fixed 5/13/2018
	public static void updateRemarks(String prod, String remarks) throws Exception{
		try {
			PreparedStatement updateRemarks = con.prepareStatement("UPDATE product SET remarks = '" + remarks + "' WHERE productName = '" + prod + "'");
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

	public static void updateEndInv(int year, int month, int day, String prodName, int deductAmt) throws Exception{
		try {
			LocalDate ld = LocalDate.of(year, month, day);
			PreparedStatement updateEndInv = con.prepareStatement("UPDATE endInv e, product p SET totalAmt = (totalAmt- " + deductAmt + ") WHERE p.productName = '" + prodName + "' AND e.eInvDate = '" + ld + "' AND e.productNo = p.productNo");
			updateEndInv.executeUpdate(); //execute the update
		}catch(Exception e) {
			System.out.println("Error in updateEndInv" + e); //in case of any errors;
		}
		finally {
			System.out.println("EndInv of " + prodName + " updated"); //tester;
		}
	}

	//fixed 5/13/2018
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
					"	bInvDate DATE NOT NULL,\n" + 
					"	productNo INT,\n" + 
					"	beginAmt INT,\n" + 
					"	totalAmt INT,\n" + 
					"	addAmt INT,\n" + 
					"	prevAmt INT,\n" +
					"	PRIMARY KEY(bInvDate, productNo),\n" +
					"	FOREIGN KEY(productNo) REFERENCES product(productNo)\n" + 
					")");
			PreparedStatement createEndInv = con.prepareStatement("CREATE TABLE IF NOT EXISTS endInv (\n" + 
					"	eInvDate DATE NOT NULL,\n" + 
					"	productNo INT,\n" + 
					"	totalAmt INT,\n" +
					"	PRIMARY KEY(eInvDate, productNo),\n" +
					"	FOREIGN KEY(productNo) REFERENCES product(productNo)\n" + 
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
