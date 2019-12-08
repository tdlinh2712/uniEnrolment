/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.io.Serializable;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author linh
 */
public abstract class Student  implements Serializable, Cloneable, Comparable<Student>
{
    protected String name;
    private static long x=0;
    protected String password;
    private String username;
    protected long ID;
    protected Course course;
    protected HashMap<String,Subject> subjects;
    protected HashMap<String,Result> results;
    public Student(String name,String password,Course course)
    {
        this.name=name;
        x++;
        int y=(int)(Math.random() * 99 + 0);
        this.ID = 40000000+x*10+y;
        this.password=password;
        this.username="std"+ID;
        this.course = course;
        results = new HashMap<>();
        subjects = new HashMap<>();
    }
    
    public Student(long id, String name,String password,Course course) throws StudentException
    {
        if(id<40000000||id>49999999)
        {
            x++;
            int y=(int)(Math.random() * 99 + 0);
            throw new StudentException(40000000+x*10+y);
        }
        this.name=name;
        this.ID = id;
        this.password=password;
        this.username="std"+ID;
        this.course = course;
        results = new HashMap<>();
        subjects = new HashMap<>();
    }
    
    public Double calcTotalTuitionFee(String stdUsername,String stdPassword)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return course.calcTotalTuitionFee(subjects);
    }
    public Double calcTuitionFeeSubject(String stdUsername,String stdPassword,Subject sj)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return sj.getFee();
    }
    
    public abstract String getCountry();
    public boolean enroll(String stdUsername,String stdPassword,Subject subject)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return false;
        }
        if (course.findSubject(subject.getID())==null|this.checkEnrolment(stdUsername,stdPassword,subject.getID())!=null)
        {
            return false;
        }
        subjects.put(subject.getID(), subject);
        results.put(subject.getID(),new Result(-1));
        return true;
    }
    public Subject checkEnrolment(String stdUsername,String stdPassword,String subjID)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return subjects.get(subjID);
    }
    
    public boolean withdraw(String stdUsername, String stdPassword,Subject subject)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return false;
        }
        if(this.checkEnrolment(stdUsername,stdPassword,subject.getID())!=null)
        {
            subjects.remove(subject.getID());
            results.remove(subject.getID());
            return true;
        }
       return false;
    }
    public boolean withdraw(String stdUsername, String stdPassword,String subjectID)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return false;
        }
        Subject sj =this.checkEnrolment(stdUsername,stdPassword,subjectID);
        if(sj!=null)
        {
            return this.withdraw(stdUsername, stdPassword, sj);
        }
       return false;
    }
    public boolean complete(String stdUsername,String stdPassword)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return false;
        }
        for(String id:course.getSubjects().keySet())
        {
            Subject mySubject = subjects.get(id);
            if(mySubject==null|results.get(id)==null|results.get(id).getMark()<50)
            {
                return false;
            }
        }
        return true;
    }
    public long getID()
    {
        return ID;
    }

    public boolean recordMark(String stdUsername,String stdPassword,Subject subject, double mark) 
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return false;
        }
        if(this.checkEnrolment(stdUsername,stdPassword,subject.getID())!=null&& mark>=0&& mark<=100)
        {
            results.put(subject.getID(), new Result(mark));
            return true;
        }
        return false;
    }
    @Override
    public String toString()
    {
        String output= "Student ID:"+ID+"| Name:"+name+" "+"| Course ID:"+course.getID()+" "+course.getName()+"| tuition fee:$"+calcTotalTuitionFee(username,password)+"\n";
        return output;
    }
    public void printReport()
    {
        System.out.println("\n"+this+"\n");
        System.out.println("Subjects Record:");
        System.out.format("%15s %30s %15s %15s\n","SjCode","Name","Mark","Grade");
        for (Subject subject:subjects.values())
        {
            subject.print();
            results.get(subject.getID()).print();
            System.out.println();
        }
    }
    public Result getResult(String stdUsername,String stdPassword,String sjID)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return results.get(sjID);
    }
    public String getName()
    {
        return name;
    }
    public boolean validateAccount(String username,String password)
    {
        if ((username.equals(this.username))&&password.equals(this.password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public HashMap<String,Subject> getSubjects(String stdUsername,String stdPassword)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return subjects;
    }
    public void print()
    {
        System.out.println(this.toString());
    }

    String getUsername() 
    {
        return username;
    }

    Course getCourse(String stdUsername,String stdPassword) 
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return course;
    }

    public int compareTo(Student that)
    {
        return name.compareToIgnoreCase(that.name);
    }
    public static Comparator<Student> tuitionFeeComparator = new Comparator<Student>() 
    {
        public int compare(Student student1, Student student2) 
        {
            return Double.compare(student1.calcTotalTuitionFee(student1.username,student1.password), student2.calcTotalTuitionFee(student2.username,student2.password));
        }
    };
    @Override
    public Student clone() throws CloneNotSupportedException
    {
        Student clone = (Student)super.clone();
        clone.course = course.clone();
        clone.subjects = Subject.deepCopy(subjects);
        clone.results = Result.deepCopy(results);
        return clone;
    }
    public static ArrayList<Student> shallowCopy(ArrayList<Student> students)
    {
        ArrayList<Student> shallowCopy = new ArrayList<>();
        for(Student student:students)
        {
            shallowCopy.add(student);
        }
        return shallowCopy;
    }
    public static ArrayList<Student> deepCopy(ArrayList<Student> students) throws CloneNotSupportedException
    {
        ArrayList<Student> deepCopy = new ArrayList<>();
        for(Student student:students)
        {
            deepCopy.add(student.clone());
        }
        return deepCopy; 
    }
    public static HashMap<Long,Student> shallowCopy(HashMap<Long,Student> students)
    {
        HashMap<Long,Student> shallowCopy = new HashMap<>();
        for(Student student:students.values())
        {
            shallowCopy.put(student.getID(),student);
        }
        return shallowCopy;
    }
    public static HashMap<Long,Student> deepCopy(HashMap<Long,Student> students) throws CloneNotSupportedException
    {
        HashMap<Long,Student> deepCopy = new HashMap<>();
        for(Student student:students.values())
        {
            Student clone = student.clone();
            deepCopy.put(student.ID, clone);
        }
        return deepCopy;
    }
    
    public static double calcTotalTuitionFee(HashMap<Long,Student> students)
    {
        double totalFee=0;
        for(Student student:students.values())
        {
            totalFee+=student.calcTotalTuitionFee(student.username,student.password);
        }
        return totalFee;
    }
    
    public static double calcTotalTuitionFee(ArrayList<Student> students)
    {
        double totalFee=0;
        for(Student student:students)
        {
            totalFee+=student.calcTotalTuitionFee(student.username,student.password);
        }
        return totalFee;
    }
    
    
    HashMap <String, Integer []> markRangePerLecturer (String stdUsername, String stdPassword, HashMap<String, Lecturer> subjectsTaughtByLecturers, int [] range)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        HashMap<String, Integer[]> countMarksPerRange = new HashMap<>();
        for(Subject subject:subjects.values())
        {
            
            Result mark = results.get(subject.getID());
            Lecturer lec = subjectsTaughtByLecturers.get(subject.getID());//in case subject has not been assigned to any lec
            if(lec!=null&&mark.getMark()>-1)
            {
                Integer[] count = countMarksPerRange.get(lec.getID());
                if(count==null)
                {
                    count=new Integer[range.length-1];
                    for(int i=0;i<count.length;i++)
                    {
                        count[i]=0;
                    }
                }
                int pos =-1;
                for(int i=1;i<range.length;i++)
                {
                    if(mark.getMark()<=range[i])
                    {
                        pos=i-1;
                        break;
                    }
                }
                if(pos>=0)
                {
                    count[pos]++;
                }
                countMarksPerRange.put(lec.getID(), count);
            }
        }
        return countMarksPerRange;
    }

    String getPassword() 
    {
        return this.password;
    }
    
    static HashMap<Long, Student> getStudentsCompleted(HashMap<Long, Student> students) 
    {
        HashMap<Long,Student> std = new HashMap<>();
        for(Student student:students.values())
        {
            if(student.complete(student.username,student.password))
            {
                System.out.println(student);
                std.put(student.getID(),student);
            }
        }
        return std;
    }
    public String toDelimitedString()
    {
        String output= this.ID+","+name+","+password+","+course.getID()+"\n";
        output+=subjects.size();
        for(String subjectID:subjects.keySet())
        {
            output+=","+subjectID;
        }
        return output;
    }
    static void printList(HashMap<Long, Student> students) 
    {
        for(Student student:students.values())
        {
            System.out.println(student);
        }
    }

    Long getCourseID(String username, String password) 
    {
        return course.getID();
    }

    String getQuery() 
    {
        return "";
    }
    
    public HashMap<String,Result> getResults(String stdUsername, String stdPassword)
    {
        if(!this.validateAccount(stdUsername, stdPassword))
        {
            return null;
        }
        return this.results;
    }

    String getType() 
    {
        return "Domestic";
    }

    String getExtraInfo() 
    {
        return "";
    }

    String toGUIString() 
    {
        String output= "Student ID:"+ID+"\nName:"+name+" "+"\nCourse ID:"+course.getID()+" "+course.getName()+"\ntuition fee:$"+calcTotalTuitionFee(username,password)+"\n";
        return output;
    }

    String getAgent() 
    {
        return "";
    }

    String getCity() 
    {
        return "";
    }

    Long getTFN()
    {
        return null;
    }

    void setCountry(String country) 
    {
        
    }

    void setAgent(String agent) 
    {
    }

    void setCity(String city) 
    {
    }

    void setTFN(Long TFN) 
    {
    }
    
    void setName(String name)
    {
        this.name=name;
    }
    
}
