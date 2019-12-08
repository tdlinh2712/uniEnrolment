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
public class StudentException extends Exception {

    long ID;
    public StudentException(long id) 
    {
        this.ID=id;
    }
    
    public String toString()
    {
        return "Invalid ID. A new ID "+ID+" has been generated.";
    }
    
}
