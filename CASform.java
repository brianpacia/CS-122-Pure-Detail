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
// forms for all the functions
public class CASform extends JFrame implements ActionListener {
	public static JFrame x;
	public CASdb functions;
        public ArrayList<String> temp;
	public CASform () throws Exception{
		functions = new CASdb();
		x = new JFrame();
                x.setPreferredSize(new Dimension(500,500));
		x.setLayout(new BorderLayout());
                x.pack();
                x.setVisible(false);
	}
	public void insertOrder() throws Exception{
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
                            try {
                                functions.insertOrder(f,m,l,y,mo,d);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});
	}
	public void insertOrderItem() {
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				String z = tfproductname.getText();
                                int y = Integer.parseInt(tfyear.getText());
                                int mo = Integer.parseInt(tfmonth.getText());
                                int d = Integer.parseInt(tfday.getText());
				int q = Integer.parseInt(tfquantity.getText());
                            try {
                                functions.insertOrderItem(f,m,l,z,q,y,mo,d);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});										
	}
	public void insertProduct() {
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String n = tfname.getText();
				String s = tfstation.getText();
				double z = Double.parseDouble(tfprice.getText());
				String r = tfremarks.getText();
                            try {
                                functions.insertProduct(n,s,z,r);
                                x.setVisible(false);
                            } catch (Exception ex) {
                               System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}
	public void insertCustomer() {
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
				double b = Double.parseDouble(tfbal.getText());
				double d = Double.parseDouble(tfdebt.getText());
                            try {
                                functions.insertCustomer(f,m,l,b,d);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});						
	}
	public void insertBegInv() {
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String z = tfprodName.getText();
				int bAmt = Integer.parseInt(tfbegAmt.getText());
				int plAmt = Integer.parseInt(tfplusAmt.getText());
				int prAmt = Integer.parseInt(tfprevAmt.getText());
                            try {
                                functions.insertBegInv(y,mo,d,z,bAmt,plAmt,prAmt);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}
        /*
	public void insertEndInv() {
                x.setVisible(true);
		final ArrayList<String> result = new ArrayList<String>();
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String z = tfprodName.getText();
				int t = Integer.parseInt(tftotAmt.getText());
                            try {
                                functions.insertEndInv(y,mo,d,z,t);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}
        **/
        public void getProfit() throws Exception {
                x.setTitle("Get Sales");
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
                            int y = Integer.parseInt(tfyear.getText());
                            int m = Integer.parseInt(tfmonth.getText());
                            int d = Integer.parseInt(tfday.getText());
                            try {
                                temp = functions.getProfit(y,m,d);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
                            drawS s = new drawS("Date: " + temp.get(0) + " and Profit: " + temp.get(1),125,250);
                            JFrame b = new JFrame();
                            b.setLayout(new BorderLayout());
                            b.add(s, BorderLayout.CENTER);
                            b.setPreferredSize(new Dimension(500,500));
                            b.pack();
                            b.setVisible(true);
                            b.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            x.setVisible(false);
			}
		});                
        }        
	public ArrayList<String> getOrder() throws Exception {
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(9,2));
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
                            try {
                                String f = tffirstname.getText();
                                String m = tfmiddlename.getText();
                                String l = tflastname.getText();
                                int y = Integer.parseInt(tfyear.getText());
                                int mo = Integer.parseInt(tfmonth.getText());
                                int d = Integer.parseInt(tfday.getText());
                                temp = functions.getOrder(y,mo,d,f,m,l);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});
                return temp;
	}
	public ArrayList<String> getPrice() throws Exception {
                x.setTitle("Get Price");
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		JLabel prodName = new JLabel("Product Name");
		JTextField tfprodName = new JTextField("");
		p.add(prodName);
		p.add(tfprodName);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String z = tfprodName.getText();
                            try {
                                temp = functions.getPrice(z);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});	
		return temp;	
	}
	public ArrayList<String> getCustInfo() throws Exception {
                x.setVisible(true);
		ArrayList<String> result = new ArrayList<String>();
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String f = tffirstname.getText();
				String m = tfmiddlename.getText();
				String l = tflastname.getText();
                            try {
                                temp = functions.getCustInfo(f,m,l);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});
		return temp;			
	}
	public ArrayList<String> getInv() {
                x.setVisible(true);
		ArrayList<String> result = new ArrayList<String>();
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int y = Integer.parseInt(tfyear.getText());
				int mo = Integer.parseInt(tfmonth.getText());
				int d = Integer.parseInt(tfday.getText());
				String z = tfprodName.getText();
                            try {
                                temp = functions.getInv(y,mo,d,z);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});			
		return temp;	
	}
	public void updatePrice() {
                x.setVisible(true);
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
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String z = tfprod.getText(); 
				double d = Double.parseDouble(tfnewPrice.getText());
                            try {
                                functions.updatePrice(z,d);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});						
	}
	public void updateStation() {
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		JLabel prod = new JLabel("Product Name");
		JTextField tfprod = new JTextField("");
		p.add(prod);
		p.add(tfprod);
		JLabel newStation = new JLabel("New Station");
		JTextField tfnewStation = new JTextField("");
		p.add(newStation);
		p.add(tfnewStation);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String z = tfprod.getText(); 
				String s = tfnewStation.getText();
                            try {
                                functions.updateStation(z,s);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});
	}
	public void updateRemarks() {
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		JLabel prod = new JLabel("Product Name");
		JTextField tfprod = new JTextField("");
		p.add(prod);
		p.add(tfprod);
		JLabel newRemarks = new JLabel("New Remarks");
		JTextField tfnewRemarks = new JTextField("");
		p.add(newRemarks);
		p.add(tfnewRemarks);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String z = tfprod.getText(); 
				String r = tfnewRemarks.getText();
                            try {
                                functions.updateRemarks(z,r);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}
	public void updateBal() {
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		JLabel idNo = new JLabel("ID Number");
		JTextField tfidNo = new JTextField("");
		p.add(idNo);
		p.add(tfidNo);
		JLabel amt = new JLabel("New Balance");
		JTextField tfamt = new JTextField("");
		p.add(amt);
		p.add(tfamt);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int z = Integer.parseInt(tfidNo.getText()); 
				double r = Double.parseDouble(tfamt.getText());
                            try {
                                x.setVisible(false);
                                functions.updateBal(z,r);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}
	public void updateDebt() {
                x.setVisible(true);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		JLabel idNo = new JLabel("ID Number");
		JTextField tfidNo = new JTextField("");
		p.add(idNo);
		p.add(tfidNo);
		JLabel amt = new JLabel("New Balance");
		JTextField tfamt = new JTextField("");
		p.add(amt);
		p.add(tfamt);
		JButton send = new JButton("Send");
		x.add(p, BorderLayout.PAGE_START);
		x.add(send, BorderLayout.PAGE_END);
                x.pack();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int z = Integer.parseInt(tfidNo.getText()); 
				double r = Double.parseDouble(tfamt.getText());
                            try {
                                functions.updateDebt(z,r);
                                x.setVisible(false);
                            } catch (Exception ex) {
                                System.out.println("Error in inserting " + ex);
                            }
			}
		});		
	}		

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
class drawS extends JComponent {
    private String s;
    private int x,y;
    public drawS(String i, int a, int b) {
        s = i;
        x = a;
        y = b;
    }
    @Override
    protected void paintComponent(Graphics g) {
        g.drawString(s,x,y);
    }
}