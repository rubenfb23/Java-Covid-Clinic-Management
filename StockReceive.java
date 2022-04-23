import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;


public class StockReceive extends Frame{
	
	Welcome ref;
	StockReceive parent;
	
	Label l1,l2,l3;
	Choice c1;
	TextField tf1,tf2;
	Button b1,b2;
	
	public StockReceive(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-300/2);
        
		l1=new Label("Type of Vaccine");
		l2=new Label("In Stock Qty ");
		l3=new Label("New Stock Received");
		
		c1=new Choice();
		c1.add("Select Name");
		for(int i=0;i<3;i++)
		{
			c1.add(ref.vac.nameOfVaccine[i]);
		}
		c1.addItemListener(new MyItem());
		tf1=new TextField(20);
		tf1.setEditable(false);
		tf2=new TextField(20);
		
		b1=new Button("Submit");
		b2=new Button("Cancel");
		b1.addActionListener(new MyButton());
		b2.addActionListener(new MyButton());
		this.setLayout(new GridLayout(4,2,10,10));
		
		this.add(l1);
		this.add(c1);
		this.add(l2);
		this.add(tf1);
		this.add(l3);
		this.add(tf2);
		this.add(b1);
		this.add(b2);
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	class MyItem implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			int index=c1.getSelectedIndex();
			int pstock=ref.vac.getStock(index-1);
			tf1.setText(""+pstock);
		}
	}
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Button bt=(Button)arg0.getSource();
			if(bt==b1) {
				int nstock=Integer.parseInt(tf2.getText());
				int index=c1.getSelectedIndex();
				ref.vac.updateStock(index-1, nstock);
				
				JOptionPane.showMessageDialog(parent,"Stock Updated Successfully","Message",JOptionPane.INFORMATION_MESSAGE);
                parent.dispose();
				ref.setVisible(true);
			}
			else {
				parent.dispose();
				ref.setVisible(true);
			}
			
		}
		
	}
}
