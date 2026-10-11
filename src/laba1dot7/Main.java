package laba1dot7;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна робота № 1.7 (Варіант 19*) ===");


        System.out.println("\n--- Тест 1: Операції з класом Date ---");
        Date d1 = new Date(2020, 3, 1);
        Date d2 = new Date();
        d2.init("2024.03.01");

        System.out.print("Дата 1: "); d1.display();
        System.out.print("Дата 2: "); d2.display();
        System.out.println("Чи рік 2024 високосний? " + d2.isLeapYear());
        System.out.println("Днів між датами: " + d1.daysBetween(d2));

        Date d3 = d1.addDays(10);
        System.out.print("Дата 1 + 10 днів: "); d3.display();

        System.out.println("\n--- Тест 2: Розрахунок зарплати та стажу (Payment) ---");
        Date hire = new Date(2018, 9, 15);
        Payment p1 = new Payment("Іваненко Петро Олексійович", 25000.0, hire, 20.0, 20, 22);

        p1.display();

        Date currentDate = new Date(2026, 10, 11);
        System.out.println("\nПоточна дата розрахунку: " + currentDate);
        System.out.println("Повний стаж працівника: " + p1.getExperience(currentDate) + " років");

        System.out.println("\n--- Тест 3: Масив працівників ---");
        Payment[] staff = new Payment[] {
            p1,
            new Payment("Сидоренко Анна Василівна", 30000.0, new Date(2022, 1, 10), 10.0, 22, 22),
            new Payment("Ковальчук Ігор Петрович", 18000.0, new Date(2025, 6, 1), 0.0, 15, 22)
        };

        for (int i = 0; i < staff.length; i++) {
            System.out.println("\n[Співробітник #" + (i + 1) + "]");
            staff[i].display();
            System.out.println("Стаж: " + staff[i].getExperience(currentDate) + " р.");
        }
    }
}