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