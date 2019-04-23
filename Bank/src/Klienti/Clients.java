package Klienti;


import LogIN.LogIN;
import Konti.Konti;
import Admin.Admin_panel;
import Admin.Admin;
import Checks.checks;
import SuperAdmin.SuperAdmin;
import DBconnect.DBConnect;
import Vesture.Vesture_User_Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */
public final class Clients extends javax.swing.JFrame {
    Connection con;
    Statement st;
    ResultSet rs;
    DBConnect connect = new DBConnect();
    /**
     * Creates new form Clients
     * @param ID
     */Clients() {
    
    }
    public Clients(int ID) {
        initComponents();
        Show_Algas_Konts_IN_JTable(ID);
        Show_Kredita_Konts_IN_JTable(ID);
        Show_Uzkrajuma_Konts_IN_JTable(ID);
        id(ID);
    }    
    public void id(int ID){
            String temp=Integer.toString(ID);
            jTextField1.setText(temp);
    }
    public void executeSQLQuery2(String query,int ID){
        DBConnect connect = new DBConnect();
         connect.DBConnect();
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
           st=con.createStatement();
           if(st.executeUpdate(query)==1){ 
               DefaultTableModel model=(DefaultTableModel)jTable_Display_Uzkrajuma_konts.getModel();
               DefaultTableModel modell=(DefaultTableModel)jTable_Display_Algas_konts.getModel();
               DefaultTableModel modelll=(DefaultTableModel)jTable_Display_Kredita_konts1.getModel();
               model.setRowCount(0);
               modell.setRowCount(0);
               modelll.setRowCount(0);
               Show_Algas_Konts_IN_JTable(ID);
               Show_Kredita_Konts_IN_JTable(ID);
               Show_Uzkrajuma_Konts_IN_JTable(ID);
               JOptionPane.showMessageDialog(null,"Ok");

           }else{
               
           } 
           con.close();
            st.close();
            rs.close();
        }catch(Exception ex){
             ex.printStackTrace();
        }
    }


    public void Show_Algas_Konts_IN_JTable(int ID){
        ArrayList<Konti> list = getAlgas_KontiList(ID);
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Algas_konts.getModel();
        Object[] row=new Object[4];
        for (int i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();  
           model.addRow(row);
        }
    } 
    public ArrayList<Konti> getAlgas_KontiList(int ID){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `algas` WHERE id="+ID;            
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                st=con.createStatement();
                rs = st.executeQuery(query);
                Konti konti;
                while(rs.next()){
                     konti=new Konti(rs.getInt("id"),rs.getString("Konta_numurs"),rs.getInt("Balance"),rs.getString("Nosaukums"));
                     kontiLists.add(konti);
                 }
            con.close();
            st.close();
            rs.close();

            }catch (Exception ex){
                    ex.printStackTrace();
                }
        return kontiLists;
    }

    public void Show_Uzkrajuma_Konts_IN_JTable(int ID){
        int IDD=ID;
        ArrayList<Konti> list = getUzkrajuma_KontiList(IDD);
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Uzkrajuma_konts.getModel();
        Object[] row=new Object[4];
        
        for (int i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();

           model.addRow(row);
        }
    } 
    public ArrayList<Konti> getUzkrajuma_KontiList(int ID){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `uzkrajuma` WHERE id="+ID;      
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                st=con.createStatement();
                rs = st.executeQuery(query);
                Konti konti;
                while(rs.next()){
                     konti=new Konti(rs.getInt("id"),rs.getString("Konta_numurs"),rs.getInt("Balance"),rs.getString("Nosaukums"));
                     kontiLists.add(konti);
                 }
            con.close();
            st.close();
            rs.close();

            }catch (Exception ex){
                    ex.printStackTrace();
                }
        return kontiLists;
    }
    public void Show_Kredita_Konts_IN_JTable(int ID){
        ArrayList<Konti> list = getKontiList(ID);
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Kredita_konts1.getModel();
        Object[] row=new Object[4];
        
        for (int i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();

           model.addRow(row);

        }
    } 
    public ArrayList<Konti> getKontiList(int ID){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `kredita` WHERE id="+ID;      
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                st=con.createStatement();
                 rs = st.executeQuery(query);
                 Konti konti;
                 while(rs.next()){
                     konti=new Konti(rs.getInt("id"),rs.getString("Konta_numurs"),rs.getInt("Balance"),rs.getString("Nosaukums"));
                     kontiLists.add(konti);
                 }
            con.close();
            st.close();
            rs.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        return kontiLists;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Display_Uzkrajuma_konts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Display_Algas_konts = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Display_Kredita_konts1 = new javax.swing.JTable();
        jTextField_Konta_numurs1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Parskaitit = new javax.swing.JButton();
        jTextField_ID = new javax.swing.JTextField();
        summa = new javax.swing.JTextField();
        jTextField_Konta_numurs = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Balance = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton_ADD = new javax.swing.JButton();
        jButton_Nonemt = new javax.swing.JButton();
        jTextField_Veids = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Add_Bank = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_Display_Uzkrajuma_konts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Konta Numurs", "Balance", "Nosaukums"
            }
        ));
        jTable_Display_Uzkrajuma_konts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_Uzkrajuma_kontsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Display_Uzkrajuma_konts);

        jLabel1.setText("Algas konti");

        jLabel2.setText("Uzkrajuma konti");

        jTable_Display_Algas_konts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Konta_Numurs", "Balance", "Nosaukums"
            }
        ));
        jTable_Display_Algas_konts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_Algas_kontsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_Display_Algas_konts);

        jLabel3.setText("Kredita konti");

        jTable_Display_Kredita_konts1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Konta_Numurs", "Balance", "Nosaukums"
            }
        ));
        jTable_Display_Kredita_konts1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_Kredita_konts1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_Display_Kredita_konts1);

        jTextField_Konta_numurs1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField_Konta_numurs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Konta_numurs1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setText("Konta Numurs");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("ID");

        jButton_Parskaitit.setText("Parskaitit naudu");
        jButton_Parskaitit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ParskaititActionPerformed(evt);
            }
        });

        jTextField_ID.setEditable(false);
        jTextField_ID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_IDActionPerformed(evt);
            }
        });

        summa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                summaActionPerformed(evt);
            }
        });

        jTextField_Konta_numurs.setEditable(false);
        jTextField_Konta_numurs.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel10.setText("Ievadiet summu");

        jTextField_Balance.setEditable(false);
        jTextField_Balance.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setText("Konta Numurs");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setText("Balance");

        jButton1.setText("Atpakaļ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton_ADD.setText("Ielikt Naudu ");
        jButton_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ADDActionPerformed(evt);
            }
        });

        jButton_Nonemt.setText("Nonemt naudu");
        jButton_Nonemt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NonemtActionPerformed(evt);
            }
        });

        jTextField_Veids.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        jLabel11.setText("Konta Veids");

        Add_Bank.setText("Parskaitit naudu Banka");
        Add_Bank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_BankActionPerformed(evt);
            }
        });

        jButton2.setText("Vesture");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(0, 255, 102));
        jButton_Clear.setForeground(new java.awt.Color(51, 51, 51));
        jButton_Clear.setText("Iztīrīt");
        jButton_Clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ClearMouseClicked(evt);
            }
        });
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField_Konta_numurs))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Veids)
                                    .addComponent(jTextField_Balance)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(130, 130, 130)
                        .addComponent(jTextField_ID))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addGap(69, 69, 69)
                        .addComponent(summa))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Nonemt, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Add_Bank))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Konta_numurs1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_Parskaitit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jLabel2)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addComponent(jLabel3)
                                    .addContainerGap())
                                .addComponent(jScrollPane4))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Konta_numurs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Veids, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(summa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton_ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Nonemt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(Add_Bank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Konta_numurs1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Parskaitit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ParskaititActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ParskaititActionPerformed
        SuperAdmin a = new SuperAdmin();
        Admin log=new Admin();
        Admin_panel panel=new Admin_panel();
        checks checks=new checks();
        
       

        String Konta_veids=jTextField_Veids.getText();
        if(!checks.checkField(jTextField_Konta_numurs.getText()) || !checks.checkField(jTextField_Konta_numurs1.getText()) || !checks.checkField(jTextField_Balance.getText()) || !checks.checkField(summa.getText()) )
          JOptionPane.showMessageDialog(null,"Kads no laukiem ir tukss, Ievaiet visu informaciju.");
        else{
            String tempp=jTextField_ID.getText();
            int ID=Integer.parseInt(tempp);

            String K_numurs1=jTextField_Konta_numurs.getText();
            String K_numurs2=jTextField_Konta_numurs1.getText();

            String temp1=jTextField_Balance.getText();
            double sum1=Double.parseDouble(temp1);

            String temp=summa.getText();
            double sum=Double.parseDouble(temp);
            
            String Konti_Uzkrajumi="konti.uzkrajuma";
            String Konti_Kredita="konti.kredita";
            String Konti_Algas="konti.algas";
            String Konti_Bankas_konts="konti.Bankas_konts";
            String check_name="Konta_numurs";
                                    
            if(!checks.hasField(K_numurs2,Konti_Uzkrajumi,check_name) &&  !checks.hasField(K_numurs2,Konti_Kredita,check_name) && !checks.hasField(K_numurs2,Konti_Algas,check_name) && !checks.hasField(K_numurs2,Konti_Bankas_konts,check_name)){
                JOptionPane.showMessageDialog(null,"Tads konta numurs neeksiste.");
            }else{
                if(sum>0){
                     if(sum1>sum){
                     connect.DBConnect();
                     String query="Select *From `kredita` WHERE Konta_numurs='"+K_numurs2+"'";
                      try{
                              con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                              st=con.createStatement();
                              rs = st.executeQuery(query);
                              if(rs.next()){
                                  double su=rs.getInt("Balance")+sum;
                                  String nosaukums="kredita";      
                                  String queryy= a.Update_Konts(su,K_numurs2,nosaukums);
                                 executeSQLQuery2(queryy,ID);

                                  double summmm=sum1-sum;
                                  String queryyy=a.Update_Konts(summmm,K_numurs1,Konta_veids);
                                  executeSQLQuery2(queryyy,ID);

                                  String text="Lietotajs ar ID"+ID+" Parskaitija no konta"+K_numurs1+":"+sum+"eiro uz kontu:"+K_numurs2+"";
                                  String IDD=Integer.toString(ID);
                                  String quer=log.LOG(IDD,text);
                                  panel.executeSQLQuery2(quer);
                               }
                              con.close();
                              st.close();
                              rs.close();
                          }catch (Exception ex){
                                  ex.printStackTrace();
                              }
                      String queryy="Select *From `algas` WHERE Konta_numurs='"+K_numurs2+"'";
                      try{
                            con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                            st=con.createStatement();
                            rs = st.executeQuery(queryy);
                            if(rs.next()){
                                double su=rs.getInt("Balance")+sum;
                                String nosaukums="algas";      
                                String queryyyy= a.Update_Konts(su,K_numurs2,nosaukums);
                                executeSQLQuery2(queryyyy,ID);

                                double summmm=sum1-sum;
                                String queryyyyy=a.Update_Konts(summmm,K_numurs1,Konta_veids);
                                executeSQLQuery2(queryyyyy,ID);

                                String text="Lietotajs ar ID"+ID+" Parskaitija no konta"+K_numurs1+":"+sum+"eiro uz kontu:"+K_numurs2+"";
                                String IDD=Integer.toString(ID);
                                String quer=log.LOG(IDD,text);
                                panel.executeSQLQuery2(quer);
                             }
                            con.close();
                            st.close();
                            rs.close();
                          }catch (Exception ex){
                                  ex.printStackTrace();
                              }
                      String queryyy="Select *From `uzkrajuma` WHERE Konta_numurs='"+K_numurs2+"'";  
                      try{
                              con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                              st=con.createStatement();
                              rs = st.executeQuery(queryyy);
                              if(rs.next()){
                                  double su=rs.getInt("Balance")+sum;
                                  String nosaukums="uzkrajuma";      
                                  String queryyyyy= a.Update_Konts(su,K_numurs2,nosaukums);
                                  executeSQLQuery2(queryyyyy,ID);

                                  double summmm=sum1-sum;
                                  String queryyyyyy=a.Update_Konts(summmm,K_numurs1,Konta_veids);
                                  executeSQLQuery2(queryyyyyy,ID); 

                                  String text="Lietotajs ar ID"+ID+" Parskaitija no konta"+K_numurs1+":"+sum+"eiro uz kontu:"+K_numurs2+"";
                                  String IDD=Integer.toString(ID);
                                  String quer=log.LOG(IDD,text);
                                  panel.executeSQLQuery2(quer);
                               }
                                 con.close();
                                st.close();
                                rs.close();
                          }catch (Exception ex){
                                  ex.printStackTrace();
                              }
                     }else{
                          JOptionPane.showMessageDialog(null,"Ievadiet citu summu.");
                     }
                 }else{
                 JOptionPane.showMessageDialog(null,"Ievadiet citu summu.");
                 }
               }
            }
    }//GEN-LAST:event_jButton_ParskaititActionPerformed

    private void jTextField_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        LogIN panel=new LogIN();
        panel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_NonemtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NonemtActionPerformed
       Admin log=new Admin();
       Admin_panel panel=new Admin_panel();
       SuperAdmin a = new SuperAdmin();
       checks checks=new checks();
       if(!checks.checkField(jTextField_Konta_numurs.getText()) || !checks.checkField(jTextField_Balance.getText()) || !checks.checkField(summa.getText()) )
          JOptionPane.showMessageDialog(null,"Kads no laukiem ir tukss, Ievaiet visu informaciju.");
       else{
            String temp=jTextField1.getText();
            int ID=Integer.parseInt(temp);

            double nonemsana_summa=Double.parseDouble(summa.getText());
            double balance=Double.parseDouble(jTextField_Balance.getText());

            String nosaukums=jTextField_Veids.getText();
            String K_numurs=jTextField_Konta_numurs.getText();

            if(balance>=nonemsana_summa){
                if(nosaukums.equals("Kredita")){
                     double summa=balance-(nonemsana_summa*0.95);
                     JOptionPane.showMessageDialog(null,"Nonemts");

                     String query=a.Update_Konts((int) summa,K_numurs,nosaukums);
                     executeSQLQuery2(query,ID);

                     String text="Lietotajs ar ID"+ID+"Nonema:"+nonemsana_summa+"eiro uz kontu:"+K_numurs+"";
                     String quer=log.LOG(temp,text);
                     panel.executeSQLQuery2(quer);
                }else{
                     double summa=balance-nonemsana_summa;
                     JOptionPane.showMessageDialog(null,"Nonemts");

                     String query=a.Update_Konts((int) summa,K_numurs,nosaukums);
                     executeSQLQuery2(query,ID);

                     String text="Lietotajs ar ID"+ID+"Nonema:"+nonemsana_summa+"eiro uz kontu:"+K_numurs+"";
                     String quer=log.LOG(temp,text);
                     panel.executeSQLQuery2(quer);
                  }
             }else{
                JOptionPane.showMessageDialog(null,"Nepietiek lidzeklju.");
             }
          }

    }//GEN-LAST:event_jButton_NonemtActionPerformed

    private void jTable_Display_Kredita_konts1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Kredita_konts1MouseClicked
        int i=jTable_Display_Kredita_konts1.getSelectedRow();
        TableModel model=jTable_Display_Kredita_konts1.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Veids.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_jTable_Display_Kredita_konts1MouseClicked

    private void jButton_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ADDActionPerformed
       Admin log=new Admin();
       SuperAdmin a = new SuperAdmin();
       Admin_panel panel=new Admin_panel();
       checks checks=new checks();
       if(!checks.checkField(jTextField_Konta_numurs.getText()) || !checks.checkField(jTextField_Balance.getText()) || !checks.checkField(summa.getText()) )
          JOptionPane.showMessageDialog(null,"Kads no laukiem ir tukss, Ievadiet visu informaciju.");
       else{
            double summa_ADD=Double.parseDouble(summa.getText());

            String nosaukums=jTextField_Veids.getText(); 
            String temp=jTextField1.getText();

            int ID=Integer.parseInt(temp);
            double balance=Double.parseDouble(jTextField_Balance.getText());

            String K_numurs=jTextField_Konta_numurs.getText();

            if(summa_ADD>0){
                 if(nosaukums.equals("Uzkrajuma")){
                     double summa=balance+(summa_ADD*0.05);
                     JOptionPane.showMessageDialog(null,"Ielikts");

                     String query=a.Update_Konts((int) summa,K_numurs,nosaukums);
                     executeSQLQuery2(query,ID);

                     String text="Lietotajs ar ID"+ID+"Ielika:"+summa_ADD+"eiro uz kontu:"+K_numurs+"";
                     String quer=log.LOG(temp,text);
                     panel.executeSQLQuery2(quer);
                 }else{
                       double summa=balance+summa_ADD;
                       JOptionPane.showMessageDialog(null,"Ielikts");

                       String query=a.Update_Konts((int) summa,K_numurs,nosaukums);
                       executeSQLQuery2(query,ID);

                       String text="Lietotajs ar ID"+ID+"Ielika:"+summa_ADD+"eiro uz kontu:"+K_numurs+"";
                       String quer=log.LOG(temp,text);
                       panel.executeSQLQuery2(quer);
                     }
              }else{
                JOptionPane.showMessageDialog(null,"Ievadiet citu summu.");
            }
       }

    }//GEN-LAST:event_jButton_ADDActionPerformed

    private void jTable_Display_Uzkrajuma_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Uzkrajuma_kontsMouseClicked
        int i=jTable_Display_Uzkrajuma_konts.getSelectedRow();
        TableModel model=jTable_Display_Uzkrajuma_konts.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Veids.setText(model.getValueAt(i,3).toString());    }//GEN-LAST:event_jTable_Display_Uzkrajuma_kontsMouseClicked

    private void summaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_summaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_summaActionPerformed

    private void Add_BankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_BankActionPerformed
        SuperAdmin a = new SuperAdmin();
        Admin log=new Admin();
        Admin_panel panel=new Admin_panel();
        checks checks=new checks();
        if(!checks.checkField(jTextField_Konta_numurs.getText()) || !checks.checkField(jTextField_Balance.getText()) || !checks.checkField(summa.getText()) )
           JOptionPane.showMessageDialog(null,"Kads no laukiem ir tukss, Ievaiet visu informaciju.");
        else{
         String tempp=jTextField_ID.getText();
         int ID=Integer.parseInt(tempp);

         String K_numurs1=jTextField_Konta_numurs.getText();
         String K_numurs2="LV70HABA8346511132976";

         String temp1=jTextField_Balance.getText();
         double sum1=Double.parseDouble(temp1);

         String temp=summa.getText();
         double sum=Double.parseDouble(temp);

         String Konta_veids=jTextField_Veids.getText();
         if(sum>0){
              if(sum1>sum){
                 connect.DBConnect();
                 String query="Select *From `bankas_konts` WHERE Konta_numurs='"+K_numurs2+"'";
                  try{
                          con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
                          st=con.createStatement();
                          rs = st.executeQuery(query);
                          if(rs.next()){
                              double su=rs.getInt("Balance")+sum;
                              String nosaukums="Bankas_konts";      
                              String queryy= a.Update_Konts(su,K_numurs2,nosaukums);
                              executeSQLQuery2(queryy,ID);

                              double summmm=sum1-sum;
                              String queryyy=a.Update_Konts(summmm,K_numurs1,Konta_veids);
                              executeSQLQuery2(queryyy,ID);

                              String text="Lietotajs ar ID"+ID+" Parskaitija no konta"+K_numurs1+":"+sum+"eiro uz kontu:"+K_numurs2+"";
                              String IDD=Integer.toString(ID);
                              String quer=log.LOG(IDD,text);
                              panel.executeSQLQuery2(quer);
                           }
                      }catch (Exception ex){
                              ex.printStackTrace();
                          }

                 }else{
                      JOptionPane.showMessageDialog(null,"Ievadiet citu summu.");
                 }
          }else{
              JOptionPane.showMessageDialog(null,"Ievadiet citu summu.");
              }
        }
    }//GEN-LAST:event_Add_BankActionPerformed

    private void jTable_Display_Algas_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Algas_kontsMouseClicked
        int i=jTable_Display_Algas_konts.getSelectedRow();
        TableModel model=jTable_Display_Algas_konts.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Veids.setText(model.getValueAt(i,3).toString());
    }//GEN-LAST:event_jTable_Display_Algas_kontsMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
        String temp=jTextField1.getText();
        int ID=Integer.parseInt(temp);
        Vesture_User_Frame panel= new Vesture_User_Frame(ID);
        panel.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField_Konta_numurs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Konta_numurs1ActionPerformed
        
    }//GEN-LAST:event_jTextField_Konta_numurs1ActionPerformed

    private void jButton_ClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ClearMouseClicked
        
    }//GEN-LAST:event_jButton_ClearMouseClicked

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        jTextField_ID.setText("");
        jTextField_Konta_numurs.setText("");
        jTextField_Balance.setText("");
        jTextField_Veids.setText("");
        summa.setText("");
        jTextField_Konta_numurs1.setText("");
    }//GEN-LAST:event_jButton_ClearActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clients().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Bank;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_ADD;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Nonemt;
    private javax.swing.JButton jButton_Parskaitit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_Display_Algas_konts;
    private javax.swing.JTable jTable_Display_Kredita_konts1;
    private javax.swing.JTable jTable_Display_Uzkrajuma_konts;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField_Balance;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Konta_numurs;
    private javax.swing.JTextField jTextField_Konta_numurs1;
    private javax.swing.JTextField jTextField_Veids;
    private javax.swing.JTextField summa;
    // End of variables declaration//GEN-END:variables


}
