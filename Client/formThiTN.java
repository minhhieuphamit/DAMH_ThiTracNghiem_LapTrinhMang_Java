/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Server.CauHoi;
import Client.formKetQua;
import Server.DapAn;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pham Minh Hieu
 */
public final class formThiTN extends javax.swing.JFrame {

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    //ButtonGroup G = new ButtonGroup();
    int soCau = 0;
    String strkq;
    String anw;
    private static int timer = 30;
    int current = -1;
    int dem = 0;
    boolean checkinput = false;
    ArrayList<CauHoi> listCauhoi = null;
    String anwClient = "";
    ArrayList<String> cauChon = null;
    ArrayList<String> ketqua = null;

    /**
     * Creates new form formThiTracNghiem
     */
    public formThiTN() {
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            this.setTitle("THI TRẮC NGHIỆM");

            listCauhoi = new ArrayList();
            cauChon = new ArrayList();
            ketqua = new ArrayList<>();
            ThiTracNghiem();
            cauHoiKeTiep();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(formThiTN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String time = " Timer : " + timer;
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 21));

        if (timer > 0) {
            g.drawString(time, 10, 100);

        } else {
            g.drawString("Times up ", 10, 100);

            timer = 30;
            try {
                try {
                    cauHoiKeTiep();
                } catch (InterruptedException ex) {
                    Logger.getLogger(formThiTN.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(formThiTN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        timer--;
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            if (timer < 0) {
                current++;
                timer = 30;
            }
        }
    }

    private void cauHoiKeTiep() throws IOException, InterruptedException {
        current++;
        timer = 30;

        if (current > 0) {
            if (jRadioButtonCauA.isSelected()) {
                cauChon.add("A");
                anwClient += dem + "///";
                anwClient += anw + "///";
                System.out.println("A");
            } else if (jRadioButtonCauB.isSelected()) {
                cauChon.add("B");
                System.out.println("B");
                anwClient += dem + "///";
                anwClient += anw + "///";
            } else if (jRadioButtonCauC.isSelected()) {
                cauChon.add("C");
                System.out.println("C");
                anwClient += dem + "///";
                anwClient += anw + "///";
            } else if (jRadioButtonCauD.isSelected()) {
                cauChon.add("D");
                System.out.println("D");
                anwClient += dem + "///";
                anwClient += anw + "///";
            } else {
                cauChon.add("E");
                System.out.println("E");
            }
        }

        if (current < soCau) {
            dem++;
            CauHoi ch = listCauhoi.get(current);
            System.out.println(dem + ": " + ch.getNoiDung());
            jLabelCauHoi.setText(dem + ". " + ch.getNoiDung());
            System.out.println("A. " + ch.getCauA());
            jRadioButtonCauA.setText("A. " + ch.getCauA());
            System.out.println("B. " + ch.getCauB());
            jRadioButtonCauB.setText("B. " + ch.getCauB());
            System.out.println("C. " + ch.getCauC());
            jRadioButtonCauC.setText("C. " + ch.getCauC());
            System.out.println("D. " + ch.getCauD());
            jRadioButtonCauD.setText("D. " + ch.getCauD());

            if (dem == 10) {
                jButtonTiepTheo.setText("NỘP BÀI");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bạn đã hoàn thành bài thi!! Nộp bài!");
            tinhSoCauDung();
            this.setVisible(false);
            formKetQua KQ = new formKetQua();
            KQ.setVisible(true);
        }
    }

    private void tinhSoCauDung() throws IOException, InterruptedException {
        int cau = 0;
        int dem = 0;
        for (int i = 0; i < soCau; i++) {

            dem++;
            String dapan = listCauhoi.get(i).getDapAn();
            String chon = cauChon.get(i);
            System.out.println(dem + " SS Đáp án -- D/A của bạn: " + dapan + "--" + chon);
            strkq = dem + " Đáp án đúng -- D/A của bạn: " + dapan + "--" + chon;
            ketqua.add(strkq);
            if (chon.equals(dapan)) {
                System.out.println("Đúng");
                cau++;
            } else {
                System.out.println("Sai");
            }
        }
        DapAn.diem = cau;
    }

    public void ThiTracNghiem() {
        try {

            socket = new Socket("localhost", 10000);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            CauHoi CH = new CauHoi();
            String strTN = dis.readUTF();
            String[] arrStr = strTN.split("///");
            List<CauHoi> listCH = new ArrayList<CauHoi>();

            int i = 0;
            int dem = 0;
            for (i = 0; i < arrStr.length;) {
                if (dem < 11) {
                    CH = new CauHoi();
                    CH.setCauHoi(Integer.parseInt(arrStr[i]));
                    CH.setTrinhDo(arrStr[i + 1]);
                    CH.setNoiDung(arrStr[i + 2]);
                    CH.setCauA(arrStr[i + 3]);
                    CH.setCauB(arrStr[i + 4]);
                    CH.setCauC(arrStr[i + 5]);
                    CH.setCauD(arrStr[i + 6]);
                    CH.setDapAn(arrStr[i + 7]);
                    listCauhoi.add(CH);
                    i = i + 8;
                }
                dem++;
            }
            dem = 0;
            String anwClient = "";
            soCau = listCauhoi.size();
            dem++;
            while (checkinput) {

                if (anw.equals("A") || anw.equals("B") || anw.equals("C") || anw.equals("D")) {
                    anwClient += dem + "///";
                    anwClient += anw + "///";
                    checkinput = false;
                } else {
                    System.out.println("Nhap sai vui long nhap lai!");
                    JOptionPane.showMessageDialog(null, "VUI Long click vao 1 dap an truoc khi tra loi!");
                }
            }
            dos.writeUTF(anwClient);

            String answer = dis.readUTF();
            System.out.println(answer);

            // Thread.sleep(3000);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(formClient.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroupDapAn = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonCauA = new javax.swing.JRadioButton();
        jRadioButtonCauB = new javax.swing.JRadioButton();
        jRadioButtonCauC = new javax.swing.JRadioButton();
        jRadioButtonCauD = new javax.swing.JRadioButton();
        jLabelCauHoi = new javax.swing.JLabel();
        jButtonTiepTheo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THI TRẮC NGHIỆM");

        buttonGroupDapAn.add(jRadioButtonCauA);
        jRadioButtonCauA.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButtonCauA.setText("A.");
        jRadioButtonCauA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCauAActionPerformed(evt);
            }
        });

        buttonGroupDapAn.add(jRadioButtonCauB);
        jRadioButtonCauB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButtonCauB.setText("B.");
        jRadioButtonCauB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCauBActionPerformed(evt);
            }
        });

