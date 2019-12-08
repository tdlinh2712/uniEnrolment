/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

/**
 *
 * @author linh
 */
public class UniEnrolmentProgram {
    public static UniversityAdmin admin;
    public static Scanner sc;
    static ArrayList<Student> students;
    static ArrayList<Course> courses;
    static ArrayList<Subject> subjects;
    static ArrayList<Lecturer> lecturers;
    public static String adminUsername="admin";
    public static String adminPassword = "password";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        sc = new Scanner(System.in);
        admin = new UniversityAdmin(adminUsername,adminPassword);
        try 
        {
            //loadFiles();
            //saveFiles();
            //admin.saveDatabase();
            admin.loadDatabase();
            //loadFiles();
            //saveFile1();
            //loadFile1(admin);
            //admin.getCoursesOfferingSubjectLambda(adminUsername,adminPassword);
            saveFiles();
            //loadFiles();
        } 
        catch (IOException ex) 
        {
            System.err.println("Main program:"+ex);
        }
        //core();
        //standard();
        //testLambda();
    }
    
    public static void standard()
    {
        //admin = new UniversityAdmin(adminUsername,adminPassword);
//        UI newUI = new UI(admin);
//        newUI.mainMenu();
          //new GUI(admin).setVisible(true);
        GUI newGUI = new GUI(admin);
        newGUI.startMenu();
          //GUI.setVisible(true);
    }
    
    public static void testLambda()
    {
        System.out.printf("Report for country:\n");
        admin.reportForCountryLambda(adminUsername, adminPassword);
        System.out.printf("Reportfor course:\n");
        admin.reportForCourseLambda(adminUsername, adminPassword);
    }
    public static void core()
    {
        
        System.out.println("\n12. List of the students completed their course grouped by domestic and international\n");
        //HashMap<Long,Student> completedStudents = admin.getStudentsCompleted(adminUsername,adminPassword);
        ArrayList<Student> completedStudents = admin.shallowCopyStudents(adminUsername, adminPassword,admin.getStudentsCompleted(adminUsername,adminPassword));
        Collections.sort(completedStudents);
        for(Student student:completedStudents)
        {
            student.print();
            System.out.println();
        }
        
        System.out.print("\n---------------------------\n");
        System.out.println("\n16.Total tuition fee for all the students in the system\n");
        for(Student student:admin.getAllStudents().values())
        {
            System.out.println("Tuition fee for student ID"+student.getID()+": "+admin.calcTuitionFee(adminUsername,adminPassword,student.getID())+"\n");
        }
        System.out.println("\nTotal tuition fee of all students:"+admin.calcTotalTuitionFee(adminUsername,adminPassword,admin.getAllStudents())+"\n");
        
        System.out.print("\n---------------------------\n");
        System.out.println("\n18.Report for all students in the system\n");
        admin.printStudentReportAll(adminUsername,adminPassword);
        
        System.out.print("\n---------------------------\n");
        System.out.println("\n20.Show the information for all the subjects in the system \n");
        admin.printSubjectReport(adminUsername,adminPassword);

        System.out.print("\n---------------------------\n");
        System.out.println("\n21. Show the list of all the subjects in the system categorized/ grouped by the course name\n");
        HashMap<Long,ArrayList<Subject>> allCourseSubjects = admin.getAllCourseSubjects(adminUsername, adminPassword);
        
        for(Long courseID:allCourseSubjects.keySet())
        {
            System.out.println("\nSubjects of course ID "+courseID+":\n");
            ArrayList<Subject> subjects = allCourseSubjects.get(courseID);
            for(Subject subject:subjects)
            {
                subject.print();
                System.out.println();
            }
        }
        
        System.out.print("\n---------------------------\n");
        System.out.println("\n22. Search for a partial subject code \n");
        System.out.println("Results when search for the part DPIT");
        HashMap<String,Subject> subjects = admin.filterByName(adminUsername,adminPassword,"DPIT");
        for(Subject sj:subjects.values())
        {
            System.out.println(sj);
        }


        //Number of students per subject type + total tuition fee per subject type
        System.out.println("\n24+25+26+27. Number of subjects, number of students, average mark, total fee per type \n");
        HashMap<SubjectType,Integer> countSubjects = new HashMap<>();
        HashMap<SubjectType,Integer> countStudents2 = new HashMap<>();
        HashMap<SubjectType,Result> averageMarks = new HashMap<>();
        HashMap<SubjectType,Double> totalTuitionFee2 = new HashMap<>();
        admin.reportForSubjectType(adminUsername,adminPassword, countSubjects, countStudents2, averageMarks, totalTuitionFee2);
        admin.printReportForSubjectType(adminUsername,adminPassword,countSubjects, countStudents2, averageMarks, totalTuitionFee2);


        //28+29. Number of students per course + total tuition fee per course
        System.out.println("\n28+29. Number of students per course + total tuition fee per course\n");
        HashMap<Long,Integer> countStudents= new HashMap<>();
        HashMap<Long,Double> totalTuitionFee = new HashMap<>();
        admin.reportForCourses(adminUsername,adminPassword,countStudents, totalTuitionFee);
        admin.printReportForCourse(adminUsername,adminPassword,countStudents, totalTuitionFee);



        System.out.println("\n30+31. Number of students per country + total tuition fee per country\n");
        HashMap<String,Integer> countStudents1 = new HashMap<>();
        HashMap<String,Double> totalTuitionFee1 = new HashMap<>();
        admin.reportForCountry(adminUsername,adminPassword, countStudents1, totalTuitionFee1);
        admin.printReportForCountry(adminUsername,adminPassword,countStudents1, totalTuitionFee1);



        System.out.println("\n32. Aggregation Report\n");
        int range[] = {0,40,70,100};
        HashMap<String,Integer[]> markRangePerLec = admin.markRangePerLecturer(adminUsername,adminPassword, range);
        admin.printMarkRangeReport(adminUsername,adminPassword,markRangePerLec, range);
    }
    private static void saveFile1 () throws IOException 
    {
        ObjectOutputStream outputst = null;
        
        try
        {
            outputst = new ObjectOutputStream(Files.newOutputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try
        {
            outputst.writeObject(admin);
        }
        catch(IOException ex)
        {
            System.err.println("error in adding the objects to the file ");
            System.exit(1);
        } 
        try
        {
            if(outputst !=null)
            outputst.close();           
        }
        catch(IOException ex)
        {
            System.err.println("error in closing the file ");
            System.exit(1);
        }
        
    }
    
    private static void loadFile1 (UniversityAdmin admin) throws IOException
    {
        ObjectInputStream inputst=null;
        try
        {
            inputst = new ObjectInputStream(Files.newInputStream(Paths.get("test.ser")));           
        }
        catch(IOException ex)
        {
            System.err.println("error in create/open the file ");
            System.exit(1);
        }
        try
        {
           while(true)
            {
                admin = (UniversityAdmin) inputst.readObject();
            }
        }
        catch(EOFException ex)
        {
            System.out.println("no more record!");
        }
        catch (ClassNotFoundException ex) 
        {
            System.err.println("error in wrong class in the file ");
        } 
        catch(IOException ex)
        {
            System.err.println("error in add object to the file ");
            System.exit(1);
        }
        try
        {
            if(inputst !=null)
            inputst.close();           
        }
        catch(IOException ex)
        {
            System.err.println("error in close the file ");
            System.exit(1);
        }
    }

    public static void loadFiles() throws IOException
    {
        loadSubjects();
        loadCourses();
        loadStudents();
        loadLecturers();
        loadMarks();
    }
    private static void saveFiles () throws IOException 
    {
        admin.saveSubjects("subjects.txt");
        admin.saveCourses("courses.txt");
        admin.saveStudents("students.txt");
        admin.saveLecturers("lecturers.txt");
        admin.saveMarks("recordMarks.txt");
    }
    
    public static void loadSubjects() throws IOException
    {
        BufferedReader in = new BufferedReader (new FileReader ("subjects.txt"));
        //read subject 
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim ();
            String[] field = line.split(",");
            String ID = field[0];
            String name = field[1];
            SubjectType type=SubjectType.valueOf(field[2].trim());
            Subject subject;
            try 
            {
                subject = new Subject(ID,name,type);
                admin.addSubject(adminUsername,adminPassword,subject);
            } 
            catch (SubjectException ex) 
            {
                System.err.println(ex);
            }
            
            line = in.readLine();
        }
        in.close ();
    }
    public static void loadCourses() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader ("courses.txt"));
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim ();
            String[] field = line.split(",");
            //"C" means on campus, "O" means online
            String type=field[0].trim();
            Course course=null;
            if(type.equals("C"))
            {

                    String campus=field[1].trim();
                    Session session=Session.valueOf(field[2].trim());
                    double discount=Double.parseDouble(field[3]);
                    Long courseID=Long.parseLong(field[4]);
                    String courseName = field[5];
                    try 
                    {
                        course= new OnCampusCourse(courseID,courseName,campus,session,discount);
                    } 
                    catch (CourseException ex) 
                    {
                        System.err.println(ex);
                        try 
                        {
                            course= new OnCampusCourse(ex.ID,courseName,campus,session,discount);
                        } 
                        catch (CourseException ex1) 
                        {
                        }
                    }
            } 
            else
            {
                String url=field[1];
                Long courseID=Long.parseLong(field[2]);
                String courseName = field[3];
                try 
                    {
                        course= new OnlineCourse(courseID,courseName,url);
                    } 
                    catch (CourseException ex) 
                    {
                        System.err.println(ex);
                        try 
                        {
                            course= new OnlineCourse(ex.ID,courseName,url);
                        } 
                        catch (CourseException ex1) 
                        {
                        }
                    }
            }
            line = in.readLine();
            if(admin.addCourse(adminUsername,adminPassword,course))
            {
                String[] field2 = line.split(",");
                int numberOfSubjects = Integer.parseInt(field2[0]);
                for(int i=1;i<1+numberOfSubjects;i++)
                {
                    String subjectID=field2[i];
                    addSubjectToCourse(course.getID(),subjectID);
                }
            }
            line = in.readLine();
        }
