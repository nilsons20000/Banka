/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vesture;

import DBconnect.DBConnect;
import Klienti.Clients;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nils
 */
public class Vesture_User_Frame extends javax.swing.JFrame {
    Connection con;
    Statement st;
    ResultSet rs;
    DBConnect connect = new DBConnect();
    /**
     * Creates new form Vesture_User_Frame
     */
    public Vesture_User_Frame() {
        initComponents();
    }
    
     public Vesture_User_Frame(int ID) {
        initComponents();
        Show_Vesture_IN_JTable(ID);
        id(ID);
    }
    public void id(int ID){
        String temp=Integer.toString(ID);
        jTextField1.setText(temp);
    }
    public void Show_Vesture_IN_JTable(int ID){
        ArrayList<LOG> list = getVestureList(ID);
        DefaultTableModel model= (DefaultTableModel)jTable_LOG.getModel();
        Object[] row=new Object[4];
        for (int i=0;i<list.size();i++){
            row[0]=list.get(i).getID();
            row[1]=list.get(i).getDarbiba();
           model.addRow(row);
        }
    } 
    public ArrayList<LOG> getVestureList(int ID){
        ArrayList<LOG> vestureList=new ArrayList<>();
        connect.DBConnect();
 
        String query="Select *From `vesture` where id="+ID;            
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/LOG?serverTimezone=Europe/Riga&useSSL=false","root","");
                st=con.createStatement();
                rs = st.executeQuery(query);
                LOG LOG_vesture;
                while(rs.next()){
                     LOG_vesture=new LOG(rs.getInt("id"),rs.getString("Darbiba"));
                     vestureList.add(LOG_vesture);
                 }
                con.close();
                st.close();
                rs.close();
            }catch (Exception ex){
                    ex.printStackTrace();
                }
        return vestureList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_LOG = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_LOG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Darbiba"
            }
        ));
        jTable_LOG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_LOGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_LOG);

        jButton1.setText("Atpakal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(137, 137, 137))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_LOGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_LOGMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_LOGMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
        String temp=jTextField1.getText();
        int ID=Integer.parseInt(temp);
        Clients panel=new Clients((int) ID);
        panel.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Vesture_User_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vesture_User_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vesture_User_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vesture_User_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vesture_User_Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_LOG;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