        buttonGroupDapAn.add(jRadioButtonCauC);
        jRadioButtonCauC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButtonCauC.setText("C.");
        jRadioButtonCauC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCauCActionPerformed(evt);
            }
        });

        buttonGroupDapAn.add(jRadioButtonCauD);
        jRadioButtonCauD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jRadioButtonCauD.setText("D.");
        jRadioButtonCauD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCauDActionPerformed(evt);
            }
        });

        jLabelCauHoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabelCauHoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelCauHoi.setText("CÂU 1. ");

        jButtonTiepTheo.setText("TIẾP THEO");
        jButtonTiepTheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTiepTheoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButtonCauD, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonCauC, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonCauB, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButtonCauA, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(jButtonTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(405, 405, 405))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jLabelCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonCauA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jRadioButtonCauB, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jRadioButtonCauC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jRadioButtonCauD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonTiepTheo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonCauCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCauCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCauCActionPerformed

    private void jRadioButtonCauAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCauAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCauAActionPerformed

    private void jRadioButtonCauBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCauBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCauBActionPerformed

    private void jRadioButtonCauDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCauDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonCauDActionPerformed

    private void jButtonTiepTheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTiepTheoActionPerformed
        // TODO add your handling code here:
        try {
            cauHoiKeTiep();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonTiepTheoActionPerformed

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
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formThiTN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formThiTN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupDapAn;
    private javax.swing.JButton jButtonTiepTheo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCauHoi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonCauA;
    private javax.swing.JRadioButton jRadioButtonCauB;
    private javax.swing.JRadioButton jRadioButtonCauC;
    private javax.swing.JRadioButton jRadioButtonCauD;
    // End of variables declaration//GEN-END:variables
}
