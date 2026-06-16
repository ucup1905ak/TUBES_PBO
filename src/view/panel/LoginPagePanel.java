package view.panel;

import control.SessionControl;
import service.AuthService;
import view.component.*;
import model.Session;
import javax.swing.JOptionPane;
import exception.authentication.InvalidLoginCredentialException;
import exception.database.DatabaseException;
import exception.validation.InvalidFormatException;
import java.util.function.Consumer;

public class LoginPagePanel extends javax.swing.JPanel {

    public LoginPagePanel() {
        initComponents();

    }

    /*
        METHOD
     */
    private java.awt.event.ActionListener onRegistListener;
    private Consumer<Integer> onLoginSuccessListener;
//    private AuthService authService = new AuthService();
    private SessionControl sessionControl = new SessionControl();

    public void setOnRegisterListener(java.awt.event.ActionListener listener) {
        this.onRegistListener = listener;
    }

    public void setOnLoginSuccessListener(Consumer<Integer> listener) {
        this.onLoginSuccessListener = listener;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        masukButton = new javax.swing.JButton();
        KataSandiTextField = new RoundedPasswordField(30);
        KataSandiTextField.setEchoChar((char) 0);
        namaPenggunaTextField = new RoundedTextField(30);
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
        masukButton.setText("Masuk");
        masukButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masukButtonActionPerformed(evt);
            }
        });
        backgroundPanel.add(masukButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 460, 426, 37));

        KataSandiTextField.setBackground(new java.awt.Color(164, 164, 164));
        KataSandiTextField.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        KataSandiTextField.setForeground(new java.awt.Color(237, 237, 244));
        KataSandiTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        KataSandiTextField.setText("Kata Sandi");
        KataSandiTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        KataSandiTextField.setMargin(new java.awt.Insets(2, 15, 2, 6));
        KataSandiTextField.setPreferredSize(new java.awt.Dimension(426, 37));
        KataSandiTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                KataSandiTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                KataSandiTextFieldFocusLost(evt);
            }
        });
        backgroundPanel.add(KataSandiTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 410, 426, 37));

        namaPenggunaTextField.setBackground(new java.awt.Color(164, 164, 164));
        namaPenggunaTextField.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        namaPenggunaTextField.setForeground(new java.awt.Color(237, 237, 244));
        namaPenggunaTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        namaPenggunaTextField.setText("Nama Pengguna");
        namaPenggunaTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        namaPenggunaTextField.setMargin(new java.awt.Insets(2, 15, 2, 6));
        namaPenggunaTextField.setPreferredSize(new java.awt.Dimension(426, 37));
        namaPenggunaTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                namaPenggunaTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                namaPenggunaTextFieldFocusLost(evt);
            }
        });
        backgroundPanel.add(namaPenggunaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 363, 426, 37));

        buatAkunLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        buatAkunLabel.setForeground(new java.awt.Color(33, 148, 255));
        buatAkunLabel.setText("Buat Akun");
        buatAkunLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buatAkunLabelMouseClicked(evt);
            }
        });
        backgroundPanel.add(buatAkunLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, -1, 30));

        askLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        askLabel.setForeground(new java.awt.Color(164, 164, 164));
        askLabel.setText("Belum memiliki akun?");
        backgroundPanel.add(askLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, -1, -1));

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
        if (onRegistListener != null) {
            onRegistListener.actionPerformed(null);
        }
    }//GEN-LAST:event_buatAkunLabelMouseClicked


    private void namaPenggunaTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaPenggunaTextFieldFocusGained
        if (namaPenggunaTextField.getText().equals("Nama Pengguna")) {
            namaPenggunaTextField.setText("");
            namaPenggunaTextField.setForeground(new java.awt.Color(20, 20, 20));
        }
    }//GEN-LAST:event_namaPenggunaTextFieldFocusGained

    private void namaPenggunaTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namaPenggunaTextFieldFocusLost
        if (namaPenggunaTextField.getText().equals("")) {
            namaPenggunaTextField.setText("Nama Pengguna");
            namaPenggunaTextField.setForeground(new java.awt.Color(237, 237, 244));
        }
    }//GEN-LAST:event_namaPenggunaTextFieldFocusLost

    private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseClicked
        backgroundPanel.requestFocusInWindow();
    }//GEN-LAST:event_backgroundMouseClicked

    private void KataSandiTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandiTextFieldFocusGained
        if (String.valueOf(KataSandiTextField.getPassword()).equals("Kata Sandi")) {
            KataSandiTextField.setText("");
            KataSandiTextField.setForeground(new java.awt.Color(20, 20, 20));
            KataSandiTextField.setEchoChar('\u2022');
        }
    }//GEN-LAST:event_KataSandiTextFieldFocusGained

    private void KataSandiTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KataSandiTextFieldFocusLost
        if (String.valueOf(KataSandiTextField.getPassword()).equals("")) {
            KataSandiTextField.setText("Kata Sandi");
            KataSandiTextField.setForeground(new java.awt.Color(237, 237, 244));
            KataSandiTextField.setEchoChar((char) 0);
        }
    }//GEN-LAST:event_KataSandiTextFieldFocusLost

    private void masukButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masukButtonActionPerformed
        String usernameOrEmail = namaPenggunaTextField.getText();
        String password = String.valueOf(KataSandiTextField.getPassword());

        if (usernameOrEmail.isEmpty() || usernameOrEmail.equals("Nama Pengguna")
                || password.isEmpty() || password.equals("Kata Sandi")) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi semua bidang.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            sessionControl.login(usernameOrEmail, password);
                onLoginSuccessListener.accept(1);
        } catch (InvalidLoginCredentialException e) {
            JOptionPane.showMessageDialog(this, "Nama pengguna atau kata sandi salah.", "Gagal", JOptionPane.ERROR_MESSAGE);
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan pada database.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidFormatException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan Input Format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_masukButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField KataSandiTextField;
    private javax.swing.JLabel askLabel;
    private javax.swing.JLabel background;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel buatAkunLabel;
    private javax.swing.JButton masukButton;
    private javax.swing.JTextField namaPenggunaTextField;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
