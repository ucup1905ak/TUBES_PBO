/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author aldio
 */
public class ProfilePanel extends JPanel {

    private static final Color RED_TOP = new Color(0xC0392B);
    private static final Color RED_BOTTOM = new Color(0xE74C3C);
    private static final Color ORANGE_BOTTOM = new Color(0xE8603C);
    private static final Color WHITE = Color.WHITE;
    private static final Color YELLOW = new Color(0xF39C12);
    private static final Color DIVIDER = new Color(255, 255, 255, 80);

    // Data
    private String fullName = "Nama Lengkap";
    private String username = "Username";
    private String email = "name@mail.com";
    private String description = "";
    private String github = "";
    private String instagram = "";

    // UI references
    private JLabel nameLabel;
    private JLabel descLabel;
    private JLabel githubLabel;
    private JLabel instagramLabel;
    private JPanel contentPanel;

    // Callback interfaces
    public interface LogoutListener {

        void onLogout();
    }

    public interface CloseListener {

        void onClose();
    }

    private LogoutListener logoutListener;
    private CloseListener closeListener;

    public ProfilePanel() {
        setPreferredSize(new Dimension(466, 720));
        setLayout(new BorderLayout());
        setOpaque(false);
        buildUI();
    }

    public void setLogoutListener(LogoutListener l) {
        this.logoutListener = l;
    }

    public void setCloseListener(CloseListener l) {
        this.closeListener = l;
    }

    // ------------------------------------------------------------------ build
    private void buildUI() {
        // Root gradient panel
        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, RED_TOP,
                        getWidth(), getHeight(), ORANGE_BOTTOM);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        root.setOpaque(false);
        root.setPreferredSize(new Dimension(466, 720));

        JButton closeBtn = new JButton();

        try {
            ImageIcon ikonAsli = new ImageIcon(getClass().getResource("/icon/profile_panel/x.png"));
            Image gambarKecil = ikonAsli.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            closeBtn.setIcon(new ImageIcon(gambarKecil));
        } catch (Exception e) {
            closeBtn.setText("✕");
            closeBtn.setForeground(WHITE);
            closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        }

        // Styling agar tombol tetap transparan dan rapi
        closeBtn.setContentAreaFilled(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        closeBtn.addActionListener(e -> {
            if (closeListener != null) {
                closeListener.onClose();
            } else {
                Window w = SwingUtilities.getWindowAncestor(this);
                if (w != null) {
                    w.dispose();
                }
            }
        });

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        topBar.setOpaque(false);
        topBar.add(closeBtn);
        root.add(topBar, BorderLayout.NORTH);

        // ---- scrollable content ----
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        contentPanel.add(buildProfileHeader());
        contentPanel.add(Box.createVerticalStrut(12));
        contentPanel.add(buildDivider());
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(buildDescriptionRow());
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(Box.createVerticalStrut(60));
        contentPanel.add(buildDivider());
        contentPanel.add(Box.createVerticalStrut(8));
        contentPanel.add(buildSocialMediaSection());

        JScrollPane scroll = new JScrollPane(contentPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        root.add(scroll, BorderLayout.CENTER);

        // ---- logout button (bottom-right) ----
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 10));
        bottomBar.setOpaque(false);
        JButton logoutBtn = makeLogoutButton();
        logoutBtn.addActionListener(e -> {
            if (logoutListener != null) {
                logoutListener.onLogout();
            } else {
                JOptionPane.showMessageDialog(this, "Log out berhasil!");
            }
        });
        bottomBar.add(logoutBtn);
        root.add(bottomBar, BorderLayout.SOUTH);

