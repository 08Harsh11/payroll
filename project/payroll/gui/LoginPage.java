package payroll.gui;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    // Hardcoded login credentials
    private final String correctUsername = "admin";
    private String correctPassword = "1234";

    // Security question answer
    private final String securityAnswer = "blue";   // <-- change answer if you want

    public LoginPage() {
        setTitle("Login - Payroll System");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblUser = new JLabel("Username:");
        JTextField txtUser = new JTextField();

        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        JButton btnForgot = new JButton("Forgot Password?");

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(new JLabel());    // empty space
        panel.add(btnLogin);
        panel.add(new JLabel());    // empty space
        panel.add(btnForgot);

        add(panel);

        // Login button action
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();

            if (user.equals(correctUsername) && pass.equals(correctPassword)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new PayrollMainGUI(); // open main payroll window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password.",
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Forgot password button action
        btnForgot.addActionListener(e -> handleForgotPassword());

        setVisible(true);
    }

    // --------------------------
    // Forgot Password Logic
    // --------------------------
    private void handleForgotPassword() {
        String answer = JOptionPane.showInputDialog(
                this,
                "Security Question:\nWhat is your favourite color?"
        );

        if (answer == null) return; // user pressed cancel

        if (answer.trim().equalsIgnoreCase(securityAnswer)) {
            // Show current password OR allow reset

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Correct! Do you want to reset your password?",
                    "Reset Password",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {
                resetPassword();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Your current password is: " + correctPassword);
            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Incorrect answer!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --------------------------
    // Reset password function
    // --------------------------
    private void resetPassword() {
        JPasswordField newPass = new JPasswordField();
        JPasswordField confirmPass = new JPasswordField();

        Object[] msg = {
                "Enter new password:", newPass,
                "Confirm password:", confirmPass
        };

        int option = JOptionPane.showConfirmDialog(
                this, msg, "Reset Password", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String p1 = new String(newPass.getPassword());
            String p2 = new String(confirmPass.getPassword());

            if (p1.equals(p2) && !p1.isEmpty()) {
                correctPassword = p1;
                JOptionPane.showMessageDialog(this, "Password reset successful!");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
