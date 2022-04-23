import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTable;

public class IssolatePatient extends JFrame{
	Welcome ref;
	IssolatePatient parent;
	
	Button btn;
	public IssolatePatient(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-700/2, height/2-300/2);
        
        btn=new Button("Return");
        btn.addActionListener(new MyButton());
        
        String head[]= {"Name","Ph. Number","Test","Result","Date of Test","Days"};
        int count=0;
        for(int i=0;i<10;i++)
        {
        	if(ref.employee[i]!=null && (ref.employee[i].testResult.equalsIgnoreCase("POSITIVE"))&&(findDay(ref.employee[i].testDate))<=10) {
        		count++;
        	}
        }
        Object data[][]=new Object[count][6];
        int x=0;
        for(int i=0;i<10;i++)
        {
        	if(ref.employee[i]!=null && (ref.employee[i].testResult.equalsIgnoreCase("POSITIVE"))&&(findDay(ref.employee[i].testDate))<=10) {
        		data[x][0]=ref.employee[i].fname+" "+ref.employee[i].lname;
        		data[x][1]=ref.employee[i].phoneno;
        		data[x][2]=ref.employee[i].typeOfTest;
        		data[x][3]=ref.employee[i].testResult;
        		data[x][4]=ref.employee[i].testDate;
        		data[x][5]=(Integer)findDay(ref.employee[i].testDate);
        		x++;
        	}
        }
        JTable table=new JTable(data,head);
		parent.getContentPane().add(table.getTableHeader(),BorderLayout.NORTH);
		parent.getContentPane().add(table,BorderLayout.CENTER);
		parent.getContentPane().add(btn,BorderLayout.SOUTH);
		parent.setVisible(true);
        
	}
	
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			parent.dispose();
			ref.setVisible(true);
			
		}
		
		
	}
	public int findDay(String dt)
	{
		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		int day=0;
		try {
		    Date date1 = myFormat.parse(dt);
		    Date date2 = new Date();
		    long diff = date2.getTime() - date1.getTime();
		    day=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		    
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return day;
	}

}
