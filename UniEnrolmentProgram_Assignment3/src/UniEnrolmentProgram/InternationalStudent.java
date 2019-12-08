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
public class InternationalStudent extends Student
{
    private String country;
    private String uniAgent;
    public InternationalStudent(String name,String password,String country,String uniAgent,Course course)
    {
        super(name,password,course);
        this.country = country;
        this.uniAgent = uniAgent; 
    }
    
    public InternationalStudent(long id,String name,String password,String country,String uniAgent,Course course) throws StudentException
    {
        super(id,name,password,course);
        this.country = country;
        this.uniAgent = uniAgent; 
    }
    @Override
    public boolean withdraw(String stdUsername, String stdPassword,Subject subject)
    {
       if (subjects.size()<4)
       {
           return false;
       }
       else return super.withdraw(stdUsername,stdPassword,subject);
    }
    public String getCountry()
    {
        return country;
    }
    
    public String toDelimitedString()
    {
        return "I"+","+country+","+uniAgent+","+super.toDelimitedString();
    }
    
    public String getQuery()
    {
        return "INSERT INTO Student (studentID, studentName,courseID, password,studentType,country,agent) VALUES ('"+ID+"', '"+name+"', '"+course.getID()+"', '"+password+"', '"+"I"+"', '"+country+"', '"+uniAgent+"');";
    }
    
    public String getType()
    {
        return "International";
    }
    
    String getExtraInfo() 
    {
        return "Country: "+country+" Agent:"+uniAgent;
    }
    
    public String toGUIString()
    {
        return super.toGUIString()+"Type: International Student\n"+"Country: "+country+"\nUni Agent: "+uniAgent+"\n";
    }
    
    public String getAgent()
    {
        return uniAgent;
    }
    
    public void setCountry(String country)
    {
        this.country=country;
    }
    
    public void setAgent(String agent)
    {
        this.uniAgent=agent;
    }
    
}
