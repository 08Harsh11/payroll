package payroll.model;

import java.util.ArrayList;

public class PayrollManager {
    private final ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee e) { employees.add(e); }

    public ArrayList<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
