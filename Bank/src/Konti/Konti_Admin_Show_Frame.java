package Konti;

import Admin.Admin_panel;
import Admin.Admin;
import Checks.checks;
import SuperAdmin.SuperAdmin;
import DBconnect.DBConnect;
import LogIN.LogIN;
import Statistika.Statistika_SHOW;
import SuperAdmin.Super_Admin;
import SuperAdmin.Super_Admin_Panel;
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
public class Konti_Admin_Show_Frame extends javax.swing.JFrame {
 Connection con;
 Statement st;
 ResultSet rs;
 DBConnect connect = new DBConnect();
    /**
     * Creates new form Konti_Admin_Show_Frame
     */
    public Konti_Admin_Show_Frame() {
        initComponents();
        Show_Algas_Konts_IN_JTable();
        Show_Kredita_Konts_IN_JTable();
        Show_Uzkrajuma_Konts_IN_JTable();
    }
       public Konti_Admin_Show_Frame(int ID,String Admin_name) {
        initComponents();
        Show_Algas_Konts_IN_JTable();
        Show_Kredita_Konts_IN_JTable();
        Show_Uzkrajuma_Konts_IN_JTable();
        id(ID,Admin_name);        
    }
    public void id(int ID,String admin){
            String temp=Integer.toString(ID);
            jTextField1.setText(temp);
            jTextField2.setText(admin);
    }

    
     public void Show_Algas_Konts_IN_JTable(){
        ArrayList<Konti> list = getAlgas_KontiList();
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Algas_konts.getModel();
        Object[] row=new Object[4];
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();

           model.addRow(row);
        }
        jTextField_Algas_Konti.setText(Integer.toString(i));
    } 
    public ArrayList<Konti> getAlgas_KontiList(){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `algas`";
               
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
    
    
    
    public void Show_Uzkrajuma_Konts_IN_JTable(){
        ArrayList<Konti> list = getUzkrajuma_KontiList();
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Uzkrajuma_konts.getModel();
        Object[] row=new Object[4];
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();
         
           model.addRow(row);
        }
         jTextField_Uzkrajuma_konti.setText(Integer.toString(i));

    } 
    public ArrayList<Konti> getUzkrajuma_KontiList(){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `uzkrajuma` ";
         
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
    
    
    public void Show_Kredita_Konts_IN_JTable(){
        ArrayList<Konti> list = getKontiList();
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Kredita_konts1.getModel();
        Object[] row=new Object[4];
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();
           model.addRow(row);

        }
         jTextField_Kredita_konti.setText(Integer.toString(i));

    } 
    public ArrayList<Konti> getKontiList(){
        ArrayList<Konti> kontiLists=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `kredita` ";
               
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
    public void executeSQLQuery(String query, String message){
        DBConnect connect = new DBConnect();
        
         connect.DBConnect();
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/konti?serverTimezone=Europe/Riga&useSSL=false","root","");
           st=con.createStatement();
           if(st.executeUpdate(query)==1){
              DefaultTableModel model=(DefaultTableModel)jTable_Display_Kredita_konts1.getModel();
              DefaultTableModel modell=(DefaultTableModel)jTable_Display_Uzkrajuma_konts.getModel();
              DefaultTableModel modelll=(DefaultTableModel)jTable_Display_Algas_konts.getModel();      
              model.setRowCount(0);
              modell.setRowCount(0);
              modelll.setRowCount(0);
               
              Show_Kredita_Konts_IN_JTable();
              Show_Uzkrajuma_Konts_IN_JTable();
              Show_Algas_Konts_IN_JTable();
              JOptionPane.showMessageDialog(null,"Data "+message+"Succefully");
           }else{
               JOptionPane.showMessageDialog(null,"Data NOT "+message);
           } 
           con.close();
           st.close();
           rs.close();
        }catch(Exception ex){
             ex.printStackTrace();
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

        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Display_Algas_konts = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Display_Kredita_konts1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Display_Uzkrajuma_konts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Delete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jTextField_Konta_numurs = new javax.swing.JTextField();
        jTextField_Balance = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Konta_Nosaukums = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField_Kredita_konti = new javax.swing.JTextField();
        jTextField_Algas_Konti = new javax.swing.JTextField();
        jTextField_Uzkrajuma_konti = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Uzkrajuma konti");

        jTable_Display_Algas_konts.setBackground(new java.awt.Color(51, 255, 51));
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

        jTable_Display_Kredita_konts1.setBackground(new java.awt.Color(51, 255, 51));
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

        jTable_Display_Uzkrajuma_konts.setBackground(new java.awt.Color(51, 255, 51));
        jTable_Display_Uzkrajuma_konts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Konta Numurs", "Balance", " Nosaukums"
            }
        ));
        jTable_Display_Uzkrajuma_konts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_Uzkrajuma_kontsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_Display_Uzkrajuma_konts);

        jLabel1.setText("Algas konti");

        Delete.setText("Nodzest ");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("ID");

        jTextField_ID.setEditable(false);
        jTextField_ID.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_IDActionPerformed(evt);
            }
        });

        jTextField_Konta_numurs.setEditable(false);
        jTextField_Konta_numurs.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jTextField_Balance.setEditable(false);
        jTextField_Balance.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setText("Konta Numurs");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel6.setText("Balance");

        jTextField1.setEditable(false);

        jButton1.setText("Atpakal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jButton2.setText("Apskatit statistiku ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Konta_numurs, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_ID)
                                    .addComponent(jTextField_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addComponent(Delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTextField_Konta_Nosaukums, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField_Kredita_konti, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(218, 218, 218))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Uzkrajuma_konti, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Algas_Konti, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(198, 198, 198))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(220, 220, 220))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(jTextField2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Konta_numurs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jTextField_Kredita_konti, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_Algas_Konti, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_Konta_Nosaukums, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_Uzkrajuma_konti, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IDActionPerformed

    private void jTable_Display_Kredita_konts1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Kredita_konts1MouseClicked
         int i=jTable_Display_Kredita_konts1.getSelectedRow();
        TableModel model=jTable_Display_Kredita_konts1.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Konta_Nosaukums.setText(model.getValueAt(i,3).toString());
  
    }//GEN-LAST:event_jTable_Display_Kredita_konts1MouseClicked

    private void jTable_Display_Algas_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Algas_kontsMouseClicked
        int i=jTable_Display_Algas_konts.getSelectedRow();
        TableModel model=jTable_Display_Algas_konts.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Konta_Nosaukums.setText(model.getValueAt(i,3).toString());

    }//GEN-LAST:event_jTable_Display_Algas_kontsMouseClicked

    private void jTable_Display_Uzkrajuma_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Uzkrajuma_kontsMouseClicked
        int i=jTable_Display_Uzkrajuma_konts.getSelectedRow();
        TableModel model=jTable_Display_Uzkrajuma_konts.getModel();
        jTextField_ID.setText(model.getValueAt(i,0).toString());
        jTextField_Konta_numurs.setText(model.getValueAt(i,1).toString());
        jTextField_Balance.setText(model.getValueAt(i,2).toString());
        jTextField_Konta_Nosaukums.setText(model.getValueAt(i,3).toString());

    }//GEN-LAST:event_jTable_Display_Uzkrajuma_kontsMouseClicked

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        Admin_panel b= new  Admin_panel();
        checks check=new checks();
        
        String nr=jTextField_Konta_numurs.getText();
        String nos=jTextField_Konta_Nosaukums.getText();
        String ID=jTextField_ID.getText();
        if(nos.equals("Algas")){
            JOptionPane.showMessageDialog(null,"Jus Nevariet nodzest GALVENO kontu ");
        }else{
            if(!check.checkField(jTextField_ID.getText())&& !check.checkField(jTextField_Konta_numurs.getText()) && !check.checkField(jTextField_Balance.getText())){ 
                JOptionPane.showMessageDialog(null,"Aizpildiet laukus ");
            }else{
                SuperAdmin a=new SuperAdmin();
                Admin delete=new Admin();
                String query=a.Delete_Konti(nr,nos);
                executeSQLQuery(query,"Deleted");

                String text="Lietotajs ar ID"+ID+" nodzesa kontu:"+nr;
                String queryy=delete.LOG(ID,text);
                b.executeSQLQuery2(queryy);
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String temp="SuperAdmin"; 
        String tempp=jTextField1.getText();
        int ID=Integer.parseInt(tempp);
        String Super_admin=jTextField2.getText();
        
        if(temp.equals(jTextField2.getText())){
            dispose();
            Super_Admin panell=new Super_Admin((int) ID,Super_admin);
            panell.setVisible(true);
        }else{
            dispose();
            Admin_panel panel=new Admin_panel((int) ID,Super_admin);
            panel.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        checks check=new checks();
        
        if(!check.checkField(jTextField_ID.getText())){ 
          JOptionPane.showMessageDialog(null,"Ievadiet ID ");
        }else{
            dispose();
            String temp=jTextField_ID.getText();

            int ID=Integer.parseInt(temp);

            String nosaukums=jTextField1.getText();
            String IDD=jTextField2.getText();
            Statistika_SHOW panel=new Statistika_SHOW((int) ID,nosaukums,IDD);
            panel.setVisible(true); 
        }
          
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Konti_Admin_Show_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Konti_Admin_Show_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Konti_Admin_Show_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Konti_Admin_Show_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Konti_Admin_Show_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_Display_Algas_konts;
    private javax.swing.JTable jTable_Display_Kredita_konts1;
    private javax.swing.JTable jTable_Display_Uzkrajuma_konts;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_Algas_Konti;
    private javax.swing.JTextField jTextField_Balance;
    private javax.swing.JTextField jTextField_ID;
    private javax.swing.JTextField jTextField_Konta_Nosaukums;
    private javax.swing.JTextField jTextField_Konta_numurs;
    private javax.swing.JTextField jTextField_Kredita_konti;
    private javax.swing.JTextField jTextField_Uzkrajuma_konti;
    // End of variables declaration//GEN-END:variables
}
