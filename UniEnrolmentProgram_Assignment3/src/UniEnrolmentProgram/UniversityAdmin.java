/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Double;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author linh
 */
public class UniversityAdmin implements Serializable
{
    private HashMap<String,Subject> subjects;
    private HashMap<Long,Course> courses;
    private HashMap<Long,Student> students;
    private HashMap<String,Lecturer> lecturers;
    private String adminUsername;
    private String adminPassword;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    public UniversityAdmin(String username,String password)
    {
        subjects = new HashMap<>();
        courses = new HashMap<>();
        students = new HashMap<>();
        lecturers = new HashMap<>();
        adminUsername=username;
        adminPassword=password;
    }
    public HashMap<String,Subject> getAllSubjects()
    {
        return subjects;
    }
    public HashMap<Long,Course> getAllCourses()
    {
        return courses;
    }
    public HashMap<String,Lecturer> getAllLecturers()
    {
        return lecturers;
    }
    public HashMap<Long,Student> getAllStudents()
    {
        return students;
    }
    public ArrayList<Student> deepCopyStudents(String username,String password,HashMap<Long,Student> students) throws CloneNotSupportedException
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<Long,Student> deepCopyStudents = Student.deepCopy(students);
        ArrayList<Student> deepCopy = new ArrayList<>();
        for(Student student:deepCopyStudents.values())
        {
            deepCopy.add(student);
        }
        return deepCopy;
    }
    public ArrayList<Student> shallowCopyStudents(String username,String password,HashMap<Long,Student> students) 
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<Long,Student> shallowCopyStudents = Student.shallowCopy(students);
        ArrayList<Student> shallowCopy = new ArrayList<>();
        for(Student student:shallowCopyStudents.values())
        {
            shallowCopy.add(student);
        }
        return shallowCopy;
    }
    public ArrayList<Course> deepCopyCourses(String username,String password,HashMap<Long,Course> courses) throws CloneNotSupportedException
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<Long,Course> deepCopyCourses = Course.deepCopy(courses);
        ArrayList<Course> deepCopy = new ArrayList<>();
        for(Course course:deepCopyCourses.values())
        {
            deepCopy.add(course);
        }
        return deepCopy;
    }
    public ArrayList<Course> shallowCopyCourses(String username,String password,HashMap<Long,Course> courses) 
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<Long,Course> shallowCopyCourses = Course.shallowCopy(courses);
        ArrayList<Course> shallowCopy = new ArrayList<>();
        for(Course course:shallowCopyCourses.values())
        {
            shallowCopy.add(course);
        }
        return shallowCopy;
    }
    public ArrayList<Subject> deepCopySubjects(String username,String password,HashMap<String,Subject> subjects) throws CloneNotSupportedException
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Subject> deepCopySubjects = Subject.deepCopy(subjects);
        ArrayList<Subject> deepCopy = new ArrayList<>();
        for(Subject subject:deepCopySubjects.values())
        {
            deepCopy.add(subject);
        }
        return deepCopy;
    }
    public ArrayList<Subject> shallowCopySubjects(String username,String password,HashMap<String,Subject> subjects) 
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Subject> shallowCopySubjects = Subject.shallowCopy(subjects);
        ArrayList<Subject> shallowCopy = new ArrayList<>();
        for(Subject subject:shallowCopySubjects.values())
        {
            shallowCopy.add(subject);
        }
        return shallowCopy;
    }
    public ArrayList<Lecturer> deepCopyLecturers(String username,String password,HashMap<String,Lecturer> lecturers) throws CloneNotSupportedException
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Lecturer> deepCopyLecturers = Lecturer.deepCopy(lecturers);
        ArrayList<Lecturer> deepCopy = new ArrayList<>();
        for(Lecturer lecturer:deepCopyLecturers.values())
        {
            deepCopy.add(lecturer);
        }
        return deepCopy;
    }
    public ArrayList<Lecturer> shallowCopyLecturers(String username,String password,HashMap<String,Lecturer> lecturers)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Lecturer> shallowCopyLecturers = Lecturer.shallowCopy(lecturers);
        ArrayList<Lecturer> shallowCopy = new ArrayList<>();
        for(Lecturer lecturer:shallowCopyLecturers.values())
        {
            shallowCopy.add(lecturer);
        }
        return shallowCopy;
    }
    
