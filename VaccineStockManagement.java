import java.awt.*;
import java.awt.event.*;

public class VaccineStockManagement extends Frame{
	
	Welcome ref;
	VaccineStockManagement parent;
	
	Button b1,b2,b3;
	public VaccineStockManagement(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-300/2);
        
		b1=new Button("Stock Receive");
		b2=new Button("Status of Stock");
		b3=new Button("Return");
		
		b1.addActionListener(new MyButton());
		b2.addActionListener(new MyButton());
		b3.addActionListener(new MyButton());
		
		this.setLayout(new GridLayout(3,1,10,10));
		this.add(b1);
		this.add(b2);
		this.add(b3);
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }

	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Button bt=(Button)arg0.getSource();
			if(bt==b1) {
				StockReceive obj=new StockReceive(ref);
				obj.setSize(300,300);
				obj.setVisible(true);
				parent.dispose();
			}
			else if(bt==b2) {
				StockStatus obj=new StockStatus(ref);
				obj.setSize(300,200);
				obj.setVisible(true);
				obj.setTitle("Stock Status");
				parent.dispose();
			}
			else if(bt==b3) {
				parent.dispose();
				ref.setVisible(true);
			}
			
		}
		
	}
}
