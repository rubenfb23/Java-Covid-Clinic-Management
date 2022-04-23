import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QueryPage extends Frame{
	Welcome ref;
	QueryPage parent;
	
	Button b1,b2,back;
	public QueryPage(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-300/2);
        
		b1=new Button("Query By Nurse");
		b2=new Button("Query By Lab Technician");
		back=new Button("Return");
		
		this.setLayout(new GridLayout(3,1,20,20));;
		this.add(b1);
		this.add(b2);
		this.add(back);
		
		b1.addActionListener(new MyButton());
		b2.addActionListener(new MyButton());
		back.addActionListener(new MyButton());
	}
	 public Insets getInsets(){
	        return new Insets(50,20,20,20);
	    }
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str=arg0.getActionCommand();
			if(str.equals("Return")) {
				parent.dispose();
				ref.setVisible(true);
			}
			else if (str.equals("Query By Nurse")){
				parent.dispose();
				QueryByNurseName obj=new QueryByNurseName(ref);
				obj.setSize(600,300);
				obj.setVisible(true);
			}
			else {
				parent.dispose();
				QueryByLabTech obj=new QueryByLabTech(ref);
				obj.setSize(600,300);
				obj.setVisible(true);
			}
			
		}
		
	}
	
}