//    public ArrayList<Result> shallowCopyResults(String username,String password,HashMap<Long,Result> results)
//    {
//        if(!this.validateAdmin(username, password))
//        {
//            return null;
//        }
//        HashMap<String,Lecturer> shallowCopyResults = Lecturer.shallowCopy(lecturers);
//        ArrayList<Lecturer> shallowCopy = new ArrayList<>();
//        for(Lecturer lecturer:shallowCopyLecturers.values())
//        {
//            shallowCopy.add(lecturer);
//        }
//        return shallowCopy;
//    }
    
    public boolean addSubject(String username,String password,Subject subject)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        if (findSubject(adminUsername,adminPassword,subject.getID())==null)
        {
            subjects.put(subject.getID(),subject);
            return true;
        }
        else return false;
    }
    public Subject findSubject(String username,String password,String subjectID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return subjects.get(subjectID);
    }
    public boolean addCourse(String username,String password,Course course)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        if (findCourse(username,password,course.getID())!=null)
        {
            return false;
        }
        else 
        {
            courses.put(course.getID(),course);
            return true;
        }
    }
    public Course findCourse(String username,String password,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return courses.get(courseID);
    }
    public boolean addSubjectToCourse(String username,String password,String subjectID,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Subject subject=findSubject(username,password,subjectID);
        Course course=findCourse(username,password,courseID);
        if (course==null||subject==null)
        {
            return false;
        }
        {
            return course.addSubject(subject);
        }
    }
    public boolean removeSubjectFromCourse(String username,String password,String subjectID,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Subject subject=findSubject(username,password,subjectID);
        Course course=findCourse(username,password,courseID);
        ArrayList<Student> enrolledStudents = this.getEnrolledStudentsPerSubject(username, password).get(subject.getID());
        if (course!=null && subject!=null && course.findSubject(subjectID)!=null && enrolledStudents.isEmpty() && course.removeSubject(subject))
        {
            return true;
        } else
        {
            return false;
        }
    }
    public boolean addStudent(String username,String password,Student student)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        if (findStudent(username,password,student.getID())!=null)
        {
            return false;
        } else
        {
            students.put(student.getID(),student);
            return true;
        }
    }
    public Student findStudent(String username,String password,long studentID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return students.get(studentID);
    }
    public boolean enroll(String username,String password,String sjCode,long studentID)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Subject subject=findSubject(username,password,sjCode);
        Student student = findStudent(username,password,studentID);
        if ((subject!=null)&&(student!=null))   
            {  
              return student.enroll(student.getUsername(),student.getPassword(),subject);
            }
        return false; 
    }
    public boolean withdraw(String username,String password,String sjCode,long studentID)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Subject subject=findSubject(username,password,sjCode);
        Student student = findStudent(username,password,studentID);
        if ((subject!=null)&&(student!=null))   
            {  
              return student.withdraw(student.getUsername(),student.getPassword(),subject);
            }
        return false; 
    }
    public boolean checkCompletion(String username,String password,Student student)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        return student.complete(student.getUsername(),student.getPassword());
    }
    public boolean addLecturer(String username,String password,Lecturer lec)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        if (findLecturer(username,password,lec.getID())==null)
        {
            lecturers.put(lec.getID(), lec);
            return true;
        } else
        {
            return false;
        }
    }
    public Lecturer findLecturer(String username,String password,String ID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return lecturers.get(ID);
    }
    public boolean assignSubject(String username,String password,String sjCode,String lecturerID)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Lecturer lecturer=findLecturer(username,password,lecturerID);
        Subject subject=findSubject(username,password,sjCode);
        if(subject!=null&&lecturer!=null&&this.getLecturers(username, password).get(subject.getID())==null)
        {
            lecturer.assignSubject(lecturer.getUsername(),lecturer.getPassword(),subject);
            return true;
        } else
            return false;
    }
    public boolean recordMark(String username,String password,String lecID,long studentID,String subjectCode,double mark)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        Lecturer lecturer = findLecturer(username,password,lecID);
        Student student = findStudent(username,password,studentID);
        Subject subject=findSubject(username,password,subjectCode);
        if((subject!=null)&&(lecturer!=null)&&(student!=null)&&(lecturer.recordMark(lecturer.getUsername(),lecturer.getPassword(),subject,student,mark)))
        {
            return true;
        } else
        {
            return false;
        }
    }
    public boolean recordMark(String username,String password,long studentID,String subjectCode,double mark)
    {
        if(!this.validateAdmin(username, password))
        {
            return false;
        }
        //Lecturer lecturer = findLecturer(username,password,lecID);
        Student student = findStudent(username,password,studentID);
        Subject subject=findSubject(username,password,subjectCode);
        if((subject!=null)&&(student!=null)&&(student.recordMark(student.getUsername(),student.getPassword(),subject,mark)))
        {
            return true;
        } else
        {
            return false;
        }
    }
    public Double calcTuitionFee(String username,String password,long studentID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Student student=findStudent(username,password,studentID);
        if(student!=null)
        {
            return student.calcTotalTuitionFee(student.getUsername(),student.getPassword());
        }
        else
        {
            return (double) -1;
        }
    }
    public Double calcTotalTuitionFee(String username,String password,HashMap<Long,Student> stds)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return Student.calcTotalTuitionFee(stds);
    }
    public HashMap<Long,Student> getStudentsCompleted(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return Student.getStudentsCompleted(students);
    }
    public Double calcFeeDomestic(String username,String password,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Course course= findCourse(username,password,courseID);
        if(course==null)
        {
            return (double) -1;
        } else
        {
            return course.calcFeeDomestic();
        }
    }
    public Double calcFeeInternational(String username,String password,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Course course= findCourse(username,password,courseID);
        if(course==null)
        {
            return (double) -1;
        } else
        {
            return course.calcFeeInternational();
        }
    }
    public void printStudentReport(String username,String password,long stdID)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        Student std=findStudent(username,password,stdID);
        if(std==null)
        {
            System.out.println("Cant find student");
        } else
        {
            std.printReport();
        }
    }
    public void printStudentReportAll(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        for(Student std:students.values())
        {
            std.printReport();
        }
    }
    public void printSubjectReport(String username,String password,Subject subject,ArrayList<Student> enrolledStudents)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        //ArrayList<Result> results = subject.getResultsForSubject(students);
        //ArrayList<Student> enrolledStudents = this.getEnrolledStudentsPerSubject(username, password).get(subject.getID());
        ArrayList<Result> results = new ArrayList<>();
        for(Student student:enrolledStudents)
        {
            results.add(student.getResult(student.getUsername(), student.getPassword(), subject.getID()));
        }
        System.out.println("Highest Mark:"+Result.getHighestMark(results).getMark());
        System.out.println("Lowest Mark:"+Result.getLowestMark(results).getMark());
        System.out.println("Average Mark:"+Result.getAverageMark(results).getMark());
        System.out.format("%15s %30s %15s %15s\n","student ID","Name","Mark","Grade");
        for(Student std:enrolledStudents)
        {
            System.out.format("%15s %30s",std.getID(),std.getName());
            std.getResult(std.getUsername(),std.getPassword(),subject.getID()).print();
            System.out.println();
        }
    }
    public void printSubjectReport(String username,String password,Subject subject)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        //ArrayList<Result> results = subject.getResultsForSubject(students);
        ArrayList<Student> enrolledStudents = this.getEnrolledStudentsPerSubject(username, password).get(subject.getID());
        ArrayList<Result> results = new ArrayList<>();
        for(Student student:enrolledStudents)
        {
            results.add(student.getResult(student.getUsername(), student.getPassword(), subject.getID()));
        }
        System.out.println("Highest Mark:"+Result.getHighestMark(results).getMark());
        System.out.println("Lowest Mark:"+Result.getLowestMark(results).getMark());
        System.out.println("Average Mark:"+Result.getAverageMark(results).getMark());
        System.out.format("%15s %30s %15s %15s\n","student ID","Name","Mark","Grade");
        for(Student std:enrolledStudents)
        {
            System.out.format("%15s %30s",std.getID(),std.getName());
            std.getResult(std.getUsername(),std.getPassword(),subject.getID()).print();
            System.out.println();
        }
    }
    public void printSubjectReport(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        HashMap<String,ArrayList<Student>> enrolledStudents = this.getEnrolledStudentsPerSubject(username, password);
        for(Subject sj:subjects.values())
        {
            System.out.println(sj);
            printSubjectReport(username,password,sj,enrolledStudents.get(sj.getID()));
            System.out.print("\n");
        }
    }
    public HashMap<String, Subject> getSubjectsPerCourse(String username,String password,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Course course=findCourse(username,password,courseID);
        if(course!=null)
        {
            return course.getSubjects();
        } else
        {
            return null;
        }
    }
    public void printSubjectsPerCourse(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        for(Course course:courses.values())
        {
            course.printSubjectsPerCourse();
            System.out.println();
        }
    }
    public HashMap<String,Subject> filterByName(String username,String password,String pattern)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return Subject.filterByName(subjects,pattern);
    }
    public ArrayList<Course> findCourseContainSubject(String username,String password,String sjCode)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        return this.getCoursesOfferingSubject(username, password).get(sjCode);
    }
    
    public Lecturer findLecturerByUsername(String username,String password,String lecUsername) 
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        for (Lecturer lec:lecturers.values())
        {
            if(lec.getUsername().equals(lecUsername))
            {
                return lec;
            }
        }
        return null;
    }
    
    public Student findStudentByUsername(String username,String password,String stdUsername) 
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        for (Student student:students.values())
        {
            if(student.getUsername().equals(stdUsername))
            {
                return student;
            }
        }
        return null;
    }
    public boolean validateAdmin(String username, String password)
    {
        if ((username.equals(adminUsername))&&(password.equals(adminPassword)))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void reportForCourses(String username,String password,HashMap<Long,Integer> countStudents,HashMap<Long,Double> totalTuitionFee) 
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        countStudents.clear();
        totalTuitionFee.clear();
        for(Student student:students.values())
        {
            Integer countStudent = countStudents.get(student.getCourseID(student.getUsername(), student.getPassword()));
            Double tuitionFee = totalTuitionFee.get(student.getCourseID(student.getUsername(), student.getPassword()));
            if(countStudent==null)
            {
                countStudent =1;
                tuitionFee = student.calcTotalTuitionFee(student.getUsername(), student.getPassword());
                countStudents.put(student.getCourseID(student.getUsername(), student.getPassword()), countStudent);
                totalTuitionFee.put(student.getCourseID(student.getUsername(), student.getPassword()),tuitionFee);
            } else
            {
                countStudents.put(student.getCourseID(student.getUsername(), student.getPassword()), countStudent+1);
                totalTuitionFee.put(student.getCourseID(student.getUsername(), student.getPassword()),tuitionFee+student.calcTotalTuitionFee(student.getUsername(), student.getPassword()));
            }
        }
    }

    public void printReportForCourse(String username,String password,HashMap<Long,Integer> countStudents,HashMap<Long,Double> totalTuitionFee) 
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        System.out.format("%15s %30s %15s %15s\n","Course ID","Course Name","Students","total fee($)");
        for(Long courseID:countStudents.keySet())
        {
            courses.get(courseID).print();
            System.out.format("%15s %15s\n",countStudents.get(courseID),totalTuitionFee.get(courseID));
        }
    }

    public void reportForCountry(String username,String password,HashMap<String,Integer> countStudents,HashMap<String,Double> totalTuitionFee) 
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        countStudents.clear();
        totalTuitionFee.clear();
        
        for(Student student:students.values())
        {
            Integer countStudent = countStudents.get(student.getCountry());
            Double tuitionFee = totalTuitionFee.get(student.getCountry());
            if(countStudent==null)
            {
                countStudent =1;
                tuitionFee = student.calcTotalTuitionFee(student.getUsername(), student.getPassword());
                countStudents.put(student.getCountry(), countStudent);
                totalTuitionFee.put(student.getCountry(),tuitionFee);
            } else
            {
                countStudents.put(student.getCountry(), countStudent+1);
                totalTuitionFee.put(student.getCountry(),tuitionFee+student.calcTotalTuitionFee(student.getUsername(), student.getPassword()));
            }
        }
    }
    public void reportForCountryLambda(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        Map<String,Long> countStudents=
            students.values().stream().collect(Collectors.groupingBy(e->e.getCountry(),Collectors.counting()));
        Map<String,Double> totalTuitionFee=
            students.values().stream().collect(Collectors.groupingBy(e->e.getCountry(),Collectors.summingDouble(e->e.calcTotalTuitionFee(e.getUsername(), e.getPassword())))); 
        
        printReportForCountryLambda(username,password,countStudents,totalTuitionFee);
    }
    
    public void reportForCountryLambda(String username,String password,Map<String,Long> countStudents,Map<String,Double> totalTuitionFee)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        countStudents.putAll(
            students.values().stream().collect(Collectors.groupingBy(e->e.getCountry(),Collectors.counting())));
        totalTuitionFee.putAll(
            students.values().stream().collect(Collectors.groupingBy(e->e.getCountry(),Collectors.summingDouble(e->e.calcTotalTuitionFee(e.getUsername(), e.getPassword()))))); 
        
        //printReportForCountryLambda(username,password,countStudents,totalTuitionFee);
    }
    
    public void printReportForCountryLambda(String username,String password,Map<String,Long> countStudents,Map<String,Double> totalTuitionFee)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        System.out.format("%15s %15s %15s\n","Country","Students","total fee($)");
        countStudents.forEach((c,s)-> 
        {
            System.out.format("%15s %15s %15s\n", c,s,totalTuitionFee.get(c));
        });
    }
    
    public void reportForCourseLambda(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        Map<Long,Long> countStudents=
            students.values().stream().collect(Collectors.groupingBy(e->e.getCourseID(e.getUsername(), e.getPassword()),Collectors.counting()));
        Map<Long,Double> totalTuitionFee=
            students.values().stream().collect(Collectors.groupingBy(e->e.getCourseID(e.getUsername(), e.getPassword()),Collectors.summingDouble(e->e.calcTotalTuitionFee(e.getUsername(), e.getPassword())))); 
        
        printReportForCourseLambda(username,password,countStudents,totalTuitionFee);
    }
    
    public void reportForCourseLambda(String username,String password,Map<Long,Long> countStudents,Map<Long,Double> totalTuitionFee)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        countStudents.putAll(
            students.values().stream().collect(Collectors.groupingBy(e->e.getCourseID(e.getUsername(), e.getPassword()),Collectors.counting())));
        totalTuitionFee.putAll(
            students.values().stream().collect(Collectors.groupingBy(e->e.getCourseID(e.getUsername(), e.getPassword()),Collectors.summingDouble(e->e.calcTotalTuitionFee(e.getUsername(), e.getPassword()))))); 
        
        //printReportForCourseLambda(username,password,countStudents,totalTuitionFee);
    }
 
    public void printReportForCourseLambda(String username,String password,Map<Long,Long> countStudents,Map<Long,Double> totalTuitionFee)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        System.out.format("%15s %15s %15s\n","Course ID","Students","total fee($)");
        countStudents.forEach((c,s)-> 
        {
            System.out.format("%15s %15s %15s\n", c,s,totalTuitionFee.get(c));
        });
    }
    
    public void printReportForCountry(String username,String password,HashMap<String,Integer> countStudents,HashMap<String,Double> totalTuitionFee) 
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        System.out.format("%15s %15s %15s\n","Country","Students","total fee($)");
        for(String country:countStudents.keySet())
        {
            System.out.format("%15s %15s %15s\n",country,countStudents.get(country),totalTuitionFee.get(country));
        }
    }
    
    public void reportForSubjectType(String username,String password,HashMap<SubjectType,Integer> countSubjects, HashMap<SubjectType,Integer> countStudents, HashMap<SubjectType,Result> averageMarks, HashMap<SubjectType,Double> totalTuitionFee)
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        countSubjects.putAll(this.countSubjectsPerType(username,password));
        countStudents.putAll(this.countStudentsPerType(username,password));
        averageMarks.putAll(getAverageMarkPerType(username,password));
        totalTuitionFee.putAll(calcTotalTuitonFeePerType(username,password));
    }
    
    public HashMap<SubjectType,Integer> countStudentsPerType(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<SubjectType,Integer> countStudents = new HashMap<>();
        for(Student student:students.values())
        {
            for(Subject studentSubject:student.getSubjects(student.getUsername(), student.getPassword()).values())
            {
                Integer countStudent = countStudents.get(studentSubject.getType());
                if(countStudent==null)
                {
                    countStudents.put(studentSubject.getType(),1);
                } else
                {
                    countStudents.put(studentSubject.getType(),countStudent+1);
                }
            }
        }
        return countStudents;
    }
    
    public HashMap<SubjectType,Integer> countSubjectsPerType(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<SubjectType,Integer> countSubjects = new HashMap<>();
        for(Subject subject:subjects.values())
        {
            Integer countSubject = countSubjects.get(subject.getType());
            if(countSubject!=null)
            {
                countSubjects.put(subject.getType(),countSubject+1);
            }
            else
            {
                countSubjects.put(subject.getType(),1);
            }
        }
        return countSubjects;
    }
    
    public HashMap<SubjectType,Result> getAverageMarkPerType(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<SubjectType,Result> averageMarkPerType = new HashMap<>();
        HashMap<SubjectType,ArrayList<Result>> averageMarks = new HashMap<>();
        for(Subject subject:subjects.values())
        {
            ArrayList<Result> averages = averageMarks.get(subject.getType());
            if(averages==null)
            {
                averages = new ArrayList<>();
            }
            ArrayList<Result> marks = new ArrayList<>();
            ArrayList<Student> enrolledStudents = this.getEnrolledStudentsPerSubject(username, password).get(subject.getID());
            for(Student student:enrolledStudents)
            {
                marks.add(student.getResult(student.getUsername(), student.getPassword(), subject.getID()));
            }
            averages.add(Result.getAverageMark(marks));
            averageMarks.put(subject.getType(), averages);
            averageMarkPerType.put(subject.getType(), Result.getAverageMark(averageMarks.get(subject.getType())));
        }
        return averageMarkPerType;
    }
    
    public HashMap<SubjectType,Double> calcTotalTuitonFeePerType(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<SubjectType,Double> totalTuitionFeePerType = new HashMap<>();
        for(Student student:students.values())
        {
            for(Subject studentSubject:student.getSubjects(student.getUsername(), student.getPassword()).values())
            {
                Double tuitionFee = totalTuitionFeePerType.get(studentSubject.getType());
                if(tuitionFee==null)
                {
                    totalTuitionFeePerType.put(studentSubject.getType(),student.calcTuitionFeeSubject(student.getUsername(), student.getPassword(), studentSubject));
                } else
                {
                    totalTuitionFeePerType.put(studentSubject.getType(),tuitionFee+student.calcTuitionFeeSubject(student.getUsername(), student.getPassword(), studentSubject));
                }
            }
        }
        return totalTuitionFeePerType;
    }
    
    
    

    public void printReportForSubjectType(String username,String password,HashMap<SubjectType,Integer> countSubjects, HashMap<SubjectType,Integer> countStudents, HashMap<SubjectType,Result> averageMarks, HashMap<SubjectType,Double> totalTuitionFee) 
    {
        if(this.validateAdmin(username, password))
        {
            System.out.format("%15s %15s %15s %15s %15s\n","Type","Subjects","Students","average Mark","total fee");
            for(SubjectType type:SubjectType.values())
            {
                System.out.format("%15s %15s %15s           %.2f %15s\n",type,countSubjects.get(type),countStudents.get(type),averageMarks.get(type).getMark(),totalTuitionFee.get(type));
            }
        }
    }


  
    public HashMap<String, ArrayList<Student>> getEnrolledStudentsPerSubject(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,ArrayList<Student>> enrolledStudentsPerSubject = new HashMap<>();
        for(Student student:students.values())
        {
            for(Subject subject:student.getSubjects(student.getUsername(), student.getPassword()).values())
            {
                ArrayList<Student> enrolledStudents = enrolledStudentsPerSubject.get(subject.getID());
                if(enrolledStudents==null)
                {
                    enrolledStudents=new ArrayList<>();
                }
                enrolledStudents.add(student);
                enrolledStudentsPerSubject.put(subject.getID(), enrolledStudents);
            }
        }
        return enrolledStudentsPerSubject;
    }
    
    
    public HashMap<String,Lecturer> getLecturers(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Lecturer> lecs = new HashMap<>();
        for(Lecturer lec:lecturers.values())
        {
            for(Subject subject:lec.getSubjects(lec.getUsername(), lec.getPassword()).values())
            {
                Lecturer teachingLec = lecs.get(subject.getID());
                if(teachingLec==null)
                {
                    lecs.put(subject.getID(), lec);
                }
            }
        }
        return lecs;
    }
    
    public HashMap<String,Subject> getAvailableSubjects(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,Subject> availableSubjects = new HashMap<>();
        availableSubjects.putAll(subjects);
        for(Lecturer lec:lecturers.values())
        {
            for(Subject subject:lec.getSubjects(lec.getUsername(), lec.getPassword()).values())
            {
                availableSubjects.remove(subject.getID());
            }
        }
        return availableSubjects;
    }
    HashMap<Long, ArrayList<Subject>> getAllCourseSubjects(String username,String password)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<Long,ArrayList<Subject>> courseSubjects = new HashMap<>();
        
        for(Course course:courses.values())
        {
            courseSubjects.put(course.getID(), new ArrayList<Subject>(course.getSubjects().values()));
        }
        
        return courseSubjects;
    }
    public HashMap<String,Subject> getCourseSubjects(String username,String password,long courseID)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Course course = this.findCourse(username,password,courseID);
        if(course==null)
        {
            return null;
        }
        return course.getSubjects();
    }
    public HashMap<String, ArrayList<Course>> getCoursesOfferingSubject(String username,String password) // lambda
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        HashMap<String,ArrayList<Course>> coursesOfferingSubject = new HashMap<>();
        for(Course course:courses.values())
        {
            for(Subject subject:course.getSubjects().values())
            {
                ArrayList<Course> coursesSubject = coursesOfferingSubject.get(subject.getID());
                if(coursesSubject==null)
                {
                    coursesSubject = new ArrayList<>();
                }
                coursesSubject.add(course);
                coursesOfferingSubject.put(subject.getID(),coursesSubject);
            }
        }
        return coursesOfferingSubject;
    }
    
    public Map<String, List<Course>> getCoursesOfferingSubjectLambda(String username,String password) // lambda
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Map<String,List<Course>> results= new HashMap<>();
        courses.values().stream().forEach( c-> {
                c.getSubjects().keySet().forEach(s-> {
                    List<Course> c2 = results.get(s);
                    if(s!=null)
                    {
                        c2= new ArrayList<>();
                    }
                    c2.add(c);
                    results.put(s, c2);
                    });
                    });
        return results;
        
    }
    
