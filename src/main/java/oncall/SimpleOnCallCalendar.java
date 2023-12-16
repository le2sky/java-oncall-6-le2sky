package oncall;

import java.time.DayOfWeek;
import oncall.domain.OnCallCalendar;
import oncall.domain.OnCallMonth;

class SimpleOnCallCalendar implements OnCallCalendar {

    @Override
    public boolean isWorkday(OnCallMonth onCallMonth, int day) {
        return !isHoliday(onCallMonth, day);
    }

    @Override
    public boolean isHoliday(OnCallMonth onCallMonth, int day) {
        return isStatutoryHoliday(onCallMonth, day) ||
                onCallMonth.getDayOfWeek(day) == DayOfWeek.SATURDAY ||
                onCallMonth.getDayOfWeek(day) == DayOfWeek.SUNDAY;
    }

    @Override
    public boolean isStatutoryHoliday(OnCallMonth onCallMonth, int day) {
        if (onCallMonth.getMonth() == 1 && day == 1) {
            return true;
        }

        if (onCallMonth.getMonth() == 3 && day == 1) {
            return true;
        }


        if (onCallMonth.getMonth() == 5 && day == 5) {
            return true;
        }

        if (onCallMonth.getMonth() == 6 && day == 6) {
            return true;
        }


        if (onCallMonth.getMonth() == 8 && day == 15) {
            return true;
        }


        if (onCallMonth.getMonth() == 10 && day == 3) {
            return true;
        }


        if (onCallMonth.getMonth() == 10 && day == 9) {
            return true;
        }

        if (onCallMonth.getMonth() == 12 && day == 25) {
            return true;
        }

        return false;
    }
}
