/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package islandfurniturepos;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
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
import sessionbean.km.ComboItemEntity;
import sessionbean.km.DishItemEntity;
import sessionbean.km.KitchenEntity;
import sessionbean.km.KitchenOrderEntity;
import sessionbean.km.StoreEntity;
import sessionbean.ocrm.MemberEntity;

/**
 *
 * @author hangsun
 */
public class Cash extends javax.swing.JFrame {

    private String POSid = null;
    private String partnerPoleDisplayCOMPort = "COM5";
    private OutputStream partnerPoleDisplayOutputStream;
    private SerialPort serialPort;
    private Long orderId = null;
    private String storeStaffId = null;
    private Double totalPrice = null;
    private Double actualTotalPrice = null;
    private Double totalMemberPrice = null;
    private Long memberId = null;
    private Double currentPoints = null;
    private Double tendered = null;
    private Double pointsEarned = null;
    private CheckOut checkOut = null;
    private KitchenOrderEntity order = null;
    private KitchenEntity kitchen = null;

    /**
     * Creates new form CheckOut
     */
    public Cash() {
        initComponents();
    }

    public Cash(String POSid, String storeStaffId, Long orderId, Double actualTotalPrice, CheckOut checkOut) {
        this();

        this.POSid = POSid;
        this.storeStaffId = storeStaffId;
        this.orderId = orderId;
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

        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jPanelMain = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jLabelTendered = new javax.swing.JLabel();
        jButtonCheckOut = new javax.swing.JButton();
        jLabelTotalPrice = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jButtonGoBack = new javax.swing.JButton();
        jFormattedTextFieldTendered = new javax.swing.JFormattedTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBoxEventBonus = new javax.swing.JComboBox();
        jLabelBase = new javax.swing.JLabel();

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
        jLabelTitle.setText("   Island Furniture POS");
        jLabelTitle.setToolTipText("");
        jLabelTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
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

        jLabelTendered.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelTendered.setText("Tendered :(S$)");

        jButtonCheckOut.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCheckOut.setFont(new java.awt.Font("Times", 3, 18)); // NOI18N
        jButtonCheckOut.setText("Check Out");
        jButtonCheckOut.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckOutActionPerformed(evt);
            }
        });

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

        jFormattedTextFieldTendered.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextFieldTendered.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N

        jScrollPane2.setEnabled(false);
        jScrollPane2.setFont(new java.awt.Font("Lucida Grande", 0, 5)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Lucida Grande", 0, 8)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jComboBoxEventBonus.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jComboBoxEventBonus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5", "10" }));

        jLabelBase.setFont(new java.awt.Font("Times", 3, 14)); // NOI18N
        jLabelBase.setText("Mutiple Point Base:");

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogo))
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelTendered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelBase))
                        .addGap(63, 63, 63)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldTendered, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTotalPrice)
                            .addComponent(jComboBoxEventBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonGoBack, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jLabelLogo)
                .addGap(1, 1, 1)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTendered, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldTendered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxEventBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBase))
                .addGap(50, 50, 50)
                .addComponent(jButtonCheckOut)
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGoBack, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        jScrollPane2.setVisible(false);
        jLabelTotalPrice.setText(String.valueOf(actualTotalPrice));
        order = findOrderById(orderId);
        totalPrice = order.getTotal();
        totalMemberPrice = order.getTotalWithDiscount();
        kitchen = findKitchenByStoreStaffId(storeStaffId);

        if (order.getMember() != null) {
            memberId = order.getMember().getMemberId();
            currentPoints = order.getMember().getCurrentPoints();
        } else {
            jLabelBase.setVisible(false);
            jComboBoxEventBonus.setVisible(false);
        }