//*************************
//Report 33
    
    public HashMap <String, Integer []> markRangePerLecturerForStudent (String username, String password, long studentID, int [] range)
    {
        if(!this.validateAdmin(username, password))
        {
            return null;
        }
        Student student = this.findStudent(username,password,studentID);
        if(student==null)
        {
            return null;
        }
        return student.markRangePerLecturer(student.getUsername(), student.getPassword(), this.getLecturers(username,password), range);
    }
    HashMap <String, Integer []> markRangePerLecturer (String adminUsername, String adminPassword, int [] range)
    {
        if(!this.validateAdmin(adminUsername, adminPassword))
        {
            return null;
        }
        HashMap<String,Integer[]> markRangePerLec = new HashMap<>();
        for(Student student:students.values())
        {
            HashMap<String,Integer[]> markRangeForStudent = this.markRangePerLecturerForStudent(adminUsername, adminPassword,student.getID(), range);
            for(String lecID:markRangeForStudent.keySet())
            {
                Integer[] countMarks = markRangePerLec.get(lecID);
                if(countMarks==null)
                {
                    markRangePerLec.put(lecID, markRangeForStudent.get(lecID));
                } else
                {
                    markRangePerLec.put(lecID, updateCountMark(countMarks,markRangeForStudent.get(lecID)));
                }
            }
        }
        return markRangePerLec;
            
    }

    private Integer[] updateCountMark(Integer[] original, Integer[] update) 
    {
        Integer[] output = new Integer[original.length];
        for(int i=0;i<original.length;i++)
        {
            output[i]=original[i]+update[i];
        }
        return output;
    }
    public void printMarkRangeReport(String username,String password,HashMap<String,Integer []> markRangePerLec, int[] range) 
    {
        if(!this.validateAdmin(username, password))
        {
            return;
        }
        System.out.format("%15s","Lecturer ID");
        for(int i=0;i<range.length-1;i++)
        {
            System.out.format("%15s",range[i]+"-"+range[i+1]);
        }
        for(String lecID:markRangePerLec.keySet())
        {
            System.out.format("\n%15s",lecID);
            Integer[] markRange = markRangePerLec.get(lecID);
            for(int i=0;i<markRange.length;i++)
            {
                System.out.format("%15s",markRange[i]);
            }
        }
        System.out.println();
    }
    
    public String getUsername()
    {
        return this.adminUsername;
    }
    
    public String getPassword()
    {
        return this.adminPassword;
    }
    
    public Student loginStudent(String adUsername,String adPassword,String username,String password)
    {
        if(!this.validateAdmin(adUsername, adPassword))
        {
            return null;
        }
        Student student = this.findStudentByUsername(adUsername,adPassword, username);
        if(student!=null&&student.validateAccount(username, password))
        {
            return student;
        }
        return null;
    }
    
    public Lecturer loginLecturer(String adUsername,String adPassword,String username,String password)
    {
        if(!this.validateAdmin(adUsername, adPassword))
        {
            return null;
        }
        Lecturer lecturer = this.findLecturerByUsername(adUsername, adPassword, username);
        if(lecturer!=null&&lecturer.validateAccount(username, password))
        {
            return lecturer;
        }
        return null;
    }

