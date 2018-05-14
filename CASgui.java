import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.nio.file.Paths;
public class CASgui extends JFrame {
	private String path;
	private String funcPath;
	private int funcInsert, funcSearch, funcUpdate;
	private int mainInsert, mainSearch, mainUpdate;
	private BufferedImage myImage;
	private JPanel fc;
	private JFrame f;
	public CASgui () {
		Path currentRelativePath = Paths.get("");
		path = currentRelativePath.toAbsolutePath().toString();
		f = new JFrame("Canteen Aid System");
	}
	// initializes
	public void init() {
		f.setLayout(new BorderLayout());
		this.funcMain();
		f.setPreferredSize(new Dimension(1920,1080));
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	// for function-main page
	public void funcMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\function-main.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// insert
				if(x >= 219 && x <= 505 && y >= 441 && y <= 879) {
					funcInsert = 1;
					funcSearch = 0;
					funcUpdate = 0;
					f.remove(bg);
					main();
				}
				// search
				else if (x >= 793 && x <= 1126 && y >= 441 && y <= 879) {
					funcSearch = 1;
					funcInsert = 0;
					funcUpdate = 0;
					f.remove(bg);
					main();
				}
				// update
				else if (x >= 1384 && x <= 1743 && y >= 441 && y <= 879) {
					funcUpdate = 1;
					funcSearch = 0;
					funcInsert = 0;
					f.remove(bg);
					main();
				}
				System.out.println(x + " , " + y);
			}
		});
		f.revalidate();
		f.repaint();
	}
	public void main() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\main.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// inventory
				if(x >= 279 && x <= 693 && y >= 564 && y <= 952) {
					f.remove(bg);
					// going to inventory
					if(funcInsert == 1) {
						mainInsert = 1;
						mainUpdate = 0;
						mainSearch = 0;
						inventoryMain();
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
						inventoryMain();
					}
					else if (funcUpdate == 1) {
						mainInsert = 0;
						mainUpdate = 1;
						mainSearch = 0;
						inventoryMain();						
					}
					f.remove(bg);
				}
				// going to order
				else if (x >= 805 && x <= 1083 && y >= 564 && y <= 952) { 
					f.remove(bg);
					if(funcInsert == 1) {
						mainInsert = 1;
						mainUpdate = 0;
						mainSearch = 0;
						orderMain();
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
						orderMain();
					}					
				}
				// going to customer
				else if (x >= 1209 && x <= 1575 && y >= 564 && y <= 952) {
					f.remove(bg);
					// form
					if(funcInsert == 1) {
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
						customerGet();
					}
					else if (funcUpdate == 1) {
						mainInsert = 0;
						mainUpdate = 1;
						mainSearch = 0;
						customerUpdate();						
					}					
				}
				System.out.println(x + " , " + y);
			}
		});		
		f.revalidate();
		f.repaint();				
	}
	public void inventoryMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\inventory-option.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// first three = product
				if(x >= 387 && x <= 699 && y >= 507 && y <= 865 && mainInsert == 1) {
				}
				else if(x >= 387 && x <= 699 && y >= 507 && y <= 865 && mainSearch == 1) {
					f.remove(bg);
					prodGet();
				}
				else if(x >= 387 && x <= 699 && y >= 507 && y <= 865 && mainUpdate == 1) {
					f.remove(bg);
					prodUpdate();
				}									
				// second = beginning
				else if (x >= 793 && x <= 1126 && y >= 441 && y <= 879) {
				}
				// third = ending
				else if (x >= 1384 && x <= 1743 && y >= 441 && y <= 879) {
				}
				System.out.println(x + " , " + y);
			}
		});		
		f.revalidate();
		f.repaint();
	}
	public void orderMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\order-option.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// first two = order
				if(x >= 442 && x <= 760 && y >= 516 && y <= 882 && mainInsert == 1) {
				}
				else if(x >= 442 && x <= 760 && y >= 516 && y <= 882 && mainSearch == 1) {
				}
				// second two = order item							
				else if (x >= 1117 && x <= 1596 && y >= 516 && y <= 882 && mainInsert == 1) {
				}
				else if (x >= 1117 && x <= 1596 && y >= 516 && y <= 882 && mainSearch == 1) {
				}
				System.out.println(x + " , " + y);
			}
		});			
		f.revalidate();
		f.repaint();		
	}
	public void customerUpdate() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\update-cust.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// debt
				if(x >= 301 && x <= 736 && y >= 411 && y <= 882) {
				}
				// balance						
				else if (x >= 1102 && x <= 1552 && y >= 411 && y <= 882) {
				}
				System.out.println(x + " , " + y);
			}
		});		
		f.revalidate();
		f.repaint();		
	}
	public void customerGet() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\get-cust.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// no debt
				if(x >= 225 && x <= 721 && y >= 393 && y <= 844) {
				}
				// with debt					
				else if (x >= 1189 && x <= 1675 && y >= 345 && y <= 919) {
				}
				System.out.println(x + " , " + y);
			}
		});			
		f.revalidate();
		f.repaint();		
	}
	public void prodGet() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\get-prod.png"));
		f.add(bg,BorderLayout.CENTER);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// price
				if(x >= 217 && x <= 700 && y >= 375 && y <= 906) {
				}
				// profit					
				else if (x >= 1189 && x <= 1779 && y >= 375 && y <= 906) {
				}
				System.out.println(x + " , " + y);
			}
		});
		f.revalidate();
		f.repaint();		
	}
	public void prodUpdate()  {
		JLabel bg = new JLabel(new ImageIcon(path+"\\update-prod.png"));
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// station
				if(x >= 73 && x <= 586 && y >= 474 && y <= 900) {
				}
				// price				
				else if (x >= 801 && x <= 1206 && y >= 474 && y <= 900) {
				}
				// remarks
				else if (x >= 1387 && x <= 1843 && y >= 474 && y <= 900) {
				}				
				System.out.println(x + " , " + y);
			}
		});			
		f.add(bg,BorderLayout.CENTER);
		f.revalidate();
		f.repaint();		
	}						
}
// draws the background image
class drawBG extends JComponent {
	private Image img;
	public drawBG(Image i) {
		img = i;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,0,0,this);
	}
}
// forms for all the functions
class form extends JFrame implements ActionListener {
	public int insertOrder, insertOrderItem, insertProduct, insertCustomer, insertBegInv, insertEndInv, getOrder, getPrice, getCustInfo, getInv, updateProduct, updatePrice, updateStation, updateRemarks, updateBal, updateDebt;
	public static JFrame x;
	public static CASdb functions;
	public form (int s) {
		functions = new CASdb();
		x = new JFrame();
		x.setLayout(new BorderLayout());
		if(s == 0) {
			insertOrder = 1;
		}
		else if (s == 1) {
			insertOrderItem = 1;
		}
		else if (s == 2) {
			insertProduct = 1;
		}
		else if (s == 3) {
			insertCustomer = 1;
		}
		else if (s == 4) {
			insertBegInv = 1;
		}
		else if (s == 5) {
			insertEndInv = 1;
		}
		else if (s == 6) {
			getOrder = 1;
		}
		else if (s == 7) {
			getPrice = 1;
		}
		else if (s == 8) {
			getCustInfo = 1;
		}
		else if (s == 9) {
			getInv = 1;
		}
		else if (s == 10) {
			updateProduct = 1;
		}
		else if (s == 11) {
			updatePrice = 1;
		}
		else if (s == 12) {
			updateStation = 1;
		}
		else if (s == 13) {
			updateRemarks = 1;
		}
		else if (s == 14) {
			updateBal = 1;
		}
		else if (s == 15) {
			updateDebt = 1;
		}
	}
	public static void main(String[] args) {
		x.setVisible(true);
	}
	public static void insertOrderForm() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6,2));
		JLabel firstname = new JLabel("First Name");
		JTextField tffirstname = new JTextField("");
		p.add(firstname);
		p.add(tffirstname);
		JLabel middlename = new JLabel("Middle Name");
		JTextField tfmiddlename = new JTextField("");
		p.add(middlename);
		p.add(tfmiddlename);
		JLabel lastname = new JLabel("Last Name");
		JTextField tflastname = new JTextField("");
		p.add(lastname);
		p.add(tflastname);
		JLabel year = new JLabel("Year");
		JTextField tfyear = new JTextField("");
		p.add(year);
		p.add(tfyear);
		JLabel month = new JLabel("Month");
		JTextField tfmonth = new JTextField("");
		p.add(month);
		p.add(tfmonth);
		JLabel day = new JLabel("Day");
		JTextField tfday = new JTextField("");
		p.add(day);
		p.add(tfday);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				functions.insertOrder(f,m,l,y,mo,d);
			}
		});
	}
	public static void insertOrderItem() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		JLabel firstname = new JLabel("First Name");
		JTextField tffirstname = new JTextField("");
		p.add(firstname);
		p.add(tffirstname);
		JLabel middlename = new JLabel("Middle Name");
		JTextField tfmiddlename = new JTextField("");
		p.add(middlename);
		p.add(tfmiddlename);
		JLabel lastname = new JLabel("Last Name");
		JTextField tflastname = new JTextField("");
		p.add(lastname);
		p.add(tflastname);
		JLabel productName = new JLabel("Product Name");
		JTextField tfproductname = new JTextField("");
		p.add(productName);
		p.add(tfproductname);
		JLabel quantity = new JLabel("Quantity");
		JTextField tfquantity = new JTextField("");
		p.add(quantity);
		p.add(tfquantity);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				String p = tfproductname.getText();
				int q = Integer.parseInt(tfquantity.getText());
				functions.insertOrderItem(f,m,l,p,q);
			}
		});										
	}
	public static void insertProduct() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		JLabel name = new JLabel("Product Name");
		JTextField tfname = new JTextField("");
		p.add(name);
		p.add(tfname);
		JLabel station = new JLabel("Station");
		JTextField tfstation = new JTextField("");
		p.add(station);
		p.add(tfstation);
		JLabel price = new JLabel("Price");
		JTextField tfprice = new JTextField("");
		p.add(price);
		p.add(tfprice);
		JLabel remarks = new JLabel("Remarks");
		JTextField tfremarks = new JTextField("");
		p.add(remarks);
		p.add(tfremarks);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String n = tfname.getText();
				String s = tfstation.getText();
				double p = Double.parseDouble(tfprice.getText());
				String r = tfremarks.getText();
				functions.insertProduct(n,s,p,r);
			}
		});		
	}
	public static void insertCustomer() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		JLabel firstname = new JLabel("First Name");
		JTextField tffirstname = new JTextField("");
		p.add(firstname);
		p.add(tffirstname);
		JLabel middlename = new JLabel("Middle Name");
		JTextField tfmiddlename = new JTextField("");
		p.add(middlename);
		p.add(tfmiddlename);
		JLabel lastname = new JLabel("Last Name");
		JTextField tflastname = new JTextField("");
		p.add(lastname);
		p.add(tflastname);
		JLabel bal = new JLabel("Balance");
		JTextField tfbal = new JTextField("");
		p.add(bal);
		p.add(tfbal);
		JLabel debt = new JLabel("Debt");
		JTextField tfdebt = new JTextField("");
		p.add(debt);
		p.add(tfdebt);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				double b = Double.parseDouble(tfbal.getText());
				double d = Double.parseDouble(tfdebt.getText());
				functions.insertCustomer(f,m,l,b,d);
			}
		});						
	}
	public static void insertBegInv() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(7,2));
		JLabel year = new JLabel("Year");
		JTextField tfyear = new JTextField("");
		p.add(year);
		p.add(tfyear);
		JLabel month = new JLabel("Month");
		JTextField tfmonth = new JTextField("");
		p.add(month);
		p.add(tfmonth);
		JLabel day = new JLabel("Day");
		JTextField tfday = new JTextField("");
		p.add(day);
		p.add(tfday);
		JLabel prodName = new JLabel("Product Name");
		JTextField tfprodName = new JTextField("");
		p.add(prodName);
		p.add(tfprodName);
		JLabel begAmt = new JLabel("Beginning Amount");
		JTextField tfbegAmt = new JTextField("");
		p.add(begAmt);
		p.add(tfbegAmt);
		JLabel plusAmt = new JLabel("Plus Amount");
		JTextField tfplusAmt = new JTextField("");
		p.add(plusAmt);
		p.add(tfplusAmt);
		JLabel prevAmt = new JLabel("Previous Amout");
		JTextField tfprevAmt = new JTextField("");
		p.add(prevAmt);
		p.add(tfprevAmt);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String p = tfprodName.getText();
				int bAmt = Integer.parseInt(tfbegAmt.getText());
				int plAmt = Integer.parseInt(tfplusAmt.getText());
				int prAmt = Integer.parseInt(tfprevAmt.getText());
				functions.insertBegInv(y,mo,d,p,bAmt,plAmt,prAmt);
			}
		});		
	}
	public static void insertEndInv() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(5,2));
		JLabel year = new JLabel("Year");
		JTextField tfyear = new JTextField("");
		p.add(year);
		p.add(tfyear);
		JLabel month = new JLabel("Month");
		JTextField tfmonth = new JTextField("");
		p.add(month);
		p.add(tfmonth);
		JLabel day = new JLabel("Day");
		JTextField tfday = new JTextField("");
		p.add(day);
		p.add(tfday);
		JLabel prodName = new JLabel("Product Name");
		JTextField tfprodName = new JTextField("");
		p.add(prodName);
		p.add(tfprodName);
		JLabel totAmt = new JLabel("Total Amount");
		JTextField tftotAmt = new JTextField("");
		p.add(totAmt);
		p.add(tftotAmt);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String p = tfprodName.getText();
				int t = Integer.parseInt(tftotAmt.getText());
				functions.insertEndInv(y,mo,d,p,t);
			}
		});		
	}
	public static void getOrder() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6,2));
		JLabel year = new JLabel("Year");
		JTextField tfyear = new JTextField("");
		p.add(year);
		p.add(tfyear);
		JLabel month = new JLabel("Month");
		JTextField tfmonth = new JTextField("");
		p.add(month);
		p.add(tfmonth);
		JLabel day = new JLabel("Day");
		JTextField tfday = new JTextField("");
		p.add(day);
		p.add(tfday);		
		JLabel firstname = new JLabel("First Name");
		JTextField tffirstname = new JTextField("");
		p.add(firstname);
		p.add(tffirstname);
		JLabel middlename = new JLabel("Middle Name");
		JTextField tfmiddlename = new JTextField("");
		p.add(middlename);
		p.add(tfmiddlename);
		JLabel lastname = new JLabel("Last Name");
		JTextField tflastname = new JTextField("");
		p.add(lastname);
		p.add(tflastname);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				functions.getOrder(y,mo,d,f,m,l);
			}
		});		
	}
	public static void getPrice() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		JLabel prodName = new JLabel("Product Name");
		JTextField tfprodName = new JTextField("");
		p.add(prodName);
		p.add(tfprodName);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String p = tfprodName.getText();
				functions.getPrice(p);
			}
		});		
	}
	public static void getCustInfo() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
		JLabel firstname = new JLabel("First Name");
		JTextField tffirstname = new JTextField("");
		p.add(firstname);
		p.add(tffirstname);
		JLabel middlename = new JLabel("Middle Name");
		JTextField tfmiddlename = new JTextField("");
		p.add(middlename);
		p.add(tfmiddlename);
		JLabel lastname = new JLabel("Last Name");
		JTextField tflastname = new JTextField("");
		p.add(lastname);
		p.add(tflastname);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				functions.getCustInfo(f,m,l);
			}
		});					
	}
	public static void getInv() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		JLabel year = new JLabel("Year");
		JTextField tfyear = new JTextField("");
		p.add(year);
		p.add(tfyear);
		JLabel month = new JLabel("Month");
		JTextField tfmonth = new JTextField("");
		p.add(month);
		p.add(tfmonth);
		JLabel day = new JLabel("Day");
		JTextField tfday = new JTextField("");
		p.add(day);
		p.add(tfday);
		JLabel prodName = new JLabel("Product Name");
		JTextField tfprodName = new JTextField("");
		p.add(prodName);
		p.add(tfprodName);	
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String p = tfprodName.getText();
				functions.getInv(y,mo,d,p);
			}
		});				
	}
	public static void updatePrice() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		JLabel prod = new JLabel("Product Name");
		JTextField tfprod = new JTextField("");
		p.add(prod);
		p.add(tfprod);
		JLabel newPrice = new JLabel("New Price");
		JTextField tfnewPrice = new JTextField("");
		p.add(newPrice);
		p.add(tfnewPrice);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);		
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String p = tfprod.getText(); 
				double d = Double.parseDouble(tfnewPrice.getText());
				functions.updatePrice(p,d);
			}
		});						

	}
}