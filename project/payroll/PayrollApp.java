package payroll;

import payroll.gui.LoginPage;

public class PayrollApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(LoginPage::new);
    }
}
