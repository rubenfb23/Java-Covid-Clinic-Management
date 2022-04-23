
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;


@SuppressWarnings("serial")
public class Welcome extends Frame{
    
    NurseDatabase ndata[]=new NurseDatabase[2];
    LabTechDatabase ldata[]=new LabTechDatabase[2];
    Vaccine vac=new Vaccine();
    Employee employee[]=new Employee[10];
    Vaccinerecord vRecord[]=new Vaccinerecord[10];
    
    int empCounter;
    
    Panel p=new Panel();
    Button b1,b2,b3,b4,b5,b6;
    Welcome parent;
    public Welcome()
    {
        parent=this;
        this.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();}});
        
        Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        parent.setLocation(width/2-200/2, height/2-400/2);
        
        for(int i=0;i<10;i++){
            vRecord[i]=null;
            employee[i]=null;
        }
        //enfermeros y tecnicos base
        ndata[0]=new NurseDatabase("Jose","Lopez","Madrid, España","123456");
        ndata[1]=new NurseDatabase("Andres","Duque","Ourense, España","456789");
        ldata[0]=new LabTechDatabase("Juan","Martinez","Sevilla, España","112233");
        ldata[1]=new LabTechDatabase("Timothy","Martin","Gibraltar, España","445566");
        
        empCounter=0;
        
        this.setLayout(new BorderLayout());
        
        b1=new Button("User Management");
        b2=new Button("Tests");
        b3=new Button("Communication with Patient");
        b4=new Button("Vaccine Stock Management");
        b5=new Button("Vaccination Management");
        b6=new Button("Query");
        
        b1.addActionListener(new manageButton());
        b2.addActionListener(new manageButton());
        b3.addActionListener(new manageButton());
        b4.addActionListener(new manageButton());
        b5.addActionListener(new manageButton());
        b6.addActionListener(new manageButton());
        
        p.setLayout(new GridLayout(6,1,10,10));
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        
        this.add(p,BorderLayout.CENTER);
    }
    public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
    class manageButton implements ActionListener
    {
          //seleccion
        @Override
        public void actionPerformed(ActionEvent e) {
            String str=e.getActionCommand();
            parent.setVisible(false);
            if(str.equals("User Management")){
                UserManagement user=new UserManagement(ndata,ldata,parent);
                user.setSize(450,600);
                user.setVisible(true);
                user.setResizable(false);
            }
            else if(str.equals("Tests")){
                ConfinedPatient cp=new ConfinedPatient(parent);
                cp.setSize(600,300);
                cp.setVisible(true);
                
            }
            else if(str.equals("Communication with Patient")){
                ContactWithPatient obj=new ContactWithPatient(parent);
                obj.setSize(300,300);
                obj.setVisible(true);
            }
            else if(str.equals("Vaccine Stock Management")){
                VaccineStockManagement vsm=new VaccineStockManagement(parent);
                vsm.setSize(300,300);
                vsm.setVisible(true);
            }
            else if(str.equals("Vaccination Management")){
                VaccineManagement vact=new VaccineManagement(parent);
                vact.setSize(450,400);
                vact.setVisible(true);
                
            }
            else if (str.equals("Query")) {
                QueryPage page=new QueryPage(parent);
                page.setSize(300,300);
                page.setVisible(true);
            }
        }
    
    }
}
