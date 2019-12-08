/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UniEnrolmentProgram;

import javax.swing.JOptionPane;

/**
 *
 * @author linh
 */
public class GUIAddSubjectToCourse extends javax.swing.JFrame {
    
    private UniversityAdmin admin;

    /**
     * Creates new customizer GUIAddSubjectToCourse
     */
    public GUIAddSubjectToCourse(UniversityAdmin admin,Long courseID) {
        this.admin=admin;
        initComponents();
        courseID1.setText(courseID+"");
        courseID1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        sjName1 = new javax.swing.JTextField();
        sjNameLabel1 = new javax.swing.JLabel();
        sjCodeLabel1 = new javax.swing.JLabel();
        addSubjectToCourseButton = new javax.swing.JButton();
        courseID1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        sjName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sjName1ActionPerformed(evt);
            }
        });

        sjNameLabel1.setText("Subject Code");

        sjCodeLabel1.setText("Course ID");

        addSubjectToCourseButton.setText("Add");
        addSubjectToCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectToCourseButtonActionPerformed(evt);
            }
        });

        courseID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseID1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 24)); // NOI18N
        jLabel1.setText("Add Subject To Course");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sjCodeLabel1)
                        .addGap(99, 99, 99)
                        .addComponent(courseID1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sjNameLabel1)
                        .addGap(77, 77, 77)
                        .addComponent(sjName1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addSubjectToCourseButton)
                        .addGap(75, 75, 75))
                    .addComponent(jLabel1))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(sjNameLabel1))
                    .addComponent(sjName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sjCodeLabel1))
                .addGap(18, 18, 18)
                .addComponent(addSubjectToCourseButton)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void sjName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sjName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sjName1ActionPerformed

    private void addSubjectToCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectToCourseButtonActionPerformed
        // TODO add your handling code here:
        String sjID = this.sjName1.getText();
        try
        {
            Long courseCode = Long.parseLong(this.courseID1.getText());
            if(admin.addSubjectToCourse(admin.getUsername(), admin.getPassword(), sjID, courseCode))
            {
                JOptionPane.showMessageDialog(null, "Subject added to course!");
                this.hide();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "FAILED to add subject to course! Subject already in course or invalid subject code");
                sjName1.setText("");
            }
        } catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Please enter a number only");
        }
    }//GEN-LAST:event_addSubjectToCourseButtonActionPerformed

    private void courseID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseID1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_courseID1ActionPerformed

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3ComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSubjectToCourseButton;
    private javax.swing.JTextField courseID1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel sjCodeLabel1;
    private javax.swing.JTextField sjName1;
    private javax.swing.JLabel sjNameLabel1;
    // End of variables declaration//GEN-END:variables
}