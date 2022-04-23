import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VaccineManagement extends Frame {
	Welcome ref;
	
	Label l1,l2,l3,l4,l5,l6,l7;
	TextField tf1,tf2,tf3,tf4;
	Choice c1,c2;
	Checkbox cb1,cb2;
	CheckboxGroup cbg;
	Button b1,b2;
	VaccineManagement parent;
	public VaccineManagement(Welcome ref) {
		this.ref=ref;
		this.parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-450/2, height/2-400/2);
        
		l1=new Label("Name",Label.RIGHT);
		l2=new Label("Vaccinated",Label.RIGHT);
		l3=new Label("Age",Label.RIGHT);
		l4=new Label("Type of Vaccine",Label.RIGHT);
		l5=new Label("Dose Number",Label.RIGHT);
		l6=new Label("Nurse Name",Label.RIGHT);
		l7=new Label("Date of Vaccination",Label.RIGHT);
		
		tf1=new TextField(20);
		cbg=new CheckboxGroup();
		cb1=new Checkbox("Yes",false,cbg);
		cb2=new Checkbox("No",true,cbg);
		
		Panel localPanel=new Panel();
		localPanel.setLayout(new GridLayout(1,2,10,10));;
		localPanel.add(cb1);
		localPanel.add(cb2);
		tf2=new TextField(20);
		c1=new Choice();
		for(int i=0;i<3;i++) {
			c1.add(ref.vac.getVacName(i));
		}
		tf3=new TextField(20);
		c2=new Choice();
		for(int i=0;i<2;i++) {
			c2.add(ref.ndata[i].fname);
		}
		tf4=new TextField(20);
		b1=new Button("Submit");
		b2=new Button("Return");
		b1.addActionListener(new MyButton());
		b2.addActionListener(new MyButton());
		
		this.setLayout(new GridLayout(8,2,10,10));
		this.add(l1);
		this.add(tf1);
		this.add(l2);
		this.add(localPanel);
		this.add(l3);
		this.add(tf2);
		this.add(l4);
		this.add(c1);
		this.add(l5);
		this.add(tf3);
		this.add(l6);
		this.add(c2);
		this.add(l7);
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		tf4.setText(sdf.format(dt));
		this.add(tf4);
		
		this.add(b1);
		this.add(b2);
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Submit")) {
				if(cb2.getState()==true && Integer.parseInt(tf2.getText())<65) {
					
	                JOptionPane.showMessageDialog(parent,"Your age is below 65. Please wait for your turn","Message",JOptionPane.INFORMATION_MESSAGE);
	                parent.dispose();
					ref.setVisible(true);
				}
				else {
					String pn=tf1.getText();
					int a=Integer.parseInt(tf2.getText());
					String type=c1.getSelectedItem();
					int stock=ref.vac.getStock(c1.getSelectedIndex());
					stock--;
					ref.vac.setStock(c1.getSelectedIndex(), stock);
					int dos=Integer.parseInt(tf3.getText());
					String nname=c2.getSelectedItem();
					String dt=tf4.getText();
					Vaccinerecord record=new Vaccinerecord(pn,a,type,dos,nname,dt);
					int index=0;
					for(index=0;index<10;index++)
						if(ref.vRecord[index]==null)
							break;
					ref.vRecord[index]=record;
					parent.dispose();
					ref.setVisible(true);
				}
			}
			else
			{
				parent.dispose();
				ref.setVisible(true);
			}
			
		}
		
	}

}
