package panel;

import component.RoundedPasswordField;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import service.AuthService;
import exception.database.DatabaseException;

public class ChangePasswordPanel extends javax.swing.JPanel {

    private JPanel backgroundPanel;
    private JButton simpanButton;
    private JButton kembaliButton;
    private RoundedPasswordField kataSandiLamaTextField;
    private RoundedPasswordField kataSandiBaruTextField;
    private RoundedPasswordField konfirmasiKataSandiTextField;
    private JLabel titleLabel;
    
    private java.awt.event.ActionListener onBackListener;
    private int currentUserId = -1;
    private AuthService authService = new AuthService();

    public ChangePasswordPanel() {
        initComponents();
    }

    public void setOnBackListener(java.awt.event.ActionListener listener) {
        this.onBackListener = listener;
    }
    
    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    private void initComponents() {
        backgroundPanel = new JPanel();
        simpanButton = new JButton();
        kembaliButton = new JButton();
        kataSandiLamaTextField = new RoundedPasswordField(30);
        kataSandiBaruTextField = new RoundedPasswordField(30);
        konfirmasiKataSandiTextField = new RoundedPasswordField(30);
        titleLabel = new JLabel();

        backgroundPanel.setBackground(new Color(255, 255, 255));
        backgroundPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleLabel.setFont(new java.awt.Font("Arial", 1, 32));
        titleLabel.setForeground(new Color(20, 20, 20));
        titleLabel.setText("Ubah Kata Sandi");
        backgroundPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        setupTextField(kataSandiLamaTextField, "Kata Sandi Lama");
        backgroundPanel.add(kataSandiLamaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 260, 426, 37));

        setupTextField(kataSandiBaruTextField, "Kata Sandi Baru");
        backgroundPanel.add(kataSandiBaruTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 310, 426, 37));

        setupTextField(konfirmasiKataSandiTextField, "Konfirmasi Kata Sandi");
        backgroundPanel.add(konfirmasiKataSandiTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 360, 426, 37));

        simpanButton.setBackground(new Color(167, 14, 45));
        simpanButton.setFont(new java.awt.Font("Arial", 1, 24));
        simpanButton.setForeground(new Color(237, 237, 244));
        simpanButton.setText("Simpan");
        simpanButton.addActionListener(e -> simpanButtonActionPerformed(e));
        backgroundPanel.add(simpanButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 420, 426, 37));

        kembaliButton.setBackground(new Color(164, 164, 164));
        kembaliButton.setFont(new java.awt.Font("Arial", 1, 24));
        kembaliButton.setForeground(new Color(237, 237, 244));
        kembaliButton.setText("Kembali");
        kembaliButton.addActionListener(e -> kembaliButtonActionPerformed(e));
        backgroundPanel.add(kembaliButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 470, 426, 37));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }
    
    private void setupTextField(RoundedPasswordField textField, String placeholder) {
        textField.setBackground(new Color(164, 164, 164));
        textField.setFont(new java.awt.Font("Arial", 0, 20));
        textField.setForeground(new Color(237, 237, 244));
        textField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textField.setText(placeholder);
        textField.setEchoChar((char) 0);
        textField.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 10, 2, 10));
        textField.setMargin(new java.awt.Insets(2, 15, 2, 6));
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (String.valueOf(textField.getPassword()).equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(new Color(20, 20, 20));
                    textField.setEchoChar('\u2022');
                }
            }
            @Override
            public void focusLost(FocusEvent evt) {
                if (String.valueOf(textField.getPassword()).equals("")) {
                    textField.setText(placeholder);
                    textField.setForeground(new Color(237, 237, 244));
                    textField.setEchoChar((char) 0);
                }
            }
        });
    }

    private void kembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (onBackListener != null) {
            onBackListener.actionPerformed(evt);
        }
    }

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentUserId == -1) {
            JOptionPane.showMessageDialog(this, "User ID tidak valid (Belum login).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String oldPass = String.valueOf(kataSandiLamaTextField.getPassword());
        String newPass = String.valueOf(kataSandiBaruTextField.getPassword());
        String confPass = String.valueOf(konfirmasiKataSandiTextField.getPassword());
        
        if (oldPass.isEmpty() || oldPass.equals("Kata Sandi Lama") || 
            newPass.isEmpty() || newPass.equals("Kata Sandi Baru") || 
            confPass.isEmpty() || confPass.equals("Konfirmasi Kata Sandi")) {
            JOptionPane.showMessageDialog(this, "Harap lengkapi semua bidang.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!newPass.equals(confPass)) {
            JOptionPane.showMessageDialog(this, "Konfirmasi kata sandi baru tidak cocok.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            boolean success = authService.changePassword(currentUserId, oldPass, newPass);
            if (success) {
                JOptionPane.showMessageDialog(this, "Kata sandi berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                // Reset fields
                kataSandiLamaTextField.setText("Kata Sandi Lama");
                kataSandiLamaTextField.setForeground(new Color(237, 237, 244));
                kataSandiLamaTextField.setEchoChar((char) 0);
                kataSandiBaruTextField.setText("Kata Sandi Baru");
                kataSandiBaruTextField.setForeground(new Color(237, 237, 244));
                kataSandiBaruTextField.setEchoChar((char) 0);
                konfirmasiKataSandiTextField.setText("Konfirmasi Kata Sandi");
                konfirmasiKataSandiTextField.setForeground(new Color(237, 237, 244));
                konfirmasiKataSandiTextField.setEchoChar((char) 0);
            } else {
                JOptionPane.showMessageDialog(this, "Kata sandi lama salah.", "Gagal", JOptionPane.ERROR_MESSAGE);
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan pada database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
