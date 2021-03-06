/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniturepos;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import sessionbean.ocrm.MemberEntity;
import sessionbean.ocrm.StoreEntity;
import sessionbean.ocrm.TransactionEntity;
import sessionbean.ocrm.TransactionItemEntity;

/**
 *
 * @author hangsun
 */
public class CreditCard extends javax.swing.JFrame {

    private String POSid = null;
    private String partnerPoleDisplayCOMPort = "COM4";
    private OutputStream partnerPoleDisplayOutputStream;
    private SerialPort serialPort;
    private Long transactionId = null;
    private String storeStaffId = null;
    private Double totalPrice = null;
    private Double actualTotalPrice = null;
    private Double totalMemberPrice = null;
    private Long memberId = null;
    private Double currentPoints = null;
    private CheckOut checkOut = null;
    private TransactionEntity transaction = null;
    private Double pointsEarned = null;
    private Boolean startCreditCardMSR = false;

    /**
     * Creates new form CheckOut
     */
    public CreditCard() {
        initComponents();
    }

    public CreditCard(String POSid, String storeStaffId, Long transactionId, Double actualTotalPrice, CheckOut checkOut) {
        this();

        this.POSid = POSid;
        this.storeStaffId = storeStaffId;
        this.transactionId = transactionId;
        this.actualTotalPrice = actualTotalPrice;
        this.checkOut = checkOut;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jLabelTotalPrice = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jButtonGoBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField2 = new javax.swing.JTextField();
        jComboBoxEventBonus = new javax.swing.JComboBox();
        jLabelBase = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanelTitle.setBackground(new java.awt.Color(255, 51, 51));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(899, 119));

