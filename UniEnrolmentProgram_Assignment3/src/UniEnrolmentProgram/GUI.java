/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import javax.swing.JFrame;

/**
 *
 * @author linh
 */
public class GUI 
{
    UniversityAdmin admin;
    public GUI(UniversityAdmin admin)
    {
        this.admin=admin;
    }
    
    public void startMenu()
    {
        Login login = new Login(admin);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setSize(500, 500);
        login.setVisible(true);
    }
}
