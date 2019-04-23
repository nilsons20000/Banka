/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistika;

import Admin.Admin_panel;
import Admin.Admin;
import SuperAdmin.SuperAdmin;
import DBconnect.DBConnect;
import Konti.Konti;
import Konti.Konti_Admin_Show_Frame;
import LogIN.LogIN;
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
/**
 *
 * @author Nils
 */
public class Statistika_SHOW extends javax.swing.JFrame {
    Connection con;
    Statement st;
    ResultSet rs;
    DBConnect connect = new DBConnect();
    /**
     * Creates new form Statistika_SHOW
     */
    public Statistika_SHOW() {
        initComponents();
    }
    public Statistika_SHOW(int ID,String Admin,String IDD) {
        initComponents();
        Show_Algas_Konts_IN_JTable(ID);
        Show_Kredita_Konts_IN_JTable(ID);
        Show_Uzkrajuma_Konts_IN_JTable(ID);
        id(IDD,Admin);
    }
    public void id(String ID,String Admin){
            jTextField1.setText(ID);
            jTextField2.setText(Admin);
    }
    public void Show_Algas_Konts_IN_JTable(int ID){
        ArrayList<Konti> list = getAlgas_KontiList(ID);
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Algas_konts.getModel();
        Object[] row=new Object[4];
        double summa=0;
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();
            summa=summa+list.get(i).getBalance();

            model.addRow(row);
        }
            jTextField_Algas_kontu_skaits.setText(Double.toString(i));
            jTextField_Algas.setText(Double.toString(summa)+"$");
    } 
    public ArrayList<Konti> getAlgas_KontiList(int ID){
        ArrayList<Konti> kontiLists=new ArrayList<>(ID);
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
        ArrayList<Konti> list = getUzkrajuma_KontiList(ID);
        DefaultTableModel model= (DefaultTableModel)jTable_Display_Uzkrajuma_konts.getModel();
        Object[] row=new Object[4];
        double summa=0;
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();
            summa=summa+list.get(i).getBalance();
           model.addRow(row);
        }
        jTextField_Uzkrajuma_kontu_skaits.setText(Double.toString(i));
        jTextField_Uzkrajuma.setText(Double.toString(summa)+"$");
    } 
    public ArrayList<Konti> getUzkrajuma_KontiList(int ID){
        ArrayList<Konti> kontiLists=new ArrayList<>(ID);
        connect.DBConnect();
        
        String query="Select *From `uzkrajuma`  WHERE id="+ID; 
         
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
        double summa=0;
        int i;
        for (i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getKredita_Numurs();
            row[2]=list.get(i).getBalance();
            row[3]=list.get(i).getTable_Name();
            summa=summa+list.get(i).getBalance();
           model.addRow(row);

        }
        jTextField_Kredita_kontu_skaits.setText(Double.toString(i));
        jTextField_Kredita.setText(Double.toString(summa)+"$");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Display_Algas_konts = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Display_Kredita_konts1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Display_Uzkrajuma_konts = new javax.swing.JTable();
        jTextField_Kredita = new javax.swing.JTextField();
        jTextField_Algas = new javax.swing.JTextField();
        jTextField_Uzkrajuma = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField_Kredita_kontu_skaits = new javax.swing.JTextField();
        jTextField_Algas_kontu_skaits = new javax.swing.JTextField();
        jTextField_Uzkrajuma_kontu_skaits = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Algas konti");

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

        jTextField_Kredita.setEditable(false);

        jTextField_Algas.setEditable(false);

        jTextField_Uzkrajuma.setEditable(false);

        jButton1.setText("Atpakal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField_Kredita_kontu_skaits.setEditable(false);

        jTextField_Algas_kontu_skaits.setEditable(false);
        jTextField_Algas_kontu_skaits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Algas_kontu_skaitsActionPerformed(evt);
            }
        });

        jTextField_Uzkrajuma_kontu_skaits.setEditable(false);

        jTextField1.setEditable(false);

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3))
                        .addGap(238, 238, 238)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(192, 192, 192)
                                    .addComponent(jLabel2))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(211, 211, 211)
                                    .addComponent(jLabel1))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jTextField_Uzkrajuma_kontu_skaits, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(290, 290, 290)
                                    .addComponent(jTextField_Uzkrajuma, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_Algas_kontu_skaits, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Algas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField_Kredita_kontu_skaits, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Kredita, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Kredita_kontu_skaits, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(jTextField_Kredita))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_Algas_kontu_skaits, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jTextField_Algas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTextField_Uzkrajuma_kontu_skaits, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Uzkrajuma)))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_Display_Algas_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Algas_kontsMouseClicked
        
    }//GEN-LAST:event_jTable_Display_Algas_kontsMouseClicked

    private void jTable_Display_Kredita_konts1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Kredita_konts1MouseClicked
        

    }//GEN-LAST:event_jTable_Display_Kredita_konts1MouseClicked

    private void jTable_Display_Uzkrajuma_kontsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Display_Uzkrajuma_kontsMouseClicked
        
    }//GEN-LAST:event_jTable_Display_Uzkrajuma_kontsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
         String temp=jTextField2.getText();
         int ID=Integer.parseInt(temp);
         
         String admin=jTextField1.getText();
         
        Konti_Admin_Show_Frame panel= new Konti_Admin_Show_Frame(ID,admin);
        panel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_Algas_kontu_skaitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Algas_kontu_skaitsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Algas_kontu_skaitsActionPerformed

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
            java.util.logging.Logger.getLogger(Statistika_SHOW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statistika_SHOW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statistika_SHOW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statistika_SHOW.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Statistika_SHOW().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable_Display_Algas_konts;
    private javax.swing.JTable jTable_Display_Kredita_konts1;
    private javax.swing.JTable jTable_Display_Uzkrajuma_konts;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField_Algas;
    private javax.swing.JTextField jTextField_Algas_kontu_skaits;
    private javax.swing.JTextField jTextField_Kredita;
    private javax.swing.JTextField jTextField_Kredita_kontu_skaits;
    private javax.swing.JTextField jTextField_Uzkrajuma;
    private javax.swing.JTextField jTextField_Uzkrajuma_kontu_skaits;
    // End of variables declaration//GEN-END:variables
}
