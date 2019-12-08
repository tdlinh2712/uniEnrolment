/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linh
 */
public class UI
{
    Scanner scanner = new Scanner(System.in);
    UniversityAdmin admin;
    public UI(UniversityAdmin admin)
    {
        this.admin=admin;
    }
    
//main Menu
    public void mainMenu()
    {
            int option = 0;
            while(option!=4)
            {
                displayMainMenu(); 
                boolean flag=false;
                while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
                switch(option)
                {
                    case 1:
                        if (adminLogin())
                        {
                            press();
                            adminMenu();
                        }
                        press();
                        break;
                    case 2: //student login(?)
                        Student student = studentLogin();
                        if ((student!=null))
                        {
                            System.out.println("\nLog in successful!");
                            press();
                            studentMenu(student);
                        } 
                        else
                        {
                            System.err.println("\nWrong username and password.");
                        }
                        press();
                        break;
                    case 3: //lecturer  login(?)
                        Lecturer lecturer = lecturerLogin();
                        if (lecturer!=null)
                        {
                            System.out.println("\nLog in successful!");
                            press();
                            lecturerMenu(lecturer);
                        } 
                        else
                        {
                            System.err.println("\nWrong username and password.");
                        }
                       press();
                       break;                 
                    case 4:
                       break;
                    default:
                       System.out.println("\nWrong option!\n ");
                       press();
                }
            }
    }
    public static void displayMainMenu()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nMain Menu\n");
        System.out.println("1. Admin Login");
        System.out.println("2. Student Login");
        System.out.println("3. Lecturer Login");
        System.out.println("4. exit");
        System.out.print("\nPlease choose an option from 1 to 4: ");
    }
    
    public boolean adminLogin()
    {
        String username;
        String password;
        System.out.print("\nPlease enter admin username: ");
        username = scanner.next();
        System.out.print("Please enter admin password: ");
        password = scanner.next();
        if (admin.validateAdmin(username,password)==false)
        {
            System.out.println("\nWrong username and password.\n");
            return false;
        }
        else
        {
            System.out.println("\nLog in successful!");
            return true;
        }  
    }
    
    private Student studentLogin() 
    {
        System.out.print("\nPlease enter student username: ");
        String username = scanner.next();
        System.out.print("\nPlease enter student password:");
        String password = scanner.next();
        return admin.loginStudent(admin.getUsername(), admin.getPassword(), username, password);
    }  
    private Lecturer lecturerLogin() 
    {
        System.out.print("\nPlease enter lecturer username: ");
        String username = scanner.next();
        System.out.print("\nPlease enter lecturer password:");
        String password = scanner.next();
        return admin.loginLecturer(admin.getUsername(), admin.getPassword(), username, password);
    } 
//*************************************************************************************
//Admin menu
    public void adminMenu()
    {
        int option = 0;
        while(option!=7)
        {
           displayAdminMenu(); 
           boolean flag=false;
           while(!flag)
            {
                try
                {
                    option = scanner.nextInt();
                    flag=true;
                }
                catch(InputMismatchException e)
                {
                    System.err.println(e+": Wrong input type");
                    flag=false;
                }
            }
           switch(option)
           {
               case 1://subjects and courses 
                   adminSubMenuSubjects();
                   press();
                   break;
               case 2://students 
                   adminSubMenuStudents();
                   press();
                   break;
               case 3://lecturers 
                   adminSubMenuLecturers();
                   press();
                   break;
               case 4://shallow copies and deep copies
                   adminSubMenuMakeCopies();
                   press();
                   break;
               case 5://sorting
                   adminSubMenuSorting();
                   press();
                   break;
               case 6://statistics 
                   adminSubMenuStat();
                   press();
                   break;
               case 7:
                   return;
               default:
                   System.out.println("\nWrong option!\n ");
                   press();
            }
        }
    }    
     
    public static void displayAdminMenu()
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu\n");
        System.out.println("1. Subjects and Courses");
        System.out.println("2. Students");
        System.out.println("3. Lecturers");
        System.out.println("4. Make shallow copy and deep copies");
        System.out.println("5. Sorting");
        System.out.println("6. Statistics");
        System.out.println("7. Exit");
        System.out.print("\nPlease choose an option from 1 to 7: ");
    }
    
