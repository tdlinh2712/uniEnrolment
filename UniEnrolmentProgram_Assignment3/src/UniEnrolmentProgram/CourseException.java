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
public class CourseException extends Exception 
{
    long ID;

    public CourseException(int i) 
    {
        ID=i;
    }
    
    public String toString()
    {
        return "Invalid ID. A new ID of "+ID+" has been generated.";
    }
    
}
