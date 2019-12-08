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
import java.util.regex.Pattern;

/**
 *
 * @author linh
 */
//enum Degree{BACHELOR,MASTER,PhD}
public class Lecturer implements Serializable, Comparable<Lecturer>, Cloneable
{
    private static long count=9000;
    private String name;
    private String password;
    private String ID;
    private String schoolName;
    //private Degree degree;
    private String degree;
    private HashMap<String,Subject>subjects;
    public Lecturer(String lecID,String name,String school,String deg) throws LecturerException
    {
        if(Pattern.matches("LEC[0-9]{4}U", lecID))
        {
            throw new LecturerException(lecID);
        }
        this.ID = lecID;
        this.name=name;
        this.password="password";
        schoolName=school;
        degree=deg;
        subjects=new HashMap<>();
    }
    public boolean recordMark(String username,String password,Subject sj,Student std,double mark)
    {
        if(!this.validateAccount(username, password))
        {
            return false;
        }
        if(hasSubject(username,password,sj.getID())!=null&&std.recordMark(std.getUsername(),std.getPassword(),sj, mark))
        {
            return true;
        }
        return false;
    }
    public String getID()
    {
        return ID;
    }
    public boolean assignSubject(String username,String password,Subject subject)
    {
        if(!this.validateAccount(username, password))
        {
            return false;
        }
        subjects.put(subject.getID(),subject);
        return true;
    }
    public Subject hasSubject(String username,String password,String subjectCode)
    {
        if(!this.validateAccount(username, password))
        {
            return null;
        }
        for(Subject subject:subjects.values())
        {
            if(subject.getID().equals(subjectCode))
            {
                return subject;
            }
        }
        return null; 
    }
    public String getUsername()
    {
        return ID;
    }
    public boolean validateAccount(String username, String password)
    {
        if ((username.equals(this.ID))&&(password.equals(this.password)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public HashMap<String,Subject> getSubjects(String username,String password)
    {
        if(!this.validateAccount(username, password))
        {
            return null;
        }
        return subjects;
    }
    public String getName()
    {
        return name;
    }
    
    public static Comparator<Lecturer> numberOfSubjectsComparator = new Comparator<Lecturer>() 
    {
        public int compare(Lecturer lec1, Lecturer lec2) 
        {
            return Integer.compare(lec1.subjects.size(), lec2.subjects.size());
        }
    };
    
    @Override
    public int compareTo(Lecturer that)
    {
        return Integer.compare(subjects.size(), that.subjects.size());
    }
    public int compareID(Lecturer that) 
    {
        return ID.compareToIgnoreCase(that.ID);
    }
    
    @Override
    public Lecturer clone() throws CloneNotSupportedException
    {
        Lecturer clone = (Lecturer)super.clone();
        clone.subjects = Subject.deepCopy(subjects);
        return clone;
    }

    String getPassword() 
    {
        return this.password;
    }
    
    static HashMap<String, Lecturer> deepCopy(HashMap<String, Lecturer> lecturers) throws CloneNotSupportedException 
    {
        HashMap<String,Lecturer> deepCopy = new HashMap<>();
        for(Lecturer lecturer:lecturers.values())
        {
            Lecturer clone = lecturer.clone();
            deepCopy.put(clone.ID,clone);
        }
        return deepCopy;
    }
    static HashMap<String, Lecturer> shallowCopy(HashMap<String, Lecturer> lecturers) 
    {
        HashMap<String,Lecturer> shallowCopy = new HashMap<>();
        for(Lecturer lecturer:lecturers.values())
        {
            shallowCopy.put(lecturer.ID,lecturer);
        }
        return shallowCopy;
    }

    String toDelimitedString() 
    {
        String output= ID+","+name+","+schoolName+","+degree+"\n";
        output+=subjects.size();
        for(Subject subject:subjects.values())
        {
            output+=","+subject.getID();
        }
        return output;
    }
    public String toString()
    {
        return "ID: "+ID+" name:"+name+" Degree:"+degree+" School:"+schoolName;
    }
    public String toGUIString()
    {
        return "ID: "+ID+"\nname:"+name+" \nDegree:"+degree+" \nSchool:"+schoolName+"\n";
    }
    public void print()
    {
        System.out.println(this);
        System.out.println("Teaching subjects:");
        System.out.format("%15s %30s\n","Subject Code","Name");
        for(Subject subject:subjects.values())
        {
            subject.print();
            System.out.println();
        }
    }
    
    static void printList(HashMap<String, Lecturer> lecturers) 
    {
        for(Lecturer lec:lecturers.values())
        {
            System.out.println(lec);
        }
    }

    String getDegree() 
    {
        return this.degree;
    }

    String getSchool() {
        return this.schoolName;
    }

    String getQuery() {
        return "INSERT INTO Lecturer (lecID, lecName,degree,school) VALUES ('"+ID+"', '"+name+"', '"+degree+"', '"+schoolName+"');";
    }
}
