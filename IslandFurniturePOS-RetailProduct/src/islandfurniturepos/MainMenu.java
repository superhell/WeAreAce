/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniturepos;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import util.login.StoreUserEntity;

/**
 *
 * @author hangsun
 */
public class MainMenu extends javax.swing.JFrame {

    private String POSid = null;
    private String partnerPoleDisplayCOMPort = "COM4";
    private OutputStream partnerPoleDisplayOutputStream;
    private SerialPort serialPort;
    private String storeStaffId = null;
    private int location;
    private Long memberId = null;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    public MainMenu(String POSid, String storeStaffId) {
        this();
        this.POSid = POSid;
        this.storeStaffId = storeStaffId;
    }

    public MainMenu(String POSid, String storeStaffId, Long memberId) {
        this();

        this.POSid = POSid;
        this.storeStaffId = storeStaffId;
        this.memberId = memberId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        javax.swing.JButton jButtonLogout = new javax.swing.JButton();
        javax.swing.JButton jButtonNewTransactionRetailProduct = new javax.swing.JButton();
        jLabelName = new javax.swing.JLabel();
        javax.swing.JButton jButtonMember = new javax.swing.JButton();
        jLabelMemberId = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(644, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        jButtonLogout.setBackground(new java.awt.Color(255, 255, 255));
        jButtonLogout.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonLogout.setText("Logout");
        jButtonLogout.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jButtonNewTransactionRetailProduct.setBackground(new java.awt.Color(255, 255, 255));
        jButtonNewTransactionRetailProduct.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonNewTransactionRetailProduct.setText("Create New Transaction for Retail Product");
        jButtonNewTransactionRetailProduct.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonNewTransactionRetailProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewTransactionRetailProductActionPerformed(evt);
            }
        });

        jLabelName.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jLabelName.setText("jLabelStaffName");
        jLabelName.setToolTipText("");

        jButtonMember.setBackground(new java.awt.Color(255, 255, 255));
        jButtonMember.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonMember.setText("Member Check");
        jButtonMember.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMemberActionPerformed(evt);
            }
        });

        jLabelMemberId.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jLabelMemberId.setText("Member ID");
        jLabelMemberId.setToolTipText("");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/singapore-logo0.1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jButtonMember, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNewTransactionRetailProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMemberId, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogo))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMemberId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addComponent(jButtonNewTransactionRetailProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMember, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanelTitle.setBackground(new java.awt.Color(255, 51, 51));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(647, 119));

        jLabelTitle.setFont(new java.awt.Font("New Peninim MT", 3, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("     Island Furniture POS");
        jLabelTitle.setToolTipText("");
        jLabelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
            .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
            .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTitleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD:IslandFurniturePOS-RetailProduct/src/islandfurniturepos/MainMenu.java
=======
    private void jButtonNewTransactionFurnitureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewTransactionFurnitureActionPerformed
        // TODO add your handling code here:
        if (POSid.substring(0, 1).equals("F")) {
            location = 1;
            NewTransaction transaction = new NewTransaction(POSid, storeStaffId, location, memberId);
            transaction.setVisible(true);
            transaction.setExtendedState(JFrame.NORMAL);
            this.setVisible(false);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "You are not allowed to create this transaction!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonNewTransactionFurnitureActionPerformed

>>>>>>> 0427c1f918685d0ec7f6b47d5ad5c944f4c44f17:IslandFurniturePOS-RetailProduct/src/islandfurniturepos/MainMenu.java
    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        // TODO add your handling code here:
        StoreUserEntity cashier = getCasherById(storeStaffId);   
        Double shift = cashier.getEndCash()-cashier.getBeginCash();
        JOptionPane.showMessageDialog(this, "End Cash: "+ cashier.getEndCash() + " Shift Revenue: " + shift, "Logout Successfully", JOptionPane.INFORMATION_MESSAGE);
        logout(storeStaffId);
        Login login = new Login();
        login.setVisible(true);
        login.setExtendedState(JFrame.NORMAL);
        this.setVisible(false);
        this.dispose();

    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonNewTransactionRetailProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewTransactionRetailProductActionPerformed
        // TODO add your handling code here:
        if (POSid.substring(0, 1).equals("R")) {
            location = 2;
            NewTransaction transaction = new NewTransaction(POSid, storeStaffId, location, memberId);
            transaction.setVisible(true);
            transaction.setExtendedState(JFrame.NORMAL);
            this.setVisible(false);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "You are not allowed to create this transaction!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonNewTransactionRetailProductActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        if (serialPort != null) {
            try {
                byte[] clear = {0x0C};
                partnerPoleDisplayOutputStream.write(clear);
                partnerPoleDisplayOutputStream.close();
                serialPort.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        if (storeStaffId != null) {
            String name = getFullNameById(storeStaffId);
            jLabelName.setText("Have a nice day! " + name);
        } else {
            jLabelName.setText("Please Login");
        }

        if (memberId != null) {
            jLabelMemberId.setText("Member ID: " + memberId);
        } else {
            jLabelMemberId.setText("Member Not Found!");
        }

//        initPartnerPoleDisplay();
//        poleDisplay();
    }//GEN-LAST:event_formWindowOpened

    private void jButtonMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMemberActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);

        Member member = new Member(POSid,storeStaffId);
        member.setVisible(true);
        member.setExtendedState(JFrame.NORMAL);

    }//GEN-LAST:event_jButtonMemberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    private void initPartnerPoleDisplay() {
        Enumeration commPortList = CommPortIdentifier.getPortIdentifiers();

        while (commPortList.hasMoreElements()) {
            CommPortIdentifier commPort = (CommPortIdentifier) commPortList.nextElement();

            if (commPort.getPortType() == CommPortIdentifier.PORT_SERIAL
                    && commPort.getName().equals(partnerPoleDisplayCOMPort)) {
                try {
                    serialPort = (SerialPort) commPort.open("POS", 5000);
                    partnerPoleDisplayOutputStream = serialPort.getOutputStream();
                } catch (PortInUseException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to initialize Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to initialize Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void poleDisplay() {
        byte[] clear = {0x0C};
        byte[] newLine = {0x0A};
        byte[] carriageReturn = {0x0D};
        byte[] message1 = new String("Island Furniture!").getBytes();
        byte[] message2 = new String("Have a Nice Day!").getBytes();

        try {
            partnerPoleDisplayOutputStream.write(clear);
            partnerPoleDisplayOutputStream.write(message1);
            partnerPoleDisplayOutputStream.write(newLine);
            partnerPoleDisplayOutputStream.write(carriageReturn);
            partnerPoleDisplayOutputStream.write(message2);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to write to Partner Pole Display: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelMemberId;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelTitle;
    // End of variables declaration//GEN-END:variables

    private static String getFullNameById(java.lang.String arg0) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        return port.getFullNameById(arg0);
    }

    private static void logout(java.lang.String arg0) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        port.logout(arg0);
    }

    private static StoreUserEntity getCasherById(java.lang.String arg0) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        return port.getCasherById(arg0);
    }

}
