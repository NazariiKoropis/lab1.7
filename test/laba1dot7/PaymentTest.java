package laba1dot7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testDateOperations() {
        Date d1 = new Date(2024, 2, 28);
        assertTrue(d1.isLeapYear());
        Date nextDay = d1.addDays(1);
        assertEquals("2024.02.29", nextDay.toString());

        Date d2 = new Date(2024, 3, 1);
        assertEquals(2, d1.daysBetween(d2));
    }

    @Test
    public void testExperienceCalculation() {
        Date hire = new Date(2020, 5, 10);
        Payment p = new Payment("Тест", 10000, hire, 0, 20, 20);

        Date current1 = new Date(2024, 5, 9);
        assertEquals(3, p.getExperience(current1)); 

        Date current2 = new Date(2024, 5, 10);
        assertEquals(4, p.getExperience(current2));     }

    @Test
    public void testSalaryCalculations() {

        Payment p = new Payment("Тест", 22000, new Date(2021, 1, 1), 10.0, 22, 22);

        assertEquals(24200.0, p.calculateGrossSalary(), 1e-4);

        assertEquals(3388.0, p.calculateWithheldAmount(), 1e-4);
        

        assertEquals(20812.0, p.calculateNetSalary(), 1e-4);
    }
}