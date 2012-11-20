/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor_sever;


import java.awt.Component;
import java.awt.Dialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author B-nth
 */
public class reportForm extends javax.swing.JFrame {
    ManageDB mdb = new ManageDB();
    String sql = "";
    /**
     * Creates new form reportForm
     */
    public reportForm() {
        initComponents();
        String[] Columname = {"Year","Mount","Day","Date","Hour","Unit"};
        DefaultTableModel model = new DefaultTableModel(Columname,0);
        //jTable1.setModel(model);
        this.setTitle("EnergyMonitor");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.jSplitPane1.setLocation(0, 0);
        mdb.ConnDB("localhost");
        //jPanel_loading_graph.setVisible(false);
        
//        ImageIcon icon = new ImageIcon("black-018-loading-p.gif");
//        JLabel loading_lbl = new JLabel("namebbbb",icon,JLabel.LEFT);
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel_L = new javax.swing.JPanel();
        jToggleDaily = new javax.swing.JToggleButton();
        jToggleWeekly = new javax.swing.JToggleButton();
        jToggleMonthly = new javax.swing.JToggleButton();
        jToggleYearly = new javax.swing.JToggleButton();
        jToggleClientlist = new javax.swing.JToggleButton();
        jPanelR = new javax.swing.JPanel();
        jPanelR_Down = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel_graph = new javax.swing.JPanel();
        jPanel_loading_graph = new javax.swing.JPanel();
        jLabel_loding_graph = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel_table = new javax.swing.JPanel();
        jPanel_loading_table = new javax.swing.JPanel();
        jLabel_load_table = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledForeground"));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jSplitPane1.setMaximumSize(new java.awt.Dimension(1255, 1174));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1255, 1174));

        jPanel_L.setFocusCycleRoot(true);
        jPanel_L.setPreferredSize(new java.awt.Dimension(100, 1172));

        buttonGroup1.add(jToggleDaily);
        jToggleDaily.setText("Daily");
        jToggleDaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleDailyActionPerformed(evt);
            }
        });

        buttonGroup1.add(jToggleWeekly);
        jToggleWeekly.setText("Weekly");
        jToggleWeekly.setMaximumSize(new java.awt.Dimension(55, 23));
        jToggleWeekly.setMinimumSize(new java.awt.Dimension(55, 23));
        jToggleWeekly.setPreferredSize(new java.awt.Dimension(55, 23));

        buttonGroup1.add(jToggleMonthly);
        jToggleMonthly.setText("Monthly");
        jToggleMonthly.setMaximumSize(new java.awt.Dimension(55, 23));
        jToggleMonthly.setMinimumSize(new java.awt.Dimension(55, 23));
        jToggleMonthly.setPreferredSize(new java.awt.Dimension(55, 23));

        buttonGroup1.add(jToggleYearly);
        jToggleYearly.setText("Yearly");
        jToggleYearly.setMaximumSize(new java.awt.Dimension(55, 23));
        jToggleYearly.setMinimumSize(new java.awt.Dimension(55, 23));
        jToggleYearly.setPreferredSize(new java.awt.Dimension(55, 23));

        buttonGroup1.add(jToggleClientlist);
        jToggleClientlist.setText("ClientList");

        javax.swing.GroupLayout jPanel_LLayout = new javax.swing.GroupLayout(jPanel_L);
        jPanel_L.setLayout(jPanel_LLayout);
        jPanel_LLayout.setHorizontalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleClientlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleYearly, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleDaily, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleWeekly, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleMonthly, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_LLayout.setVerticalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleDaily, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleWeekly, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleMonthly, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleYearly, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleClientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel_L);

        jPanelR.setBackground(new java.awt.Color(0, 0, 0));

        jPanelR_Down.setBackground(new java.awt.Color(0, 204, 255));

        javax.swing.GroupLayout jPanelR_DownLayout = new javax.swing.GroupLayout(jPanelR_Down);
        jPanelR_Down.setLayout(jPanelR_DownLayout);
        jPanelR_DownLayout.setHorizontalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelR_DownLayout.setVerticalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel_graph.setBackground(new java.awt.Color(0, 255, 255));

        jLabel_loding_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/black-018-loading-p.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel_loading_graphLayout = new javax.swing.GroupLayout(jPanel_loading_graph);
        jPanel_loading_graph.setLayout(jPanel_loading_graphLayout);
        jPanel_loading_graphLayout.setHorizontalGroup(
            jPanel_loading_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loading_graphLayout.createSequentialGroup()
                .addContainerGap(436, Short.MAX_VALUE)
                .addComponent(jLabel_loding_graph, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(478, 478, 478))
        );
        jPanel_loading_graphLayout.setVerticalGroup(
            jPanel_loading_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_loading_graphLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel_loding_graph, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_graphLayout = new javax.swing.GroupLayout(jPanel_graph);
        jPanel_graph.setLayout(jPanel_graphLayout);
        jPanel_graphLayout.setHorizontalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_graphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_graphLayout.setVerticalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_graphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Graph", jPanel1);

        jPanel_table.setBackground(new java.awt.Color(0, 255, 255));

        jLabel_load_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/black-018-loading-p.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel_loading_tableLayout = new javax.swing.GroupLayout(jPanel_loading_table);
        jPanel_loading_table.setLayout(jPanel_loading_tableLayout);
        jPanel_loading_tableLayout.setHorizontalGroup(
            jPanel_loading_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loading_tableLayout.createSequentialGroup()
                .addContainerGap(425, Short.MAX_VALUE)
                .addComponent(jLabel_load_table, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(482, 482, 482))
        );
        jPanel_loading_tableLayout.setVerticalGroup(
            jPanel_loading_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_loading_tableLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jLabel_load_table, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_tableLayout = new javax.swing.GroupLayout(jPanel_table);
        jPanel_table.setLayout(jPanel_tableLayout);
        jPanel_tableLayout.setHorizontalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_tableLayout.setVerticalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Table", jPanel2);

        javax.swing.GroupLayout jPanelRLayout = new javax.swing.GroupLayout(jPanelR);
        jPanelR.setLayout(jPanelRLayout);
        jPanelRLayout.setHorizontalGroup(
            jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelR_Down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanelRLayout.setVerticalGroup(
            jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelR_Down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanelR);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleDailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleDailyActionPerformed
                
        Calendar cal = Calendar.getInstance();
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String str_date = sdf.format(cal.getTime());
        
        if(jToggleDaily.isSelected()){
            sql ="select * from client_info where time = "+ str_date;
            JOptionPane.showConfirmDialog((Component)
                null, sql, "Alert", JOptionPane.DEFAULT_OPTION);
        }
    }//GEN-LAST:event_jToggleDailyActionPerformed

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
            java.util.logging.Logger.getLogger(reportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel_load_table;
    private javax.swing.JLabel jLabel_loding_graph;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelR;
    private javax.swing.JPanel jPanelR_Down;
    private javax.swing.JPanel jPanel_L;
    private javax.swing.JPanel jPanel_graph;
    private javax.swing.JPanel jPanel_loading_graph;
    private javax.swing.JPanel jPanel_loading_table;
    private javax.swing.JPanel jPanel_table;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleClientlist;
    private javax.swing.JToggleButton jToggleDaily;
    private javax.swing.JToggleButton jToggleMonthly;
    private javax.swing.JToggleButton jToggleWeekly;
    private javax.swing.JToggleButton jToggleYearly;
    // End of variables declaration//GEN-END:variables


}