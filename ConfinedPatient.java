import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfinedPatient extends JFrame{
	Welcome ref;
	String head[]= {"Name","Type Of Test","Test Result","Date of Test","Nurse","Lab. Technician"};
	JButton btn;
	ConfinedPatient parent;
	public ConfinedPatient(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-600/2, height/2-300/2);
        
		int count=0;
		for(int i=0;i<10;i++)
			if(ref.employee[i]!=null)
				count++;
			else
				break;
		
		Object data[][]=new Object[count][6];
		for(int i=0;i<count;i++) {
			data[i][0]=ref.employee[i].fname+" "+ref.employee[i].lname;
			data[i][1]=ref.employee[i].typeOfTest;
			data[i][2]=ref.employee[i].testResult;
			data[i][3]=ref.employee[i].testDate;
			data[i][4]=ref.employee[i].nurseName;
			data[i][5]=ref.employee[i].labTechName;
		}
		
		btn=new JButton("Back");
		btn.addActionListener(new MyButton());
		
		JTable table=new JTable(data,head);
		getContentPane().add(table.getTableHeader(),BorderLayout.NORTH);
		getContentPane().add(table);
		getContentPane().add(btn,BorderLayout.SOUTH);
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			parent.dispose();
			ref.setVisible(true);
			
		}
		
	}
}

