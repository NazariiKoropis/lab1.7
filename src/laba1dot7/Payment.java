package laba1dot7;

import java.util.Scanner;

public class Payment {
    private String fullName;      // прізвище, ім'я, по батькові
    private double salary;        // ставка
    private Date hireDate;        // композиція: замість року - об'єкт Date
    private double bonusPercent;  // відсоток надбавки (наприклад 15.0 для 15%)
    private double incomeTaxRate; // прибутковий податок (13%)
    private int workedDays;       // кількість відпрацьованих днів у місяці
    private int totalWorkDays;    // кількість робочих днів у місяці
    private double grossSalary;   // нарахована сума
    private double withheldAmount;// утримана сума

    public Payment() {
        this.fullName = "Невідомо";
        this.salary = 0.0;
        this.hireDate = new Date();
        this.bonusPercent = 0.0;
        this.incomeTaxRate = 13.0;
        this.workedDays = 0;
        this.totalWorkDays = 1;
        calculateSalaries();
    }

    public Payment(String fullName, double salary, Date hireDate, double bonusPercent,
                   int workedDays, int totalWorkDays) {
        this.fullName = fullName;
        this.salary = Math.max(0, salary);
        this.hireDate = (hireDate != null) ? new Date(hireDate.getYear(), hireDate.getMonth(), hireDate.getDay()) : new Date();
        this.bonusPercent = Math.max(0, bonusPercent);
        this.incomeTaxRate = 13.0;
        this.workedDays = Math.max(0, workedDays);
        this.totalWorkDays = Math.max(1, totalWorkDays);
        calculateSalaries();
    }


    public String getFullName() { return fullName; }
    public double getSalary() { return salary; }
    public Date getHireDate() { return hireDate; }
    public double getBonusPercent() { return bonusPercent; }
    public int getWorkedDays() { return workedDays; }
    public int getTotalWorkDays() { return totalWorkDays; }
    public double getGrossSalary() { return grossSalary; }
    public double getWithheldAmount() { return withheldAmount; }


    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
            calculateSalaries();
        }
    }
    public void setHireDate(Date hireDate) {
        if (hireDate != null) {
            this.hireDate.init(hireDate);
        }
    }
    public void setBonusPercent(double bonusPercent) {
        if (bonusPercent >= 0) {
            this.bonusPercent = bonusPercent;
            calculateSalaries();
        }
    }
    public boolean setDays(int worked, int total) {
        if (worked >= 0 && total > 0 && worked <= total) {
            this.workedDays = worked;
            this.totalWorkDays = total;
            calculateSalaries();
            return true;
        }
        return false;
    }

    public int getExperience(Date currentDate) {
        if (currentDate == null || currentDate.isBefore(hireDate)) return 0;
        int exp = currentDate.getYear() - hireDate.getYear();

        if (currentDate.getMonth() < hireDate.getMonth() ||
           (currentDate.getMonth() == hireDate.getMonth() && currentDate.getDay() < hireDate.getDay())) {
            exp--;
        }
        return Math.max(0, exp);
    }

    public double calculateGrossSalary() {
        double baseForDays = (salary / totalWorkDays) * workedDays;
        double bonus = baseForDays * (bonusPercent / 100.0);
        return baseForDays + bonus;
    }

    public double calculateWithheldAmount() {
        double gross = calculateGrossSalary();
        double pensionFund = gross * 0.01; 
        double incomeTax = gross * 0.13;   
        return pensionFund + incomeTax;
    }

    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateWithheldAmount();
    }

    private void calculateSalaries() {
        this.grossSalary = calculateGrossSalary();
        this.withheldAmount = calculateWithheldAmount();
    }

    public boolean init(String fullName, double salary, String hireDateStr, double bonus, int worked, int total) {
        if (salary >= 0 && bonus >= 0 && worked >= 0 && total > 0 && worked <= total) {
            Date d = new Date();
            if (d.init(hireDateStr)) {
                this.fullName = fullName;
                this.salary = salary;
                this.hireDate = d;
                this.bonusPercent = bonus;
                this.workedDays = worked;
                this.totalWorkDays = total;
                calculateSalaries();
                return true;
            }
        }
        return false;
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ПІБ: ");
        this.fullName = sc.nextLine();
        System.out.print("Оклад (ставка): ");
        this.salary = sc.nextDouble();
        System.out.print("Відсоток надбавки (%): ");
        this.bonusPercent = sc.nextDouble();
        System.out.print("Робочих днів у місяці: ");
        this.totalWorkDays = sc.nextInt();
        System.out.print("Відпрацьовано днів: ");
        this.workedDays = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Дата прийому на роботу:");
        this.hireDate.read();
        calculateSalaries();
    }

    @Override
    public String toString() {
        return String.format("Працівник: %s | Дата прийому: %s\n" +
                             "Ставка: %.2f грн | Відпрацьовано: %d/%d дн. | Надбавка: %.1f%%\n" +
                             "Нараховано: %.2f грн | Утримано: %.2f грн | На руки: %.2f грн",
                             fullName, hireDate.toString(), salary, workedDays, totalWorkDays,
                             bonusPercent, grossSalary, withheldAmount, calculateNetSalary());
    }

    public void display() {
        System.out.println(toString());
    }
}