/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor_sever;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

/**
 *
 * @author B-nth
 */
public class reportForm extends javax.swing.JFrame {

    ManageDB mdb = new ManageDB();
    static String sql = "";
    static String sqlClient = "";
    static String sqlBargraph = "";
    static String sqlClientOnline = "";
    static String sqlClientOnlineBar = "";

    public int sum_onlineTime = 0;
    public int sum_wasteTime = 0;
    
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
        
        JPanel jPanelBorder = new javax.swing.JPanel();
        jPanelBorder.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
        
        p.setSize(400, this.getHeight()-500);
        p.setLocation(0,0);
                
        jPanelR.add(p);
        jPanelR.setComponentZOrder(p, 0);
    
        btn_ok_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed

                String DATE_FORMAT_NOW = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                String str_date1 = sdf.format(cld.getDate());
                String str_date2 = sdf.format(cld2.getDate());
                
                String txt_sql = "select MAC , name , detail , sum(lost_min) as lost_min , watt ";
                txt_sql += "from client_info where time  between '"+ str_date1 +" 00:00:00'";
                txt_sql += " and '"+ str_date2 +" 23:59:59' group by MAC";
                reportForm.this.sql = txt_sql;
                              
                txt_sql = "select MAC , name , detail , sum(online_min) as online_min ";
                txt_sql += "from client_online where time  between '"+ str_date1 +" 00:00:00'";
                txt_sql += " and '"+ str_date2 +" 23:59:59' group by MAC";
                reportForm.this.sqlClientOnline = txt_sql;
                 
                reportForm.this.sqlClient = "select MAC ,name , detail from client_info where time between '"+str_date1 +" 00:00:00'";
                reportForm.this.sqlClient += " and '"+ str_date2 +" 23:59:59' group by MAC";
                
                reportForm.this.sqlBargraph = "select start_time , lost_min , DATE_FORMAT(start_time,'%H') as hr "
                        + ", DATE_FORMAT(start_time,'%i') as min "
                        + "from client_info where time between '"+str_date1 +" 00:00:00'"
                        +" and '"+ str_date2 +" 23:59:59'"+" ";
 
                reportForm.this.sqlClientOnlineBar = "select start_time , online_min , DATE_FORMAT(start_time,'%H') as hr "
                        + ", DATE_FORMAT(start_time,'%i') as min "
                        + "from client_online where time between '"+str_date1 +" 00:00:00'"
                        +" and '"+ str_date2 +" 23:59:59'"+" ";
                
                
                lbl_date.setText(reportForm.this.getfomatDate(cld.getDate(), cld2.getDate()));
                //System.out.println(reportForm.this.sql);
                //System.out.println(sqlBargraph);
                reportForm.this.selectView(reportForm.this.sql,reportForm.this.sqlClientOnline);

