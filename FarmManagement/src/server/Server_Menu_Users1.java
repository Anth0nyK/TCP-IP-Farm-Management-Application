/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JOptionPane;

import javax.swing.JButton;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;

import java.util.*;
/**
 *
 * @author nunos
 */

class UsersData {
    String UserId;
    String Username;
}

public class Server_Menu_Users1 extends javax.swing.JPanel {
    private boolean adminAccount = false;
    /**
     * Creates new form Server_Menu_Users1
     */
    public Server_Menu_Users1(boolean adminAccount) {
        initComponents();
        this.PNLUserEdit.setVisible(false);
        this.adminAccount = adminAccount;
        if (!this.adminAccount) {
            this.BTNAddUserAccount.setVisible(false);
        }
        setUsersData();
    }

    TreeMap<Integer, List<String>> usersLst = new java.util.TreeMap<Integer, List<String>>();
    
    private void setUsersData() { //List.of
        this.usersLst = FileHandler.getInstance().getAllUsers(this.adminAccount);
        
        DefaultTableModel model = (DefaultTableModel) this.TBLUsers.getModel();
        model.setNumRows(0);
        for (Map.Entry<Integer, List<String>> user : this.usersLst.entrySet()) {
            String activate = (user.getValue().get(1).equals("1")) ? "ACTIVATE" : "DEACTIVATE";
            model.addRow(new Object[]{user.getValue().get(0), user.getValue().get(2), activate, "Edit"});
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TBLUsers = new javax.swing.JTable();
        PNLUserEdit = new java.awt.Panel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        TXTUsername = new javax.swing.JTextField();
        TXTPassword2 = new javax.swing.JTextField();
        TXTUserId = new javax.swing.JTextField();
        TXTPassword1 = new javax.swing.JTextField();
        BTNDelete = new javax.swing.JButton();
        BTNUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BTNAddUserAccount = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 500));

        TBLUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "UserId", "Username", "Activate", "Edit"
            }
        ));
        TBLUsers.setEnabled(false);
        TBLUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLUsers);

        label1.setText("Username:");

        label2.setText("Password:");

        label3.setText("Repeat Password:");

        label4.setText("UserId:");

        TXTUserId.setEnabled(false);

        BTNDelete.setText("Delete User");
        BTNDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTNDeleteMouseClicked(evt);
            }
        });
        BTNDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNDeleteActionPerformed(evt);
            }
        });

        BTNUpdate.setText("Update");
        BTNUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTNUpdateMouseClicked(evt);
            }
        });

        jLabel1.setText("Leave empty to keep the current password...");

        javax.swing.GroupLayout PNLUserEditLayout = new javax.swing.GroupLayout(PNLUserEdit);
        PNLUserEdit.setLayout(PNLUserEditLayout);
        PNLUserEditLayout.setHorizontalGroup(
            PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNLUserEditLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TXTPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNLUserEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNLUserEditLayout.createSequentialGroup()
                        .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNLUserEditLayout.createSequentialGroup()
                        .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PNLUserEditLayout.createSequentialGroup()
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TXTPassword2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(TXTUsername))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PNLUserEditLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(TXTUserId, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(45, 45, 45))))
        );
        PNLUserEditLayout.setVerticalGroup(
            PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNLUserEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTUserId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNLUserEditLayout.createSequentialGroup()
                        .addComponent(TXTUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TXTPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(PNLUserEditLayout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TXTPassword2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(PNLUserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        BTNAddUserAccount.setText("New User");
        BTNAddUserAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTNAddUserAccountMousePressed(evt);
            }
        });
        BTNAddUserAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAddUserAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(PNLUserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTNAddUserAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNAddUserAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PNLUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TBLUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLUsersMouseClicked
        JTable tableSource = (JTable)evt.getSource();
        int row = tableSource.rowAtPoint(evt.getPoint());
        int column = tableSource.columnAtPoint(evt.getPoint());
        
        int userId = -1;
        switch (column) {
            case 2:
                userId = Integer.parseInt((String)tableSource.getModel().getValueAt(row, 0));
                String action = (String)tableSource.getModel().getValueAt(row, 2);
                activateUserAccount(userId, (action != "ACTIVATE"));
                break;
            case 3:
                PNLUserEdit.setVisible(true);
                this.validate();
                userId = Integer.parseInt((String)tableSource.getModel().getValueAt(row, 0));
                updatePNLUserData(userId);
                break;
            default:
                PNLUserEdit.setVisible(false);
                this.validate();
                break;
        }

        // JOptionPane.showMessageDialog(null, s + " " + (column==3));
    }//GEN-LAST:event_TBLUsersMouseClicked

    private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNDeleteActionPerformed

    private void BTNDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNDeleteMouseClicked
        deleteCurrentUser();
        this.PNLUserEdit.setVisible(false);
    }//GEN-LAST:event_BTNDeleteMouseClicked

    private void BTNUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNUpdateMouseClicked
        if (this.BTNUpdate.getText() == "Update") {
            updateCurrentUserData();
        } else {
            createNewAccount();
        }
    }//GEN-LAST:event_BTNUpdateMouseClicked

    private void BTNAddUserAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddUserAccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNAddUserAccountActionPerformed

    private void BTNAddUserAccountMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNAddUserAccountMousePressed
        clearPNLData();
        this.BTNUpdate.setText("Create");
        this.BTNUpdate.setVisible(true);
        PNLUserEdit.setVisible(true);
        this.validate();
    }//GEN-LAST:event_BTNAddUserAccountMousePressed

    private void clearPNLData() {
        this.TXTUserId.setText("");
        this.TXTUsername.setText("");
        this.BTNDelete.setVisible(false);
        this.BTNUpdate.setVisible(false);
    }
    
    private void updatePNLUserData(int userId) {
        this.TXTUserId.setText(this.usersLst.get(userId).get(0));
        this.TXTUsername.setText(this.usersLst.get(userId).get(2));
        this.TXTPassword1.setText("");
        this.TXTPassword2.setText("");
        this.BTNUpdate.setText("Update");
        this.BTNDelete.setVisible(true);
        this.BTNUpdate.setVisible(true);
    }
    
    private void activateUserAccount(int userId, boolean activate) {
        FileHandler.getInstance().changeUserActiveStatus(this.adminAccount, userId, activate);
        setUsersData();
    }
        
    private void deleteCurrentUser() {
        FileHandler.getInstance().deleteUser(this.adminAccount, Integer.parseInt(this.TXTUserId.getText()));
        setUsersData();
    }
    
    private void updateCurrentUserData() {
        int userId = Integer.parseInt(this.TXTUserId.getText());
        String username = this.TXTUsername.getText();
        String password1 = this.TXTPassword1.getText();
        String password2 = this.TXTPassword2.getText();
        
        if (!(username.length() >= 4)) {
            JOptionPane.showMessageDialog(this, "A username must have at least 4 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password1.isEmpty()) {
            if (!(password1.length() >= 4)) {
                JOptionPane.showMessageDialog(this, "A password must have at least 4 characters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!password1.equals(password2)) {
                JOptionPane.showMessageDialog(this, "Please repeat your password properly.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            FileHandler.getInstance().updateUserData(this.adminAccount, userId, username, password1);
            setUsersData();
            JOptionPane.showMessageDialog(this, "User data sucessfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (Map.Entry<Integer, List<String>> user : this.usersLst.entrySet()) {
            if (user.getKey() != userId) {
                setUsersData();
                if (username.equals(user.getValue().get(2))) {
                    JOptionPane.showMessageDialog(this, "This username already exists.\nPlease choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
        FileHandler.getInstance().updateUserData(this.adminAccount, userId, username, "");
        setUsersData();
        JOptionPane.showMessageDialog(this, "User data sucessfully updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void createNewAccount() {
        String username = this.TXTUsername.getText();
        String password1 = this.TXTPassword1.getText();
        String password2 = this.TXTPassword2.getText();
        
        if (!(username.length() >= 4)) {
            JOptionPane.showMessageDialog(this, "A username must have at least 4 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!(password1.length() >= 4)) {
            JOptionPane.showMessageDialog(this, "A password must have at least 4 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this, "Please repeat your password properly.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
       
        for (Map.Entry<Integer, List<String>> user : this.usersLst.entrySet()) {
            if (username.equals(user.getValue().get(2))) {
                JOptionPane.showMessageDialog(this, "This username already exists.\nPlease choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        int userId = 0;
        if (this.usersLst.size() > 0) {
            userId = Integer.parseInt(usersLst.lastEntry().getValue().get(0)) + 1;
        }
        String code = FileHandler.getInstance().registerAccount(userId, username, password1, true);
        setUsersData();
        JOptionPane.showMessageDialog(this, "User created!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAddUserAccount;
    private javax.swing.JButton BTNDelete;
    private javax.swing.JButton BTNUpdate;
    private java.awt.Panel PNLUserEdit;
    private javax.swing.JTable TBLUsers;
    private javax.swing.JTextField TXTPassword1;
    private javax.swing.JTextField TXTPassword2;
    private javax.swing.JTextField TXTUserId;
    private javax.swing.JTextField TXTUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    // End of variables declaration//GEN-END:variables
}