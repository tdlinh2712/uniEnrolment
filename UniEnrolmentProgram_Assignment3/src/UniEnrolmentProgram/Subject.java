/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.*;

/**
 *
 * @author linh
 */
enum SubjectType implements Serializable
{
    GENERAL(1000),SPECIALISED(2000),ADVANCED(3000);
    double fee;
    SubjectType(double fee)
    {
        this.fee = fee;
    }
    double getFee() 
    {
        return fee;
    }
    public void setFee(double fee)
    {
        this.fee=fee;
    }
}
public class Subject implements Serializable, Cloneable, Comparable<Subject>
{
    private String ID;
    private String name;
    private SubjectType type;
//    private boolean avail;
    public Subject(String id,String name,SubjectType type) throws SubjectException
    {
        if(!Pattern.matches("[a-zA-Z]{4}[0-9]{3}",id))
        {
            throw new SubjectException(id);
        }
        ID = id;
        this.name = name;
        this.type = type;
//        avail = true;
    }
    public String getID() 
    {
        return ID;
    }
    
    public void print()
    {
        System.out.format("%15s %30s",ID,name);
    }
    public double getFee()
    {
        return type.getFee();
    }
    public SubjectType getType()
    {
        return type;
    }
    @Override
    public int compareTo(Subject that) 
    {
        if(type.compareTo(that.type)!=0)//how to override subject type compare 
        {
            return type.compareTo(that.type);
        }
        return ID.compareTo(that.ID);
        
    }
    
    @Override
    public Subject clone() throws CloneNotSupportedException
    {
        return (Subject) super.clone();
        
    }
    
    public static ArrayList<Subject> deepCopy(ArrayList<Subject> subjects) throws CloneNotSupportedException
    {
        ArrayList<Subject> deepCopy = new ArrayList<>();
        for(Subject subject:subjects)
        {
            deepCopy.add(subject.clone());
        }
        return deepCopy; 
    }
    public static HashMap<String,Subject> shallowCopy(HashMap<String,Subject> subjects)
    {
        HashMap<String,Subject> shallowCopy = new HashMap<>();
        for(Subject subject:subjects.values())
        {
            shallowCopy.put(subject.getID(),subject);
        }
        return shallowCopy;
    }
    
    public static HashMap<String,Subject> deepCopy(HashMap<String,Subject> subjects) throws CloneNotSupportedException
    {
        HashMap<String,Subject> deepCopy = new HashMap<>();
        for(Subject subject:subjects.values())
        {
            Subject clone = subject.clone();
            deepCopy.put(clone.ID,clone);
        }
        return deepCopy;
    }
    static HashMap<String, Subject> filterByName(HashMap<String, Subject> subjects, String pattern) 
    {
        HashMap<String,Subject> foundSubjects=new HashMap<>();
        for (Subject sj:subjects.values())
        {
            if(sj.getID().contains(pattern))
            {
                foundSubjects.put(sj.getID(), sj);
            }
        }
        return foundSubjects;
    }

    String toDelimitedString() 
    {
        return ID+","+name+","+type;
    }
    public String toString()
    {
        return "Subject code:"+ID+"| Name: "+name +"| type: "+type+"| fee: "+getFee();   
    }
    String saveMarks(ArrayList<Student> enrolledStudents) 
    {
        String output="";
        output+=ID+" "+enrolledStudents.size()+"\n";
        for(Student student:enrolledStudents)
        {
            output+=student.getID()+" "+student.getResult(student.getUsername(), student.getPassword(), ID).toDelimitedString()+" ";
        }
        return output;
    }
    static void printList(HashMap<String, Subject> subjects) 
    {
        for(Subject subject:subjects.values())
        {
            System.out.println(subject);
        }
    }

    String getName() 
    {
        return this.name;
    }

}