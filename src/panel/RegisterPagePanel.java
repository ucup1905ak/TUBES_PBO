package panel;

import component.*;
import view.*;
import javax.swing.JComponent;

public class RegisterPagePanel extends javax.swing.JPanel {

    public RegisterPagePanel() {
        initComponents();

    }

    /*
        METHOD
     */
    private java.awt.event.ActionListener onLoginListener;

    public void setOnLoginListener(java.awt.event.ActionListener listener) {
        this.onLoginListener = listener;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        masukButton = new javax.swing.JButton();
        emailtxt = new RoundedTextField(30);
        namaLengkaptxt = new RoundedTextField(30);
        KataSandiTxt = new RoundedTextField(30);
        buatAkunLabel = new javax.swing.JLabel();
        askLabel = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();
        userIcon = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        masukButton.setBackground(new java.awt.Color(167, 14, 45));
        masukButton.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        masukButton.setForeground(new java.awt.Color(237, 237, 244));
        masukButton.setText("Buat Akun");
        backgroundPanel.add(masukButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 501, 426, 37));

        emailtxt.setBackground(new java.awt.Color(164, 164, 164));
        emailtxt.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        emailtxt.setForeground(new java.awt.Color(237, 237, 244));
        emailtxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        emailtxt.setText("Email");
        emailtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        emailtxt.setMargin(new java.awt.Insets(2, 15, 2, 6));
        emailtxt.setPreferredSize(new java.awt.Dimension(426, 37));
        emailtxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailtxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailtxtFocusLost(evt);
            }
        });
        backgroundPanel.add(emailtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 410, 426, 35));

        namaLengkaptxt.setBackground(new java.awt.Color(164, 164, 164));
        namaLengkaptxt.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        namaLengkaptxt.setForeground(new java.awt.Color(237, 237, 244));
        namaLengkaptxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        namaLengkaptxt.setText("Nama Lengkap");
        namaLengkaptxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        namaLengkaptxt.setMargin(new java.awt.Insets(2, 15, 2, 6));
        namaLengkaptxt.setPreferredSize(new java.awt.Dimension(426, 37));
        namaLengkaptxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                namaLengkaptxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                namaLengkaptxtFocusLost(evt);
            }
        });
        namaLengkaptxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaLengkaptxtActionPerformed(evt);
            }
        });
        backgroundPanel.add(namaLengkaptxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 363, 426, 35));

        KataSandiTxt.setBackground(new java.awt.Color(164, 164, 164));
        KataSandiTxt.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        KataSandiTxt.setForeground(new java.awt.Color(237, 237, 244));
        KataSandiTxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        KataSandiTxt.setText("Kata Sandi");
        KataSandiTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        KataSandiTxt.setMargin(new java.awt.Insets(2, 15, 2, 6));
        KataSandiTxt.setPreferredSize(new java.awt.Dimension(426, 37));
        KataSandiTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                KataSandiTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                KataSandiTxtFocusLost(evt);
            }
        });
        backgroundPanel.add(KataSandiTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 457, 426, 37));

        buatAkunLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buatAkunLabel.setForeground(new java.awt.Color(33, 148, 255));
        buatAkunLabel.setText("Masuk");
        buatAkunLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buatAkunLabelMouseClicked(evt);
            }
        });
        backgroundPanel.add(buatAkunLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, -1, 30));

        askLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        askLabel.setForeground(new java.awt.Color(164, 164, 164));
        askLabel.setText("Sudah memiliki akun?");
        backgroundPanel.add(askLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, -1, -1));

        welcomeLabel.setBackground(new java.awt.Color(20, 20, 20));
        welcomeLabel.setFont(new java.awt.Font("Arial", 1, 32)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(20, 20, 20));
        welcomeLabel.setText("Selamat Datang,");
        backgroundPanel.add(welcomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 256, -1, -1));

        userIcon.setBackground(new java.awt.Color(255, 255, 255));
        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LoginPage/avatar_icon.png"))); // NOI18N
        backgroundPanel.add(userIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(608, 187, -1, -1));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        background.setForeground(new java.awt.Color(33, 148, 255));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/LoginPage/Vector.png"))); // NOI18N
        background.setOpaque(true);
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundMouseClicked(evt);
            }
        });
        backgroundPanel.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buatAkunLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buatAkunLabelMouseClicked
        if (onLoginListener != null) {
            onLoginListener.actionPerformed(null);
        }
    }//GEN-LAST:event_buatAkunLabelMouseClicked

    private void namaLengkaptxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaLengkaptxtFocusGained
        if (namaLengkaptxt.getText().equals("Nama Lengkap")) {
            namaLengkaptxt.setText("");
            namaLengkaptxt.setForeground(new java.awt.Color(20, 20, 20));
        }
    }//GEN-LAST:event_namaLengkaptxtFocusGained

    private void namaLengkaptxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaLengkaptxtFocusLost
        if (namaLengkaptxt.getText().equals("")) {
            namaLengkaptxt.setText("Nama Lengkap");
            namaLengkaptxt.setForeground(new java.awt.Color(237, 237, 244));
        }
    }//GEN-LAST:event_namaLengkaptxtFocusLost

    private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseClicked
        backgroundPanel.requestFocusInWindow();
    }//GEN-LAST:event_backgroundMouseClicked

    private void emailtxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailtxtFocusGained
        if (emailtxt.getText().equals("Email")) {
            emailtxt.setText("");
            emailtxt.setForeground(new java.awt.Color(20, 20, 20));
        }
    }//GEN-LAST:event_emailtxtFocusGained

    private void emailtxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailtxtFocusLost
        if (emailtxt.getText().equals("")) {
            emailtxt.setText("Email");
            emailtxt.setForeground(new java.awt.Color(237, 237, 244));
        }
    }//GEN-LAST:event_emailtxtFocusLost

    private void KataSandiTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandiTxtFocusGained
        if (KataSandiTxt.getText().equals("Kata Sandi")) {
            KataSandiTxt.setText("");
            KataSandiTxt.setForeground(new java.awt.Color(20, 20, 20));
        }
    }//GEN-LAST:event_KataSandiTxtFocusGained

    private void KataSandiTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandiTxtFocusLost
        if (KataSandiTxt.getText().equals("")) {
            KataSandiTxt.setText("Kata Sandi");
            KataSandiTxt.setForeground(new java.awt.Color(20, 20, 20));
        }
    }//GEN-LAST:event_KataSandiTxtFocusLost

    private void namaLengkaptxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaLengkaptxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaLengkaptxtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField KataSandiTxt;
    private javax.swing.JLabel askLabel;
    private javax.swing.JLabel background;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel buatAkunLabel;
    private javax.swing.JTextField emailtxt;
    private javax.swing.JButton masukButton;
    private javax.swing.JTextField namaLengkaptxt;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