        jLabelTitle.setFont(new java.awt.Font("New Peninim MT", 3, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("     Island Furniture POS");
        jLabelTitle.setToolTipText("");
        jLabelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMain.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTotal.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelTotal.setText("Total Price :(S$)");

        jLabelTotalPrice.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelTotalPrice.setText("Total Price");

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/singapore-logo0.1.jpg"))); // NOI18N

        jButtonGoBack.setBackground(new java.awt.Color(255, 255, 255));
        jButtonGoBack.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonGoBack.setText("Go Back");
        jButtonGoBack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonGoBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoBackActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBoxEventBonus.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jComboBoxEventBonus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "10" }));

        jLabelBase.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelBase.setText("Mutiple Point Base:");

        jLabel1.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabel1.setText("Credit Card: ");

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(597, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jButtonGoBack, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(241, 241, 241))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBase)
                                    .addComponent(jLabelTotal))
                                .addGap(77, 77, 77)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTotalPrice)
                                    .addComponent(jComboBoxEventBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(159, 159, 159))))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGap(37, 37, 37)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEventBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBase))
                .addGap(33, 33, 33)
                .addComponent(jButtonGoBack)
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
            .addComponent(jPanelMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:    

        jScrollPane2.setVisible(false);
//        jTextField2.setVisible(false);
        jLabelTotalPrice.setText(String.valueOf(actualTotalPrice));
        transaction = getTransactionById(transactionId);
        totalPrice = transaction.getTotalPrice();
        totalMemberPrice = transaction.getTotalMemberPrice();

        if (transaction.getMember() != null) {
            memberId = transaction.getMember().getMemberId();
            currentPoints = transaction.getMember().getCurrentPoints();
        }else{
           jLabelBase.setVisible(false);
           jComboBoxEventBonus.setVisible(false);
        }

        jTextField2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String kbValue = jTextField2.getText();

                if (kbValue != null && kbValue.trim().length() > 0) {
                    kbValue = kbValue.trim();
                    JOptionPane.showMessageDialog(null, "Successful!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                    checkOut();

                }
            }
        });

    }//GEN-LAST:event_formWindowOpened

    private void jButtonGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoBackActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        this.dispose();
        checkOut.setLocationRelativeTo(null);
        checkOut.setVisible(true);
        checkOut.setExtendedState(JFrame.NORMAL);
    }//GEN-LAST:event_jButtonGoBackActionPerformed

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

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        String kbValue = jTextField2.getText();
        
        if(!startCreditCardMSR)
        {
            if(kbValue != null && kbValue.trim().length() > 0)
            {
                kbValue = kbValue.trim();

                if(kbValue.length() >= 2)
                {
                    if(kbValue.startsWith("%B"))
                    {
                        startCreditCardMSR = true;
                    }
                }
            }
        }
        else
        {
            if(kbValue != null && kbValue.trim().length() > 0)
            {
                kbValue = kbValue.trim();

                if(kbValue.length() >= 2)
                {
                    if(kbValue.endsWith("?"))
                    {
                        startCreditCardMSR = false;
                        JOptionPane.showMessageDialog(null, "Successful!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                        checkOut();
                        //JOptionPane.showMessageDialog(null, kbValue, "Detected Credit Card MSR Input", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
    }//GEN-LAST:event_jTextField2KeyReleased

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
            java.util.logging.Logger.getLogger(CreditCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreditCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreditCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreditCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreditCard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGoBack;
    private javax.swing.JComboBox jComboBoxEventBonus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBase;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotalPrice;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void checkOut() {
        setTendered(transactionId,actualTotalPrice);
        caculateChange(transactionId, 0D);
        inventoryMovement(transactionId);
        if (memberId != null) {
            MemberEntity member = getMemberById(memberId);
            Double base = Double.parseDouble((String)jComboBoxEventBonus.getSelectedItem());
            try {
                Calendar birthday = fromXMLGregorianCalendar(member.getBirthday());
                Calendar today = Calendar.getInstance();

                if (birthday.get(Calendar.YEAR) == today.get(Calendar.YEAR) && birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                        && birthday.get(Calendar.DATE) == today.get(Calendar.DATE)) {
                    pointsEarned = actualTotalPrice * 2 * base;
                    addNewPointsForMember(pointsEarned, memberId);
                    JOptionPane.showMessageDialog(this, "Birthday, double points", "Happy Birthday", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    pointsEarned = actualTotalPrice * base;
                    addNewPointsForMember(pointsEarned, memberId);
                }
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(Cash.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        PartnerThermalPrinterAndCashBox();

        checkOut.closePort();
        checkOut.dispose();
        this.setVisible(false);
        this.dispose();
        Change change = new Change(POSid, storeStaffId, 0D);
        change.setLocationRelativeTo(null);
        change.setVisible(true);
        change.setExtendedState(JFrame.NORMAL);
    } 

    private void PartnerThermalPrinterAndCashBox() {
        Double margin = 1.0;
        Integer lines = 8;
        DecimalFormat df = new DecimalFormat("0.00");
        
        //title
        String printData = "";
        StoreEntity store = getStoreByStaffId(storeStaffId);
        String title = "                          Island Furniture\n";
        String storeId = String.valueOf(store.getStoreId());
        String storeAddress = store.getAddress();
        String[] address = storeAddress.split(", ");  
        String storeContact = store.getContact();
        String line = "===========================================";
        printData = printData + title + "\nStore ID: " + storeId + "\n";
                             
        for(int i = 0;i < address.length;i++){
            printData = printData + address[i] + "\n";
        }
        
        printData = printData+ "Tel: " + storeContact + "\n\n" + line + "\n";

        //transaction detail
        TransactionEntity transaction = getTransactionById(transactionId);
        XMLGregorianCalendar generateTime = transaction.getGenerateTime();
        String formatTime = "Date: " + generateTime.getDay() + "/" + (generateTime.getMonth() + 1)
                + "/" + generateTime.getYear() + "\n" +
                "Time: " + generateTime.getHour() + ":"
                + generateTime.getMinute() + ":" + generateTime.getSecond();
        String TransactionId = String.valueOf(transactionId);
        String transactionType = "Product (Furniture)";
        String cashier = getFullNameById(storeStaffId);
        String counter = POSid;
        printData = printData + "\n" + formatTime + "\nTransaction ID: " + TransactionId + "\nCashier: " + cashier + "\nCounter: " + counter + "\nTransaction Type: " + transactionType + "\n\n" + line + "\n\n";

        //transaction 
        String TransactionTitle = "Item ID      Item Name"+   "\n"+
                "(Member Price)Price * Amount = \n                  (Member Total Price)Total Price\n\n";
        printData = printData + TransactionTitle + line + "\n\n";
        List<TransactionItemEntity> transactionList = getTransactionItemList(transactionId);
        for (TransactionItemEntity ti : transactionList) {
            String itemId = String.valueOf(ti.getItemId());
            String itemName = ti.getItemName();
            String amount = String.valueOf(ti.getAmount());
            String unitPrice = String.valueOf(df.format(ti.getUnitPrice()));
            String itemTotalPrice = String.valueOf(df.format(ti.getTotalPrice()));
            //product, member price

            printData = printData + "" + itemId + "   " + itemName + "\n"
                    + "                       S$" + unitPrice + " * " + amount + " = " + " S$" + itemTotalPrice + "\n";
        }

        printData = printData + "\n" + line + "\n\n";

        //price
        String TotalPrice = String.valueOf(df.format(totalPrice));
        String TotalMemberPrice = String.valueOf(df.format(totalMemberPrice));
        String ActualPrice = String.valueOf(df.format(actualTotalPrice));
        String save = String.valueOf(df.format(totalPrice - totalMemberPrice));
        String Tendered = String.valueOf(df.format(actualTotalPrice));
        String MoneyChange = String.valueOf(df.format(0D));
        printData = printData + "Total Price:                              S$" + TotalPrice;
        if (transaction.getMember() != null) {
        printData = printData + "\nTotal Price After Redemption:       S$" + ActualPrice;
        }
        printData = printData + "\n\n"
                              + line + "\n\n"
                              + "Payment: Credit Card\n"
                              + "Tendered:                                S$" + Tendered + "\n"
                              + "Money Change:                         S$" + MoneyChange + "\n\n" + line + "\n\n";

        //member
         if (transaction.getMember() != null) {
            String MemberId = String.valueOf(memberId);
            String CurrentPoints = String.valueOf(df.format(currentPoints));
            String PointsEarned = String.valueOf(df.format(pointsEarned));
            printData = printData + "Member ID: " + MemberId
                    + "\nPoints Earned: " + PointsEarned
                    + "\nCurrent Points: " + CurrentPoints
                    + "\nYou Have Saved S$" + save + "!" + "\n\n"
                    + line + "\n\n";
        } else {
            printData = printData + "             Sign Up As A New Member\n"
                    + "                           &\n"
                    + "                Get Redemption Now!\n"
                   + "\n\n" + line + "\n\n";
       }

        //thank you
        String thankyou = "                     THANK YOU\n"
                + "                PLEASE COME AGAIN\n\n";
        printData = printData + thankyou + line + "\n\n";

        jTextArea1.setText(printData);
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PageFormat pageFormat = printerJob.defaultPage();
        Paper paper = new Paper();
        paper.setSize(180.0, (double) (paper.getHeight() + lines * 10.0));
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
        pageFormat.setPaper(paper);
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        printerJob.setPrintable(jTextArea1.getPrintable(null, null), pageFormat);
        try {
            printerJob.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Cash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static java.util.List<sessionbean.ocrm.TransactionItemEntity> getTransactionItemList(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionItemList(arg0);
    }

    private static void deleteUnfinishedTransaction(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.deleteUnfinishedTransaction(arg0);
    }

    private static StoreEntity getStoreByStaffId(java.lang.String storeStaffId) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getStoreByStaffId(storeStaffId);
    }

    private static TransactionEntity getTransactionById(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionById(arg0);
    }

    private static String getFullNameById(java.lang.String id) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        return port.getFullNameById(id);
    }

    private static java.util.List<sessionbean.ocrm.TransactionItemEntity> getTransactionItemList_1(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.getTransactionItemList(arg0);
    }

    private static void redemption(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        port.redemption(arg0, arg1);
    }

    private static void addNewPointsForMember(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        port.addNewPointsForMember(arg0, arg1);
    }

    private static Double caculateRedemption(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        return port.caculateRedemption(arg0, arg1);
    }

    private static void caculateChange(java.lang.Long transactionId, java.lang.Double moneyChange) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.caculateChange(transactionId, moneyChange);
    }

    private static MemberEntity getMemberById(java.lang.Long arg0) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        return port.getMemberById(arg0);
    }

    private static Calendar fromXMLGregorianCalendar(XMLGregorianCalendar xc)
            throws DatatypeConfigurationException {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(xc.toGregorianCalendar().getTimeInMillis());
        return c;
    }

    private static void setTendered(java.lang.Long transactionId, java.lang.Double tendered) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.setTendered(transactionId, tendered);
    }

    private static void inventoryMovement(java.lang.Long arg0) {
        sessionbean.ocrm.TransactionModuleService service = new sessionbean.ocrm.TransactionModuleService();
        sessionbean.ocrm.TransactionModule port = service.getTransactionModulePort();
        port.inventoryMovement(arg0);
    }

}
