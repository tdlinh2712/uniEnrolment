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
public class OnlineCourse extends Course 
{
    protected String url;
    public OnlineCourse(String name, String url,HashMap<String,Subject> subjects) 
    {
        super(name, subjects);
        this.url=url;
    }
    public OnlineCourse(String name, String url) 
    {
        super(name);
        this.url=url;
    }
    public OnlineCourse(Long id,String name, String url) throws CourseException 
    {
        super(id,name);
        this.url=url;
    }
    @Override
    public String toString()
    {
        String output= super.toString()+" Online"+"\n";
        output +="URL: "+url+"\n";
        return output;
    }
    
    public String toDelimitedString()
    {
        return "O"+","+url+","+super.toDelimitedString();
    }
    public Integer getType()
    {
        return 1;
    }
    String getURL()
    {
        return this.url;
    }
    
    public String getQuery()
    {
        return "INSERT INTO Course (courseID, cName, cType,URL) VALUES ('"+ID+"', '"+name+"', '"+"O"+"', '"+url+"');";
    }
    
    public String getType1()
    {
        return "Online";
    }
    
}
