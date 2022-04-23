import java.awt.*;
import java.awt.event.*;
public class StockStatus extends Frame {
	
	Welcome ref;
	StockStatus parent;
	
	Label l1,l2;
	TextField tf1;
	Choice c1;
	Button b1;
	public StockStatus(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-200/2);
        
		l1=new Label("Type of Vaccine");
		l2=new Label("Stock Qty");
		
		tf1=new TextField(20);
		tf1.setEditable(false);
		
		c1=new Choice();
		c1.add("Select Vaccine");
		for(int i=0;i<3;i++)
			c1.add(ref.vac.getVacName(i));
		c1.addItemListener(new MyItem());
		b1=new Button("Return");
		b1.addActionListener(new MyButton());
		
		this.setLayout(new BorderLayout());
		Panel localPanel=new Panel();
		localPanel.setLayout(new GridLayout(2,2,10,10));;
		localPanel.add(l1);
		localPanel.add(c1);
		localPanel.add(l2);
		localPanel.add(tf1);
		
		this.add(localPanel,BorderLayout.CENTER);
		this.add(b1,BorderLayout.SOUTH);
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
			parent.dispose();
			ref.setVisible(true);
		}
	}
}
