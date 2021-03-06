/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author B-nth
 */
public class ClientInfo extends javax.swing.JFrame {

    /**
     * Creates new form ClientInfo
     */

    ManageDB db = new ManageDB();
    
    public ClientInfo() {
        initComponents();
        txt_IP.setText(GetInfo.loadSeverIP());
        lbl_IPconn.setText("");
        lbl_MAC.setText(GetInfo.getMAC());
        String tmp_name = GetInfo.name_txt.equals("") ? GetInfo.getClientName() : GetInfo.name_txt;
        txt_name.setText(tmp_name);
        txt_detail.setText(GetInfo.detail_txt);
        String tmp_watttype = GetInfo.watt_type.equals("") ?  "0" : GetInfo.watt_type;
        CB_type.setSelectedIndex(Integer.parseInt(tmp_watttype));
        if(!GetInfo.watt.equals("")){
            if(Double.parseDouble(GetInfo.watt) > 0){

                String[] wattvalue = (GetInfo.watt).split("\\.");
                spin_watt.setEnabled(true);
                spin_watt2.setEnabled(true);
                spin_watt.setValue(Integer.parseInt(wattvalue[0]));
                spin_watt2.setValue(Integer.parseInt(wattvalue[1]));
                chk_specwatt.setSelected(true);
                
            }
        }
        Image icon_Image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/green-energy_logo.png"));
        this.setIconImage(icon_Image);
              
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_sv = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_IP = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_detail = new javax.swing.JTextField();
        lbl_MAC = new javax.swing.JLabel();
        lbl_IPconn = new javax.swing.JLabel();
        btn_testIP = new javax.swing.JButton();
        btn_ok = new javax.swing.JButton();
        lbl_type = new javax.swing.JLabel();
        CB_type = new javax.swing.JComboBox();
        spin_watt = new javax.swing.JSpinner();
        chk_specwatt = new javax.swing.JCheckBox();
        spin_watt2 = new javax.swing.JSpinner();
        lbl_dot = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 153, 51));
        setResizable(false);

        lbl_sv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_sv.setText("Server IP         :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("MAC Address       :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name               :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Detail               :");

        txt_IP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_IP.setText("txt_IP");

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_name.setText("txt_name");

        txt_detail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_detail.setText("txt_Detail");

        lbl_MAC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_MAC.setText("lbl_MAC");

        lbl_IPconn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_IPconn.setText("lbl_IPconn");

        btn_testIP.setText("test");
        btn_testIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_testIPActionPerformed(evt);
            }
        });

        btn_ok.setText("OK");
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        lbl_type.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_type.setText("Type of client    :");

        CB_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Desktop (PC)", "Laptop (notebook)", " " }));

        spin_watt.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));
        spin_watt.setEnabled(false);

        chk_specwatt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chk_specwatt.setText("specify watt      :");
        chk_specwatt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_specwattActionPerformed(evt);
            }
        });

        spin_watt2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 99, 1));
        spin_watt2.setEnabled(false);

        lbl_dot.setText(".");
        lbl_dot.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_sv)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chk_specwatt)
                        .addGap(1, 1, 1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_MAC, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CB_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_name)
                            .addComponent(txt_IP)
                            .addComponent(txt_detail)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spin_watt, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_dot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spin_watt2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_testIP, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_IPconn))
                            .addComponent(btn_ok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(60, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_sv)
                    .addComponent(txt_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_testIP)
                    .addComponent(lbl_IPconn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbl_MAC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txt_detail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_type)
                            .addComponent(CB_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spin_watt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chk_specwatt)
                            .addComponent(spin_watt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_dot)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        // check and create database for sever
//        if(db.CheckDB("localhost", "EnergyMonitor")){
//                
//               JOptionPane.showMessageDialog(null, "Database has Exists.");
//                
//        }else{
//        
//            if(db.CreatDB()){
//                if(db.Createtable("localhost", "EnergyMonitor")){
//                    JOptionPane.showMessageDialog(null, "Database has been Create.");
//                }
//            }
//        }
        btn_testIPActionPerformed(evt);
        this.setVisible(false);
        
    }//GEN-LAST:event_btn_okActionPerformed

    private void btn_testIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_testIPActionPerformed
        
        if (db.CheckDB(txt_IP.getText(),"EnergyMonitor")) {
            ImageIcon icon = new ImageIcon(EnergyMonitor.class.getResource("/img/correctlogo.png"));
            lbl_IPconn.setIcon(icon);
            String wattvalue = spin_watt.getValue()+"."+spin_watt2.getValue();
            GetInfo.saveServerIP(txt_IP.getText(),txt_name.getText(),txt_detail.getText(),CB_type.getSelectedIndex()+"",wattvalue);
        } else {
            ImageIcon icon = new ImageIcon(EnergyMonitor.class.getResource("/img/incorrectuse.png"));
            lbl_IPconn.setIcon(icon);
        }
    }//GEN-LAST:event_btn_testIPActionPerformed

    private void chk_specwattActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_specwattActionPerformed
       if(chk_specwatt.isSelected()){
           spin_watt.setEnabled(true);
           spin_watt2.setEnabled(true);
           lbl_dot.setEnabled(true);
       }else{
           spin_watt.setEnabled(false);
           spin_watt2.setEnabled(false);
           lbl_dot.setEnabled(false);
           spin_watt.setValue(0);
           spin_watt2.setValue(0);
       }
    }//GEN-LAST:event_chk_specwattActionPerformed

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
            java.util.logging.Logger.getLogger(ClientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientInfo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CB_type;
    private javax.swing.JButton btn_ok;
    private javax.swing.JButton btn_testIP;
    private javax.swing.JCheckBox chk_specwatt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbl_IPconn;
    private javax.swing.JLabel lbl_MAC;
    private javax.swing.JLabel lbl_dot;
    private javax.swing.JLabel lbl_sv;
    private javax.swing.JLabel lbl_type;
    private javax.swing.JSpinner spin_watt;
    private javax.swing.JSpinner spin_watt2;
    private javax.swing.JTextField txt_IP;
    private javax.swing.JTextField txt_detail;
    private javax.swing.JTextField txt_name;
    // End of variables declaration//GEN-END:variables
}