//        for(Long courseID:admin.getAllCourseSubjects(adminUsername, adminPassword).keySet())
//        {
//            System.out.print("\n"+courseID+"\n");
//            for(Subject subject:admin.getAllCourseSubjects(adminUsername, adminPassword).get(courseID))
//            {
//                System.out.print(subject+",  ");
//            }
//        }
        in.close ();
    }

    public static void loadStudents() throws IOException
    {
        BufferedReader in = new BufferedReader (new FileReader ("students.txt"));
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim ();
            String[] field = line.split(",");
            String type=field[0].trim();
            long studentID=Long.parseLong(field[3]);
            String name = field[4];
            String password=field[5];
            long courseID=Long.parseLong(field[6]);
            Course course=admin.findCourse(adminUsername,adminPassword,courseID);
            Student student=null;
            //1 means domestic, 0 means international
            if(type.equals("D"))
            {
                String city=field[1];
                long TFN=Long.parseLong(field[2]);
                try 
                {
                    student = new DomesticStudent(studentID,name,password,city,TFN,course);
                } 
                catch (StudentException ex) 
                {
                    System.err.println(ex);
                    try {
                        student = new DomesticStudent(ex.ID,name,password,city,TFN,course);
                    } 
                    catch (StudentException ex1) 
                    {
                    }
                }
            } else
            {
                String country=field[1];
                String uniAgent=field[2];
                try 
                {
                    student = new InternationalStudent(studentID,name,password,country,uniAgent,course);
                } catch (StudentException ex) 
                {
                    System.err.println(ex);
                    try {
                        student = new InternationalStudent(ex.ID,name,password,country,uniAgent,course);
                    } 
                    catch (StudentException ex1) 
                    {
                    }
                }
            }
            line = in.readLine();
            if(admin.addStudent(adminUsername,adminPassword,student))
            {
                String[] field2 = line.split(",");
                int numberOfSubjects = Integer.parseInt(field2[0]);
                for(int i=1;i<1+numberOfSubjects;i++)
                {
                    String subjectID=field2[i];
                    enrollStudentForSubject(student.getID(),subjectID);
                }
            }
            line = in.readLine();
        }
        in.close ();
    }
    public static void loadLecturers() throws IOException
    {
        BufferedReader in = new BufferedReader (new FileReader ("lecturers.txt"));
        //read subject 
        String line = in.readLine();
        while (line != null) 
        {
            line = line.trim();
            String[] field = line.split(",");
            String lecID=field[0];
            String name=field[1];
            String school=field[2];
            String deg=field[3];
            Lecturer lecturer=null;
            try 
            {
                lecturer = new Lecturer(lecID,name,school,deg);
                admin.addLecturer(adminUsername,adminPassword,lecturer);
            } 
            catch (LecturerException ex) 
            {
                System.err.println(ex);
            }
            line = in.readLine();
            String[] field2 = line.split(",");
            int numberOfSubjects = Integer.parseInt(field2[0]);
            for(int i=0;i<numberOfSubjects;i++)
            {
                String subjectID=field2[i+1];
                assignSubject(lecturer.getID(),subjectID);
            }
            line = in.readLine();
        }
        //readCourse
        in.close ();
    }
    public static void loadMarks() throws IOException
    {
        
        BufferedReader in = new BufferedReader (new FileReader ("recordMarks.txt"));
        //read subject 
        String line = in.readLine();
        while (line != null) 
        {
            line=line.trim();
            String[] field2 = line.split(" ");
            String subjectCode=field2[0];
            double numberOfStudents = Double.parseDouble(field2[1]);
            if(numberOfStudents>0)
            {
                line=in.readLine().trim();
                String[] field3 = line.split(" ");
                for(int j=0;j<numberOfStudents;j++)
                {
                    long studentID=Long.parseLong(field3[2*j]);
                    double mark = Double.parseDouble(field3[2*j+1]);
                    admin.recordMark(admin.getUsername(),admin.getPassword(),studentID, subjectCode, mark);
                }
            } 
            line = in.readLine();
        }
        //readCourse
        in.close ();
    }

    private static void addSubject(Subject subject) 
    {
        if(!admin.addSubject(adminUsername,adminPassword,subject))
        {
            System.out.println("Subject ID "+subject.getID()+" already exist");
        }
    }

    private static void recordMark(String lecturerID, long studentID, String subjectCode, double mark) 
    {
        if(!admin.recordMark(adminUsername,adminPassword,lecturerID, studentID, subjectCode, mark))
        {
            System.out.println("Invalid request!");
        }
    }

    private static void addCourse(Course course) 
    {
        if(!admin.addCourse(adminUsername,adminPassword,course))
        {
            System.out.println("Course ID "+course.getID()+" already exist");
        }
    }

    private static void addStudent(Student student) 
    {
        if(!admin.addStudent(adminUsername,adminPassword,student))
        {
            System.out.println("Student ID "+student.getID()+" already exist");
        }
    }

    private static void addLecturer(Lecturer lecturer) 
    {
        if(!admin.addLecturer(adminUsername,adminPassword,lecturer))
        {
            System.out.println("Lecturer with ID "+lecturer.getID()+" already exist");
        }
    }

    private static void enrollStudentForSubject(long studentID, String subjectID) 
    {
        if(!admin.enroll(adminUsername,adminPassword,subjectID, studentID))
        {
            System.out.println("Cannot enroll subject "+subjectID+" for student ID "+studentID);
        }
    }

    private static void addSubjectToCourse(long courseID, String subjectCode) 
    {
        if(!admin.addSubjectToCourse(adminUsername,adminPassword,subjectCode, courseID))
        {
            System.out.println("Cannot add subject "+subjectCode+" to course ID "+courseID);
        }
    }

    private static void assignSubject(String lecID, String subjectCode) 
    {
        if(!admin.assignSubject(adminUsername,adminPassword,subjectCode, lecID))
        {
            System.out.println("Cannot assign subject "+subjectCode+" to lecturer ID "+lecID);
        }
    }

    private static void removeSubjectFromCourse(String subjectCode, long courseID) 
    {
        if(!admin.removeSubjectFromCourse(adminUsername,adminPassword,subjectCode, courseID))
        {
            System.out.println("Cannot remove subject "+subjectCode+" from course ID "+courseID);
        }
    }

    private static void withdrawStudent(long studentID, String subjectCode) 
    {
        if(!admin.withdraw(adminUsername,adminPassword,subjectCode, studentID))
        {
            System.out.println("Cannot withdraw student ID "+studentID+" from subject ID "+subjectCode);
        }
    }
}
