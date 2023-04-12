package be.rubus.courses.payara.micro.jaxrs.validation;

import java.time.DayOfWeek;

public enum WeekDay {

    MONDAY(DayOfWeek.MONDAY), TUESDAY(DayOfWeek.TUESDAY), WEDNESDAY(DayOfWeek.WEDNESDAY), THURSDAY(DayOfWeek.THURSDAY),
    FRIDAY(DayOfWeek.FRIDAY), SATURDAY(DayOfWeek.SATURDAY), SUNDAY(DayOfWeek.SUNDAY);

    private final DayOfWeek dayOfWeek;

    WeekDay(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
}
