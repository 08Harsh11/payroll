package payroll.model;

public class PartTimeEmployee extends Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    public PartTimeEmployee(String id, String name, double hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate;
    }
}
