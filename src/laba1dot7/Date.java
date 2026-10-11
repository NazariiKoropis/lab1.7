package laba1dot7;

import java.util.Scanner;

public class Date {
    private int year;
    private int month;
    private int day;

    private static final int[] DAYS_IN_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Date() {
        this.year = 2000;
        this.month = 1;
        this.day = 1;
    }

    public Date(int year, int month, int day) {
        if (!init(year, month, day)) {
            this.year = 2000;
            this.month = 1;
            this.day = 1;
        }
    }


    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }


    public static boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    public boolean isLeapYear() {
        return isLeapYear(this.year);
    }

    public int getDaysInMonth(int y, int m) {
        if (m == 2 && isLeapYear(y)) return 29;
        return DAYS_IN_MONTH[m];
    }

    public boolean setYear(int y) {
        if (y >= 1) {
            this.year = y;
            return true;
        }
        return false;
    }

    public boolean setMonth(int m) {
        if (m >= 1 && m <= 12) {
            this.month = m;
            return true;
        }
        return false;
    }

    public boolean setDay(int d) {
        if (d >= 1 && d <= getDaysInMonth(this.year, this.month)) {
            this.day = d;
            return true;
        }
        return false;
    }


    public boolean init(int y, int m, int d) {
        if (y >= 1 && m >= 1 && m <= 12) {
            int maxDays = (m == 2 && isLeapYear(y)) ? 29 : DAYS_IN_MONTH[m];
            if (d >= 1 && d <= maxDays) {
                this.year = y;
                this.month = m;
                this.day = d;
                return true;
            }
        }
        return false;
    }


    public boolean init(String dateStr) {
        if (dateStr == null) return false;
        String[] parts = dateStr.trim().split("\\.");
        if (parts.length != 3) return false;
        try {
            int y = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            int d = Integer.parseInt(parts[2]);
            return init(y, m, d);
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public boolean init(Date other) {
        if (other == null) return false;
        return init(other.year, other.month, other.day);
    }

    public void read() {
        Scanner sc = new Scanner(System.in);
        String s;
        do {
            System.out.print("Введіть дату (у форматі РРРР.ММ.ДД, наприклад 2018.05.20): ");
            s = sc.nextLine();
        } while (!init(s));
    }

    @Override
    public String toString() {
        return String.format("%04d.%02d.%02d", year, month, day);
    }

    public void display() {
        System.out.println(toString());
    }

    public int compareTo(Date other) {
        if (this.year != other.year) return Integer.compare(this.year, other.year);
        if (this.month != other.month) return Integer.compare(this.month, other.month);
        return Integer.compare(this.day, other.day);
    }

    public boolean isEqual(Date other) { return compareTo(other) == 0; }
    public boolean isBefore(Date other) { return compareTo(other) < 0; }
    public boolean isAfter(Date other) { return compareTo(other) > 0; }

    private long toAbsoluteDays() {
        long days = this.day;
        for (int y = 1; y < this.year; y++) {
            days += isLeapYear(y) ? 366 : 365;
        }
        for (int m = 1; m < this.month; m++) {
            days += getDaysInMonth(this.year, m);
        }
        return days;
    }

    private static Date fromAbsoluteDays(long totalDays) {
        int y = 1;
        while (true) {
            int daysInY = isLeapYear(y) ? 366 : 365;
            if (totalDays <= daysInY) break;
            totalDays -= daysInY;
            y++;
        }
        int m = 1;
        while (true) {
            int dim = (m == 2 && isLeapYear(y)) ? 29 : DAYS_IN_MONTH[m];
            if (totalDays <= dim) break;
            totalDays -= dim;
            m++;
        }
        return new Date(y, m, (int) totalDays);
    }

    public long daysBetween(Date other) {
        return Math.abs(this.toAbsoluteDays() - other.toAbsoluteDays());
    }


    public Date addDays(int days) {
        return fromAbsoluteDays(this.toAbsoluteDays() + days);
    }

    public Date subtractDays(int days) {
        return fromAbsoluteDays(this.toAbsoluteDays() - days);
    }
}