                p.setVisible(false);
                jToggleView.setSelected(true);
                
            }
        });      
  
        lbl_in_p.setText("From     ");
        lbl_in_p2.setText("To");
        
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanelBorder);
        jPanelBorder.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_in_p, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_in_p2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cld2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cld, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btn_ok_p, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cld, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_in_p, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_in_p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cld2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btn_ok_p, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(p);
        p.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanelBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
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
        
        //disable downPanel
        this.jPanel_Dn_1.setVisible(false);
        this.jPanel_Dn_2.setVisible(false);
        this.lbl_date.setVisible(false);
        this.lbl_title_User.setVisible(false);
        
        if (mdb.ConnDB("localhost")) {
            System.out.println("connnect");        
            if (mdb.CheckDB("localhost", "EnergyMonitor")) {
                System.out.println("Databse coorect");
            } else {
                mdb.CreatDB();
                mdb.Createtable("localhost", "EnergyMonitor");
                mdb.setMysqlPrivileges();
                System.out.println("no Database");
            }
        } else {
            System.out.println("not  connnect");
        }
        
        
        //sql for view dataTable Clientlist
        String txt_sql = "select MAC , name , detail , sum(lost_min) as lost_min , watt ";
        txt_sql += "from client_info where time  between  DATE_FORMAT(CURDATE(),'%Y-%m-%d 00:00:00') ";
        txt_sql += " and  DATE_FORMAT(CURDATE(),'%Y-%m-%d 23:59:59') group by MAC";
        reportForm.this.sql = txt_sql;

        txt_sql = "select MAC , name , detail , sum(online_min) as online_min ";
        txt_sql += "from client_online where time  between DATE_FORMAT(CURDATE(),'%Y-%m-%d 00:00:00') ";
        txt_sql += " and DATE_FORMAT(CURDATE(),'%Y-%m-%d 23:59:59') group by MAC";
        reportForm.this.sqlClientOnline = txt_sql;
                
          
        // sql  for clientlist dataTable  Piechart
        this.sqlClient = "select name , detail from client_info group by MAC";
        // sql for view BarChart
        this.sqlBargraph = "select start_time , lost_min , DATE_FORMAT(start_time,'%H') as hr , DATE_FORMAT(start_time,'%i') as min from client_info";
        
        //this.mdb.setMysqlPrivileges();
        //jToggleView.setSelected(true);
        //jToggleViewActionPerformed(null);

        String DATE_FORMAT_NOW = "dd  MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        
        lbl_date.setText(sdf.format(cal.getTime()));
        this.createFilterPanel();
        
        jTable1.setEnabled(false);
        p.setVisible(false);
        
        //this.selectView(sql);
        

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
        jToggleClientlist = new javax.swing.JToggleButton();
        btn_setting = new javax.swing.JButton();
        jPanelR = new javax.swing.JPanel();
        jPanelR_Down = new javax.swing.JPanel();
        jPanel_Dn_1 = new javax.swing.JPanel();
        lbl_cost = new javax.swing.JLabel();
        txt_cost_bath = new javax.swing.JLabel();
        txt_Sum_unit = new javax.swing.JTextField();
        lbl_unit_unit = new javax.swing.JLabel();
        lbl_losttime_unit = new javax.swing.JLabel();
        txt_Sum_lostTime = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        lbl_Sum_unit = new javax.swing.JLabel();
        lbl_sum_lostTime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_coal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jPanel_Dn_2 = new javax.swing.JPanel();
        lbl_curr_user_unit = new javax.swing.JLabel();
        lbl_amount_user_unit = new javax.swing.JLabel();
        lbl_curr_user = new javax.swing.JLabel();
        txt_amount_user = new javax.swing.JTextField();
        txt_curr_user = new javax.swing.JTextField();
        lbl_amount_user = new javax.swing.JLabel();
        lbl_title_User = new javax.swing.JLabel();
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

        jToggleView.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup1.add(jToggleView);
        jToggleView.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jToggleView.setText("View");
        jToggleView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleViewActionPerformed(evt);
            }
        });

        jToggleClientlist.setBackground(new java.awt.Color(0, 204, 204));
        buttonGroup1.add(jToggleClientlist);
        jToggleClientlist.setFont(new java.awt.Font("Trajan Pro", 1, 12)); // NOI18N
        jToggleClientlist.setText("ClientList");
        jToggleClientlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleClientlistActionPerformed(evt);
            }
        });

        btn_setting.setBackground(new java.awt.Color(0, 204, 51));
        btn_setting.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_setting.setText("Setting");
        btn_setting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_settingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_LLayout = new javax.swing.GroupLayout(jPanel_L);
        jPanel_L.setLayout(jPanel_LLayout);
        jPanel_LLayout.setHorizontalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_setting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleClientlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_LLayout.setVerticalGroup(
            jPanel_LLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_LLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleView, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleClientlist, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btn_setting, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(369, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel_L);

        jPanelR.setBackground(new java.awt.Color(0, 0, 0));

        jPanelR_Down.setBackground(new java.awt.Color(0, 153, 153));

        jPanel_Dn_1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel_Dn_1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_cost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_cost.setText("value                  :");

        txt_cost_bath.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_cost_bath.setText("THB");

        txt_Sum_unit.setEditable(false);
        txt_Sum_unit.setBackground(new java.awt.Color(153, 255, 153));
        txt_Sum_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Sum_unit.setText("jTextField1");

        lbl_unit_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_unit_unit.setText("unit");

        lbl_losttime_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_losttime_unit.setText("minute");

        txt_Sum_lostTime.setEditable(false);
        txt_Sum_lostTime.setBackground(new java.awt.Color(153, 255, 153));
        txt_Sum_lostTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_Sum_lostTime.setText("jTextField1");

        txt_cost.setEditable(false);
        txt_cost.setBackground(new java.awt.Color(153, 255, 153));
        txt_cost.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_cost.setText("jTextField1");

        lbl_Sum_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_Sum_unit.setText("energy consumed  :");

        lbl_sum_lostTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_sum_lostTime.setText("waste time           :");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("coal-burning");

        txt_coal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_coal.setText("jTextField1");
        txt_coal.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("kg");

        javax.swing.GroupLayout jPanel_Dn_1Layout = new javax.swing.GroupLayout(jPanel_Dn_1);
        jPanel_Dn_1.setLayout(jPanel_Dn_1Layout);
        jPanel_Dn_1Layout.setHorizontalGroup(
            jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Dn_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_cost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_Sum_unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_sum_lostTime))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_Sum_lostTime)
                        .addComponent(txt_Sum_unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cost_bath)
                    .addGroup(jPanel_Dn_1Layout.createSequentialGroup()
                        .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_unit_unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_losttime_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txt_coal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Dn_1Layout.setVerticalGroup(
            jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Dn_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_sum_lostTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Sum_lostTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_losttime_unit)
                    .addComponent(jLabel1)
                    .addComponent(txt_coal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_Sum_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Sum_unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_unit_unit))
                .addGap(18, 18, 18)
                .addGroup(jPanel_Dn_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_cost)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cost_bath))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        lbl_date.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(102, 0, 102));
        lbl_date.setText("lbl_date");

        jPanel_Dn_2.setBackground(new java.awt.Color(0, 120, 120));
        jPanel_Dn_2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_curr_user_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_curr_user_unit.setText("เครื่อง");

        lbl_amount_user_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_amount_user_unit.setText("เครื่อง");

        lbl_curr_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_curr_user.setText("ณ ช่วงเวลา  : ");

        txt_amount_user.setEditable(false);
        txt_amount_user.setBackground(new java.awt.Color(153, 255, 153));
        txt_amount_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_amount_user.setText("jTextField1");

        txt_curr_user.setEditable(false);
        txt_curr_user.setBackground(new java.awt.Color(153, 255, 153));
        txt_curr_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_curr_user.setText("jTextField1");

        lbl_amount_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_amount_user.setText("ทั้งหมด       : ");

        javax.swing.GroupLayout jPanel_Dn_2Layout = new javax.swing.GroupLayout(jPanel_Dn_2);
        jPanel_Dn_2.setLayout(jPanel_Dn_2Layout);
        jPanel_Dn_2Layout.setHorizontalGroup(
            jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Dn_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_Dn_2Layout.createSequentialGroup()
                        .addComponent(lbl_amount_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_amount_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_amount_user_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Dn_2Layout.createSequentialGroup()
                        .addComponent(lbl_curr_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_curr_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_curr_user_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_Dn_2Layout.setVerticalGroup(
            jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Dn_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_amount_user)
                    .addComponent(txt_amount_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_amount_user_unit))
                .addGap(12, 12, 12)
                .addGroup(jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_curr_user)
                    .addGroup(jPanel_Dn_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_curr_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_curr_user_unit)))
                .addContainerGap())
        );

        lbl_title_User.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_title_User.setText("จำนวนเครื่อง");

        javax.swing.GroupLayout jPanelR_DownLayout = new javax.swing.GroupLayout(jPanelR_Down);
        jPanelR_Down.setLayout(jPanelR_DownLayout);
        jPanelR_DownLayout.setHorizontalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelR_DownLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelR_DownLayout.createSequentialGroup()
                        .addComponent(lbl_date)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel_Dn_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_title_User, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_Dn_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelR_DownLayout.setVerticalGroup(
            jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelR_DownLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_date)
                    .addComponent(lbl_title_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelR_DownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelR_DownLayout.createSequentialGroup()
                        .addComponent(jPanel_Dn_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel_Dn_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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

    
    public String getfomatDate(Date date1, Date date2){
    
        String DATE_FORMAT_NOW = "dd  MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        
        return "From "+sdf.format(date1)+"   to   "+sdf.format(date2);
    }
    
    private void selectView(String str_sql,String str_online){

        lbl_date.setVisible(true);
        //remove data showing
        if (this.jPanel_graph.getComponentCount() > 1) {
            this.jPanel_graph.remove(1);
        }
        
        jLabel_loding_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/loading15.gif")));
        jLabel_loding_graph.setText("");
        jLabel_load_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/loading15.gif")));
        jLabel_load_table.setText("");

        jPanel_loading_table.setVisible(true);
        jScrollPane1.setVisible(false);
        jPanel_loading_graph.setVisible(true);
        jPanel1.setVisible(false);
        jPanel1.setVisible(true);
        

            
            String[] Columname = {"no", "name", "detail" , "online time (min)" , "wastse time (min)","wastse time (%)", "unit"};
            DefaultTableModel model = new DefaultTableModel(Columname, 0);
            jTable1.setModel(model);      
            

            //mac min  for check wastetime
            ResultSet rs_online = mdb.getSelectdata(str_online);
            ArrayList<String> tmm_MacOnline = new ArrayList<String>();
            ArrayList<String> tmm_MinOnline = new ArrayList<String>();
            
             
            try{
                while(rs_online.next()){
                    tmm_MacOnline.add(rs_online.getString("MAC"));
                    tmm_MinOnline.add(rs_online.getString("online_min"));
               
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            //wastetime query
            ResultSet rs = mdb.getSelectdata(str_sql);
            String pattern = new String ( "#,###,##0.#####" );
            DecimalFormat decimal_format = new DecimalFormat ( pattern );
            
            
            double total_unit = 0; 
            int sum_minute = 0;
            int c = 0;
            try {                 
               
                while (rs.next()) {
                    //sum waste time
                    sum_minute += Integer.parseInt(rs.getString("lost_min"));
                    // convert minute to hour
                    double hr = Double.parseDouble(rs.getString("lost_min")) / 60;
                    double watt = Double.parseDouble(rs.getString("watt"));
                    // energy unit
                    double unit = watt / 1000 * hr;
                    model.addRow(data);
                    jTable1.setValueAt(c + 1 + "", c, 0);
                    jTable1.setValueAt(rs.getString("name"), c, 1);
                    jTable1.setValueAt(rs.getString("detail"), c, 2);
                    // index for get value tmm_minOnline
                    int tmpc = 0;
                    int wasteTime = Integer.parseInt(rs.getString("lost_min"));
                    double onlineTime =0;
                    for(String n:tmm_MacOnline){
                        if(rs.getString("MAC").equals(n)){
                             //get value onlinetime matching watsetime
		             decimal_format = new DecimalFormat ( "#,###,##0" );
                             onlineTime = Integer.parseInt(tmm_MinOnline.get(tmpc));
                             jTable1.setValueAt(decimal_format.format(onlineTime), c, 3);
                            
                        }
                        tmpc++;
                    }
                    // wastetime = ? %
                    double wastePercent = (wasteTime/onlineTime)*100;

                    decimal_format = new DecimalFormat ( pattern );
                    jTable1.setValueAt(rs.getString("lost_min"), c, 4);

                    jTable1.setValueAt(decimal_format.format(wastePercent)+" %", c, 5);
                    jTable1.setValueAt(decimal_format.format(unit), c, 6);

                    c++;
                    total_unit += unit;
                    //System.out.println(unit+"  : unit \n"+watt+" : watt  \n"+hr+" : hr");
                }

            } catch (SQLException ex) {
                JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
                ex.printStackTrace();
            }
            
            
            
        str_sql = "select cost_per_unit from sever_info where Id = 1";
        rs = mdb.getSelectdata(str_sql);
        double cost_unit = 0;
        try{
            rs.next();           
            cost_unit = Double.parseDouble(rs.getString("cost_per_unit"));
        }catch(Exception ex){
        
        }
        
        decimal_format = new DecimalFormat ( pattern );
        double total_cost = total_unit*cost_unit;
        String coal_burn = decimal_format.format(total_unit/3);  // coal 1 kg  = 3 unit
        
        txt_coal.setText(coal_burn);
        txt_Sum_unit.setText(decimal_format.format(total_unit));
        txt_Sum_lostTime.setText(sum_minute+"");
        txt_cost.setText(decimal_format.format(total_cost));
        
            jPanel_loading_table.setVisible(false);
            jScrollPane1.setVisible(true);
            //jPanel_table.setVisible(true);
            
            if(c<=0){
                //jPanel_table.setVisible(false);
                jScrollPane1.setVisible(false);
                jLabel_load_table.setIcon(null);
                jLabel_load_table.setText("                         No Data");
                jPanel_loading_table.setVisible(true);
                
                jLabel_loding_graph.setIcon(null);
                jLabel_loding_graph.setText("                         No Data");
                jLabel_loding_graph.setVisible(true);
                
                jPanel_Dn_1.setVisible(false);
                
            }else{
            
                
                rs = mdb.getSelectdata(this.sqlBargraph);
                int barValue[] = new int[24] ;
                for(int i = 0 ; i<barValue.length ; i++){
                
                    barValue[i] = 0;
                }
                
                try {

                    while (rs.next()) {
                        int lost_min = Integer.parseInt(rs.getString("lost_min"));
                        int hr = Integer.parseInt(rs.getString("hr"));
                        int min = Integer.parseInt(rs.getString("min"));
                        
                        barValue[hr] += lost_min;
                        
//                        if(lost_min > min){
//                            while (lost_min != 0) {
//
//                                barValue[hr] += lost_min;
//
//                                lost_min = lost_min - min;
//                                hr = hr-1;
//                            }
//                        }else{
//                        
//                            barValue[hr] += lost_min;
//                        }
//
//
                    }

                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
                    ex.printStackTrace();
                }
                
                
                rs = mdb.getSelectdata(this.sqlClientOnlineBar);
                int barValueOnline[] = new int[24] ;
                for(int i = 0 ; i<barValue.length ; i++){
                
                    barValueOnline[i] = 0;
                }
                
                try {

                    while (rs.next()) {
                        int online_min = Integer.parseInt(rs.getString("online_min"));
                        int hr = Integer.parseInt(rs.getString("hr"));
                        int min = Integer.parseInt(rs.getString("min"));
                        
                        barValueOnline[hr] += online_min;
                        
                    }

                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
                    ex.printStackTrace();
                }
                
                
                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                String strOnline = "online time";
                String strWaste = "waste time";
                String rowString = "minute";
                String colString = "time";
                String strAM = "";
                String strPM = "";
        
                for(int i=1 ; i<=23 ; i++){
                    dataset.setValue(barValue[i], strWaste, i+" ");
                    dataset.setValue(barValueOnline[i], strOnline, i+" ");
                }
                dataset.setValue(barValue[0], strWaste, "24 ");
                dataset.setValue(barValueOnline[0], strOnline, "24 ");
                


                JFreeChart chart = ChartFactory.createStackedBarChart3D("WASTE TIME CHART", "TIME", rowString, dataset, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot p = chart.getCategoryPlot();

                BarRenderer renderer = (BarRenderer) p.getRenderer();
                DecimalFormat decimalformat = new DecimalFormat("##.##");
                renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat));
                p.setRenderer(renderer);
                //p.getRenderer().setSeriesPaint(0, Color.RED);
                //p.getRenderer().setSeriesPaint(1, Color.BLUE);

                renderer.setItemLabelsVisible(true);
                p.setRangeGridlinePaint(Color.BLACK);
                ChartPanel barchartP = new ChartPanel(chart);
                barchartP.setSize(this.jPanel_graph.getSize());
                this.jPanel_graph.add(barchartP, -1);
    
                barchartP.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //reportForm.this.p.setVisible(false);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        reportForm.this.p.setVisible(false);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                
                barchartP.setVisible(true);
                
                jPanel_loading_table.setVisible(false);
                jPanel_loading_graph.setVisible(false);
                jScrollPane1.setVisible(true);
                
                jPanel_Dn_1.setVisible(true);
                
            }
            
            jTabbedPane1.setSelectedIndex(0);
    }
    
    private void selectClientlist(String str_sql,String str_sqlOnline){
    
        
        lbl_date.setVisible(true);
        
        if (this.jPanel_graph.getComponentCount() > 1) {
            this.jPanel_graph.remove(1);
        }
        jLabel_loding_graph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/energymonitor_sever/loading15.gif")));
        jPanel_loading_table.setVisible(true);
        jScrollPane1.setVisible(false);
        jPanel_loading_graph.setVisible(true);
        jPanel1.setVisible(false);
        jPanel1.setVisible(true);

        String[] Columname = {"No", "Name", "Detail"};
        DefaultTableModel model = new DefaultTableModel(Columname, 0);
        jTable1.setModel(model);

        //String sql_client = "select name , detail from client_info group by MAC";
        ResultSet rs = mdb.getSelectdata(str_sqlOnline);


        int c = 0;
        this.sum_onlineTime = 0;
        try {

            while (rs.next()) {

                model.addRow(data);
                jTable1.setValueAt(c + 1 + "", c, 0);
                jTable1.setValueAt(rs.getString("name"), c, 1);
                jTable1.setValueAt(rs.getString("detail"), c, 2);
                this.sum_onlineTime += Integer.parseInt(rs.getString("online_min"));
                c++;
                //System.out.println(unit+"  : unit \n"+watt+" : watt  \n"+hr+" : hr");
            }

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog((Component) null, "Error", "Alert", JOptionPane.DEFAULT_OPTION);
            ex.printStackTrace();
        }
        
        rs = mdb.getSelectdata(str_sql);
        
        int c_cuurent = 0;
        this.sum_wasteTime = 0;
        try {
            
             while (rs.next()) {
                 this.sum_wasteTime += Integer.parseInt(rs.getString("lost_min"));
                 c_cuurent++;
             }
        } catch (Exception ex) {
        }


        str_sql = "";
        //jPanel_table.setVisible(true);

        txt_amount_user.setText(c+"");
        txt_curr_user.setText(c_cuurent+"");
        if (c <= 0) {
            //jPanel_table.setVisible(false);
            jScrollPane1.setVisible(false);
            jLabel_load_table.setIcon(null);
            jLabel_load_table.setText("                         No Data");
            jPanel_loading_table.setVisible(true);

            jLabel_loding_graph.setIcon(null);
            jLabel_loding_graph.setText("                         No Data");
            jLabel_loding_graph.setVisible(true);

            jPanel_Dn_2.setVisible(false);
            lbl_title_User.setVisible(false);
            
        } else {

            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("All user", c);
            pieDataset.setValue("Current user",c_cuurent);

            JFreeChart chart = ChartFactory.createPieChart3D("User ratio", pieDataset, true, true, true);
            ChartPanel chartP = new ChartPanel(chart);
            PiePlot3D plot = (PiePlot3D) chart.getPlot();  
            plot.setForegroundAlpha(0.60f);
            plot.setDirection(Rotation.ANTICLOCKWISE);
            plot.setSectionPaint("All user", Color.green);
            plot.setSectionPaint("Current user", Color.red);
            
            //roration animation
//            final Rotator rotator = new Rotator(plot);
//            rotator.start();
            
            chartP.setSize(this.jPanel_graph.getSize());
            this.jPanel_graph.add(chartP, -1);
            
             chartP.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //reportForm.this.p.setVisible(false);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        reportForm.this.p.setVisible(false);
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        //throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
             
            chartP.setVisible(true);

            jPanel_loading_table.setVisible(false);
            jPanel_loading_graph.setVisible(false);
            jScrollPane1.setVisible(true);
            
            jPanel_Dn_2.setVisible(true);
            lbl_title_User.setVisible(true);
        }
        
        jTabbedPane1.setSelectedIndex(0);
    }
    
    private void jToggleViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleViewActionPerformed

        if (jToggleView.isSelected()) {
            
            p.setVisible(true);
            
        }else{
            p.setVisible(false);

        }
        
    }//GEN-LAST:event_jToggleViewActionPerformed

   
    private void jToggleClientlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleClientlistActionPerformed

        p.setVisible(false);
        
        if(jToggleClientlist.isSelected()){

            selectClientlist(this.sql,this.sqlClientOnline);
        }
    }//GEN-LAST:event_jToggleClientlistActionPerformed

    private void btn_settingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_settingActionPerformed

        settingFrame sttingFrame = new settingFrame(); 
        Image icon_Image = Toolkit.getDefaultToolkit().getImage("green-energy-icon-md.png");
        
        sttingFrame.setVisible(true);
    }//GEN-LAST:event_btn_settingActionPerformed

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
    private javax.swing.JButton btn_setting;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_load_table;
    private javax.swing.JLabel jLabel_loding_graph;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelR;
    private javax.swing.JPanel jPanelR_Down;
    private javax.swing.JPanel jPanel_Dn_1;
    private javax.swing.JPanel jPanel_Dn_2;
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
    public static javax.swing.JToggleButton jToggleView;
    private javax.swing.JLabel lbl_Sum_unit;
    private javax.swing.JLabel lbl_amount_user;
    private javax.swing.JLabel lbl_amount_user_unit;
    private javax.swing.JLabel lbl_cost;
    private javax.swing.JLabel lbl_curr_user;
    private javax.swing.JLabel lbl_curr_user_unit;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_losttime_unit;
    private javax.swing.JLabel lbl_sum_lostTime;
    private javax.swing.JLabel lbl_title_User;
    private javax.swing.JLabel lbl_unit_unit;
    private javax.swing.JTextField txt_Sum_lostTime;
    private javax.swing.JTextField txt_Sum_unit;
    private javax.swing.JTextField txt_amount_user;
    private javax.swing.JTextField txt_coal;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JLabel txt_cost_bath;
    private javax.swing.JTextField txt_curr_user;
    // End of variables declaration//GEN-END:variables
}
