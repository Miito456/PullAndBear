package PullAndBear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());

        setTitle("Pull And Bear");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel);

        // Título
        JLabel titleLabel = new JLabel("Log-In");
        titleLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 28));
        titleLabel.setBounds(190, 45, 150, 150);
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel);

        JLabel appNameLabel = new JLabel("Pull And Bear");
        appNameLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 32));
        appNameLabel.setBounds(140, 20, 250, 100);
        appNameLabel.setForeground(Color.WHITE); 
        mainPanel.add(appNameLabel);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Arial", Font.BOLD, 9));
        closeButton.setForeground(Color.BLACK);
        closeButton.setBackground(Color.RED);
        closeButton.setOpaque(true);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setBounds(getWidth() - 40, 0, 40, 40);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                System.exit(0); 
            }
        });
        mainPanel.add(closeButton);

        // Logo
        ImageIcon logoIcon = new ImageIcon("logop2.jpg");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(165, 100, 150, 150);
        mainPanel.add(logoLabel);

        // Nombre de usuario
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        usernameLabel.setBounds(60, 270, 150, 30);
        usernameLabel.setForeground(Color.WHITE); 
        mainPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 270, 200, 30);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usernameField.setBackground(Color.WHITE);
        usernameField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        mainPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 16));
        passwordLabel.setBounds(60, 320, 150, 30);
        passwordLabel.setForeground(Color.WHITE);
        mainPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 320, 200, 30);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        passwordField.setBackground(Color.WHITE);
        passwordField.setFont(loadCustomFont("FontPAndBearSemi.otf").deriveFont(Font.PLAIN, 14));
        mainPanel.add(passwordField);

        RoundedButton loginButton = new RoundedButton("Ingresar");
        loginButton.setBounds(300, 370, 100, 40);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("123")) {
                    PullAndBear pullAndBear = new PullAndBear();
                    pullAndBear.setVisible(true);
                    dispose();
                } else if (username.equals("trabajador") && password.equals("123")) {
                    PullAndBearTrabajador pullAndBearTrabajador = new PullAndBearTrabajador();
                    pullAndBearTrabajador.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(loginButton);
    }

    private Font loadCustomFont(String fontFileName) {
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName)).deriveFont(12f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        return customFont;
    }

    class RoundedButton extends JButton {

        public RoundedButton(String text) {
            super(text);
            setPreferredSize(new Dimension(160, 40));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(false);
            setFont(loadCustomFont("FontPAndBearSemi.otf"));

            setVerticalTextPosition(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (getModel().isPressed()) {
                g2d.setColor(Color.decode("#552828"));
            } else {
                g2d.setColor(Color.decode("#265D80"));
            }

            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            g2d.setColor(getForeground());
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            g2d.dispose();

            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
