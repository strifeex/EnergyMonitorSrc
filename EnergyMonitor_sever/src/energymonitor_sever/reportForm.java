/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor_sever;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author B-nth
 */
public class reportForm extends javax.swing.JFrame {

    ManageDB mdb = new ManageDB();
    static String sql = "";

    Object[] data = new Object[0];
    Calendar cal = Calendar.getInstance();
    
    JPanel p = new JPanel();
    GroupLayout layout = new GroupLayout(p);
    
    JDateChooser cld = new JDateChooser(cal.getTime());
    JDateChooser cld2 = new JDateChooser(cal.getTime());
    JLabel lbl_in_p = new JLabel();
    JLabel lbl_blank = new JLabel();
    JLabel lbl_in_p2 = new JLabel();
    JButton btn_ok_p = new JButton("OK");
    NewJPanel np = new NewJPanel();
    
    public void createFilterPanel(){
        
        
        p.setSize(400, this.getHeight()-500);
        p.setLocation(0, 200);
        
        Dimension dime_cld = new Dimension(250, 30);
        cld.setPreferredSize(dime_cld);
        cld2.setPreferredSize(dime_cld);
        cld.setAlignmentX(TOP_ALIGNMENT);
        cld.setAlignmentY(LEFT_ALIGNMENT);
        cld2.setAlignmentX(TOP_ALIGNMENT);
        cld2.setAlignmentY(LEFT_ALIGNMENT);
                
        jPanelR.add(p);
        jPanelR.setComponentZOrder(p, 0);
    
        btn_ok_p.setPreferredSize(dime_cld);
        btn_ok_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed

                String DATE_FORMAT_NOW = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                String str_date1 = sdf.format(cld.getDate());
                String str_date2 = sdf.format(cld2.getDate());
                
                String txt_sql = "select name , detail , sum(lost_min) as lost_min , watt ";
                txt_sql += "from client_info where time  between '"+ str_date1 +" 00:00:00'";
                txt_sql += " and '"+ str_date2 +" 23:59:59' group by MAC";
                reportForm.this.sql = txt_sql;

                p.setVisible(false);
                jToggleView.setSelected(true);
                jToggleViewActionPerformed(null);
            }
        });      
        
        lbl_in_p.setText("From     ");
        lbl_in_p2.setText("To");
        
        //p.add(lbl_blank);
