
public class Patient {
    String fname;
    String lname;
    String address;
    String phoneno;
    String testResult;
    String typeOfTest;
    String testDate;
   
    
    public Patient()
    {
        fname="";
        lname="";
        address="";
        phoneno="";
        testResult="";
        typeOfTest="";
        testDate="";
     
    }
    public Patient(String fn, String ln, String ad, String pno){
        fname=fn;
        lname=ln;
        address=ad;
        phoneno=pno;
        testResult="";
        typeOfTest="";
        testDate="";
       
    }
    
    
}