//*************************************************************************************
//lecturer menu
    private static void displayLecturerMenu() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nLecturer Menu\n");
        System.out.println("1. Record Marks for my subjects");
        System.out.println("2. My information");
        System.out.println("3. exit");
        System.out.print("\nPlease choose an option from 1 to 3: ");
    }
    private void lecturerMenu(Lecturer lecturer) 
    {
        //Scanner scanner = new Scanner(System.in);
        try
        {
            int option = 0;
            while(option!=3)
            {
               displayLecturerMenu(); 
                boolean flag=false;
                while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
               switch(option)
               {
                   case 1://record mark
                       recordMark(lecturer);
                       press();
                       break;
                   case 2://my infomation
                       lecturer.print();
                       press();
                       break;
                   case 3://exit
                       return;
                   default:
                       System.out.println("\nWrong option!\n ");
                       press(); 
                }
            }   
        }
        catch (InputMismatchException e)
        {
            System.err.println(e);
        }
        catch(NullPointerException e)
        {
            System.err.println(e);
        }
    }
    
    private void recordMark(Lecturer lecturer) 
    {
        try
        {
            System.out.print("Enter Subject Code:");
            Subject subject=this.findSubject();
            Lecturer lecLogin = this.lecturerLogin();
            while(lecLogin==null|lecLogin.getID()!=lecturer.getID())
            {
                System.out.println("Can't login to lecturer. Please try again");
            }
            if(subject!=null)
            {
                if(lecturer.hasSubject(lecturer.getUsername(),lecturer.getPassword(),subject.getID())==null)
                {
                    System.err.println("Teacher doesn't teach this subject");
                }
                //HashMap<Long,Student> enrolledStudents = admin.findEnrolledStudentsPerSubject(admin.getUsername(),admin.getPassword(),subject.getID());
                ArrayList<Student> enrolledStudents = admin.getEnrolledStudentsPerSubject(admin.getUsername(), admin.getPassword()).get(subject.getID());
                if(!enrolledStudents.isEmpty())
                {
                    System.out.format("\n%15s %30s %15s","Student ID","Student Name","Mark");
                    for(Student std:enrolledStudents)
                    {
                        double mark=-1;
                        while (!admin.recordMark(admin.getUsername(), admin.getPassword(), lecturer.getID(), std.getID(), subject.getID(), mark))
                        {
                            System.out.format("\n%15s %30s %13s",std.getID(),std.getName(),"");
                            mark = scanner.nextDouble();
                            if(mark<0||mark>100)
                            {
                                System.err.println("Please enter a mark from 0 to 100 only");
                            }
                        }

                    }
                    System.out.println("Record mark finished");
                } else
                {
                    System.out.println("No student in this class yet");
                }
            }
        }
        catch (InputMismatchException e)
        {
            System.err.println(e);
        }
    }
