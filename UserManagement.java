

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class UserManagement extends Frame {
    Panel p;
    Panel centerPanel1=new Panel();
    Panel centerPanel2=new Panel();
    Panel centerPanel3=new Panel();
    Button b1,b2,b3;
    
    //Registro
    Label l1,l2,l3,l4,l5,l6,l7;
    TextField fn,ln,ad,ph;
    Checkbox cb1,cb2;
    CheckboxGroup cbg;
    Button submit,cancel;
    Choice choice, nameChoice;
    
    
    //Controles para la cancelacion
    TextField cat;
    
    UserManagement parent;
    Welcome ref;
    
    NurseDatabase ndata[];
    LabTechDatabase ldata[];
    
   
    
    
    public UserManagement(NurseDatabase ndata[], LabTechDatabase ldata[], Welcome ref){
    	this.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();}});
    	
    	this.ref=ref;
        parent=this;

        Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-450/2, height/2-600/2);
        
        this.ndata=ndata;
        this.ldata=ldata;
        
        p=new Panel();
        p.setLayout(new GridLayout(1,3,10,10));
        
        b1=new Button("Registration");
        b2=new Button("Cancellation");
        b3=new Button("Modification");
        b1.addActionListener(new buttonEvent());
        b2.addActionListener(new buttonEvent());
        b3.addActionListener(new buttonEvent());
        
        p.add(b1);
        p.add(b2);
        p.add(b3);
        
        this.setLayout(new BorderLayout());
        this.add(p,BorderLayout.NORTH);
        this.add(centerPanel1,BorderLayout.CENTER);
        this.setTitle("User Management");
        
        //Panel de registro
        
    }
    public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
    class buttonEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String str=e.getActionCommand();
            
            //Registro de nuevo paciente
            if(str.equals("Registration")){
                l1=new Label("First Name",Label.RIGHT);
                l2=new Label("Last Name",Label.RIGHT);
                l3=new Label("Address",Label.RIGHT);
                l4=new Label("Phone Number",Label.RIGHT);
                l5=new Label("Type",Label.RIGHT);
                l6=new Label("Emp. Category",Label.RIGHT);
                l7=new Label("Select Nurse/Lab Tech.",Label.RIGHT);
                
                fn=new TextField(20);
                ln=new TextField(20);
                ad=new TextField(20);
                ph=new TextField(20);
                
                cbg=new CheckboxGroup();
                cb1=new Checkbox("Patient",true,cbg);
                cb2=new Checkbox("Employee",false,cbg);
                cb1.addItemListener(new categoryListener());
                cb2.addItemListener(new categoryListener());
                Panel localPanel=new Panel();
                localPanel.setLayout(new GridLayout(1,2));
                localPanel.add(cb1);
                localPanel.add(cb2);
                
                choice=new Choice();
                choice.add("Administrator");
                choice.add("Nurses");
                choice.add("Lab Technicians");
                choice.setEnabled(false);
                choice.addItemListener(new typeListener());
                
                nameChoice=new Choice();
                nameChoice.setEnabled(false);
                nameChoice.addItemListener(new nameChoiceListener());
                
                
                submit=new Button("Submit");
                cancel=new Button("Cancel");
                
                centerPanel1.removeAll();
                centerPanel1.setLayout(new GridLayout(8,2,10,10));
                centerPanel1.add(l5);
                centerPanel1.add(localPanel);
                centerPanel1.add(l6);
                centerPanel1.add(choice);
                centerPanel1.add(l7);
                centerPanel1.add(nameChoice);
                centerPanel1.add(l1);
                centerPanel1.add(fn);
                centerPanel1.add(l2);
                centerPanel1.add(ln);
                centerPanel1.add(l3);
                centerPanel1.add(ad);
                centerPanel1.add(l4);
                centerPanel1.add(ph);
                
                
                centerPanel1.add(submit);
                centerPanel1.add(cancel);
                
                parent.remove(centerPanel1);
                parent.remove(centerPanel2);
                parent.remove(centerPanel3);   
                
                parent.add(centerPanel1,BorderLayout.CENTER);
                parent.setSize(450,600);
                parent.setVisible(true);
                                
                submit.addActionListener(new regButton());
                cancel.addActionListener(new regButton());
            }
            else if(str.equals("Cancellation")){
                //Cancelar cualquier registro
            	
            	l1=new Label("Select Record",Label.RIGHT);
                l2=new Label("Name",Label.RIGHT);
                l3=new Label("Address",Label.RIGHT);
                l4=new Label("Phone Number",Label.RIGHT);
                l5=new Label("Category",Label.RIGHT);
                fn=new TextField(20);
                cat=new TextField(20);
                ad=new TextField(20);
                ph=new TextField(20);
                
                choice=new Choice();
                for(int i=0;i<10;i++) {
                	if(ref.employee[i]!=null)
                		choice.add(ref.employee[i].fname+" "+ref.employee[i].lname);
                	else
                		break;
                }
                choice.addItemListener(new CancelListener());
                choice.addFocusListener(new CancelFocus());
                centerPanel2.removeAll();
                centerPanel2.setLayout(new GridLayout(6,2,10,10));
                centerPanel2.add(l1);centerPanel2.add(choice);
                centerPanel2.add(l2);centerPanel2.add(fn);
                centerPanel2.add(l3);centerPanel2.add(ad);
                centerPanel2.add(l4);centerPanel2.add(ph);
                centerPanel2.add(l5);centerPanel2.add(cat);
                submit=new Button("Yes");
                cancel=new Button("No");
                submit.addActionListener(new YesButton());
                cancel.addActionListener(new YesButton());
                centerPanel2.add(submit);centerPanel2.add(cancel);

                parent.remove(centerPanel1);
                parent.remove(centerPanel2);
                parent.remove(centerPanel3);   
                
                parent.add(centerPanel2,BorderLayout.CENTER);
                parent.setSize(450,500);
                parent.setVisible(true);
            }
            else{
                //Modificaciones
            	l1=new Label("Select Record",Label.RIGHT);
                l2=new Label("Name",Label.RIGHT);
                l3=new Label("Address",Label.RIGHT);
                l4=new Label("Phone Number",Label.RIGHT);
                l5=new Label("Category",Label.RIGHT);
                fn=new TextField(20);
                cat=new TextField(20);
                ad=new TextField(20);
                ph=new TextField(20);
                
                choice=new Choice();
                for(int i=0;i<10;i++) {
                	if(ref.employee[i]!=null)
                		choice.add(ref.employee[i].fname+" "+ref.employee[i].lname);
                	else
                		break;
                }
                choice.addItemListener(new CancelListener());
                choice.addFocusListener(new CancelFocus());
                
                centerPanel3.removeAll();
                centerPanel3.setLayout(new GridLayout(6,2,10,10));
                centerPanel3.add(l1);centerPanel3.add(choice);
                centerPanel3.add(l2);centerPanel3.add(fn);
                centerPanel3.add(l3);centerPanel3.add(ad);
                centerPanel3.add(l4);centerPanel3.add(ph);
                centerPanel3.add(l5);centerPanel3.add(cat);
                submit=new Button("Modify");
                cancel=new Button("Return");
                submit.addActionListener(new ModifyButton());
                cancel.addActionListener(new ModifyButton());
                
                centerPanel3.add(submit);centerPanel3.add(cancel);
                
                parent.remove(centerPanel1);
                parent.remove(centerPanel2);
                parent.remove(centerPanel3);   
                
                parent.add(centerPanel3,BorderLayout.CENTER);
                parent.setSize(450,500);
                parent.setVisible(true);
            }
        }
        
    }
    
   
    class regButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String str=e.getActionCommand();
            if(str.equals("Submit")){
                //Almacenamiento de datos en el array
                String type=cb1.getState()?"Patient":"Employee";
                String fname=fn.getText().trim();
                String lname=ln.getText().trim();
                String address=ad.getText().trim();
                String phoneno=ph.getText().trim();
                String desig="";
                if(type.equals("Patient")){
                	desig="Patient";
                }
                else
                {
                	desig=choice.getSelectedItem();
                }
                Employee obj=new Employee(fname,lname,address,phoneno,desig);
            	for(int i=0;i<10;i++)
            	{
            		if(ref.employee[i]==null) {
            			ref.employee[i]=obj;
            			break;
            		}
            	}
                
                
                TestPage vpage=new TestPage(ref);
                vpage.setSize(300,300);
                parent.setVisible(false);
                vpage.setVisible(true);
                vpage.setTitle("Diagnostic Test");
                vpage.setResizable(false);
            }
            else{
                fn.setText(" ");
                ln.setText(" ");
                ad.setText(" ");
                ph.setText(" ");
                cb1.setState(true);
                cb2.setState(false);
                choice.setEnabled(false);
            }
        }
        
    }
    class categoryListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(cb1.getState()==true){
                choice.setEnabled(false);
            }
            else if(cb2.getState()==true){
                choice.setEnabled(true);
            }
        }
    }
    class typeListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            String value=choice.getSelectedItem();
            
            if(value.equals("Nurses") || value.equals("Lab Technicians")){
                nameChoice.setEnabled(true);
                fn.setEnabled(false);
                ln.setEnabled(false);
                ad.setEnabled(false);
                ph.setEnabled(false);
                nameChoice.removeAll();
                
                if(value.equals("Nurses")){
                    for(int i=0;i<ndata.length;i++)
                        nameChoice.add(ndata[i].fname+" "+ndata[i].lname);
                }
                else{
                    for(int i=0;i<ldata.length;i++)
                        nameChoice.add(ldata[i].fname+" "+ldata[i].lname);
                }
                nameChoice.select(0);
                String str=nameChoice.getSelectedItem();
    			fn.setText(str.substring(0,str.indexOf(' ')));
    			ln.setText(str.substring(str.indexOf(' ')+1));
    			
    			if(choice.getSelectedIndex()==1) {
    				ad.setText(ndata[0].address);
    				ph.setText(ndata[0].phoneno);
    			}
    			else if(choice.getSelectedIndex()==2) {
    				ad.setText(ldata[0].address);
    				ph.setText(ldata[0].phoneno);
    			}
                
            }
            else{
                nameChoice.setEnabled(false);
                fn.setEnabled(true);
                ln.setEnabled(true);
                ad.setEnabled(true);
                ph.setEnabled(true);
                fn.setText(" ");
                ln.setText(" ");
                ad.setText(" ");
                ph.setText(" ");
            }
        }
    }
    class nameChoiceListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			String str=nameChoice.getSelectedItem();
			fn.setText(str.substring(0,str.indexOf(' ')));
			ln.setText(str.substring(str.indexOf(' ')+1));
			int index=nameChoice.getSelectedIndex();
			
			if(choice.getSelectedIndex()==1) {
				ad.setText(ndata[index].address);
				ph.setText(ndata[index].phoneno);
			}
			else if(choice.getSelectedIndex()==2) {
				ad.setText(ldata[index].address);
				ph.setText(ldata[index].phoneno);
			}
		}
    }
    class CancelListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
	
			int index=choice.getSelectedIndex();
			fn.setText(ref.employee[index].fname+" "+ref.employee[index].lname);
			ad.setText(ref.employee[index].address);
			ph.setText(ref.employee[index].phoneno);
			cat.setText(ref.employee[index].desig);
		}
    	
    }
    
    class CancelFocus implements FocusListener{

		@Override
		public void focusGained(FocusEvent arg0) {
		
			int index=choice.getSelectedIndex();
			fn.setText(ref.employee[index].fname+" "+ref.employee[index].lname);
			ad.setText(ref.employee[index].address);
			ph.setText(ref.employee[index].phoneno);
			cat.setText(ref.employee[index].desig);
		}
		@Override
		public void focusLost(FocusEvent arg0) {
		}
    }
    class YesButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str=arg0.getActionCommand();
			if(str.equals("Yes")) {
				int index=choice.getSelectedIndex();
				int i=0;
				for(i=index;i<9;i++)
					ref.employee[i]=ref.employee[i+1];
				ref.employee[i]=null;
				parent.dispose();
				ref.setVisible(true);
			}
			else {
				parent.dispose();
				ref.setVisible(true);
			}
			
		}
    	
    }
    class ModifyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str=arg0.getActionCommand();
			String fullname=choice.getSelectedItem();
			
			if(str.equals("Modify")) {
				int index=choice.getSelectedIndex();
				ref.employee[index].fname=fullname.substring(0,fullname.indexOf(' '));
				ref.employee[index].lname=fullname.substring(fullname.indexOf(' ')+1);
				ref.employee[index].address=ad.getText().trim();
				ref.employee[index].phoneno=ph.getText().trim();
				ref.employee[index].desig=cat.getText().trim();
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