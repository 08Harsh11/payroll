package payroll.gui;

import payroll.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PayrollMainGUI extends JFrame {

    private final PayrollManager manager = new PayrollManager();

    public PayrollMainGUI() {
        setTitle("Payroll Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton b1 = new JButton("Add Full-Time Employee");
        JButton b2 = new JButton("Add Part-Time Employee");
        JButton b3 = new JButton("List Employees");
        JButton b4 = new JButton("Show Payroll");
        JButton b5 = new JButton("Logout");

        panel.add(b1); panel.add(b2);
        panel.add(b3); panel.add(b4); panel.add(b5);

        add(panel);

        b1.addActionListener(e -> addFullTime());
        b2.addActionListener(e -> addPartTime());
        b3.addActionListener(e -> showEmployees());
        b4.addActionListener(e -> showPayroll());
        b5.addActionListener(e -> { dispose(); new LoginPage(); });

        setVisible(true);
    }

    private void addFullTime() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField sal = new JTextField();
        JTextField ded = new JTextField();

        Object[] arr = {"ID:", id, "Name:", name, "Basic Salary:", sal, "Deduction:", ded};

        int result = JOptionPane.showConfirmDialog(this, arr, "Add Full-Time Employee",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            FullTimeEmployee emp = new FullTimeEmployee(
                    id.getText(),
                    name.getText(),
                    Double.parseDouble(sal.getText()),
                    Double.parseDouble(ded.getText())
            );
            manager.addEmployee(emp);
            JOptionPane.showMessageDialog(this, "Full-Time Employee Added");
        }
    }

    private void addPartTime() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField hrs = new JTextField();
        JTextField rate = new JTextField();

        Object[] arr = {"ID:", id, "Name:", name, "Hours:", hrs, "Rate:", rate};

        int result = JOptionPane.showConfirmDialog(this, arr, "Add Part-Time Employee",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            PartTimeEmployee emp = new PartTimeEmployee(
                    id.getText(),
                    name.getText(),
                    Double.parseDouble(hrs.getText()),
                    Double.parseDouble(rate.getText())
            );
            manager.addEmployee(emp);
            JOptionPane.showMessageDialog(this, "Part-Time Employee Added");
        }
    }

    private void showEmployees() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Type"}, 0);

        for (Employee e : manager.getEmployees()) {
            model.addRow(new Object[]{e.getId(), e.getName(), e.getClass().getSimpleName()});
        }

        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, pane);
    }

    private void showPayroll() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Net Pay"}, 0);

        double total = 0;
        for (Employee e : manager.getEmployees()) {
            double pay = e.calculatePay();
            model.addRow(new Object[]{e.getId(), e.getName(), pay});
            total += pay;
        }

        JTable table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, pane);
        JOptionPane.showMessageDialog(this, "Total Payroll = " + total);
    }
}
