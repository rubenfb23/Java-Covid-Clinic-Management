import java.awt.*;
import java.awt.event.*;


public class ContactWithPatient extends Frame{
	
	Welcome ref;
	ContactWithPatient parent;
	
	Button b1,b2,b3;
	public ContactWithPatient(Welcome ref) {
		this.ref=ref;
		parent=this;
		
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-300/2, height/2-300/2);
        
		b1=new Button("List of Home Issolation Patient");
		b2=new Button("List of Patient Completed Confinement");
		b3=new Button("Return");
		
		parent.setLayout(new GridLayout(3,1,10,10));
		parent.add(b1);
		parent.add(b2);
		parent.add(b3);
		
		b1.addActionListener(new MyButton());
		b2.addActionListener(new MyButton());
		b3.addActionListener(new MyButton());
	}
	public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
	class MyButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Button btn=(Button)arg0.getSource();
			if(btn==b1) {
				IssolatePatient obj=new IssolatePatient(ref);
				obj.setSize(700,300);
				obj.setVisible(true);
				parent.dispose();
			}
			else if(btn==b2) {
				NonIssolatePatient obj=new NonIssolatePatient(ref);
				obj.setSize(700,300);
				obj.setVisible(true);
				parent.dispose();
			}
			else {
				parent.dispose();
				ref.setVisible(true);
			}
			
		}
		
	}
}
