import java.awt.Button;
import java.awt.Choice;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;



@SuppressWarnings("serial")
public class VaccinePage extends Frame{
	Welcome ref;
	Employee employee[];
	int empCounter;
	
	Label l1,l2,l3,l4;
	Label pname;
	Choice type;
	TextField result,tdate;
	Button b1;
	
	VaccinePage parent;
	public VaccinePage(Employee employee[],int empCounter, Welcome ref) {
		this.ref=ref;
		parent=this;
		
		this.employee=employee;
		this.empCounter=empCounter;
		
		l1=new Label("Name ",Label.RIGHT);
		l2=new Label("Type of Test ",Label.RIGHT);
		l3=new Label("Result of Test",Label.RIGHT);
		l4=new Label("Date",Label.RIGHT);
		
		pname=new Label(employee[empCounter-1].fname+" "+employee[empCounter-1].lname);
		type=new Choice();
		type.add("Antigen Test");
		type.add("PCR Test");
		type.add("Serological Analyze");
		type.select(0);
		result=new TextField(20);
		tdate=new TextField(20);
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		tdate.setText(sdf.format(dt));
		tdate.setEnabled(false);
		b1=new Button("Submit");
		
		GridBagLayout grid=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.weightx=0.5;
		gbc.insets = new Insets(10,0,0,0); 
		this.setLayout(grid);

		gbc.gridx=0;
		gbc.gridy=0;
		grid.setConstraints(l1,gbc);
		add(l1);
		
		gbc.gridx=1;
		gbc.gridy=0;
		grid.setConstraints(pname,gbc);
		this.add(pname);
		
		gbc.gridx=0;
		gbc.gridy=1;
		grid.setConstraints(l2,gbc);
		this.add(l2);
		
		gbc.gridx=1;
		gbc.gridy=1;
		grid.setConstraints(type,gbc);
		this.add(type);
		
		gbc.gridx=0;
		gbc.gridy=2;
		grid.setConstraints(l3,gbc);
		this.add(l3);
		
		gbc.gridx=1;
		gbc.gridy=2;
		grid.setConstraints(result,gbc);
		this.add(result);
		
		gbc.gridx=0;
		gbc.gridy=3;
		grid.setConstraints(l4,gbc);
		this.add(l4);
		
		gbc.gridx=1;
		gbc.gridy=3;
		grid.setConstraints(tdate,gbc);
		this.add(tdate);
		
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=2;
		grid.setConstraints(b1,gbc);
		this.add(b1);
		
		b1.addActionListener(new SubmitButton());;
		
	}
	class SubmitButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			employee[empCounter-1].testDate=tdate.getText();
			employee[empCounter-1].testResult=result.getText();
			employee[empCounter-1].typeOfTest=type.getSelectedItem();
			parent.dispose();
			ref.setVisible(true);

		}
		
	}
}
