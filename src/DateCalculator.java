public class DateCalculator {

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    private static final int FIRST_DAY = 1;
    private static final int MONTH_31 = 31;
    private static final int MONTH_30 = 30;
    private static final int MONTH_29 = 29;
    private static final int MONTH_28 = 28;

    private static final int LEAP_YEAR = 4;
    private static final int CENTURY = 100;

    /**
     * add / subtract num to date and modify the initial date to receive a correct date.
     * @param date : initial date
     * @param num number of days that we have to add.
     * @return the calculated date.
     */

    public static Date addToDate(Date date, int num) {
        Date newDate = new Date(date.getDay(), date.getMonth(), date.getYear());
        if (num == 0) {
            return newDate;
        }
        else{
            Date tempDate = new Date(num + date.getDay(), date.getMonth(), date.getYear());
            if(num > 0){
                if(tempDate.getDay() > numberDays(tempDate)){
                    if(changeYear(tempDate, num)){
                        newDate = new Date(FIRST_DAY, JANUARY, tempDate.getYear() + 1);
                    }
                    else{
                        newDate = new Date(FIRST_DAY,tempDate.getMonth() + 1, tempDate.getYear() );
                    }
                    return newDate = addToDate(newDate, tempDate.getDay() - numberDays(tempDate) - 1);
                }
                else{
                    return newDate = addToDate(tempDate,0);
                }
            }
            else{
                if(tempDate.getDay() < FIRST_DAY){
                    if(changeYear(tempDate, num)){
                        newDate = new Date(MONTH_31, DECEMBER, tempDate.getYear() - 1);
                    }
                    else{
                        newDate = new Date(reversePassTo(tempDate), tempDate.getMonth() - 1, tempDate.getYear());

                    }
                    return newDate = addToDate(newDate, tempDate.getDay());
                }
                else{
                    return newDate = addToDate(tempDate, 0);
                }
            }
        }
    }

    /**
     * checks if we have to change the year.
     * @param date the initial date
     * @param num the number of days that we want to add or subtract.
     * @return true if we have to change the year, else return false.
     */

    public static boolean changeYear(Date date, int num){
        return (date.getMonth() == DECEMBER && date.getDay() >= MONTH_31 && num > 0) ||
                (date.getMonth() == JANUARY && date.getDay() < FIRST_DAY && num < 0);
    }

    /**
     * checks if the year is a leap year.
     * @param date the initial date
     * @return true if the year is a leap year, else return false.
     */

    public static boolean checkLeapYear(Date date){
        return (date.getYear() % LEAP_YEAR == 0 && date.getYear() % CENTURY != 0)
                || date.getYear() % (LEAP_YEAR * CENTURY) == 0;
    }

    /**
     * returns number of days of the month of a specific date.
     * @param date specific date.
     * @return number of days of the month of a specific date.
     */

    public static int numberDays(Date date){
        if(date.getMonth() == JANUARY || date.getMonth() == MARCH || date.getMonth() == MAY || date.getMonth() == JULY ||
                date.getMonth() == AUGUST ||date.getMonth() == OCTOBER || date.getMonth() == DECEMBER){
            return MONTH_31;
        }
        else if(date.getMonth() == FEBRUARY){
            if(checkLeapYear(date)){
                return MONTH_29;
            }
            else{
                return MONTH_28;
            }
        }
        else{
            return MONTH_30;
        }
    }

    /**
     * returns the number of month of the precedent month of a specific date.
     * @param date the specific date.
     * @return the number of month of the precedent month of a specific date.
     */

    public static int reversePassTo(Date date){
        if(date.getMonth() == FEBRUARY || date.getMonth() == APRIL || date.getMonth() == JUNE ||
                date.getMonth() == AUGUST || date.getMonth() == SEPTEMBER || date.getMonth() == NOVEMBER){
            return MONTH_31;
        }
        else if(date.getMonth() == MARCH){
            if(checkLeapYear(date)){
                return MONTH_29;
            }
            else{
                return MONTH_28;
            }
        }
        else{
            return MONTH_30;
        }
    }
}
