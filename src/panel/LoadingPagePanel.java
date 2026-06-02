package panel;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import java.awt.event.ActionListener;

public class LoadingPagePanel extends javax.swing.JPanel {

    public LoadingPagePanel() {
        initComponents();
        startLoading();
    }

    /*
        METHOD
     */
    private ActionListener onFinishListener; // dipanggil saat loading selesai

    public void setOnFinishListener(ActionListener listener) {
        this.onFinishListener = listener;
    }

    private void startLoading() {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50);
                    publish(i); 
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int i = chunks.get(chunks.size() - 1); // ambil nilai terbaru
                percentageLable.setText(i + "%");
                loading_progressbar.setValue(i);

                if (i == 0) {
                    loadingLable.setText("Turning on database");
                }
                if (i == 10) {
                    loadingLable.setText("Turning on database...");
                }
                if (i == 20) {
                    loadingLable.setText("Opening database");
                }
                if (i == 30) {
                    loadingLable.setText("Opening database...");
                }
                if (i == 40) {
                    loadingLable.setText("Connection to database");
                }
                if (i == 50) {
                    loadingLable.setText("Connection to database...");
                }
                if (i == 60) {
                    loadingLable.setText("Connection successful");
                }
                if (i == 70) {
                    loadingLable.setText("Connection successful...");
                }
                if (i == 80) {
                    loadingLable.setText("Launching application");
                }
                if (i == 90) {
                    loadingLable.setText("Launching application...");
                }
            }

            @Override
            protected void done() {
                // Selesai loading → panggil listener untuk ganti panel
                if (onFinishListener != null) {
                    onFinishListener.actionPerformed(null);
                }
            }
        };
        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        loading_progressbar = new javax.swing.JProgressBar();
        loadingLable = new javax.swing.JLabel();
        taglineLable = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        percentageLable = new javax.swing.JLabel();
        background_gradient = new javax.swing.JLabel();

        backgroundPanel.setPreferredSize(new java.awt.Dimension(1280, 720));
        backgroundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loading_progressbar.setBackground(new java.awt.Color(237, 237, 244));
        backgroundPanel.add(loading_progressbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 1280, 20));

        loadingLable.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        loadingLable.setForeground(new java.awt.Color(237, 237, 244));
        loadingLable.setText("Loading...");
        backgroundPanel.add(loadingLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, -1, -1));

        taglineLable.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        taglineLable.setForeground(new java.awt.Color(237, 237, 244));
        taglineLable.setText("Udah Macetnya, Yuk Mulai Geraknya.");
        backgroundPanel.add(taglineLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, -1, -1));

        titleLabel.setFont(new java.awt.Font("Arial", 1, 96)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(237, 237, 244));
        titleLabel.setText("MagerNoMore.");
        backgroundPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        percentageLable.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        percentageLable.setForeground(new java.awt.Color(237, 237, 244));
        percentageLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        percentageLable.setText("0%");
        percentageLable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backgroundPanel.add(percentageLable, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 660, -1, -1));

        background_gradient.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/background_page.png"))); // NOI18N
        backgroundPanel.add(background_gradient, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel background_gradient;
    private javax.swing.JLabel loadingLable;
    private javax.swing.JProgressBar loading_progressbar;
    private javax.swing.JLabel percentageLable;
    private javax.swing.JLabel taglineLable;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
