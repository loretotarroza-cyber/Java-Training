package Task121;

public class DateTask {
    
    private int day;
    private int month;
    private int year;

    
    public DateTask() {
        this(1, 1, 1);
    }

    public DateTask(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    // Step 6: toString() method
    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    
    public int getDay() {
        return day;
    }

    
    public void setDay(int day) {
        if (day >= 1 && day <= 31) {
            this.day = day;
        } else {
            this.day = 0; // if condition false
        }
    }

    
    public int getMonth() {
        return month;
    }

    
    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            this.month = month;
        } else {
            this.month = 0; // if condition false
        }
    }

    
    public int getYear() {
        return year;
    }

    
    public void setYear(int year) {
        if (year > 0) {
            this.year = year;
        } else {
            this.year = 0; // if condition false
        }
    }

    
    public boolean isLeapYear() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}

package Task121;

public class MyDate {
    public static void main(String[] args) {
        // Step 13: Create objects with given dates
        DateTask date1 = new DateTask(1, 1, 1978);
        DateTask date2 = new DateTask(21, 9, 1984);

        // Step 14: Display leap year status and date2
        System.out.println("Date1: " + date1 + " | Leap year? " + date1.isLeapYear());
        System.out.println("Date2: " + date2 + " | Leap year? " + date2.isLeapYear());
    }
}