//        initPartnerPoleDisplay();
//        poleDisplay();
    }//GEN-LAST:event_formWindowOpened

    private void jButtonCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckOutActionPerformed
        // TODO add your handling code here:
        if (jFormattedTextFieldTendered.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please insert tendered", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            tendered = Double.parseDouble(jFormattedTextFieldTendered.getText());
            if (tendered < actualTotalPrice) {
                JOptionPane.showMessageDialog(this, "Tendered is smaller than total!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                checkOut(orderId, tendered);
                order = findOrderById(orderId);

                jFormattedTextFieldTendered.setText("");

                if (memberId != null) {
                    MemberEntity member = getMemberById(memberId);
                    Double base = Double.parseDouble((String) jComboBoxEventBonus.getSelectedItem());
                    try {
                        Calendar birthday = fromXMLGregorianCalendar(member.getBirthday());
                        Calendar today = Calendar.getInstance();

                        if (birthday.get(Calendar.YEAR) == today.get(Calendar.YEAR) && birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                                && birthday.get(Calendar.DATE) == today.get(Calendar.DATE)) {
                            pointsEarned = actualTotalPrice * 2.0 * base;
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

                updateEndCash(storeStaffId, actualTotalPrice);
                PartnerThermalPrinterAndCashBox();

                closePort();
                this.setVisible(false);
                this.dispose();
                Change change = new Change(POSid, storeStaffId, orderId);
                change.setVisible(true);
                change.setExtendedState(JFrame.NORMAL);
            }
        }


    }//GEN-LAST:event_jButtonCheckOutActionPerformed

    private void jButtonGoBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoBackActionPerformed
        // TODO add your handling code here:
        closePort();
        this.setVisible(false);
        this.dispose();
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
            java.util.logging.Logger.getLogger(Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCheckOut;
    private javax.swing.JButton jButtonGoBack;
    private javax.swing.JComboBox jComboBoxEventBonus;
    private javax.swing.JFormattedTextField jFormattedTextFieldTendered;
    private javax.swing.JLabel jLabelBase;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTendered;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotalPrice;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

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
        byte[] message1 = new String("Make Payment").getBytes();
        byte[] message2 = new String("Cash").getBytes();

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

    private void closePort() {
        System.err.println("Window closed");
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

    }

    private void PartnerThermalPrinterAndCashBox() {
        Double margin = 1.0;
        Integer lines = 8;
        DecimalFormat df = new DecimalFormat("0.00");

        //title
        String printData = "";
        StoreEntity store = kitchen.getStore();
        String title = "                          Island Furniture\n";
        String storeId = String.valueOf(store.getStoreId());
        String storeAddress = store.getAddress();
        String[] address = storeAddress.split(", ");
        String storeContact = store.getContact();
        String line = "===========================================";
        printData = printData + title + "\nStore ID: " + storeId + "\n";

        for (int i = 0; i < address.length; i++) {
            printData = printData + address[i] + "\n";
        }

        printData = printData + "Tel: " + storeContact
                + "\nKitchen ID: " + kitchen.getId() + "\n\n"
                + line + "\n";

        //transaction detail
        XMLGregorianCalendar generateTime = order.getCreationTime();
        String formatTime = "Date: " + generateTime.getDay() + "/" + (generateTime.getMonth() + 1)
                + "/" + generateTime.getYear() + "\n"
                + "Time: " + generateTime.getHour() + ":"
                + generateTime.getMinute() + ":" + generateTime.getSecond();
        String OrderId = String.valueOf(orderId);
        String Type = "Kitchen Order";
        String cashier = getFullNameById(storeStaffId);
        String counter = POSid;
        printData = printData + "\n" + formatTime + "\nOrder ID: " + OrderId + "\nCashier: " + cashier + "\nCounter: " + counter + "\nReceipt Type: " + Type + "\n\n" + line + "\n\n";

        //order
        String OrderTitleCombo = "ID           Name             Price/Quantity/Total\n\n";
        printData = printData + OrderTitleCombo + line + "\n\n";
        //combo
        List<ComboItemEntity> comboList = getComboItemByOrderId(orderId);
        for (ComboItemEntity comboItem : comboList) {
            String itemId = String.valueOf(comboItem.getId());
            String itemName = comboItem.getCombo().getName();
            String amount = String.valueOf(comboItem.getQuantity());
            String unitPrice = String.valueOf(df.format(comboItem.getCombo().getPrice()));
            String itemTotalPrice = String.valueOf(df.format(comboItem.getCombo().getPrice() * comboItem.getQuantity()));

            printData = printData + itemId + "            " + itemName + "\n"
                    + "                    S$" + unitPrice + "   *   " + amount + "   =   "
                    + " S$" + itemTotalPrice + "\n";
        }

        //dish
        List<DishItemEntity> dishList = getDishItemByOrderId(orderId);
        for (DishItemEntity dishItem : dishList) {
            String itemId = String.valueOf(dishItem.getId());
            String itemName = dishItem.getDish().getName();
            String amount = String.valueOf(dishItem.getQuantity());
            String unitPrice = String.valueOf(df.format(dishItem.getDish().getPrice()));
            String itemTotalPrice = String.valueOf(df.format(dishItem.getDish().getPrice() * dishItem.getQuantity()));

            printData = printData + itemId + "            " + itemName + "\n"
                    + "                   S$" + unitPrice + "   *   " + amount + "   =   "
                    + " S$" + itemTotalPrice + "\n";
        }

        printData = printData + "\n" + line + "\n\n";

        //price
        String TotalPrice = String.valueOf(df.format(totalPrice));
        String TotalMemberPrice = String.valueOf(df.format(totalMemberPrice));
        String ActualPrice = String.valueOf(df.format(actualTotalPrice));
        String save = String.valueOf(df.format(totalPrice - actualTotalPrice));
        String Tendered = String.valueOf(df.format(order.getReceived()));
        String MoneyChange = String.valueOf(df.format(order.getDue()));
        printData = printData + "Total Price:                                S$" + TotalPrice ;
        if (order.getMember() != null) {
            printData = printData + "\nTotal Member Price:                     S$" + TotalMemberPrice;
        }
                printData = printData +  "\n\n"
                + line + "\n\n"
                + "Payment: Cash\n"
                + "Tendered:                                 S$" + Tendered + "\n"
                + "Money Change:                           S$" + MoneyChange + "\n\n" + line + "\n\n";

        //member
        if (order.getMember() != null) {
            String MemberId = String.valueOf(memberId);
            String CurrentPoints = String.valueOf(df.format(currentPoints));
            String PointsEarned = String.valueOf(df.format(pointsEarned));
            printData = printData + "Member ID: " + MemberId
                    + "\nPoints Earned: " + PointsEarned
                    + "\nCurrent Points: " + CurrentPoints
                    + "\nYou Have Saved S$" + save + "!" + "\n\n"
                    + line + "\n\n";
        } else {
            printData = printData + "       You may get up to 50% discount\n"
                    + "         if you join our membership!"
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

    private static void updateEndCash(java.lang.String arg0, java.lang.Double arg1) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        port.updateEndCash(arg0, arg1);
    }

    private static KitchenOrderEntity findOrderById(java.lang.Long arg0) {
        sessionbean.km.CustomerOrderFulfillmentModuleService service = new sessionbean.km.CustomerOrderFulfillmentModuleService();
        sessionbean.km.CustomerOrderFulfillmentModule port = service.getCustomerOrderFulfillmentModulePort();
        return port.findOrderById(arg0);
    }

    private static Double checkOut(java.lang.Long orderId, java.lang.Double received) {
        sessionbean.km.CustomerOrderFulfillmentModuleService service = new sessionbean.km.CustomerOrderFulfillmentModuleService();
        sessionbean.km.CustomerOrderFulfillmentModule port = service.getCustomerOrderFulfillmentModulePort();
        return port.checkOut(orderId, received);
    }

    private static void addNewPointsForMember(java.lang.Double arg0, java.lang.Long arg1) {
        sessionbean.ocrm.MemberRegistrationModuleService service = new sessionbean.ocrm.MemberRegistrationModuleService();
        sessionbean.ocrm.MemberRegistrationModule port = service.getMemberRegistrationModulePort();
        port.addNewPointsForMember(arg0, arg1);
    }

    private static String getFullNameById(java.lang.String userId) {
        util.login.IFManagerBeanService service = new util.login.IFManagerBeanService();
        util.login.IFManagerBean port = service.getIFManagerBeanPort();
        return port.getFullNameById(userId);
    }

    private static KitchenEntity findKitchenByStoreStaffId(java.lang.String arg0) {
        sessionbean.km.CustomerOrderFulfillmentModuleService service = new sessionbean.km.CustomerOrderFulfillmentModuleService();
        sessionbean.km.CustomerOrderFulfillmentModule port = service.getCustomerOrderFulfillmentModulePort();
        return port.findKitchenByStoreStaffId(arg0);
    }

    private static java.util.List<sessionbean.km.ComboItemEntity> getComboItemByOrderId(java.lang.Long arg0) {
        sessionbean.km.CustomerOrderFulfillmentModuleService service = new sessionbean.km.CustomerOrderFulfillmentModuleService();
        sessionbean.km.CustomerOrderFulfillmentModule port = service.getCustomerOrderFulfillmentModulePort();
        return port.getComboItemByOrderId(arg0);
    }

    private static java.util.List<sessionbean.km.DishItemEntity> getDishItemByOrderId(java.lang.Long arg0) {
        sessionbean.km.CustomerOrderFulfillmentModuleService service = new sessionbean.km.CustomerOrderFulfillmentModuleService();
        sessionbean.km.CustomerOrderFulfillmentModule port = service.getCustomerOrderFulfillmentModulePort();
        return port.getDishItemByOrderId(arg0);
    }

}
