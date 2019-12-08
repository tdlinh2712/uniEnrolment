/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author linh
 */
public class OnCampusCourse extends Course
{
    protected String campusName;
    protected Session startingSession;
    protected double discount;
    public OnCampusCourse(String name,String campus,Session session,double discount, HashMap<String,Subject> subjects) 
    {
        super(name, subjects);
        campusName=campus;
        startingSession = session;
        this.discount=discount;
    }
    public OnCampusCourse(String name, String campus,Session session,double discount) 
    {
        super(name);
        campusName=name;
        startingSession = session;
        this.discount=discount;
    }
    
    public OnCampusCourse(Long id,String name, String campus,Session session,double discount) throws CourseException 
    {
        super(id,name);
        campusName=campus;
        startingSession = session;
        this.discount=discount;
    }
    @Override
    public double getDiscount()
    {
        return discount;
    }
    @Override
    public String toString()
    {
        String output= super.toString()+" On Campus"+ "\n";
        output +="Campus Name: "+campusName+"| Starting Session: "+startingSession+"| discount rate:"+discount+"\n";
        return output;
    }
    
    public String toDelimitedString()
    {
        return "C"+","+campusName+","+startingSession+","+discount+","+super.toDelimitedString();
    }
    
    public Session getStartingSession()
    {
        return this.startingSession;
    }
    public String getCampus()
    {
        return this.campusName;
    }
    
    public String getQuery()
    {
        return "INSERT INTO Course (courseID, cName, cType,session,campus, discount) VALUES ('"+ID+"', '"+name+"', '"+"C"+"', '"+startingSession+"', '"+campusName+"', '"+discount+"');";
    }
    
}
