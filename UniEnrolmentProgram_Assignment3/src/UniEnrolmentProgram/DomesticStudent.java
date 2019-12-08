/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

/**
 *
 * @author linh
 */
public class DomesticStudent extends Student 
{
    private String city;
    private long TFN;
    public DomesticStudent(String name,String password,String city,long TFN,Course course)
    {
        super(name,password,course);
        this.city = city;
        this.TFN=TFN;
    }
    public DomesticStudent(long id,String name,String password,String city,long TFN,Course course) throws StudentException
    {
        super(id,name,password,course);
        this.city = city;
        this.TFN=TFN;
    }
    public String getCountry()
    {
        return "Australia";
    }
    
    public String toDelimitedString()
    {
        return "D"+","+city+","+TFN+","+super.toDelimitedString();
    }
    
    public Double calcTuitionFeeSubject(String stdUsername,String stdPassword,Subject sj)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return sj.getFee()*(1-course.getDiscount());
    }
    public Double calcTotalTuitionFee(String stdUsername,String stdPassword)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return course.calcTotalTuitionFee(subjects)*(1-course.getDiscount());
    }
    public String getQuery()
    {
        return "INSERT INTO Student (studentID, studentName,courseID, password,studentType,city,TFN) VALUES ('"+ID+"', '"+name+"', '"+course.getID()+"', '"+password+"', '"+"D"+"', '"+city+"', '"+TFN+"');";
    }
    
    String getExtraInfo() 
    {
        return "TFN: "+TFN+" City:"+city;
    }
    
    public String toGUIString()
    {
        return super.toGUIString()+"TFN: "+TFN+"\nCity: "+city+"\n";
    }
    
    public String getCity()
    {
        return city;
    }
    public Long getTFN()
    {
        return TFN;
    }
    
    public void setCity(String city)
    {
        this.city=city;
    }
    public void setTFN(Long tfn)
    {
        this.TFN=tfn;
    }
}