//*************************************************************************************
//Student menu
    private static void displayStudentMenu() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nStudent Menu\n");
        System.out.println("1. Enroll to a subject");
        System.out.println("2. Withdraw a subject");
        System.out.println("3. My information");
        System.out.println("4. Fee Statement");
        System.out.println("5. Print Enrolment Record");
        System.out.println("6. exit");
        System.out.print("\nPlease choose an option from 1 to 6: ");
    }
    private void studentMenu(Student student) 
    {
            int option = 0;
            while(option!=6)
            {
               displayStudentMenu(); 
                boolean flag=false;
                while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
               switch(option)
               {
                   case 1://add a subject
                       studentEnrollSubject(student);
                       press();
                       break;
                   case 2://withdraw a subject
                       studentWithdrawSubject(student);
                       press();
                       break;
                   case 3://print information 
                       student.print();
                       press();
                       break;
                   case 4://fee statement 
                       System.out.println("\nTotal tuiton fee:"+admin.calcTuitionFee(admin.getUsername(), admin.getPassword(), student.getID())+"\n");
                       press();
                       break;
                   case 5://print enrolment record 
                       student.printReport();
                       press();
                       break;
                   case 6://exit
                       return;
                   default:
                       System.out.println("\nWrong option!\n ");
                       press(); 
                }
            }
    }
    private void studentEnrollSubject(Student std) 
    {
        Subject subject=this.findSubject();
        Student studentLogin = this.studentLogin();
        while(studentLogin==null||studentLogin.getID()!=std.getID())
        {
            System.out.println("Please try again");
        }
        if(subject!=null)
        {
            if (admin.enroll(admin.getUsername(), admin.getPassword(), subject.getID(), std.getID()))
            {
                System.out.println("Enroll successful");
            } else
            {
                System.out.println("FAILED. Subject already been enrolled ");//How about subject not in course?
            }
        }
    }
    
    public Subject findSubject()
    {
        System.out.print("Enter Subject Code:");
        String subjectID=scanner.next();
        Subject subject = admin.findSubject(admin.getUsername(),admin.getPassword(),subjectID);
        if(subject==null)
        {
            System.out.println("Cant find subject");
        }
        return subject;
    }

    private void studentWithdrawSubject(Student std) 
    {
        System.out.println("Enter subject ID:");
        String subjectID = scanner.next();
        Student studentLogin = this.studentLogin();
        while(studentLogin==null||studentLogin.getID()!=std.getID())
        {
            System.out.println("Please try again");
        }
        if (admin.withdraw(admin.getUsername(),admin.getPassword(), subjectID, std.getID()))
        {
            System.out.println("Withdraw successful");
        } else
        {
            System.out.println("FAILED ti withdraw");//How about subject not in course?
        }
    }
    
    private void press()
    {
        System.out.print("\nPlease press any key to continue");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void adminSubMenuSubjects() 
    {
            int option = 0;
            while(option!=7)
            {
                displaySubMenuSubjects(); 
                boolean flag=false;
                while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
               switch(option)
               {
                    case 1://Add a subject
                       addSubject();
                       press();
                       break;
                    case 2://View subject report
                       subjectReport();
                       press();
                       break;
                    case 3://Find all coursescontain a subject 
                       findCourseContainSubject();
                       press();
                       break;
                    case 4://Add a new course
                       addCourse();
                       press();
                       break;
                    case 5://Add subject to course
                       addSubjectToCourse();
                       press();
                       break;
                    case 6://Remove a subject from course
                       removeSubjectFromCourse();
                       press();
                       break;
                    case 7:
                       return;
                    default:
                       System.out.println("\nWrong option!\n ");
                       press();
                }
            }
        
    }
    
    private void displaySubMenuSubjects() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Subjects and Courses\n");
        System.out.println("1. Add a subject");
        System.out.println("2. View subject report");
        System.out.println("3. Find all courses contain a subject ");
        System.out.println("4. Add a new course");
        System.out.println("5. Add subject to course");
        System.out.println("6. Remove a subject from course");
        System.out.println("7. exit");
        System.out.print("\nPlease choose an option from 1 to 7: ");
    }

    private void adminSubMenuStudents() 
    {
        int option = 0;
        while(option!=7)
        {
            displaySubMenuStudents(); 
            boolean flag=false;
            while(!flag)
            {
                try
                {
                    option = scanner.nextInt();
                    flag=true;
                }
                catch(InputMismatchException e)
                {
                    System.err.println(e+": Wrong input type");
                    flag=false;
                }
            }
           switch(option)
           {
                case 1://add student
                    addStudent();
                    press();
                    break;
                case 2://report for a student 
                    studentReport();
                    press();
                    break;
                case 3://enroll a student to a subject 
                    enrollStudent(); 
                    press();
                    break;
                case 4://withdraw student from subject 
                    withdrawStudent();
                    press();
                    break;
                case 5://List all of the students completed their course
                    printCompletedStudent();
                    press();
                    break;
                case 6://Record a mark for a student
                    recordMarkForAStudent();
                    press();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\nWrong option!\n ");
                    press();
            }
        }
    }
    private void displaySubMenuStudents() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Students\n");
        System.out.println("1. Add a student");
        System.out.println("2. Show report for a student");
        System.out.println("3. Enroll a student to a subject");
        System.out.println("4. Withdraw a student from a subject");
        System.out.println("5. List all of the students completed their course");
        System.out.println("6. Record a mark for a student");
        System.out.println("7. exit");
        System.out.print("\nPlease choose an option from 1 to 7: ");
    }
    private void adminSubMenuLecturers() 
    {
        int option = 0;
        while(option!=3)
        {
           displaySubMenuLecturers(); 
            boolean flag=false;
               while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
            switch(option)
            {
                case 1://add lecturers  
                    addLecturer();
                    press();
                    break;
                case 2://assign lecturer 
                    assignLecturer();
                    press();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nWrong option!\n ");
                    press();
            }
        }
    }
    private void displaySubMenuLecturers() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Lecturer\n");
        System.out.println("1. Add a lecturer to the system");
        System.out.println("2. Assign subjects to a lecturer");
        System.out.println("3. exit");
        System.out.print("\nPlease choose an option from 1 to 3: ");
    }
    private void adminSubMenuStat() 
    {
            int option = 0;
            while(option!=8)
            {
               displaySubMenuStat();
               boolean flag=false;
               while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
               switch(option)
               {
                    case 1://Report for all students in the system
                        admin.printStudentReportAll(admin.getUsername(),admin.getPassword());
                        press();
                        break;
                    case 2://Report for all subjects in the system
                        admin.printSubjectReport(admin.getUsername(),admin.getPassword());
                        press();
                        break;
                    case 3://Show the list of all the subjects in the system categorized/ grouped by the course name
                        admin.printSubjectsPerCourse(admin.getUsername(),admin.getPassword());
                        press();
                        break;
                    case 4://Number of students per course + total tuition fee per course
                        HashMap<Long,Integer> countStudents= new HashMap<>();
                        HashMap<Long,Double> totalTuitionFee = new HashMap<>();
                        admin.reportForCourses(admin.getUsername(),admin.getPassword(),countStudents, totalTuitionFee);
                        admin.printReportForCourse(admin.getUsername(),admin.getPassword(),countStudents, totalTuitionFee);
                        press();
                        break;
                    case 5://Number of students per country + total tuition fee per country
                        HashMap<String,Integer> countStudents1 = new HashMap<>();
                        HashMap<String,Double> totalTuitionFee1 = new HashMap<>();
                        admin.reportForCountry(admin.getUsername(),admin.getPassword(), countStudents1, totalTuitionFee1);
                        admin.printReportForCountry(admin.getUsername(),admin.getPassword(),countStudents1, totalTuitionFee1);
                        press();
                        break;
                    case 6://Number of students per subject type + total tuition fee per subject type
                        HashMap<SubjectType,Integer> countSubjects = new HashMap<>();
                        HashMap<SubjectType,Integer> countStudents2 = new HashMap<>();
                        HashMap<SubjectType,Result> averageMarks = new HashMap<>();
                        HashMap<SubjectType,Double> totalTuitionFee2 = new HashMap<>();
                        admin.reportForSubjectType(admin.getUsername(),admin.getPassword(), countSubjects, countStudents2, averageMarks, totalTuitionFee2);
                        admin.printReportForSubjectType(admin.getUsername(),admin.getPassword(),countSubjects, countStudents2, averageMarks, totalTuitionFee2);
                        press();
                        break;
                    case 7://Aggregation Report
                        int range[] = {0,40,70,100};
                        HashMap<String,Integer[]> markRangePerLec = admin.markRangePerLecturer(admin.getUsername(),admin.getPassword(), range);
                        admin.printMarkRangeReport(admin.getUsername(),admin.getPassword(),markRangePerLec, range);
                        press();
                        break;

                    case 8:
                        return;
                    default:
                        System.out.println("\nWrong option!\n ");
                        press();
                }
            }
    }

    private void displaySubMenuStat() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Statistics\n");
        System.out.println("1. Report for all students in the system");
        System.out.println("2. Report for all subjects in the system");
        System.out.println("3. Show the list of all the subjects in the system categorized/ grouped by the course name");
        System.out.println("4. Number of students per course + total tuition fee per course");
        System.out.println("5. Number of students per country + total tuition fee per country");
        System.out.println("6. Number of students per subject type + total tuition fee per subject type");
        System.out.println("7. Aggregation Report");
        System.out.println("8. exit");
        System.out.print("\nPlease choose an option from 1 to 8: ");
    }

    private void addSubject() 
    {
        System.out.print("Subject name:");
        String name= scanner.next();
        SubjectType type=null;
        boolean flag=false;
        while(!flag)
        {
            try
            {
                System.out.println("Select subject type(GENERAL/SPECIALISED/ADVANCED)");
                type = SubjectType.valueOf(scanner.next());
                flag=true;
            }
            catch(IllegalArgumentException e)
            {
                System.err.println(e+": Wrong enum input \n");
                flag=false;
            }
        }
        Subject subject=null;
        flag=false;
        while(!flag)
        {
            try
            {
                flag=true;
                System.out.print("Subject code:");
                String sjCode=scanner.next();
                subject = new Subject(sjCode,name,type);
                
            }
            catch (SubjectException ex) 
            {
                System.err.println(ex);
                flag=false;
            }
        }
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.addSubject(admin.getUsername(),admin.getPassword(),subject))
        {
            System.out.println("Subject added to course");
        } else
        {
            System.out.println("Subject added to course FAILED");
        }
            
    }

    private void subjectReport() 
    {
        Subject sj=this.findSubject();
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(sj!=null)
        {
            admin.printSubjectReport(admin.getUsername(), admin.getPassword(), sj);
        }
    }

    private void addCourse() 
    {
        Course course1;
//            System.out.print("Please enter course ID:");
//            long courseID = scanner.nextLong();
            System.out.println("PLease enter course name: ");
            String name = scanner.next();
            int type=0;
            while(!(type==1||type==2))
            {
                try
                {
                System.out.println("Please enter course type: 1. Online 2. On Campus");
                type = scanner.nextInt();
                if (!(type==1||type==2))
                {
                    System.err.println("Please choose 1 or 2 only");
                }
                }
                catch(InputMismatchException e)
                {
                    System.err.println(e+": Wrong input type");
                }
            }
            if(type==1)
            {
                System.out.print("Please enter course url:");
                String url = scanner.next();
                course1 = new OnlineCourse(name,url);
            } else
            {
                System.out.print("Please enter campus:");
                String campus = scanner.next();
                System.out.print("Please enter session (Spring/Autumn/Summer:");
                String sess = scanner.next();
                Session session=Session.valueOf(name);
                double discount=0;
                boolean flag=false;
                while(!flag)
                {
                    try
                    {
                    System.out.print("Please enter discount rate for domestic students:");
                    discount = scanner.nextDouble();
                    flag=true;
                    if(discount<0||discount>1)
                    {
                        System.err.println("Please enter a number from 0 to 1 only");
                        flag=false;
                    }
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
                course1 = new OnCampusCourse(name, campus,session,discount);
            }
            while(!this.adminLogin())
            {
                System.out.println("Please try again");
            }
            if(admin.addCourse(admin.getUsername(), admin.getPassword(), course1))
            {
                System.out.println("Course added to the system successful! An ID "+course1.getID()+" has been assigned to the course");
            }
            else
            {
                System.out.println("CANNOT add course to the system");
            }
    }

    private void addSubjectToCourse() 
    {
        System.out.print("Enter subject code:");
        String sjCode = scanner.next();
        long courseID=0;
        boolean flag=false;
        while(!flag)
        {
            try
            {
            System.out.print("Enter course code:");
            courseID = scanner.nextLong();
            flag=true;
            }
            catch(InputMismatchException e)
            {
                System.err.println(e+": Wrong input type");
                flag=false;
            }
        }
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.addSubjectToCourse(admin.getUsername(), admin.getPassword(),sjCode, courseID))
        {
            System.out.println("Subject added to course!");
        } else
        {
            System.err.println("FAILED to add subject to course!");
        }
    }

    private void removeSubjectFromCourse() 
    {
        System.out.println("Enter subject ID: ");
        String sjID = scanner.next();
        long courseID=0;
        boolean flag=false;
        while(!flag)
        {
            try
            {
            System.out.print("Enter course code:");
            courseID = scanner.nextLong();
            flag=true;
            }
            catch(InputMismatchException e)
            {
                System.err.println(e+": Wrong input type");
                flag=false;
            }
        }
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.removeSubjectFromCourse(admin.getUsername(), admin.getPassword(),sjID,courseID))
        {
            System.out.println("Subject added to course!");
        } else
        {
            System.err.println("FAILED to add subject to course!");
        }
    }

    private void addStudent() 
    {
        Student student;
        System.out.print("Enter student name:");
        String name = scanner.next();
        System.out.print("Enter student password:");
        String password = scanner.next();
        long courseID=0;
        boolean flag=false;
        while(!flag)
        {
            try
            {
            System.out.print("Enter course code:");
            courseID = scanner.nextLong();
            flag=true;
            }
            catch(InputMismatchException e)
            {
                System.err.println(e+": Wrong input type");
                flag=false;
            }
        }
        Course course=admin.findCourse(admin.getUsername(), admin.getPassword(),courseID);
        if(course==null)
        {
            System.err.println("Can't find course");
        } else
        {
            int type=0;
            while(!(type==1||type==2))
            {
                try
                {
                System.out.println("Please enter course type: 1. International 2. Domestic");
                type = scanner.nextInt();
                if (!(type==1||type==2))
                {
                    System.err.println("Please choose 1 or 2 only");
                }
                }
                catch(InputMismatchException e)
                {
                    System.err.println(e+": Wrong input type");
                }
            }
            if(type==1)//international student
            {
                System.out.print("Enter student's country:");
                String country = scanner.next();
                System.out.println("Enter student's agent");
                String agent = scanner.next();
                student= new InternationalStudent(name,password,country,agent,course);
            } else
            {
                System.out.print("Enter student's city:");
                String city = scanner.next();
                flag=false;
                long TFN=0;
                while(!flag)
                {
                    try
                    {
                    System.out.println("Enter student's TFN");
                    TFN = scanner.nextLong();
                    flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
                student= new DomesticStudent(name,password,city,TFN,course);
            }
            while(!this.adminLogin())
            {
                System.out.println("Please try again");
            }
            if(admin.addStudent(admin.getUsername(), admin.getPassword(),student))
            {
                System.out.println("Student adđed to the system");
                student.print();
            } else
            {
                System.err.println("Student adđed to the system");
            }
        }
        
    }
    
    private Student findStudent()
    {
        System.out.println("Enter student number:");
        long stdID = scanner.nextLong();
        Student student= admin.findStudent(admin.getUsername(), admin.getPassword(), stdID);
        if(student==null)
        {
            System.err.println("Can't find student!");
        }
        return student;
    }
    private void studentReport() 
    {
        Student student = this.findStudent();
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        student.printReport();
    }

    private void enrollStudent() 
    {
        Student student = this.findStudent();
        if(student!=null)
        {
            Subject subject=this.findSubject();
            if(subject!=null)
            {
                while(!this.adminLogin())
                {
                    System.out.println("Please try again");
                }
                if (admin.enroll(admin.getUsername(), admin.getPassword(), subject.getID(), student.getID()))
                {
                    System.out.println("Enroll successful");
                } else
                {
                    System.out.println("FAILED. Subject already been enrolled ");//How about subject not in course?
                }
            }
        }
    }

    private void withdrawStudent() 
    {
        try
        {
            System.out.println("Enter student number:");
            long stdID = scanner.nextLong();
            System.out.println("Enter subject ID:");
            String subjectID = scanner.next();
            while(!this.adminLogin())
            {
                System.out.println("Please try again");
            }
            if (admin.withdraw(admin.getUsername(), admin.getPassword(),subjectID, stdID))
            {
                System.out.println("Withdraw successful");
            } else
            {
                System.out.println("FAILED to withdraw student");
            }
        }
        catch (InputMismatchException e)
        {
            System.err.println(e);
        }
    }

    private void printCompletedStudent() 
    {
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        System.out.println("\nCOMPLETED STUDENTS:\n");
        ArrayList<Student> completedStudents = admin.shallowCopyStudents(admin.getUsername(), admin.getPassword(),admin.getStudentsCompleted(admin.getUsername(), admin.getPassword()));
        Collections.sort(completedStudents);
        for(Student student:completedStudents)
        {
            student.print();
            System.out.println();
        }
    }

    private void recordMarkForAStudent() 
    {
        System.out.print("Enter lecturer ID: ");
        String lecturerID = scanner.next();
        boolean flag=false;long studentID=0;double mark=-1;
        while(!flag)
        {
            try
            {
            System.out.println("Enter student ID: ");
            studentID = scanner.nextLong();
            flag=true;
            }
            catch (InputMismatchException e)
            {
                System.err.println(e);
                flag=false;
            }
        }
        System.out.println("Enter Subject ID: ");
        String subjectID = scanner.next();
        flag=false;
        while(!flag)
        {
            try
            {
                System.out.println("Enter mark: ");
                mark = scanner.nextDouble();
                flag=true;
            }
            catch (InputMismatchException e)
            {
                System.err.println(e);
                flag=false;
            }
        }
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.recordMark(admin.getUsername(), admin.getPassword(),lecturerID, studentID, subjectID, mark))
        {
            System.out.println("Record mark successful!");
        } else
        {
            System.err.println("FAILED to record mark for student");
        }
    }

    private void addLecturer() 
    {
        System.out.print("\nADD A LECTURER\n");
        System.out.print("Enter lecturer's name: ");
        String lecturerName =scanner.next();
        System.out.print("Enter lecturer's degree: ");
        String degree = scanner.next();
        System.out.print("Enter lecturer's University: ");
        String university = scanner.nextLine();
        scanner.next();
        boolean flag=false;
        Lecturer lec=null;
        while(!flag)
        {
            System.out.print("Enter lecturer's ID: ");
            String lecturerID =scanner.next();
            try
            {
                lec = new Lecturer(lecturerID,lecturerName,university,degree);
                flag=true;
            }
            catch(LecturerException ex)
            {
                System.err.println(ex);
                flag=false;
            }
        }
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.addLecturer(admin.getUsername(), admin.getPassword(),lec))
        {
            System.out.println("Lecturer added to the system!");
            lec.print();
        } else
        {
            System.err.println("FAILED to add lecturer to the system");
        }
    }

    private void assignLecturer() 
    {
        System.out.println("\nASSIGN LECTURER TO SUBJECT\n");
        System.out.println("Enter lecturer's ID:");
        String lecID = scanner.next();
        System.out.println("Enter Subject ID:");
        String subjectID = scanner.next();
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        if(admin.assignSubject(admin.getUsername(), admin.getPassword(),subjectID, lecID))
        {
            System.out.println("Subject assigned to lecturer");
        } else
        {
            System.err.println("FAILED to assign subject to lecturer");
        }
    }

    private void findCourseContainSubject() 
    {
        System.out.print("Please enter subject code: ");
        String sjCode = scanner.next(); 
        
        while(!this.adminLogin())
        {
            System.out.println("Please try again");
        }
        
        ArrayList<Course> coursesContainSubject = admin.getCoursesOfferingSubject(admin.getUsername(), admin.getPassword()).get(sjCode);
        if(coursesContainSubject==null)
        {
            System.out.println("No courses available");
        } else
        {
            System.out.println("All courses contain subject "+sjCode+" are:");
            for(Course course:coursesContainSubject)
            {
                course.print();
            }
        }
    }

    private void displaySubMenuSorting() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Sorting\n");
        System.out.println("1. Sort students based on type then name");
        System.out.println("2. Sort students based on tuition fee");
        System.out.println("3. Sort courses based on course type then course Name");
        System.out.println("4. Sort lecturers based on lecturer ID");
        System.out.println("5. Sort lecturers based on number of subjects teaching");
        System.out.println("6. Sort subjects based on subject type then subject code");
        System.out.println("7. exit");
        System.out.print("\nPlease choose an option from 1 to 6: ");
    }
    private void adminSubMenuSorting() 
    {
        int option = 0;
        while(option!=6)
        {
           displaySubMenuSorting(); 
            boolean flag=false;
               while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
            switch(option)
            {
                case 1://Sort students based on type then name
                    System.out.println("1. Sort students based on type then name");
                    ArrayList<Student> students = admin.shallowCopyStudents(admin.getUsername(), admin.getPassword(), admin.getAllStudents());
                    admin.sortStudents(students);
                    for(Student student:students)
                    {
                        System.out.println(student);
                    }
                    press();
                    break;
                case 2://Sort students based on tuition fee
                    System.out.println("Sort students based on tuition fee");
                    ArrayList<Student> students2 = admin.shallowCopyStudents(admin.getUsername(), admin.getPassword(), admin.getAllStudents());
                    admin.sortStudents(students2,Student.tuitionFeeComparator);
                    for(Student student:students2)
                    {
                        System.out.println(student);
                    }
                    press();
                    break;
                case 3://Sort courses based on course type then course Name
                    System.out.println("Sort students based on tuition fee");
                    ArrayList<Course> courses = admin.shallowCopyCourses(admin.getUsername(), admin.getPassword(), admin.getAllCourses());
                    admin.sortCourses(courses);
                    for(Course course:courses)
                    {
                        System.out.println(course);
                    }
                    press();
                    break;
                case 4://Sort lecturers based on lecturer ID
                    System.out.println("Sort lecturers based on lecturer ID");
                    ArrayList<Lecturer> lecturers = admin.shallowCopyLecturers(admin.getUsername(), admin.getPassword(), admin.getAllLecturers());
                    admin.sortLecturers(lecturers);
                    for(Lecturer lec:lecturers)
                    {
                        System.out.println(lec);
                    }
                    press();
                    break;
                case 5://Sort lecturers based on number of subjects teaching
                    System.out.println("Sort lecturers based on number of subjects teachingSort lecturers based on number of subjects teaching");
                    ArrayList<Lecturer> lecturers2 = admin.shallowCopyLecturers(admin.getUsername(), admin.getPassword(), admin.getAllLecturers());
                    admin.sortLecturers(lecturers2,Lecturer.numberOfSubjectsComparator);
                    for(Lecturer lec:lecturers2)
                    {
                        System.out.println(lec);
                    }
                    press();
                    break;
                case 6://Sort subjects based on subject type then subject code
                    System.out.println("Sort subjects based on subject type then subject code");
                    ArrayList<Subject> subjects = admin.shallowCopySubjects(admin.getUsername(), admin.getPassword(), admin.getAllSubjects());
                    admin.sortSubjects(subjects);
                    for(Subject subject:subjects)
                    {
                        System.out.println(subject);
                    }
                    press();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("\nWrong option!\n ");
                    press();
            }
        }
    }
    
    private void displaySubMenuCopies() 
    {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // clear screen
        System.out.println("\nAdmin Menu: Make Copies\n");
        System.out.println("1. Make deep and shallow copies of students");
        System.out.println("2. Make deep and shallow copies of lecturers");
        System.out.println("3. Make deep and shallow copies of courses");
        System.out.println("4. Make deep and shallow copies of subjects");
        System.out.println("5. exit");
        System.out.print("\nPlease choose an option from 1 to 5: ");
    }
    private void adminSubMenuMakeCopies() 
    {
        int option = 0;
        while(option!=6)
        {
           displaySubMenuCopies(); 
            boolean flag=false;
               while(!flag)
                {
                    try
                    {
                        option = scanner.nextInt();
                        flag=true;
                    }
                    catch(InputMismatchException e)
                    {
                        System.err.println(e+": Wrong input type");
                        flag=false;
                    }
                }
            switch(option)
            {
                case 1://Make deep and shallow copies of students
                    System.out.println("1. Deep copy:");
                    try 
                    {
                        HashMap<Long,Student> deepCopy = Student.deepCopy(admin.getAllStudents());
                        admin.prinStudentList(deepCopy);
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("error:"+ex);
                    }
                    System.out.println("2. Shallow Copy:");
                    HashMap<Long,Student> shallowCopy = Student.shallowCopy(admin.getAllStudents());
                    admin.prinStudentList(shallowCopy);
                    press();
                    break;

                case 2://Make deep and shallow copies of lecturers
                    System.out.println("1. Deep copy:");
                    try 
                    {
                        HashMap<String,Lecturer> deepCopy = Lecturer.deepCopy(admin.getAllLecturers());
                        admin.printLecturerList(deepCopy);
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("error:"+ex);
                    }
                    System.out.println("2. Shallow Copy:");
                    HashMap<String,Lecturer> shallowCopy1 = Lecturer.shallowCopy(admin.getAllLecturers());
                    admin.printLecturerList(shallowCopy1);
                    press();
                    break;

                case 3://Make deep and shallow copies of courses
                    System.out.println("Sort students based on tuition fee");
                    System.out.println("1. Deep copy:");
                    try 
                    {
                        HashMap<Long,Course> deepCopy = Course.deepCopy(admin.getAllCourses());
                        admin.printCourseList(deepCopy);
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("error:"+ex);
                    }
                    System.out.println("2. Shallow Copy:");
                    HashMap<Long,Course> shallowCopy2 = Course.shallowCopy(admin.getAllCourses());
                    admin.printCourseList(shallowCopy2);
                    press();
                    break;
                case 4://Make deep and shallow copies of subjects
                    System.out.println("1. Deep copy:");
                    try 
                    {
                        HashMap<String,Subject> deepCopy = Subject.deepCopy(admin.getAllSubjects());
                        admin.printSubjectList(deepCopy);
                    } catch (CloneNotSupportedException ex) {
                        System.err.println("error:"+ex);
                    }
                    System.out.println("2. Shallow Copy:");
                    HashMap<String,Subject> shallowCopy3 = Subject.shallowCopy(admin.getAllSubjects());
                    admin.printSubjectList(shallowCopy3);
                    press();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nWrong option!\n ");
                    press();
            }
        }
    }
}
