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
public class LecturerException extends Exception 
{
    String lecID;
    public LecturerException(String lecID) 
    {
        this.lecID=lecID;
    }
    @Override
    public String toString()
    {
        return "Lecturer ID mus have the pattern LECxxxxU (“LEC” followed by 4 digits followed by “U” )";
    }
    
}
