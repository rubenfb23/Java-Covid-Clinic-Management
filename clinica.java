

import java.awt.*;
import java.awt.event.*;
//login
@SuppressWarnings("serial")
class clinica extends Frame
{
    Label l1,l2;
    TextField tf1,tf2;
    Button b1,b2;
    clinica parent;
    public clinica()
    {
        this.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();}});
        parent=this;
        
        Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();
        this.setLocation(width/2-370/2, height/2-200/2);
        
        this.setBackground(Color.pink);
        this.setTitle("COVID-19 Clinic");
        Font f=new Font("Arial",Font.BOLD,14);
        l1=new Label("User Name",Label.RIGHT);
        l2=new Label("Password",Label.RIGHT);
        l1.setFont(f);
        l2.setFont(f);
        
        tf1=new TextField(20);
        tf2=new TextField(20);
        tf2.setEchoChar('*');
        
        b1=new Button("Login");
        b2=new Button("Cancel");
        
        this.setLayout(new BorderLayout());
        
        Font font=new Font("Arial",Font.BOLD,18);
        Label head=new Label("Login Page",Label.CENTER);
        head.setFont(font);
        this.add(head,BorderLayout.NORTH);
        
        Panel p=new Panel();
        p.setLayout(new GridLayout(3,2,5,5));
        p.add(l1);
        p.add(tf1);
        p.add(l2);
        p.add(tf2);
        p.add(b1);
        p.add(b2);
        
        this.add(p,BorderLayout.CENTER);
        
        b1.addActionListener(new myButton());
        b2.addActionListener(new myButton());
    }
    public Insets getInsets(){
        return new Insets(50,20,20,20);
    }
    class myButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            String str=e.getActionCommand();
            
            if(str.equalsIgnoreCase("Cancel")){
                tf1.setText(" ");
                tf2.setText(" ");
            }
            else
            {
                String user=tf1.getText().trim();
                String psw=tf2.getText().trim();
                if(user.equals("admin") && psw.equals("admin"))
                {
                    parent.setVisible(false);
                    Welcome welcome=new Welcome();
                    welcome.setVisible(true);
                    welcome.setSize(200,400);
                    welcome.setResizable(false);
                }
            }
        }
        
    }
    public static void main()
    {
        clinica obj=new clinica();
        obj.setSize(400,200);
        obj.setVisible(true);
        obj.setResizable(false);
    }
}