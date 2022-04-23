import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;



@SuppressWarnings("serial")
public class TestPage extends Frame{
	Welcome ref;
	
	Label l1,l2,l3,l4,l5,l6;
	Label pname;
	Choice type,nurseName,labTech;
	TextField result,tdate;
	Button b1;
	int counter=0;
	TestPage parent;
	public TestPage(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-300/2);
        
		for(counter=0;counter<10;counter++)
		{
			if(ref.employee[counter]==null) {
				counter--;
				break;
			}
		}
		
		
		l1=new Label("Name ",Label.RIGHT);
		l2=new Label("Type of Test ",Label.RIGHT);
		l3=new Label("Result of Test",Label.RIGHT);
		l4=new Label("Date",Label.RIGHT);
		l5=new Label("Assigned Nurse",Label.RIGHT);
		l6=new Label("Lab Technician",Label.RIGHT);
		
		pname=new Label(ref.employee[counter].fname+" "+ref.employee[counter].lname);
		type=new Choice();
		type.add("Antigen Test");
		type.add("PCR Test");
		type.add("Serological Analyze");
		type.select(0);
		result=new TextField(20);
		tdate=new TextField(20);
		nurseName=new Choice();
		for(int i=0;i<2;i++) {
				nurseName.add(ref.ndata[i].fname);
			}
		labTech=new Choice();
		for(int i=0;i<2;i++) {
			labTech.add(ref.ldata[i].fname);
			}
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		tdate.setText(sdf.format(dt));
		
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
		grid.setConstraints(l5,gbc);
		this.add(l5);
		
		gbc.gridx=1;
		gbc.gridy=4;
		grid.setConstraints(nurseName,gbc);
		this.add(nurseName);
		
		gbc.gridx=0;
		gbc.gridy=5;
		grid.setConstraints(l6,gbc);
		this.add(l6);
		
		gbc.gridx=1;
		gbc.gridy=5;
		grid.setConstraints(labTech,gbc);
		this.add(labTech);
		
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.gridwidth=2;
		grid.setConstraints(b1,gbc);
		this.add(b1);
		
		b1.addActionListener(new SubmitButton());;
		
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	class SubmitButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ref.employee[counter].testDate=tdate.getText();
			ref.employee[counter].testResult=result.getText().toUpperCase();
			ref.employee[counter].typeOfTest=type.getSelectedItem();
			ref.employee[counter].nurseName=nurseName.getSelectedItem().trim();
			ref.employee[counter].labTechName=labTech.getSelectedItem().trim();
			parent.dispose();
			ref.setVisible(true);

		}
		
	}
}
