package otc.be.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningTime {
    private DayOfWeek dayOfWeek;
    private LocalTime opening;
    private LocalTime closing;

    public OpeningTime(DayOfWeek dayOfWeek, LocalTime opening, LocalTime closing) {
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
//        this.opening = LocalTime.parse(opening);
//        this.closing = LocalTime.parse(closing);
        this.closing = closing;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpening() {
        return opening;
    }

    public void setOpening(LocalTime opening) {
        this.opening = opening;
    }

    public LocalTime getClosing() {
        return closing;
    }

    public void setClosing(LocalTime closing) {
        this.closing = closing;
    }
}
