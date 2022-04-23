
public class Employee extends Patient{
    String desig;   //Puede ser el Admin, un enfermero/a o un tecnico/a de laboratorio    
    String nurseName;
    String labTechName;
    public Employee(){
        super();
        desig="";
        nurseName="";
        labTechName="";
    }
    public Employee(String fn, String ln, String ad, String pno, String dsg){
        super(fn,ln,ad,pno);
        desig=dsg;
        nurseName="";
        labTechName="";

    }
    
    
}
