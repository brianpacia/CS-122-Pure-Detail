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
import java.util.logging.Level;
import java.util.logging.Logger;
public class CASgui extends JFrame {
	private String path;
	private String funcPath;
	private int funcInsert, funcSearch, funcUpdate;
	private int mainInsert, mainSearch, mainUpdate;
	private BufferedImage myImage;
	private ArrayList<String> results;
	private JPanel fc;
	private JFrame f;
	private CASform casf;
	private CASdb db;
	private MouseListener ml;
	public CASgui () {
		Path currentRelativePath = Paths.get("");
		path = currentRelativePath.toAbsolutePath().toString();
		f = new JFrame("Canteen Aid System");
		try {
			casf = new CASform();
		}
		catch(Exception e) {
			System.out.println("CASform error");
		}
	}
	// initializes
	public void init() {
		f.setLayout(new BorderLayout());
		this.funcMain();
		f.setPreferredSize(new Dimension(1280,720));
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	// for function-main page
	public void funcMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\function-main.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// insert
				if(x >= 116 && x <= 334 && y >= 288 && y <= 594) {
					funcInsert = 1;
					funcSearch = 0;
					funcUpdate = 0;
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					main();
				}
				// search
				else if (x >= 516  && x <= 748 && y >= 288 && y <= 594) {
					funcSearch = 1;
					funcInsert = 0;
					funcUpdate = 0;
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					main();
				}
				// update
				else if (x >= 918 && x <= 1160 && y >= 441 && y <= 879) {
					funcUpdate = 1;
					funcSearch = 0;
					funcInsert = 0;
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					main();
				}
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);
		f.revalidate();
		f.repaint();
	}
	public void main() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\main.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// inventory
				if(x >= 172 && x <= 456 && y >= 378 && y <= 648) {
					// going to inventory
					if(funcInsert == 1) {
						mainInsert = 1;
						mainUpdate = 0;
						mainSearch = 0;
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
					}
					else if (funcUpdate == 1) {
						mainInsert = 0;
						mainUpdate = 1;
						mainSearch = 0;					
					}
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					inventoryMain();
				}
				// going to order
				else if (x >= 520 && x <= 720 && y >= 378 && y <= 648) { 
					if(funcInsert == 1) {
						mainInsert = 1;
						mainUpdate = 0;
						mainSearch = 0;
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
					}
					else if (funcUpdate == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 0;
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();
					}
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					orderMain();					
				}
				// going to customer
				else if (x >= 800 && x <= 1040 && y >= 378 && y <= 648) {
					f.remove(bg);
					// form
					if(funcInsert == 1) {
						mainInsert = 1;
						mainUpdate = 0;
						mainSearch = 0;
						casf.insertCustomer();
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();
					}
					else if (funcSearch == 1) {
						mainInsert = 0;
						mainUpdate = 0;
						mainSearch = 1;
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						customerGet();
					}
					else if (funcUpdate == 1) {
						mainInsert = 0;
						mainUpdate = 1;
						mainSearch = 0;
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						customerUpdate();						
					}					
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						funcMain();										
				}
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);	
		f.revalidate();
		f.repaint();				
	}
	public void inventoryMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\inventory-option.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// first three = product
				if(mainInsert == 1 && x >= 250 && x <= 462 && y >= 334 && y <= 588) {
					casf.insertProduct();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);					
					inventoryMain();
				}
				else if(mainSearch == 1 && x >= 250 && x <= 462 && y >= 334 && y <= 588) {
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);					
					prodGet();
				}
				else if(mainUpdate == 1 && x >= 250 && x <= 462 && y >= 334 && y <= 588	) {
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);					
					prodUpdate();
				}									
				// second = beginning
				else if (x >= 813 && x <= 1035 && y >= 348 && y <= 616 && mainInsert == 1) {
					casf.insertBegInv();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);					
					inventoryMain();
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();										
				}				
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);	
		f.revalidate();
		f.repaint();
	}
	public void orderMain() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\order-option.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// first two = order
				if(mainInsert == 1 && x >= 286 && x <= 506 && y >= 340 && y <= 582) {
					try {
						casf.insertOrder();
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						orderMain();
					}
					catch(Exception h) {
						System.out.println("Insert Order CASForm Error");
					}
				}
				else if(mainSearch == 1 && x >= 286 && x <= 506 && y >= 340 && y <= 582) {
					try {
					    results = casf.getOrder();
					    f.getContentPane().removeAll();
					    f.removeMouseListener(ml);	
					    orderMain();
					} catch (Exception ex) {
					    System.out.println("Error in getOrder " + ex);
					}
				}
				// second two = order item							
				else if (x >= 744 && x <= 1044 && y >= 334 && y <= 588 && mainInsert == 1) {
					casf.insertOrderItem();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					orderMain();
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();										
				}				
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);			
		f.revalidate();
		f.repaint();		
	}
	public void customerUpdate() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\update-cust.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// debt
				if(x >= 198 && x <= 496 && y >= 276 && y <= 594 && mainUpdate == 1) {
					casf.updateBal();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					customerUpdate();
				}
				// balance						
				else if (x >= 732 && x <= 1032 && y >= 276 && y <= 594 && mainUpdate == 1) {
					casf.updateDebt();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					customerUpdate();
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();										
				}				
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);	
		f.revalidate();
		f.repaint();		
	}
	public void customerGet() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\get-cust.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// no debt
				if(x >= 160 && x <= 476 && y >= 254 && y <= 564 && mainSearch == 1) {
					try {
					    results = casf.getCustInfo();
					    f.getContentPane().removeAll();
					    f.removeMouseListener(ml);	
					    customerGet();
					} catch (Exception ex) {
					    System.out.println("Error in customerGet " + ex);
					}
				}
				// with debt					
				else if (x >= 770 && x <= 1222 && y >= 254 && y <= 622 & mainSearch == 1) {
					try {
						casf.getCustWithDebt();
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						customerGet();
					}
					catch (Exception ex) {
						System.out.println("Error in inserting " + ex);
					}
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						main();										
				}				
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);	
		f.revalidate();
		f.repaint();		
	}
	public void prodGet() {
		JLabel bg = new JLabel(new ImageIcon(path+"\\get-prod.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// price
				if(x >= 138 && x <= 474 && y >= 244 && y <= 594 & mainSearch == 1) {
					try {
					    results = casf.getPrice();
					    f.getContentPane().removeAll();
					    f.removeMouseListener(ml);	
					    prodGet();
					} catch (Exception ex) {
					    System.out.println();
					}
				}
				// profit					
				else if (x >= 792 && x <= 1187 && y >= 244 && y <= 594 & mainSearch == 1) {
					try {
					    casf.getProfit();
					    f.getContentPane().removeAll();
					    f.removeMouseListener(ml);	
					    prodGet();
					} catch (Exception ex) {
					    System.out.println();
					}
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						inventoryMain();										
				}				
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);
		f.revalidate();
		f.repaint();		
	}
	public void prodUpdate()  {
		JLabel bg = new JLabel(new ImageIcon(path+"\\update-prod.png"));
		f.add(bg,BorderLayout.CENTER);
		ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				// station
				if(x >= 44 && x <= 404 && y >= 320 && y <= 598 && mainUpdate == 1) {
					casf.updateStation();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					prodUpdate();
				}
				// price				
				else if (x >= 528 && x <= 798 && y >= 244 && y <= 594 && mainUpdate == 1) {
					casf.updatePrice();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					prodUpdate();
				}
				// remarks
				else if (x >= 922 && x <= 1228 && y >= 244 && y <= 594 && mainUpdate == 1) {
					casf.updateRemarks();
					f.getContentPane().removeAll();
					f.removeMouseListener(ml);	
					prodUpdate();
				}
				else if(x >= 6 && x <= 256 && y >= 624 && y <= 715) {
						f.getContentPane().removeAll();
						f.removeMouseListener(ml);	
						inventoryMain();										
				}								
				System.out.println(x + " , " + y);
			}
		};
		f.addMouseListener(ml);
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
