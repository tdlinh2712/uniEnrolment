/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author linh
 */
enum Session implements Serializable
{SPRING,SUMMER,AUTUMN} 
public abstract class Course implements Serializable, Comparable<Course>, Cloneable
{
    protected long ID;
    protected int x=0;
    protected String name;
    protected HashMap<String,Subject>subjects;
    public Course(String name,HashMap<String,Subject>subjects)
    {
        x++;
        int y=(int)(Math.random() * 99 + 0);
        this.ID = 3000000+x*100+y;
        this.name=name;
        this.subjects=subjects;
    }
    public Course(String name)
    {
        x++;
        int y=(int)(Math.random() * 99 + 0);
        this.ID = 3000000+x*100+y;
        this.name=name;
        this.subjects=new HashMap<>();
    }
    
    public Course(Long id,String name) throws CourseException
    {
        if(id<3000000||id>3999999)
        {
            x++;
            int y=(int)(Math.random() * 99 + 0);
            throw new CourseException(3000000+x*100+y);
        }
        this.ID = id;
        this.name=name;
        this.subjects=new HashMap<>();
    }
//    public Course(String name)
//    {
//        this.name=name;
//        subjects = new ArrayList<>();
//    }
    public double calcFeeDomestic()
    {
       return calcTotalTuitionFee(subjects)*(1-this.getDiscount());
    }
    public double calcFeeInternational()
    {
       return calcTotalTuitionFee(subjects);
    }
    public double calcTotalTuitionFee(HashMap<String,Subject> subjects)
    {
        double fee=0;
        for(Subject sj:subjects.values())
        {
            fee+=sj.getFee();
        }
        return fee;
    }

    public Subject findSubject(String subjectID) 
    {
        return subjects.get(subjectID);
    }
    public boolean addSubject(Subject subject)
    {
        if (findSubject(subject.getID())==null)
        {
            subjects.put(subject.getID(), subject);
            return true;
        } else
        {
            return false;
        }
    }
    public boolean removeSubject(Subject subject)
    {
        if (findSubject(subject.getID())==null)
        {
            return false;
        }
        else 
        {
            subjects.remove(subject);
            return true;
        }
    }
    public long getID()
    {
        return ID;
    }
    public void print()
    {
        System.out.format("%15s %30s",ID,name);
    }
    public HashMap<String,Subject> getSubjects()
    {
        return subjects;
    }
    String getName() 
    {
        return name;
    }
    public double getDiscount()
    {
        return 0;
    }
    @Override
    public int compareTo(Course that)
    {
        if(this.getType()!=that.getType())
        {
            return Integer.compare(this.getType(), that.getType());
        }
        return name.compareToIgnoreCase(that.name);
    }
    public Course clone() throws CloneNotSupportedException
    {
        Course clone = (Course) super.clone();
        clone.subjects = Subject.deepCopy(subjects);
        return clone;
    }
    public static HashMap<Long,Course> shallowCopy(HashMap<Long,Course> courses)
    {
        HashMap<Long,Course> shallowCopy = new HashMap<>();
        for(Course course:courses.values())
        {
            shallowCopy.put(course.getID(),course);
        }
        return shallowCopy;
    }
    public static HashMap<Long,Course> deepCopy (HashMap<Long,Course> courses) throws CloneNotSupportedException
    {
        HashMap<Long,Course> deepCopy = new HashMap<>();
        for(Course course:courses.values())
        {
            Course clone = course.clone();
            deepCopy.put(clone.ID, clone);
        }
        return deepCopy;
    }
    public Integer getType()
    {
        return 0;
    }
    
    public void printSubjectsPerCourse()
    {
        System.out.println(this);
        for(Subject subject:subjects.values())
        {
            subject.print();
            System.out.print("\n");
        }
    }
    public String toDelimitedString()
    {
        
        String output= this.ID+","+name+"\n";
        output+=subjects.size();
        for(String subjectID:subjects.keySet())
        {
            output+=","+subjectID;
        }
        return output;
    }
    
    public  String toString()
    {
        return "Course ID:"+ID+"| Name: "+name+"| Type:";
    }
    static void printList(HashMap<Long, Course> courses) 
    {
        for(Course course:courses.values())
        {
            System.out.println(course);
        }
    }

    String getCampus() 
    {
        return null;
    }
    String getURL()
    {
        return "";
    }
    
    public String getQuery()
    {
        return "";
    }
    public String getType1()
    {
        return "On Campus";
    }
    
    public Session getStartingSession()
    {
        return null;
    }
}