//        p.add(lbl_in_p);
//        p.add(cld);
//        p.add(lbl_in_p2);
//        p.add(cld2);
//        p.add(btn_ok_p);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(p);
        p.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_in_p)
                            .addComponent(lbl_in_p2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cld2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(cld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btn_ok_p, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cld, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(lbl_in_p, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_in_p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cld2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(btn_ok_p, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }
    /**
     * Creates new form reportForm
     */
    public reportForm() {
        initComponents();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setTitle("EnergyMonitor");
        Image icon_Image = Toolkit.getDefaultToolkit().getImage("green-energy-icon-md.png");
        this.setIconImage(icon_Image);
        
        if (mdb.ConnDB("localhost")) {
            System.out.println("connnect");
        } else {
            System.out.println("not   connnect");
        }
        if(mdb.CheckDB("localhost", "EnergyMonitor")){
            System.out.println("connnect");
        }else{
            System.out.println("not   connnect");
        }
        
        this.sql = "select name , detail , sum(lost_min) as lost_min , watt from client_info group by MAC";
        
        jToggleView.setSelected(true);
        jToggleViewActionPerformed(null);

        this.createFilterPanel();
//        np.setLocation(0, 200);
//        jPanelR.add(np);
//        jPanelR.setComponentZOrder(np, 0);
        
        jTable1.setEnabled(false);
        

        
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
        jToggleView = new javax.swing.JToggleButton();
        jToggleFilter = new javax.swing.JToggleButton();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(javax.swing.UIManager.getDefaults().getColor("Button.disabledForeground"));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);

        jSplitPane1.setEnabled(false);
        jSplitPane1.setMaximumSize(new java.awt.Dimension(1255, 1174));
        jSplitPane1.setMinimumSize(new java.awt.Dimension(1255, 1174));

        jPanel_L.setFocusCycleRoot(true);
        jPanel_L.setPreferredSize(new java.awt.Dimension(100, 1172));

        buttonGroup1.add(jToggleView);
        jToggleView.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jToggleView.setText("View");
        jToggleView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleViewActionPerformed(evt);
            }
        });

        buttonGroup1.add(jToggleFilter);
        jToggleFilter.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jToggleFilter.setText("Filter");
        jToggleFilter.setMaximumSize(new java.awt.Dimension(55, 23));
        jToggleFilter.setMinimumSize(new java.awt.Dimension(55, 23));
        jToggleFilter.setPreferredSize(new java.awt.Dimension(55, 23));
        jToggleFilter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleFilterStateChanged(evt);
            }
        });
        jToggleFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleFilterActionPerformed(evt);
            }
        });

        jToggleClientlist.setBackground(new java.awt.Color(0, 153, 153));
        buttonGroup1.add(jToggleClientlist);
        jToggleClientlist.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jToggleClientlist.setText("ClientList");
        jToggleClientlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleClientlistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_LLayout = new javax.swing.GroupLayout(jPanel_L);
        jPanel_L.setLayout(jPanel_LLayout);
        jPanel_LLayout.setHorizontalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleClientlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleFilter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_LLayout.setVerticalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleView, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleClientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jToggleFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(426, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel_L);

        jPanelR.setBackground(new java.awt.Color(0, 0, 0));

        jPanelR_Down.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanelR_DownLayout = new javax.swing.GroupLayout(jPanelR_Down);
        jPanelR_Down.setLayout(jPanelR_DownLayout);
        jPanelR_DownLayout.setHorizontalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelR_DownLayout.setVerticalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        jTabbedPane1.setBackground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane1.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel_graph.setBackground(new java.awt.Color(0, 0, 0));

        jPanel_loading_graph.setBackground(new java.awt.Color(51, 51, 51));

        jLabel_loding_graph.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        jLabel_loding_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/loading15.gif"))); // NOI18N
        jLabel_loding_graph.setMaximumSize(new java.awt.Dimension(150, 100));
        jLabel_loding_graph.setMinimumSize(new java.awt.Dimension(150, 100));
        jLabel_loding_graph.setPreferredSize(new java.awt.Dimension(150, 100));

        javax.swing.GroupLayout jPanel_loading_graphLayout = new javax.swing.GroupLayout(jPanel_loading_graph);
        jPanel_loading_graph.setLayout(jPanel_loading_graphLayout);
        jPanel_loading_graphLayout.setHorizontalGroup(
            jPanel_loading_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loading_graphLayout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(jLabel_loding_graph, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(431, 431, 431))
        );
        jPanel_loading_graphLayout.setVerticalGroup(
            jPanel_loading_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loading_graphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_loding_graph, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
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

        jTabbedPane1.addTab("Chart", jPanel1);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        jPanel_table.setBackground(new java.awt.Color(0, 0, 0));

        jPanel_loading_table.setBackground(new java.awt.Color(51, 51, 51));

        jLabel_load_table.setFont(new java.awt.Font("Trajan Pro", 0, 11)); // NOI18N
        jLabel_load_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/loading15.gif"))); // NOI18N
        jLabel_load_table.setPreferredSize(new java.awt.Dimension(150, 100));

        javax.swing.GroupLayout jPanel_loading_tableLayout = new javax.swing.GroupLayout(jPanel_loading_table);
        jPanel_loading_table.setLayout(jPanel_loading_tableLayout);
        jPanel_loading_tableLayout.setHorizontalGroup(
            jPanel_loading_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_loading_tableLayout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel_load_table, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(426, 426, 426))
        );
        jPanel_loading_tableLayout.setVerticalGroup(
            jPanel_loading_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loading_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_load_table, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel_tableLayout = new javax.swing.GroupLayout(jPanel_table);
        jPanel_table.setLayout(jPanel_tableLayout);
        jPanel_tableLayout.setHorizontalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_tableLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel_tableLayout.setVerticalGroup(
            jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_tableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_loading_table, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_tableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_tableLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addContainerGap()))
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
                .addGroup(jPanelRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanelRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelR_Down, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectView(String str_sql){
               
            String[] Columname = {"No", "Name", "Detail", "losttime (min)", "Unit"};
            DefaultTableModel model = new DefaultTableModel(Columname, 0);
            jTable1.setModel(model);      
            
            ResultSet rs = mdb.getSelectdata(str_sql);
            
            int c = 0;
            try {                 
               
                while (rs.next()) {
                    double hr = Double.parseDouble(rs.getString("lost_min")) / 60;
                    double watt = Double.parseDouble(rs.getString("watt"));
                    double unit = watt / 1000 * hr;
                    model.addRow(data);
                    jTable1.setValueAt(c + 1 + "", c, 0);
                    jTable1.setValueAt(rs.getString("name"), c, 1);
                    jTable1.setValueAt(rs.getString("detail"), c, 2);
                    jTable1.setValueAt(rs.getString("lost_min"), c, 3);
                    jTable1.setValueAt(unit + "", c, 4);
                    c++;
                    //System.out.println(unit+"  : unit \n"+watt+" : watt  \n"+hr+" : hr");
                }

            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
                ex.printStackTrace();
            }
            
            jPanel_loading_table.setVisible(false);
            jScrollPane1.setVisible(true);
            //jPanel_table.setVisible(true);
            
            if(c<=0){
                //jPanel_table.setVisible(false);
                jScrollPane1.setVisible(false);
                jLabel_load_table.setIcon(null);
                jLabel_load_table.setText("                         No Data");
                jPanel_loading_table.setVisible(true);
                
            }
    }
    
    
    private void jToggleViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleViewActionPerformed
        p.setVisible(false);
        //jPanel_table.setEnabled(false);
        jPanel_loading_table.setVisible(true);
                
        if (jToggleView.isSelected()) {

            selectView(this.sql);            
        }
        
    }//GEN-LAST:event_jToggleViewActionPerformed

   
    private void jToggleFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleFilterActionPerformed
            
        //System.out.println(jPanelR.getComponentCount()+"  comp  Oder");
        if(jToggleFilter.isSelected()){
            
            //np.setVisible(true);
            
            p.setVisible(true);
        }else{
            p.setVisible(false);
             //np.setVisible(false);
        }
    }//GEN-LAST:event_jToggleFilterActionPerformed

    private void jToggleClientlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleClientlistActionPerformed

        p.setVisible(false);
        jPanel_loading_table.setVisible(true);
        jScrollPane1.setVisible(false);
        
        if(jToggleClientlist.isSelected()){
            
            String[] Columname = {"No", "Name", "Detail"};
            DefaultTableModel model = new DefaultTableModel(Columname, 0);
            jTable1.setModel(model);
            
            String sql_client = "select name , detail from client_info";
            ResultSet rs = mdb.getSelectdata(sql_client);
            
            int c = 0;
            try {                 
               
                while (rs.next()) {
     
                    model.addRow(data);
                    jTable1.setValueAt(c + 1 + "", c, 0);
                    jTable1.setValueAt(rs.getString("name"), c, 1);
                    jTable1.setValueAt(rs.getString("detail"), c, 2);
                    c++;
                    //System.out.println(unit+"  : unit \n"+watt+" : watt  \n"+hr+" : hr");
                }

            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
                ex.printStackTrace();
            }
            
            jPanel_loading_table.setVisible(false);
            jScrollPane1.setVisible(true);
            //jPanel_table.setVisible(true);
            
            if(c<=0){
                //jPanel_table.setVisible(false);
                jScrollPane1.setVisible(false);
                jLabel_load_table.setIcon(null);
                jLabel_load_table.setText("                         No Data");
                jPanel_loading_table.setVisible(true);
                
            }
            
        }
    }//GEN-LAST:event_jToggleClientlistActionPerformed

    private void jToggleFilterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleFilterStateChanged

    }//GEN-LAST:event_jToggleFilterStateChanged

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelR;
    private javax.swing.JPanel jPanelR_Down;
    private javax.swing.JPanel jPanel_L;
    private javax.swing.JPanel jPanel_graph;
    private javax.swing.JPanel jPanel_loading_graph;
    private javax.swing.JPanel jPanel_loading_table;
    private javax.swing.JPanel jPanel_table;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleClientlist;
    private javax.swing.JToggleButton jToggleFilter;
    public static javax.swing.JToggleButton jToggleView;
    // End of variables declaration//GEN-END:variables
}
