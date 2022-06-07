/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
/**
 *
 * @author nunos
 */
public class ServerClientMainWindow extends javax.swing.JFrame {
    int userId;
    JFrame loginW;
    final List<WeatherStation> StationList;
    final List<Integer> ConnectedFarmerClients;
    
    
    /**
     * Creates new form ServerClientMainWindow
     */
    public ServerClientMainWindow(JFrame loginW, int userId, List<WeatherStation> StationList, List<Integer> ConnectedFarmerClients) {
        this.loginW = loginW;
        this.userId = userId;
        this.StationList = StationList;
        this.ConnectedFarmerClients = ConnectedFarmerClients;
        
        initComponents();
        this.setLocationRelativeTo(null);

        InternalFrameUI ifu = this.jInternalFrame1.getUI();
        ((BasicInternalFrameUI)ifu).setNorthPane(null);
        
        Timer timer = new Timer();
        timer.schedule(new recorsiveUpdateConnectedClients(), 0, 5000);
    }

    
    /**
     * Class to be runned each recursively that updates the number of connected clients/weather stations connected
     */
    class recorsiveUpdateConnectedClients extends TimerTask {
        public recorsiveUpdateConnectedClients() {}

        public void run() {
            LBLClientsConnected.setText(String.valueOf(ConnectedFarmerClients.size()));
            LBLWeatherStationsConnected.setText(String.valueOf(StationList.size()));
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    // Checks if any iframe is already created
    private boolean iframeCreated = false;
    
    
    /**
     * Changes the current frame (menu option)
     * 
     * @param  panelToShow  Panel to show on the main iframe
     */
    private void changesFrame(JPanel panelToShow) {
        if (this.iframeCreated) {
            this.jInternalFrame1.getContentPane().remove(0);
        }
        this.iframeCreated = true;
        this.jInternalFrame1.setLayout(new BorderLayout(0, 0));
        this.jInternalFrame1.getContentPane().add(panelToShow);
        this.jInternalFrame1.validate();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        PNLMenu = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        BTNServerAccounts = new javax.swing.JButton();
        BTNLogs = new javax.swing.JButton();
        BTNWeatherStations = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel2 = new javax.swing.JLabel();
        LBLClientsConnected = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LBLWeatherStationsConnected = new javax.swing.JLabel();
        BTNLogOut = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Server Client");
        jLabel1.setName("LBLTitle"); // NOI18N

        PNLMenu.setBackground(new java.awt.Color(102, 153, 255));
        PNLMenu.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setText("User Accounts");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        BTNServerAccounts.setText("Server Accounts");
        BTNServerAccounts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTNServerAccountsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTNServerAccountsMousePressed(evt);
            }
        });

        BTNLogs.setText("Logs");
        BTNLogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTNLogsMousePressed(evt);
            }
        });

        BTNWeatherStations.setText("Weather Stations");
        BTNWeatherStations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTNWeatherStationsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTNWeatherStationsMousePressed(evt);
            }
        });

        javax.swing.GroupLayout PNLMenuLayout = new javax.swing.GroupLayout(PNLMenu);
        PNLMenu.setLayout(PNLMenuLayout);
        PNLMenuLayout.setHorizontalGroup(
            PNLMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BTNServerAccounts, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
            .addComponent(BTNLogs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
            .addComponent(BTNWeatherStations, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );
        PNLMenuLayout.setVerticalGroup(
            PNLMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNLMenuLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNServerAccounts, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNWeatherStations, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(BTNLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 295, Short.MAX_VALUE))
        );

        jInternalFrame1.setBorder(null);
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(600, 500));
        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Clients online:");

        LBLClientsConnected.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBLClientsConnected.setText("...");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Weather Stations Connected:");

        LBLWeatherStationsConnected.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LBLWeatherStationsConnected.setText("...");

        BTNLogOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BTNLogOut.setText("Log Out");
        BTNLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BTNLogOutMousePressed(evt);
            }
        });
        BTNLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBLWeatherStationsConnected, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBLClientsConnected, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTNLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addComponent(PNLMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(LBLClientsConnected)
                        .addComponent(jLabel3)
                        .addComponent(LBLWeatherStationsConnected))
                    .addComponent(BTNLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PNLMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        changesFrame(new Server_Menu_Users1(false));
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void BTNServerAccountsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNServerAccountsMouseClicked

    }//GEN-LAST:event_BTNServerAccountsMouseClicked

    private void BTNServerAccountsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNServerAccountsMousePressed
        changesFrame(new Server_Menu_Users1(true));
    }//GEN-LAST:event_BTNServerAccountsMousePressed

    private void BTNWeatherStationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNWeatherStationsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNWeatherStationsMouseClicked

    private void BTNWeatherStationsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNWeatherStationsMousePressed
        changesFrame(new Server_Menu_WeatherStations());
    }//GEN-LAST:event_BTNWeatherStationsMousePressed

    private void BTNLogsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNLogsMousePressed
        changesFrame(new Server_Menu_Logs(StationList, ConnectedFarmerClients));
    }//GEN-LAST:event_BTNLogsMousePressed

    private void BTNLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNLogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNLogOutActionPerformed

    private void BTNLogOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTNLogOutMousePressed
        loginW.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTNLogOutMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNLogOut;
    private javax.swing.JButton BTNLogs;
    private javax.swing.JButton BTNServerAccounts;
    private javax.swing.JButton BTNWeatherStations;
    private javax.swing.JLabel LBLClientsConnected;
    private javax.swing.JLabel LBLWeatherStationsConnected;
    private javax.swing.JPanel PNLMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}