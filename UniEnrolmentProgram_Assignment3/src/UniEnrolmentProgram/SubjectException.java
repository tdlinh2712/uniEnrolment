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
public class SubjectException extends Exception
{
    String ID;
    public SubjectException(String id) 
    {
        ID=id;
    }
    public String toString()
    {
        return "Subject ID must have 4 characters followed by 3 numbers.";
    }
}
