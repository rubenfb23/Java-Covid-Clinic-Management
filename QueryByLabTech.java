import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class QueryByLabTech  extends JFrame{
	Label lname;
	
	Choice nameChoice;
	Welcome ref;
	QueryByLabTech parent;
	JPanel localPanel;
	JButton btn;
	
	public QueryByLabTech(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-600/2, height/2-300/2);
        
		lname=new Label("Name of Nurse");
		nameChoice=new Choice();
		nameChoice.addItemListener(new MyItem());
		nameChoice.add("Name of Lab Technician");
		for(int i=0;i<2;i++) {
			nameChoice.add(ref.ldata[i].fname);
		}
		localPanel=new JPanel();
		localPanel.setLayout(new GridLayout(1,2));
		localPanel.add(lname);
		localPanel.add(nameChoice);
		parent.add(localPanel,BorderLayout.NORTH);
		btn=new JButton("Return");
		btn.addActionListener(new MyButton());
		
	}

	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	class MyButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			parent.dispose();
			ref.setVisible(true);
			
		}
		
	}
	class MyItem implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			
			String choice=((Choice)arg0.getSource()).getSelectedItem();
			parent.remove(localPanel);
			String head[]= {"Patient Name","Test","Test Result","Test Date"};
			int count=0;
			for(int i=0;i<10;i++) {
				if(ref.employee[i]!=null && ref.employee[i].labTechName.indexOf(choice)>=0)
					count++;
				
			}
			Object data[][]=new Object[count][4];
			
			int x=0;
			for(int i=0;i<10;i++) {
				if(ref.employee[i]!=null && ref.employee[i].labTechName.indexOf(choice)>=0) {
					data[x][0]=ref.employee[i].fname+" "+ref.employee[i].lname;
					data[x][1]=ref.employee[i].typeOfTest;
					data[x][2]=ref.employee[i].testResult;
					data[x][3]=ref.employee[i].testDate;
					x++;
				}
					
			}
			
			JTable table=new JTable(data,head);
			parent.getContentPane().add(table.getTableHeader(),BorderLayout.NORTH);
			parent.getContentPane().add(table,BorderLayout.CENTER);
			parent.getContentPane().add(btn,BorderLayout.SOUTH);
			parent.setVisible(true);
		}
		
	}
}