//    HashMap<Long,Student> populateEnrolledStudent(String username, String password, String subjectID) 
//    {
//        if(!this.validateAdmin(username, password))
//        {
//            return null;
//        }
//        Subject subject = this.findSubject(username, password, subjectID);
//        if(subject==null)
//        {
//            return null;
//        }
//        return subject.populateEnrolledStudent(students);   
//    }
    
    void saveSubjects(String fname) throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
        for (Subject subject :subjects.values())
        {
            out.write(subject.toDelimitedString()+ "\n");
        }
        out.close();

    }

    void saveCourses(String fname) throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
        for (Course course :courses.values())
        {
            out.write(course.toDelimitedString()+ "\n");
        }
        out.close();
    }

    void saveStudents(String fname) throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
        for (Student student:students.values())
        {
            out.write(student.toDelimitedString()+ "\n");
        }
        out.close();
    }

    void saveLecturers(String fname) throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
        for (Lecturer lecturer :lecturers.values())
        {
            out.write(lecturer.toDelimitedString()+ "\n");
        }
        out.close();
    }

    void saveMarks(String fname) throws IOException 
    {
        BufferedWriter out = new BufferedWriter (new FileWriter (fname));
            for(Subject subject:subjects.values())
            {
                ArrayList<Student> enrolledStudents = this.getEnrolledStudentsPerSubject(adminUsername, adminPassword).get(subject.getID());
                out.write(subject.saveMarks(enrolledStudents)+"\n");
            }
        out.close();
    }

    private ArrayList<Result> shallowCopyResult(HashMap<Long, Result> results) 
    {
        ArrayList<Result> shallowCopy = new ArrayList<>();
        for(Result result:results.values())
        {
            shallowCopy.add(result);
        }
        return shallowCopy;
    }
    
    public void sortSubjects(ArrayList<Subject> subjects)
    {
        Collections.sort(subjects);
    }
    
    public void sortCourses(ArrayList<Course> courses)
    {
        Collections.sort(courses);
    }
    
    public void sortLecturers(ArrayList<Lecturer> lecturers)
    {
        Collections.sort(lecturers);
    }
    
    public void sortLecturers(ArrayList<Lecturer> lecturers,Comparator<Lecturer> comparator)
    {
        Collections.sort(lecturers, comparator);
    }
    
    public void sortStudents(ArrayList<Student> students)
    {
        Collections.sort(students);
    }
    
    public void sortStudents(ArrayList<Student> students,Comparator<Student> comparator)
    {
        Collections.sort(students, comparator);
    }
    public String toString()
    {
        String output="";
        output+="List of subjects:\n";
        for(Subject subject:subjects.values())
        {
            output+=subject+"\n";
        }
        output+="List of courses:\n";
        for(Course course:courses.values())
        {
            output+=course+"\n";
        }
        output+="List of students:\n";
        for(Student student:students.values())
        {
            output+=student+"\n";
        }
        output+="List of lecturers:\n";
        for(Lecturer lec:lecturers.values())
        {
            output+=lecturers+"\n";
        }
        return output;
    }

    void prinStudentList(HashMap<Long, Student> students) 
    {
        Student.printList(students);
    }

    void printLecturerList(HashMap<String, Lecturer> lecturers) 
    {
        Lecturer.printList(lecturers);
    }

    void printCourseList(HashMap<Long, Course> courses) 
    {
        Course.printList(courses);
    }

    void printSubjectList(HashMap<String, Subject> subjects) 
    {
        Subject.printList(subjects);
    }
    
    public Object login(String username, String password)
    {
        if(this.validateAdmin(username, password))
        {
            return this;
        }
        if(username.contains("std"))
        {
            return this.loginStudent(this.adminUsername, this.adminPassword, username, password);
        }
        if(username.contains("LEC"))
        {
            return this.loginLecturer(adminUsername, adminPassword, username, password);
        }
        return null;
    }
    public void saveDatabase()
    {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        try {
            String dbURL = "jdbc:ucanaccess://UniversityDatabase.accdb" ; 

            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM LecturerSubjects");
            statement.executeUpdate("DELETE FROM CourseSubject");
            statement.executeUpdate("DELETE FROM StudentSubjects");
            statement.executeUpdate("DELETE FROM Course");
            statement.executeUpdate("DELETE FROM Student");
            statement.executeUpdate("DELETE FROM Lecturer");
            statement.executeUpdate("DELETE FROM Subject");
            
//            for(Subject sbj:subjects.values())
//            {
//                String query = "INSERT INTO Subject (subjectID, sName, sType) VALUES ('"+sbj.getID()+"', '"+sbj.getName()+"', '"+sbj.getType()+"');";
//                statement = connection.createStatement();
//                statement.executeUpdate(query); 
//            }

            for(Subject subject:subjects.values())
            {
                String query = "INSERT INTO Subject (subjectID, sName, sType) VALUES ('"+subject.getID()+"', '"+subject.getName()+"', '"+subject.getType()+"');";
                statement = connection.createStatement();
                statement.executeUpdate(query); 
            }
            
            for(Course course:courses.values()) {
                String query = course.getQuery();
                statement = connection.createStatement();
                statement.executeUpdate(query); 
            }
            
            
            for (Student student:students.values()) 
            {
                String query = student.getQuery();
                statement = connection.createStatement();
                statement.executeUpdate(query); 
            }
            
            for(Lecturer lec:lecturers.values())
            {
                String query = lec.getQuery();
                statement = connection.createStatement();
                statement.executeUpdate(query); 
            }
            
            for(Student student:students.values())
            {
                for(Subject subject:student.getSubjects(student.getUsername(), student.getPassword()).values())
                {
                    String query = "INSERT INTO StudentSubjects (studentID, subjectID,mark) VALUES ('"+student.getID()+"', '"+subject.getID()+"', '"+student.getResult(student.getUsername(), student.getPassword(), subject.getID()).getMark()+"');";
                    statement = connection.createStatement();
                    statement.executeUpdate(query); 
                }
            }
            
            for(Course course:courses.values())
            {
                for(Subject subject:course.getSubjects().values())
                {
                    String query = "INSERT INTO CourseSubject (cID, subjectID) VALUES ('"+course.getID()+"', '"+subject.getID()+"');";
                    statement = connection.createStatement();
                    statement.executeUpdate(query); 
                }
            }
            
            for(Lecturer lec:lecturers.values())
            {
                for(Subject subject:lec.getSubjects(lec.getUsername(), lec.getPassword()).values())
                {
                    String query = "INSERT INTO LecturerSubjects (lecID, subjectID) VALUES ('"+lec.getID()+"', '"+subject.getID()+"');";
                    statement = connection.createStatement();
                    statement.executeUpdate(query); 
                }
            }
            
//            for(Student student:students.values())
//            {
//                HashMap<String,Result> results = student.getResults(student.getUsername(),student.getPassword());
//                for(String sjID:results.keySet())
//                {
//                    if(results.get(sjID)!=null)
//                    {
//                        String query = "INSERT INTO Mark (studentID, subjectID,mark) VALUES ('"+student.getID()+"', '"+sjID+"', '"+results.get(sjID).getMark()+"');";
//                        statement = connection.createStatement();
//                        statement.executeUpdate(query); 
//                    }
//                }
//            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {	
            if(connection != null) {
                try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
                try { if (statement != null) statement.close(); } catch (Exception e) {};
                try { if (connection != null) connection.close(); } catch (Exception e) {}
            }
        }
        System.out.println("Data has been saved by the database");
    }
    
    
    public void loadDatabase(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading or registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
        try {
            String dbURL = "jdbc:ucanaccess://UniversityDatabase.accdb" ; 

            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();
            subjects.clear();
            courses.clear();
            students.clear();
            lecturers.clear();
            
            resultSet = statement.executeQuery("SELECT * FROM Subject");
            while(resultSet.next())
            {
                String sjCode = resultSet.getString(1);
                String sjName = resultSet.getString(2);
                SubjectType sjType = SubjectType.valueOf(resultSet.getString(3).trim());
                try 
                {
                    Subject subject = new Subject(sjCode,sjName,sjType);
                    this.addSubject(adminUsername, adminPassword, subject);
//                    if(this.addSubject(adminUsername, adminPassword, subject))
//                    {
//                        System.out.println(subject);
//                    } else System.out.println("error") ;
                } catch (SubjectException ex) 
                {
                    Logger.getLogger(UniversityAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM Course");
            while(resultSet.next()) 
            {
                Course course=null;
                long courseID = resultSet.getLong(1);
                String courseName = resultSet.getString(2);
                String type = resultSet.getString(3);
                if(type.equals("O"))
                {
                    String url = resultSet.getString(7);
                    try {
                        course = new OnlineCourse(courseID,courseName,url);
                    } 
                    catch (CourseException ex) 
                    {
                        Logger.getLogger(UniversityAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else 
                {
                    Session session = Session.valueOf(resultSet.getString(4));
                    String campus = resultSet.getString(5);
                    double discount = resultSet.getDouble(6);
                    
                    try {

                        course = new OnCampusCourse(courseID,courseName,campus,session,discount);
                    } 
                    catch (CourseException ex) 
                    {
                        Logger.getLogger(UniversityAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(this.addCourse(adminUsername, adminPassword, course))
                {
                    System.out.println(course);
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM Student");
            while(resultSet.next()) 
            {
                Student student = null;
                long studentID = resultSet.getLong(1);
                String studentName = resultSet.getString(2);
                Course c = this.findCourse(adminUsername,adminPassword,resultSet.getLong(3));
                String password = resultSet.getString(4);
                if(resultSet.getString(5).equals("D"))
                {
                    String city = resultSet.getString(6);
                    long TFN = resultSet.getLong(7);
                    try {
                        student = new DomesticStudent(studentID,studentName,password,city,TFN,c);
                    } catch (StudentException ex) 
                    {
                        Logger.getLogger(UniversityAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else
                {
                    String country = resultSet.getString(8);
                    String agent = resultSet.getString(9);
                    try {
                        student = new InternationalStudent(studentID,studentName,password,country,agent,c);
                    } catch (StudentException ex) 
                    {
                        Logger.getLogger(UniversityAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(!this.addStudent(adminUsername, adminPassword, student))
                {
                    System.err.println(student.getID());
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM Lecturer");
            while(resultSet.next())
            {
                String lecID = resultSet.getString(1);
                String lecName = resultSet.getString(2);
                String deegree = resultSet.getString(3);
                String school = resultSet.getString(4);
                try {
                    Lecturer lec = new Lecturer(lecID,lecName,deegree,school);
                    if(!this.addLecturer(adminUsername, adminPassword, lec))
                    {
                        System.err.println("CANT add lecturer "+lecID+" to the system");
                    }
                } catch (LecturerException ex) 
                {
                    System.out.println(ex);
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM CourseSubject");
            while(resultSet.next())
            {
                long courseID = resultSet.getLong(1);
                String sjCode = resultSet.getString(2);
                if(!this.addSubjectToCourse(adminUsername, adminPassword, sjCode, courseID))
                {
                    System.out.println(sjCode+"CANT BE added to"+courseID);
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM LecturerSubjects");
            while(resultSet.next())
            {
                String lecID = resultSet.getString(1);
                String sjCode = resultSet.getString(2);
                if(!this.assignSubject(adminUsername, adminPassword, sjCode, lecID))
                {
                    System.out.println(sjCode+"CANT BE assigned to"+lecID);
                }
            }
            
            resultSet = statement.executeQuery("SELECT * FROM StudentSubjects");
            while(resultSet.next())
            {
                long studentID = resultSet.getLong(1);
                String sjCode = resultSet.getString(2);
                Double mark = resultSet.getDouble(3);
                if(this.enroll(adminUsername, adminPassword, sjCode, studentID))
                {
                    if(!this.recordMark(adminUsername, adminPassword, studentID, sjCode, mark))
                    {
                        System.out.println("CANT RECORD MARK of subject "+sjCode+" for student "+studentID);
                    }
                }
            }
            
            
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        finally {	
            if(connection != null) {
                try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
                try { if (statement != null) statement.close(); } catch (Exception e) {};
                try { if (connection != null) connection.close(); } catch (Exception e) {}
            }
        }
        System.out.println("Data has been Loaded from the database");
    }
}
