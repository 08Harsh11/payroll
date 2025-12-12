package payroll.model;

public class FullTimeEmployee extends Employee {
    private final double basicSalary;
    private final double deduction;

    public FullTimeEmployee(String id, String name, double basicSalary, double deduction) {
        super(id, name);
        this.basicSalary = basicSalary;
        this.deduction = deduction;
    }

    @Override
    public double calculatePay() {
        return Math.max(basicSalary - deduction, 0);
    }
}