        add(root, BorderLayout.CENTER);
    }

    // ----------------------------------------------------------------- header
    private JPanel buildProfileHeader() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 6));
        panel.setOpaque(false);

        // Photo placeholder
        JLabel photo = new JLabel("foto profil", SwingConstants.CENTER);
        photo.setPreferredSize(new Dimension(70, 70));
        photo.setFont(new Font("Arial", Font.PLAIN, 10));
        photo.setForeground(new Color(180, 180, 180));
        photo.setBackground(new Color(220, 220, 220));
        photo.setOpaque(true);
        photo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panel.add(photo);

        // Info column
        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        // Full name row
        // Menggunakan FlowLayout dengan gap kiri '4' pixel
        JPanel nameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        nameRow.setOpaque(false);
        nameRow.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- 1. PAKSA PANEL NAMA ALIGNMENT KIRI

        nameLabel = new JLabel(fullName);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(WHITE);

        try {
            nameLabel.setIcon(new ImageIcon(getClass().getResource("/icon/user_icon.png")));
            nameLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
            nameLabel.setVerticalTextPosition(SwingConstants.CENTER);
            nameLabel.setIconTextGap(8);
        } catch (Exception e) {
            System.out.println("Icon Nama Lengkap belum ditemukan.");
        }

        JButton editNameBtn = makeSmallEditButton();
        editNameBtn.addActionListener(e -> startEditName(nameRow, editNameBtn));
        nameRow.add(nameLabel);
        nameRow.add(editNameBtn);
        info.add(nameRow);

        // Username row
        // <-- BARU: Kita bungkus uLabel ke dalam panel ber-FlowLayout yang sama persis dengan nameRow agar otomatis lurus vertikal -->
        JPanel usernameRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        usernameRow.setOpaque(false);
        usernameRow.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- 2. PAKSA PANEL USERNAME ALIGNMENT KIRI

        JLabel uLabel = new JLabel(username);
        uLabel.setFont(new Font("Arial", Font.BOLD, 13));
        uLabel.setForeground(YELLOW);
        // Kosongkan border kirinya agar tidak membuat jaraknya ganda (double gap)
        uLabel.setBorder(new EmptyBorder(2, 0, 2, 0));

        usernameRow.add(uLabel);
        info.add(usernameRow); // <-- Masukkan panel barunya ke panel info

        // Email row
        JPanel emailRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        emailRow.setOpaque(false);
        emailRow.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- 3. PAKSA PANEL EMAIL ALIGNMENT KIRI

        JLabel mailIcon = new JLabel("✉");
        mailIcon.setForeground(WHITE);

        JLabel eLabel = new JLabel(email);
        eLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        eLabel.setForeground(WHITE);
        emailRow.add(mailIcon);
        emailRow.add(eLabel);
        info.add(emailRow);

        panel.add(info);
        return panel;
    }

    // -------------------------------------------------------------- edit name
    private void startEditName(JPanel nameRow, JButton editBtn) {
        nameRow.remove(nameLabel);
        nameRow.remove(editBtn);

        JTextField tf = new JTextField(fullName, 14);
        tf.setFont(new Font("Arial", Font.BOLD, 14));
        tf.setForeground(Color.BLACK);
        tf.setMaximumSize(new Dimension(180, 28));
        nameRow.add(tf);

        JButton okBtn = makeSmallIconButton("simpan");
        okBtn.setForeground(new Color(0x2ECC71));
        nameRow.add(okBtn);

        nameRow.revalidate();
        nameRow.repaint();
        tf.requestFocusInWindow();
        tf.selectAll();

        ActionListener confirm = ev -> {
            String val = tf.getText().trim();
            if (!val.isEmpty()) {
                fullName = val;
            }
            nameLabel.setText(fullName);
            nameRow.remove(tf);
            nameRow.remove(okBtn);
            nameRow.add(nameLabel);
            nameRow.add(editBtn);
            nameRow.revalidate();
            nameRow.repaint();
        };
        okBtn.addActionListener(confirm);
        tf.addActionListener(confirm);
    }

    // ----------------------------------------------------------- description
    private JPanel buildDescriptionRow() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        row.setOpaque(false);

        descLabel = new JLabel(description.isEmpty() ? "Add description..." : description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        descLabel.setForeground(description.isEmpty() ? new Color(255, 255, 255, 160) : WHITE);

        JButton editBtn = makeSmallEditButton();
        editBtn.addActionListener(e -> startEditDescription(wrapper, row, editBtn));

        row.add(descLabel);
        row.add(editBtn);
        wrapper.add(row);
        return wrapper;
    }

    private void startEditDescription(JPanel wrapper, JPanel row, JButton editBtn) {
        row.setVisible(false);

        JTextArea ta = new JTextArea(description, 3, 28);
        ta.setFont(new Font("Arial", Font.PLAIN, 13));
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(WHITE, 1),
                new EmptyBorder(4, 6, 4, 6)));

        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(400, 80)); // Gunakan preferredSize, bukan maximumSize
        sp.getViewport().setOpaque(false);

        JButton okBtn = makeSmallIconButton("✔ Simpan");
        okBtn.setForeground(new Color(0x2ECC71));

        // --- SOLUSI: BUNGKUS DENGAN FLOWLAYOUT KIRI ---
        JPanel editContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        editContainer.setOpaque(false);

        // Sub-panel untuk menyusun Text Area & Tombol secara vertikal
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setOpaque(false);

        sp.setAlignmentX(Component.LEFT_ALIGNMENT);
        okBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        innerPanel.add(sp);
        innerPanel.add(Box.createVerticalStrut(4));
        innerPanel.add(okBtn);

        editContainer.add(innerPanel);

        wrapper.add(editContainer); // Masukkan ke wrapper utama
        wrapper.revalidate();
        wrapper.repaint();
        ta.requestFocusInWindow();

        okBtn.addActionListener(ev -> {
            String val = ta.getText().trim();
            description = val;
            descLabel.setText(description.isEmpty() ? "Add description..." : description);
            descLabel.setForeground(description.isEmpty() ? new Color(255, 255, 255, 160) : WHITE);

            wrapper.remove(editContainer); // Hapus kontainer edit yang baru
            row.setVisible(true);
            wrapper.revalidate();
            wrapper.repaint();
        });

        // Ctrl+Enter confirm
        ta.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), "confirm");
        ta.getActionMap().put("confirm", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                okBtn.doClick();
            }
        });
    }

    // --------------------------------------------------------- social media
    private JPanel buildSocialMediaSection() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT); // Pastikan panel utama rata kiri

        JLabel title = new JLabel("Social Media");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(title);
        panel.add(Box.createVerticalStrut(10));

        // 1. Inisialisasi Label terlebih dahulu agar tidak memicu error
        githubLabel = new JLabel(github.isEmpty() ? "Add GitHub..." : github);
        githubLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        githubLabel.setForeground(github.isEmpty() ? new Color(255, 255, 255, 160) : WHITE);

        instagramLabel = new JLabel(instagram.isEmpty() ? "Add Instagram..." : instagram);
        instagramLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        instagramLabel.setForeground(instagram.isEmpty() ? new Color(255, 255, 255, 160) : WHITE);

        // 2. Panggil baris GitHub
        JPanel ghRow = buildSocialRow("github", githubLabel);
        ghRow.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- Paksa baris GitHub rata kiri
        panel.add(ghRow);
        panel.add(Box.createVerticalStrut(8));

        // 3. Panggil baris Instagram
        JPanel igRow = buildSocialRow("instagram", instagramLabel);
        igRow.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- Paksa baris Instagram rata kiri
        panel.add(igRow);

        return panel;
    }

    private JPanel buildSocialRow(String type, JLabel targetLabel) {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- Paksa wrapper rata kiri

        // Gunakan FlowLayout kiri untuk menyusun Ikon + Teks + Tombol Edit
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- Paksa isi row rata kiri

        JLabel iconLabel = new JLabel(type.equalsIgnoreCase("github") ? "⬡ " : "◎ ");
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        iconLabel.setForeground(WHITE);

        JButton editBtn = makeSmallEditButton();
        editBtn.addActionListener(e -> startEditSocial(wrapper, row, type, targetLabel));

        row.add(iconLabel);
        row.add(targetLabel);
        row.add(editBtn);

        wrapper.add(row);
        return wrapper;
    }

    private void startEditSocial(JPanel wrapper, JPanel row, String type, JLabel targetLabel) {
        row.setVisible(false);

        String currentVal = type.equalsIgnoreCase("github") ? github : instagram;
        JTextField tf = new JTextField(currentVal, 15);
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(WHITE, 1),
                new EmptyBorder(2, 4, 2, 4)));

        JButton okBtn = makeSmallIconButton("✔");
        okBtn.setForeground(new Color(0x2ECC71));

        // Bungkus form edit dengan FlowLayout rata kiri
        JPanel editContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        editContainer.setOpaque(false);
        editContainer.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- Paksa mode edit rata kiri

        editContainer.add(tf);
        editContainer.add(okBtn);

        wrapper.add(editContainer);
        wrapper.revalidate();
        wrapper.repaint();
        tf.requestFocusInWindow();

        ActionListener confirmAction = ev -> {
            String val = tf.getText().trim();
            if (type.equalsIgnoreCase("github")) {
                github = val;
                targetLabel.setText(github.isEmpty() ? "Add GitHub..." : github);
            } else {
                instagram = val;
                targetLabel.setText(instagram.isEmpty() ? "Add Instagram..." : instagram);
            }
            targetLabel.setForeground(val.isEmpty() ? new Color(255, 255, 255, 160) : WHITE);

            wrapper.remove(editContainer);
            row.setVisible(true);
            wrapper.revalidate();
            wrapper.repaint();
        };

        okBtn.addActionListener(confirmAction);
        tf.addActionListener(confirmAction);
    }

    // --------------------------------------------------------------- helpers
    private JPanel buildDivider() {
        JPanel d = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(DIVIDER);
                g.fillRect(0, 0, getWidth(), 1);
            }
        };
        d.setOpaque(false);
        d.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        d.setPreferredSize(new Dimension(426, 1));
        return d;
    }

    private JButton makeIconButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton makeSmallEditButton() {
        JButton btn = new JButton();

        try {

            ImageIcon ikonAsli = new ImageIcon(getClass().getResource("/icon/profile_panel/pencil_edit.png"));

            Image gambarKecil = ikonAsli.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(gambarKecil));
        } catch (Exception e) {
            System.out.println("Icon edit tidak ditemukan.");
            btn.setText("edit");
            btn.setFont(new Font("Arial", Font.PLAIN, 12));
            btn.setForeground(new Color(255, 255, 255, 180));
        }

        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(0, 2, 0, 2));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton makeSmallIconButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        btn.setForeground(WHITE);
        btn.setBackground(new Color(0, 0, 0, 60));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMargin(new Insets(2, 6, 2, 6));

        try {
            ImageIcon ikonAsli = new ImageIcon(getClass().getResource("/icon/profile_panel/diskete.png"));
            Image gambarKecil = ikonAsli.getImage().getScaledInstance(14, 14, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(gambarKecil));
        } catch (Exception e) {
            System.out.println("Ikon simpan tidak ditemukan, menggunakan teks saja.");
        }

        return btn;
    }

    private JButton makeLogoutButton() {
        JButton btn = new JButton("  Log Out") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0xC0392B));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 34));
        // add power icon prefix via label
        btn.setIcon(new ImageIcon(getClass().getResource("/icon/profile_panel/logout.png")));
        return btn;
    }

    // ------------------------------------------------------------------ main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Profile Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            ProfilePanel panel = new ProfilePanel();
            panel.setLogoutListener(()
                    -> JOptionPane.showMessageDialog(null, "Anda telah logout."));
            panel.setCloseListener(() -> frame.dispose());

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
